package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;


/*
  ## Typed Vertices

  A typed vertex. A vertex and its type need to be defined at the same time. The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
interface TypedVertex <
  N  extends      TypedVertex<N,NT, G,RV,RE>,
  NT extends TypedVertex.Type<N,NT, G,RV,RE>,
  G  extends TypedGraph<G,RV,RE>,
  RV,RE
>
  extends TypedElement<N,NT,G,RV>
{

  interface Type <
    N  extends      TypedVertex<N,NT, G,RV,RE>,
    NT extends TypedVertex.Type<N,NT, G,RV,RE>,
    G  extends TypedGraph<G,RV,RE>,
    RV,RE
  > extends TypedElement.Type<N,NT,G,RV> {}


  /*
    ### Create relationships in/out of this node

    There are two methods for creating new relationships, into and out of this node respectively. Their implementation delegates to the typed graph methods. Note that all graphs are in principle different.
  */
  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, N,NT, ?,RV,RE>
  >
  R addInEdge(S from, RT relType) { return graph().addEdge( from, relType, self() ); }

  default <
    R  extends      TypedEdge<N,NT, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  R addOutEdge(RT relType, T to) { return graph().addEdge( self(), relType, to ); }


  /* ### Properties */
  @Override default
  <V> V get(Property<NT,V> property) { return graph().getProperty(self(), property); }


  @Override default
  <V> N set(Property<NT,V> property, V value) {

    graph().setProperty(self(), property, value);
    return self();
  }

  /*
    ### Getting incoming and outgoing relationships

    For when you don't know anything about the arity, we have unbounded in/out methods which return `Stream`s
  */
  default <
    R  extends      TypedEdge<N,NT, R,RT, ?,?, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, ?,?, ?,RV,RE>
  >
  Stream<R> outE(RT relType) { return graph().outE(self(), relType); }

  default <
    R  extends      TypedEdge<N,NT, R,RT, ?,?, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, ?,?, ?,RV,RE>
             & TypedEdge.Type.ToAtLeastOne
  >
  Stream<R> outAtLeastOneE(RT relType) { return graph().outAtLeastOneE(self(), relType); }

  default <
    R  extends      TypedEdge<N,NT, R,RT, ?,?, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, ?,?, ?,RV,RE>
             & TypedEdge.Type.ToAtMostOne
  >
  Optional<R> outAtMostOneE(RT relType) { return graph().outAtMostOneE(self(), relType); }

  default <
    R  extends      TypedEdge<N,NT, R,RT, ?,?, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, ?,?, ?,RV,RE>
             & TypedEdge.Type.ToOne
  >
  R outOneE(RT relType) { return graph().outOneE(self(), relType); }


  default <
    R  extends      TypedEdge<?,?, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, N,NT, ?,RV,RE>
  >
  Stream<R> inE(RT relType) { return graph().inE(self(), relType); }

  default <
    R  extends      TypedEdge<?,?, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, N,NT, ?,RV,RE>
             & TypedEdge.Type.FromAtLeastOne
  >
  Stream<R> inAtLeastOneE(RT relType) { return graph().inAtLeastOneE(self(), relType); }

  default <
    R  extends      TypedEdge<?,?, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, N,NT, ?,RV,RE>
             & TypedEdge.Type.FromAtMostOne
  >
  Optional<R> inAtMostOneE(RT relType) { return graph().inAtMostOneE(self(), relType); }

  default <
    R  extends      TypedEdge<?,?, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, N,NT, ?,RV,RE>
             & TypedEdge.Type.FromOne
  >
  R inOneE(RT relType) { return graph().inOneE(self(), relType); }

  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RG extends TypedGraph<RG,I,RV,RE>
  // >
  // Stream<R> inE(RT relType) { return graph().inE( relType, self() ); }
  //
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RG extends TypedGraph<RG,I,RV,RE>
  // >
  // Stream<S> inV(RT relType) { return graph().inV( relType, self() ); }
  //
  // /////////////////////////////////////////////////////////////////////////////////////////////////////
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromOne,
  //   RG extends TypedGraph<RG,I,RV,RE>
  // >
  // R inOneE(RT relType) { return graph().inOneE( relType, self() ); }
  //
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromOne,
  //   RG extends TypedGraph<RG,I,RV,RE>
  // >
  // S inOneV(RT relType) { return graph().inOneV( relType, self() ); }
  //
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromAtMostOne,
  //   RG extends TypedGraph<RG,I,RV,RE>
  // >
  // Optional<R> inOptionalE(RT relType) { return graph().inOptionalE( relType, self() ); }
  //
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromAtMostOne,
  //   RG extends TypedGraph<RG,I,RV,RE>
  // >
  // Optional<S> inOptionalV(RT relType) { return graph().inOptionalV( relType, self() ); }
  //
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromAtLeastOne,
  //   RG extends TypedGraph<RG,I,RV,RE>
  // >
  // Stream<R> inManyE(RT relType) { return graph().inManyE( relType, self() ); }
  //
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromAtLeastOne,
  //   RG extends TypedGraph<RG,I,RV,RE>
  // >
  // Stream<S> inManyV(RT relType) { return graph().inManyV( relType, self() ); }
  //

  //
  // default <
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // Stream<T> outV(RT relType) { return graph().outV( self(), relType ); }
  //
  //
  // default <
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToAtLeastOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // Stream<T> outManyV(RT relType) { return graph().outManyV( self(), relType ); }
  //
  //
  // default <
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // T outOneV(RT relType) { return graph().outOneV( self(), relType ); }
  //
  //
  // default <
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToAtMostOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // Optional<T> outOptionalV(RT relType) { return graph().outOptionalV( self(), relType ); }
}
