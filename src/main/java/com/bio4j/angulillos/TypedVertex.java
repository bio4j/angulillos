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

  /* #### outE */
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


  /* #### inE */
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

  /////////////////////////////////////////////////////////////////////////////////////////////////////


  /* #### outV */
  default <
    R  extends      TypedEdge<N,NT, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Stream<T> outV(RT relType) { return graph().outV(self(), relType); }

  default <
    R  extends      TypedEdge<N,NT, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.ToAtLeastOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Stream<T> outAtLeastOneV(RT relType) { return graph().outAtLeastOneV(self(), relType); }

  default <
    R  extends      TypedEdge<N,NT, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.ToAtMostOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Optional<T> outAtMostOneV(RT relType) { return graph().outAtMostOneV(self(), relType); }

  default <
    R  extends      TypedEdge<N,NT, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<N,NT, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.ToOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  T outOneV(RT relType) { return graph().outOneV(self(), relType); }


  /* #### inV */
  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, N,NT, ?,RV,RE>
  >
  Stream<S> inV(RT relType) { return graph().inV(self(), relType); }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, N,NT, ?,RV,RE>
             & TypedEdge.Type.FromAtLeastOne
  >
  Stream<S> inAtLeastOneV(RT relType) { return graph().inAtLeastOneV(self(), relType); }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, N,NT, ?,RV,RE>
             & TypedEdge.Type.FromAtMostOne
  >
  Optional<S> inAtMostOneV(RT relType) { return graph().inAtMostOneV(self(), relType); }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, N,NT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, N,NT, ?,RV,RE>
             & TypedEdge.Type.FromOne
  >
  S inOneV(RT relType) { return graph().inOneV(self(), relType); }

}
