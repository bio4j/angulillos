
```java
package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
import java.util.Collection;

public interface TypedElementIndex <
  // element
  E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
  ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
  // property
  P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
  // graph
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
{
```

Index name

```java
  String name();
```

The graph

```java
  G graph();
```

Get the indexed property.

```java
  P property();

  default ET elementType() { return property().elementType(); }
```

Query this index by comparing the property value with the given one

```java
  Stream<E> query(QueryPredicate.Compare predicate, V value);
```

Query this index by checking whether the property value is in/not in the given collection

```java
  Stream<E> query(QueryPredicate.Contain predicate, Collection<V> values);
```

This interface declares that this index is over a property that uniquely classifies a element type for exact match queries; it adds the method `getTypedElement` for that.

```java
  public interface Unique <
    // element
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    // property
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
    // graph
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  >
    extends TypedElementIndex<E,ET, P,V, G, I,RV,RVT,RE,RET>
  {
```

Get a element by providing a value of the indexed property

```java
    default Optional<E> getElement(V byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue).findFirst();
    }
  }
```

This interface declares that this index is over a property that classifies lists of elements for exact match queries; it adds the method `getTypedElements` for that.

```java
  public interface List <
    // element
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    // property
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
    // graph
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  >
    extends TypedElementIndex<E,ET, P,V, G, I,RV,RVT,RE,RET>
  {
```

Get a list of elements by providing a value of the property

```java
    default Stream<E> getElements(V byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue);
    }
  }
}

```




[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/Property.java]: Property.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: TypedEdge.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: TypedElement.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: TypedVertex.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[test/java/com/bio4j/angulillos/TwitterGraph.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraph.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md