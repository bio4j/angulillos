
```java
package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
import java.util.Collection;

public interface TypedElementIndex <
  // element
  F  extends      TypedElement<F,FT, ?,?>,
  FT extends TypedElement.Type<F,FT, ?,?>,
  // property
  P extends Property<FT,X>,
  X
>
{
```

Index name

```java
  String name();

  // /* The graph */
  // G graph();

```

Get the indexed property.

```java
  P property();

  default FT elementType() { return property().elementType(); }
```

Query this index by comparing the property value with the given one

```java
  Stream<F> query(QueryPredicate.Compare predicate, X value);
```

Query this index by checking whether the property value is in/not in the given collection

```java
  Stream<F> query(QueryPredicate.Contain predicate, Collection<X> values);
```

This interface declares that this index is over a property that uniquely classifies a element type for exact match queries

```java
  interface Unique <
    // element
    F  extends      TypedElement<F,FT, ?,?>,
    FT extends TypedElement.Type<F,FT, ?,?>,
    // property
    P extends Property<FT,X>,
    X
  >
    extends TypedElementIndex<F,FT, P,X>
  {
```

Get an element by providing a value of the indexed property

```java
    default Optional<F> getElement(X byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue).findFirst();
    }
  }
```

This interface declares that this index is over a property that classifies lists of elements for exact match queries

```java
  interface List <
    // element
    F  extends      TypedElement<F,FT, ?,?>,
    FT extends TypedElement.Type<F,FT, ?,?>,
    // property
    P extends Property<FT,X>,
    X
  >
    extends TypedElementIndex<F,FT, P,X>
  {
```

Get a list of elements by providing a value of the property

```java
    default Stream<F> getElements(X byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue);
    }
  }
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