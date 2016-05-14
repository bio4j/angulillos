
```java
package com.bio4j.angulillos;

import java.util.Set;
```


## Elements

This is a base interface for both [Vertices](TypedVertex.java.md) and [Edges](TypedEdge.java.md); it only has that which is common to both:

1. a reference to its [type](#Element_types) `ET`
2. methods for [properties](Property.java.md)
3. a reference to the [graph](TypedGraph.java.md) `G` of which they are elements.

#### elements and their types

`E` refers to the element itself, and `ET` its type. You cannot define one without defining the other.


```java
public interface AnyElementType extends HasLabel {

  AnyTypedGraph graph();
  Set<AnyProperty> properties();
}

interface TypedElement <
  F  extends      TypedElement<F,FT, G,RF>,
  FT extends TypedElement.Type<F,FT, G,RF>,
  G    extends TypedGraph<G,?,?>,
  RF
>
{
```


### Element types

Element types are also used as factories for constructing instances of the corresponding elements.


```java
  interface Type <
    F  extends      TypedElement<F,FT, G,RF>,
    FT extends TypedElement.Type<F,FT, G,RF>,
    G    extends TypedGraph<G,?,?>,
    RF
  > extends AnyElementType {

    G graph();
```

Constructs a value of the typed element of this type

```java
    F fromRaw(RF rawElem);
  }
```

The type of this element

```java
  FT type();
```

An abstract reference to the instance of the implementing class. This should return `this` in all cases; it just cannot be implemented at this level.

```java
  F self();
```

`raw` should return a reference to the instance of the corresponding raw type underlying this element. The return type should be `RV + RE` but Java does not have sum types, and it will collapse anyway at the level of Vertex and Edge so `Object` is not that bad here.

```java
  RF raw();
```

The graph in which this element lives.

```java
  default G graph() { return type().graph(); }
```

The `get` method lets you get the value of a `property` which this element has. For that, you pass as an argument the [property](Property.java.md). Note that the type bounds only allow properties of this element, which are declared to be always defined.

```java
  <X, P extends Property<FT,X> & Arity.ToOne> X get(P property);
```

This get method is available for any property of this element, irrespectively of its arity. Note that we assume throughout that properties are single-valued, being implicitly `Arity.ToAtMostOne`

```java
  <X> java.util.Optional<X> getOpt(Property<FT,X> property);
```

`set` sets the value of a `property` for this element. Again, you can only set properties that this element has, using values of the corresponding property value type.

```java
  <X> F set(Property<FT,X> property, X value);

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