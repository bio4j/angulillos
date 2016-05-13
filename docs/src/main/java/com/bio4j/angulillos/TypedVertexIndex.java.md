
```java
package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
```


## Vertex Indices

A vertex index indexes vertices of a given type through values of one of its properties. This just adds a bound on the indexed type to be a TypedVertex; see `TypedElementIndex`


```java
public interface TypedVertexIndex <
  V  extends      TypedVertex<V,VT, ?,?,?>,
  VT extends TypedVertex.Type<V,VT, ?,?,?>,
  P extends Property<VT,X>,
  X
> extends
  TypedElementIndex<V,VT, P,X>
{

  default VT vertexType() { return elementType(); }
```

This interface declares that this index is over a property that uniquely classifies a vertex type for exact match queries; it adds the method `getTypedVertex` for that.

```java
  interface Unique <
    V  extends      TypedVertex<V,VT, ?,?,?>,
    VT extends TypedVertex.Type<V,VT, ?,?,?>,
    P extends Property<VT,X>,
    X
  > extends
    TypedVertexIndex<V,VT, P,X>,
    TypedElementIndex.Unique<V,VT, P,X>
  {
```

get a vertex by providing a value of the indexed property. The default implementation relies on `query`.

```java
    default Optional<V> getVertex(X byValue) { return getElement(byValue); }
  }
```

This interface declares that this index is over a property that classifies lists of vertices for exact match queries; it adds the method `getTypedVertexs` for that.

```java
  interface List <
    V  extends      TypedVertex<V,VT, ?,?,?>,
    VT extends TypedVertex.Type<V,VT, ?,?,?>,
    P extends Property<VT,X>,
    X
  > extends
    TypedVertexIndex<V,VT, P,X>,
    TypedElementIndex.List<V,VT, P,X>
  {
```

get a list of vertices by providing a value of the property. The default

```java
    default Stream<V> getVertices(X byValue) { return getElements(byValue); }
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