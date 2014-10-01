
```java
package com.ohnosequences.angulillos;
```

A typed TypedElement. Base class for both `Node`s and `Relationship`s; essentially they only have

  1. their type
  2. and properties
  3. a reference to the typed graph of which they are elements.

  `E` refers to the element itself, and `ET` its type. You cannot define one without defining the other.

  `G` refers to the graph in which this element lives.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>


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

  E self();

  Object raw();
```


the graph in which this element lives


```java
  G graph();
```

This method let's you get the value of a property which this element has. For that, you pass as an argument the property _type_. The type bounds only allow properties of this `TypedElement`

```java
  <
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  V get(P p);

  <
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  void set(P p, V value);
```

The type of an TypedElement.

    @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>


```java
  public interface Type <
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
  {
```

values of an TypedElement Type act as witnesses for that type; they will all be treated as equal.

```java
    ET value();

    Object raw();

    default String name() { 

      return getClass().getCanonicalName();
    }

    // TODO is this actually needed??
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
        + ohnosequences
          + angulillos
            + go
              + [TitanGoGraph.java][test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/ohnosequences/angulillos/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/ohnosequences/angulillos/go/TestTypeNames.java]
    + scala
  + main
    + java
      + com
        + ohnosequences
          + angulillos
            + [TypedGraph.java][main/java/com/ohnosequences/angulillos/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/ohnosequences/angulillos/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/ohnosequences/angulillos/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/ohnosequences/angulillos/TypedEdge.java]
            + [TypedElementIndex.java][main/java/com/ohnosequences/angulillos/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/angulillos/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/angulillos/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/angulillos/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/angulillos/TypedEdgeIndex.java]
            + titan
              + [TitanTypedEdgeIndex.java][main/java/com/ohnosequences/angulillos/titan/TitanTypedEdgeIndex.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/angulillos/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/angulillos/titan/TitanUntypedGraph.java]
            + [TypedVertex.java][main/java/com/ohnosequences/angulillos/TypedVertex.java]

[test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java]: ../../../../../test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/angulillos/go/GoGraph.java]: ../../../../../test/java/com/ohnosequences/angulillos/go/GoGraph.java.md
[test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/angulillos/go/TestTypeNames.java]: ../../../../../test/java/com/ohnosequences/angulillos/go/TestTypeNames.java.md
[main/java/com/ohnosequences/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/ohnosequences/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedEdge.java]: TypedEdge.java.md
[main/java/com/ohnosequences/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/ohnosequences/angulillos/Property.java]: Property.java.md
[main/java/com/ohnosequences/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/ohnosequences/angulillos/TypedElement.java]: TypedElement.java.md
[main/java/com/ohnosequences/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanTypedEdgeIndex.java]: titan/TitanTypedEdgeIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanTypedVertexIndex.java]: titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanUntypedGraph.java]: titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedVertex.java]: TypedVertex.java.md