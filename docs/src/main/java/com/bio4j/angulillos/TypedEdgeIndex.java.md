
```java
package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;

public interface TypedEdgeIndex <
  E  extends      TypedEdge<?,?, E,ET, ?,?, ?,?,?>,
  ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,?,?>,
  P extends Property<ET,X>,
  X
> extends
  TypedElementIndex<E,ET, P,X>
{

  default ET edgeType() { return elementType(); }

  interface Unique <
    E  extends      TypedEdge<?,?, E,ET, ?,?, ?,?,?>,
    ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,?,?>,
    P extends Property<ET,X>,
    X
  > extends
    TypedEdgeIndex<E,ET, P,X>,
    TypedElementIndex.Unique<E,ET, P,X>
  {
```

get a node by providing a value of the indexed property.

```java
    default Optional<E> getEdge(X byValue) { return getElement(byValue); }
  }

  interface List <
    E  extends      TypedEdge<?,?, E,ET, ?,?, ?,?,?>,
    ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,?,?>,
    P extends Property<ET,X>,
    X
  > extends
    TypedEdgeIndex<E,ET, P,X>,
    TypedElementIndex.List<E,ET, P,X>
  {
```

get a list of nodes by providing a value of the indexed property.

```java
    default Stream<E> getEdges(X byValue) { return getElements(byValue); }
  }


}

```




[test/java/com/bio4j/angulillos/Twitter.java]: ../../../../../test/java/com/bio4j/angulillos/Twitter.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md
[main/java/com/bio4j/angulillos/Arity.java]: Arity.java.md
[main/java/com/bio4j/angulillos/UntypedGraphSchema.java]: UntypedGraphSchema.java.md
[main/java/com/bio4j/angulillos/AnyElementType.java]: AnyElementType.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/Labeled.java]: Labeled.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/AnyEdgeType.java]: AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: AnyVertexType.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md