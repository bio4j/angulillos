
```java
package com.bio4j.angulillos;
```



## Elements

This is a base interface for both [Vertices](TypedVertex.java.md) and [Edges](TypedEdge.java.md); it only has that which is common to both:

1. a reference to its [type](#Element_types) `ET`
2. methods for [properties](Property.java.md)
3. a reference to the [graph](TypedGraph.java.md) `G` of which they are elements.

#### elements and their types

`E` refers to the element itself, and `ET` its type. You cannot define one without defining the other.



```java
public interface TypedElement <
  E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
  ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
{
```


  the type of this element


```java
  ET type();
```


  an abstract reference to the instance of the implementing class. This should return `this` in all cases; it just cannot be implemented at this level.


```java
  E self();
```


  `raw` should return a reference to the instance of the corresponding raw type underlying this element. The return type should be `RV + RE` but Java does not have sum types, and it will collapse anyway at the level of Vertex and Edge so `Object` is not that bad here.


```java
  Object raw();
```


  `graph` returns the graph in which this element lives.


```java
  G graph();
```


  The `get` method lets you get the value of a `property` which this element has. For that, you pass as an argument the [property](Property.java.md). Note that the type bounds only allow properties of this element.


```java
  <
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  V get(P property);
```


  `set` sets the value of a `property` for this element. Again, you can only set properties that this element has, using values of the corresponding property value type.


```java
  <
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, 
    V
  >
  void set(P property, V value);
```



  ### Element types

  Element types are also used as factories for constructing instances of the corresponding elements



```java
  public interface Type <
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
  {
```


    values of an element type act as witnesses for the element having that type; they will all be treated as equal.


```java
    ET value();

    Object raw();

    default String name() { 

      return getClass().getCanonicalName();
    }

    G graph();
  }
}

```


------

### Index

+ src
  + test
    + java
      + com
        + bio4j
          + angulillos
            + [TwitterGraph.java][test/java/com/bio4j/angulillos/TwitterGraph.java]
            + [TwitterGraphTestSuite.java][test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]
    + resources
  + main
    + java
      + com
        + bio4j
          + angulillos
            + [TypedGraph.java][main/java/com/bio4j/angulillos/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/bio4j/angulillos/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/bio4j/angulillos/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/bio4j/angulillos/TypedEdge.java]
            + [conversions.java][main/java/com/bio4j/angulillos/conversions.java]
            + [TypedElementIndex.java][main/java/com/bio4j/angulillos/TypedElementIndex.java]
            + [Property.java][main/java/com/bio4j/angulillos/Property.java]
            + [TypedVertexQuery.java][main/java/com/bio4j/angulillos/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/bio4j/angulillos/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/bio4j/angulillos/TypedEdgeIndex.java]
            + [TypedVertex.java][main/java/com/bio4j/angulillos/TypedVertex.java]

[test/java/com/bio4j/angulillos/TwitterGraph.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraph.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: TypedEdge.java.md
[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: Property.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: TypedElement.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: TypedVertex.java.md