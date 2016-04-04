package com.bio4j.angulillos;

/*
  ## Edges

  A typed edge with explicit source and target.

  - `S` the source TypedVertex, `ST` the source TypedVertex type
  - `E` the edge, `ET` the edge type
  - `T` the target TypedVertex, `TT` the target TypedVertex type
*/
public interface TypedEdge <
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

  /* the source vertex of this edge */
  default S source() { return graph().source( self() ); }

  /* the target vertex of this edge */
  default T target() { return graph().target( self() ); }


  @Override default
  <X> X get(Property<ET,X> property) { return graph().getProperty(self(), property); }

  @Override default
  <X> E set(Property<ET,X> property, X value) {

    graph().setProperty(self(), property, value);
    return self();
  }


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
    HasArity
  {
    ST sourceType();
    TT targetType();
  }

}
