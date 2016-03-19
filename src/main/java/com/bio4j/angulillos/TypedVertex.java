package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;


/*
  ## Typed Vertices

  A typed vertex. A vertex and its type need to be defined at the same time. The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
interface TypedVertex <
  N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
  NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
  extends TypedElement<N,NT,G,I,RV,RVT,RE,RET>
{

  @Override
  RV raw();

  /*
    ### Create relationships in/out of this node

    There are two methods for creating new relationships, into and out of this node respectively. Their implementation delegates to the graph methods.
  */
  default <
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>
  >
  R addInEdge(S from, RT relType) { return graph().addEdge( from, relType, self() ); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  R addOutEdge(RT relType, T to) { return graph().addEdge( self(), relType, to ); }


  /* ### Properties */
  @Override
  default <
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  V get(P property) { return graph().getProperty(self(), property); }


  @Override
  default <
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  N set(P property, V value) {

    graph().setProperty(self(), property, value);
    return self();
  }

  /* ### Getting incoming and outgoing relationships */

  /* #### In-methods */
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>
  >
  Stream<R> inE(RT relType) { return graph().inE( relType, self() ); }


  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>
  >
  Stream<S> inV(RT relType) { return graph().inV( relType, self() ); }


  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromAtMostOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>
  >
  Optional<R> inOptionalE(RT relType) { return inE(relType).findFirst(); }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromAtMostOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>
  >
  Optional<S> inOptionalV(RT relType) { return inV(relType).findFirst(); }


  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>
  >
  R inOneE(RT relType) { return inE(relType).findFirst().get(); }


  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>
  >
  S inOneV(RT relType) { return inV(relType).findFirst().get(); }


  /* #### Out-methods */

  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  Stream<R> outE(RT relType) { return graph().outE( self(), relType ); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  Stream<T> outV(RT relType) { return graph().outV( self(), relType ); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG> &
      TypedEdge.Type.ToAtMostOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  Optional<R> outOptionalE(RT relType) { return outE(relType).findFirst(); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG> &
      TypedEdge.Type.ToAtMostOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  Optional<T> outOptionalV(RT relType) { return outV(relType).findFirst(); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG> &
      TypedEdge.Type.ToOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  R outOneE(RT relType) { return outE(relType).findFirst().get(); }

  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG> &
      TypedEdge.Type.ToOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  T outOneV(RT relType) { return outV(relType).findFirst().get(); }


  /////////////////////////////////////////////////////////////////////////////////////////////////////

  interface Type <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > extends
    TypedElement.Type<N,NT,G,I,RV,RVT,RE,RET>
  {
    @Override
    RVT raw();

    /* Constructs a value of the typed vertex of this type */
    N vertex(RV rawVertex);
  }
}
