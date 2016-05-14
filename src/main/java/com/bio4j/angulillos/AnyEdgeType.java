package com.bio4j.angulillos;

/*
  ## Edges

  A typed edge with explicit source and target.

  - `S` the source TypedVertex, `ST` the source TypedVertex type
  - `E` the edge, `ET` the edge type
  - `T` the target TypedVertex, `TT` the target TypedVertex type
*/
public interface AnyEdgeType extends
  AnyElementType,
  HasFromArity,
  HasToArity
{

  AnyVertexType sourceType();
  AnyVertexType targetType();
}

interface TypedEdge <
  // source vertex
  S  extends      TypedVertex<S,ST, ?,RV,RE>,
  ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  // edge
  E  extends      TypedEdge<S,ST, E,ET, T,TT, G,RV,RE>,
  ET extends TypedEdge.Type<S,ST, E,ET, T,TT, G,RV,RE>,
  // target vertex
  T  extends      TypedVertex<T,TT, ?,RV,RE>,
  TT extends TypedVertex.Type<T,TT, ?,RV,RE>,
  // graph & raws
  G extends TypedGraph<G,RV,RE>,
  RV,RE
>
  extends TypedElement<E,ET,G,RE>
{

  interface Type <
    // source vertex
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    // edge
    E  extends      TypedEdge<S,ST, E,ET, T,TT, G,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, T,TT, G,RV,RE>,
    // target vertex
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>,
    // graph & raws
    G extends TypedGraph<G,RV,RE>,
    RV,RE
  > extends
    TypedElement.Type<E,ET,G,RE>,
    AnyEdgeType
  {
    ST sourceType();
    TT targetType();

    /* adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G. */
    default E addEdge(S from, T to) {

      return this.fromRaw(
        graph().raw().addEdge( from.raw(), this, to.raw() )
      );
    }

  }


  /* the source vertex of this edge */
  default S source() {
    return type().sourceType().fromRaw(
      graph().raw().source( raw() )
    );
  }

  /* the target vertex of this edge */
  default T target() {
    return type().targetType().fromRaw(
      graph().raw().target( raw() )
    );
  }

  @Override default
  <X, P extends Property<ET,X> & Arity.ToOne> X get(P property) {
    return graph().raw().<X>getPropertyE(raw(), property);
  }

  @Override default
  <X> java.util.Optional<X> getOpt(Property<ET,X> property) {
    return java.util.Optional.ofNullable(
      graph().raw().<X>getPropertyE(raw(), property)
    );
  }

  @Override default
  <X> E set(Property<ET,X> property, X value) {

    graph().raw().setPropertyE(raw(), property, value);
    return self();
  }

}
