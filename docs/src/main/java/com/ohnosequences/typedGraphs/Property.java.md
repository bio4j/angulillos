
```java
package com.ohnosequences.typedGraphs;
```


Properties.

@author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>


```java
public interface Property <
  // the element type
  N extends TypedElement<N,NT,G,I,RV,RVT,RE,RET>, NT extends TypedElement.Type<N,NT,G,I,RV,RVT,RE,RET>,
  // the property type and its value type
  P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, V,
  // graph stuff
  G extends TypedGraph<G,I,RV,RVT,RE,RET>, I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
{
```

the element type which has this property type

```java
  NT elementType();
```

the class of the property value, so that implementing classes can create values of it.

```java
  Class<V> valueClass();
```

the name of the property.

```java
  default String name() {

    return getClass().getCanonicalName();
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
          + typedGraphs
            + go
              + [TitanGoGraph.java][test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]
    + scala
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [TypedGraph.java][main/java/com/ohnosequences/typedGraphs/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/ohnosequences/typedGraphs/TypedEdge.java]
            + [TypedElementIndex.java][main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/typedGraphs/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]
            + titan
              + [TitanTypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]
            + [TypedVertex.java][main/java/com/ohnosequences/typedGraphs/TypedVertex.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdge.java]: TypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElement.java]: TypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java]: titan/TitanTypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]: titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertex.java]: TypedVertex.java.md