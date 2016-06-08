package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;

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
  > extends
    TypedElement.Type<V,VT,G,RV>,
    AnyVertexType
  {

    default V addVertex() {

      return this.fromRaw(
        graph().raw().addVertex( this )
      );
    }

    /* This method returns a stream of **all** `V` vertices. Use with care. */
    default Stream<V> vertices() {

      return graph().raw()
        .vertices(this)
        .map(this::fromRaw)
      ;
    }
  }


  /* ### Properties */
  @Override default
  <X, P extends Property<VT,X> & Arity.ToOne> X get(P property) {
    return graph().raw().<X>getPropertyV(raw(), property);
  }

  @Override default
  <X> Optional<X> getOpt(Property<VT,X> property) {
    return Optional.ofNullable(
      graph().raw().<X>getPropertyV(raw(), property)
    );
  }


  @Override default
  <X> V set(Property<VT,X> property, X value) {

    graph().raw().setPropertyV(this.raw(), property, value);
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
      edgeType
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
      edgeType
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
      edgeType
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
        edgeType
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
      edgeType
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
      edgeType
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
      edgeType
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
        edgeType
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
      edgeType
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
      edgeType
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
      edgeType
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
        edgeType
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
      edgeType
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
      edgeType
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
      edgeType
    ).map(
      edgeType.sourceType()::fromRaw
    );
  }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
    ET extends Arity.FromOne & TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  >
  S inOneV(ET edgeType) {

    return edgeType.sourceType().fromRaw(
      graph().raw().inOneV(
        this.raw(),
        edgeType
      )
    );
  }
}
