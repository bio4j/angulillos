package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;


/*
  ## Typed Vertices

  A typed vertex. A vertex and its type need to be defined at the same time. The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
public interface TypedVertex <
  V  extends      TypedVertex<V,VT, G,RV,RE>,
  VT extends TypedVertex.Type<V,VT, G,RV,RE>,
  G  extends TypedGraph<G,RV,RE>,
  RV,RE
>
  extends TypedElement<V,VT,G,RV>
{

  interface Type <
    V  extends      TypedVertex<V,VT, G,RV,RE>,
    VT extends TypedVertex.Type<V,VT, G,RV,RE>,
    G  extends TypedGraph<G,RV,RE>,
    RV,RE
  > extends TypedElement.Type<V,VT,G,RV> {}


  /*
    ### Create edges in/out of this vertex

    There are two methods for creating new edges, into and out of this vertex respectively. Their implementation delegates to the typed graph methods. Note that all graphs are in principle different.
  */
  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  >
  E addInEdge(S from, ET edgeType) { return graph().addEdge( from, edgeType, self() ); }

  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  E addOutEdge(ET edgeType, T to) { return graph().addEdge( self(), edgeType, to ); }


  /* ### Properties */
  @Override default
  <X> X get(Property<VT,X> property) { return graph().getProperty(self(), property); }


  @Override default
  <X> V set(Property<VT,X> property, X value) {

    graph().setProperty(self(), property, value);
    return self();
  }

  /*
    ### Getting incoming and outgoing edges

    For when you don't know anything about the arity, we have unbounded in/out methods which return `Stream`s
  */

  /* #### outE */
  default <
    E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
  >
  Stream<E> outE(ET edgeType) { return graph().outE(self(), edgeType); }

  default <
    E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
             & Arity.ToAtLeastOne
  >
  Stream<E> outAtLeastOneE(ET edgeType) { return graph().outAtLeastOneE(self(), edgeType); }

  default <
    E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
             & Arity.ToAtMostOne
  >
  Optional<E> outAtMostOneE(ET edgeType) { return graph().outAtMostOneE(self(), edgeType); }

  default <
    E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
             & Arity.ToOne
  >
  E outOneE(ET edgeType) { return graph().outOneE(self(), edgeType); }


  /* #### inE */
  default <
    E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
  >
  Stream<E> inE(ET edgeType) { return graph().inE(self(), edgeType); }

  default <
    E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
             & Arity.FromAtLeastOne
  >
  Stream<E> inAtLeastOneE(ET edgeType) { return graph().inAtLeastOneE(self(), edgeType); }

  default <
    E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
             & Arity.FromAtMostOne
  >
  Optional<E> inAtMostOneE(ET edgeType) { return graph().inAtMostOneE(self(), edgeType); }

  default <
    E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
             & Arity.FromOne
  >
  E inOneE(ET edgeType) { return graph().inOneE(self(), edgeType); }

  /////////////////////////////////////////////////////////////////////////////////////////////////////


  /* #### outV */
  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Stream<T> outV(ET edgeType) { return graph().outV(self(), edgeType); }

  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
             & Arity.ToAtLeastOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Stream<T> outAtLeastOneV(ET edgeType) { return graph().outAtLeastOneV(self(), edgeType); }

  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
             & Arity.ToAtMostOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Optional<T> outAtMostOneV(ET edgeType) { return graph().outAtMostOneV(self(), edgeType); }

  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
             & Arity.ToOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  T outOneV(ET edgeType) { return graph().outOneV(self(), edgeType); }


  /* #### inV */
  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  >
  Stream<S> inV(ET edgeType) { return graph().inV(self(), edgeType); }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
             & Arity.FromAtLeastOne
  >
  Stream<S> inAtLeastOneV(ET edgeType) { return graph().inAtLeastOneV(self(), edgeType); }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
             & Arity.FromAtMostOne
  >
  Optional<S> inAtMostOneV(ET edgeType) { return graph().inAtMostOneV(self(), edgeType); }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
             & Arity.FromOne
  >
  S inOneV(ET edgeType) { return graph().inOneV(self(), edgeType); }

}
