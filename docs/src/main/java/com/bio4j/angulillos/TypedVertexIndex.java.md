
```java
package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
```


## Vertex Indices

A vertex index indexes vertices of a given type through values of one of its properties. This just adds a bound on the indexed type to be a TypedVertex; see `TypedElementIndex`


```java
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

    return untypedGraph().<X>queryVertices(property(), predicate, value).map( vertexType()::fromRaw );
  }

  default Stream<V> query(QueryPredicate.Contain predicate, java.util.Collection<X> values) {

    return untypedGraph().<X>queryVertices(property(), predicate, values).map( vertexType()::fromRaw );
  }
```

This interface declares that this index is over a property that uniquely classifies a vertex type for exact match queries; it adds the method `getTypedVertex` for that.

```java
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
```

This interface declares that this index is over a property that classifies lists of vertices for exact match queries; it adds the method `getTypedVertexs` for that.

```java
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

```




[test/java/com/bio4j/angulillos/Twitter.java]: ../../../../../test/java/com/bio4j/angulillos/Twitter.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: TypedElement.java.md
[main/java/com/bio4j/angulillos/Arity.java]: Arity.java.md
[main/java/com/bio4j/angulillos/UntypedGraphSchema.java]: UntypedGraphSchema.java.md
[main/java/com/bio4j/angulillos/AnyElementType.java]: AnyElementType.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/Labeled.java]: Labeled.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: TypedVertex.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: TypedEdge.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/AnyEdgeType.java]: AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: AnyVertexType.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: Property.java.md