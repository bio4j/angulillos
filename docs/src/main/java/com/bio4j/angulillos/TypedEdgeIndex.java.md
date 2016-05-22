
```java
package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;

public interface TypedEdgeIndex <
  E  extends      TypedEdge<?,?, E,ET, ?,?, ?,RV,RE>,
  ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,RV,RE>,
  P extends Property<ET,X>,
  X,
  RV,RE
> extends
  TypedElementIndex<E,ET, P,X, RE>
{

  default ET edgeType() { return elementType(); }

  default UntypedGraph<RV,RE> untypedGraph() { return property().elementType().graph().raw(); }

  default Stream<E> query(QueryPredicate.Compare predicate, X value) {

    return untypedGraph().<X>queryEdges(property(), predicate, value).map( edgeType()::fromRaw );
  }

  default Stream<E> query(QueryPredicate.Contain predicate, java.util.Collection<X> values) {

    return untypedGraph().<X>queryEdges(property(), predicate, values).map( edgeType()::fromRaw );
  }

  interface Unique <
    E  extends      TypedEdge<?,?, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,RV,RE>,
    P extends Property<ET,X> & Arity.FromAtMostOne,
    X,
    RV,RE
  > extends
    TypedEdgeIndex<E,ET, P,X, RV,RE>,
    TypedElementIndex.Unique<E,ET, P,X, RE>
  {}

  interface NonUnique <
    E  extends      TypedEdge<?,?, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,RV,RE>,
    P extends Property<ET,X>,
    X,
    RV,RE
  > extends
    TypedEdgeIndex<E,ET, P,X, RV,RE>,
    TypedElementIndex.NonUnique<E,ET, P,X, RE>
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
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/AnyEdgeType.java]: AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: AnyVertexType.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: Property.java.md