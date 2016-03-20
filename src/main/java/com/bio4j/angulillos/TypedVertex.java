package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;


/*
  ## Typed Vertices

  A typed vertex. A vertex and its type need to be defined at the same time. The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
interface TypedVertex <
  N extends TypedVertex<N,NT,G,I,RV,RE>,
  NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  G extends TypedGraph<G,I,RV,RE>,
  I extends UntypedGraph<RV,RE>, RV,RE
>
  extends TypedElement<N,NT,G,I,RV,RE>
{

  @Override
  RV raw();

  /*
    ### Create relationships in/out of this node

    There are two methods for creating new relationships, into and out of this node respectively. Their implementation delegates to the graph methods.
  */
  default <
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RE>
  >
  R addInEdge(S from, RT relType) { return graph().addEdge( from, relType, self() ); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  R addOutEdge(RT relType, T to) { return graph().addEdge( self(), relType, to ); }


  /* ### Properties */
  @Override
  default <
    P extends Property<N,NT,P,V,G,I,RV,RE>,
    V
  >
  V get(P property) { return graph().getProperty(self(), property); }


  @Override
  default <
    P extends Property<N,NT,P,V,G,I,RV,RE>,
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
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RE>
  >
  Stream<R> inE(RT relType) { return graph().inE( relType, self() ); }


  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RE>
  >
  Stream<S> inV(RT relType) { return graph().inV( relType, self() ); }


  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
      TypedEdge.Type.FromAtMostOne,
    RG extends TypedGraph<RG,I,RV,RE>
  >
  Optional<R> inOptionalE(RT relType) { return inE(relType).findFirst(); }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
      TypedEdge.Type.FromAtMostOne,
    RG extends TypedGraph<RG,I,RV,RE>
  >
  Optional<S> inOptionalV(RT relType) { return inV(relType).findFirst(); }


  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
      TypedEdge.Type.FromOne,
    RG extends TypedGraph<RG,I,RV,RE>
  >
  R inOneE(RT relType) { return inE(relType).findFirst().get(); }


  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
      TypedEdge.Type.FromOne,
    RG extends TypedGraph<RG,I,RV,RE>
  >
  S inOneV(RT relType) { return inV(relType).findFirst().get(); }


  /* #### Out-methods */

  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  Stream<R> outE(RT relType) { return graph().outE( self(), relType ); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  Stream<T> outV(RT relType) { return graph().outV( self(), relType ); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
      TypedEdge.Type.ToAtMostOne,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  Optional<R> outOptionalE(RT relType) { return outE(relType).findFirst(); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
      TypedEdge.Type.ToAtMostOne,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  Optional<T> outOptionalV(RT relType) { return outV(relType).findFirst(); }


  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
      TypedEdge.Type.ToOne,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  R outOneE(RT relType) { return outE(relType).findFirst().get(); }

  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
      TypedEdge.Type.ToOne,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  T outOneV(RT relType) { return outV(relType).findFirst().get(); }


  /////////////////////////////////////////////////////////////////////////////////////////////////////

  interface Type <
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
    G extends TypedGraph<G,I,RV,RE>,
    I extends UntypedGraph<RV,RE>, RV,RE
  > extends
    TypedElement.Type<N,NT,G,I,RV,RE>
  {

    /* Constructs a value of the typed vertex of this type */
    N vertex(RV rawVertex);
  }
}
