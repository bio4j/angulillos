package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;


/*
  ## Typed Vertices

  A typed vertex. A vertex and its type need to be defined at the same time. The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
interface AnyVertexType extends AnyElementType {}

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
  > extends TypedElement.Type<V,VT,G,RV>, AnyVertexType {

    default V addVertex() {

      return this.fromRaw(
        graph().raw().addVertex( this._label() )
      );
    }
  }


  /* ### Properties */
  @Override default
  <X> X get(Property<VT,X> property) {
    return graph().raw().<X>getPropertyV(this.raw(), property._label());
  }


  @Override default
  <X> V set(Property<VT,X> property, X value) {

    graph().raw().setPropertyV(this.raw(), property._label(), value);
    return this.self();
  }

  /*
    ### Getting incoming and outgoing edges

    For when you don't know anything about the arity, we have unbounded in/out methods which return `Stream`s
  */

  /* #### Outgoing edges */
  default <
    E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
  >
  Stream<E> outE(ET edgeType) {

    return graph().raw().outE(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
             & Arity.ToAtLeastOne
  >
  Stream<E> outAtLeastOneE(ET edgeType) {

    return graph().raw().outAtLeastOneE(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
             & Arity.ToAtMostOne
  >
  Optional<E> outAtMostOneE(ET edgeType) {

    return graph().raw().outAtMostOneE(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
             & Arity.ToOne
  >
  E outOneE(ET edgeType) {

    return edgeType.fromRaw(
      graph().raw().outOneE(
        this.raw(),
        edgeType._label()
      )
    );
  }


  /* #### Incoming edges */
  default <
    E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
  >
  Stream<E> inE(ET edgeType) {

    return graph().raw().inE(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
             & Arity.FromAtLeastOne
  >
  Stream<E> inAtLeastOneE(ET edgeType) {

    return graph().raw().inAtLeastOneE(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
             & Arity.FromAtMostOne
  >
  Optional<E> inAtMostOneE(ET edgeType) {

    return graph().raw().inAtMostOneE(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
             & Arity.FromOne
  >
  E inOneE(ET edgeType) {

    return edgeType.fromRaw(
      graph().raw().inOneE(
        this.raw(),
        edgeType._label()
      )
    );
  }

  /////////////////////////////////////////////////////////////////////////////////////////////////////


  /* #### Outgoing vertices */
  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Stream<T> outV(ET edgeType) {

    return graph().raw().outV(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType.targetType()::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
             & Arity.ToAtLeastOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Stream<T> outAtLeastOneV(ET edgeType) {

    return graph().raw().outAtLeastOneV(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType.targetType()::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
             & Arity.ToAtMostOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Optional<T> outAtMostOneV(ET edgeType) {

    return graph().raw().outAtMostOneV(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType.targetType()::fromRaw
    );
  }

  default <
    E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
    ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
             & Arity.ToOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  T outOneV(ET edgeType) {

  return edgeType.targetType().fromRaw(
      graph().raw().outOneV(
        this.raw(),
        edgeType._label()
      )
    );
  }


  /* #### Incoming vertices */
  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  >
  Stream<S> inV(ET edgeType) {

    return graph().raw().inV(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType.sourceType()::fromRaw
    );
  }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
             & Arity.FromAtLeastOne
  >
  Stream<S> inAtLeastOneV(ET edgeType) {

    return graph().raw().inAtLeastOneV(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType.sourceType()::fromRaw
    );
  }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
             & Arity.FromAtMostOne
  >
  Optional<S> inAtMostOneV(ET edgeType) {

    return graph().raw().inAtMostOneV(
      this.raw(),
      edgeType._label()
    ).map(
      edgeType.sourceType()::fromRaw
    );
  }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
             & Arity.FromOne
  >
  S inOneV(ET edgeType) {

    return edgeType.sourceType().fromRaw(
      graph().raw().inOneV(
        this.raw(),
        edgeType._label()
      )
    );
  }

}
