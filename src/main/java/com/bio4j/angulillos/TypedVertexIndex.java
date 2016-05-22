package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;

/*
  ## Vertex Indices

  A vertex index indexes vertices of a given type through values of one of its properties. This just adds a bound on the indexed type to be a TypedVertex; see `TypedElementIndex`
*/
public interface TypedVertexIndex <
  V  extends      TypedVertex<V,VT, ?,RV,RE>,
  VT extends TypedVertex.Type<V,VT, ?,RV,RE>,
  P extends Property<VT,X>,
  X,
  RV,RE
> extends
  TypedElementIndex<V,VT, P,X, RV>
{

  default VT vertexType() { return elementType(); }

  default UntypedGraph<RV,RE> untypedGraph() { return property().elementType().graph().raw(); }

  default Stream<V> query(QueryPredicate.Compare predicate, X value) {

    return untypedGraph().<X>queryVertices(predicate, value).map( vertexType()::fromRaw );
  }

  default Stream<V> query(QueryPredicate.Contain predicate, java.util.Collection<X> values) {

    return untypedGraph().<X>queryVertices(predicate, values).map( vertexType()::fromRaw );
  }

  /* This interface declares that this index is over a property that uniquely classifies a vertex type for exact match queries; it adds the method `getTypedVertex` for that.  */
  interface Unique <
    V  extends      TypedVertex<V,VT, ?,RV,RE>,
    VT extends TypedVertex.Type<V,VT, ?,RV,RE>,
    P extends Property<VT,X> & Arity.FromAtMostOne,
    X,
    RV,RE
  > extends
    TypedVertexIndex<V,VT, P,X, RV,RE>,
    TypedElementIndex.Unique<V,VT, P,X, RV>
  {}

  /* This interface declares that this index is over a property that classifies lists of vertices for exact match queries; it adds the method `getTypedVertexs` for that.  */
  interface NonUnique <
    V  extends      TypedVertex<V,VT, ?,RV,RE>,
    VT extends TypedVertex.Type<V,VT, ?,RV,RE>,
    P extends Property<VT,X>,
    X,
    RV,RE
  > extends
    TypedVertexIndex<V,VT, P,X, RV,RE>,
    TypedElementIndex.NonUnique<V,VT, P,X, RV>
  {}
}
