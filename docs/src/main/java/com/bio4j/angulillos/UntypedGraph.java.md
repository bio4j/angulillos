
```java
package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
```


## Untyped graph

This interface represents a way of storing and working with the raw data. For example, if you have a graph database X you could perfectly have an implementation of `UntypedGraph` based on X.

With respect to the type parameters, they represent the vertex and edge types used by this particular untyped graph. The four parameters are:

- `RV` for **R**aw **V**ertex, the raw type used for vertices (like `TitanVertex`, `Node` in Neo4j, etc)
- `RVT` for **R**aw **V**ertex **T**ype, the raw type used for vertex types (`String`s, `Label` in Neo4j, etc)
- `RE` for **R**aw **E**dge, the raw type used for edge (like `TitanEdge`, `Relationship` in Neo4j)
- `RET` for **R**aw **E**dge **T**ype, the raw type used for edge types (like `EdgeLabel`, or `Label` in Neo4j)

Properties are represented using `String`s.


```java
public interface UntypedGraph<RV,RVT,RE,RET> {
```


  What the methods are supposed to do is I think pretty obvious from their names; there is anyway a short explanation for each.


  #### methods on vertices


  get from `vertex` the value of `property`


```java
  <V> V getPropertyV(RV vertex, String property);
```


  set the `value` of `property` in `vertex`


```java
  <V> void setPropertyV(RV vertex, String property, V value);
```


  get the edges of type `edgeType` _out_ of `vertex`


```java
  Stream<RE> outE(RV vertex, RET edgeType);
```


  get the _target_ vertices of the edges of type `edgeType` _out_ of `vertex`


```java
  Stream<RV> outV(RV vertex, RET edgeType);
```


  get the edges of type `edgeType` _into_ `vertex`


```java
  Stream<RE> inE(RV vertex, RET edgeType);
```


  get the _source_ vertices of the edges of type `edgeType` _into_ `vertex`


```java
  Stream<RV> inV(RV vertex, RET edgeType);
```


  #### methods on edges


  get from `edge` the value of `property`


```java
  <V> V getPropertyE(RE edge, String property);
```


  set the `value` of `property` in `edge`


```java
  <V> void setPropertyE(RE vertex, String property, V value);
```


  get the source vertex of `edge`


```java
  RV source(RE edge);
```


  get the target vertex of `edge`


```java
  RV target(RE edge);
```


  #### create vertices and edges


  returns a new edge of type `edgeType`, having source `from` and target `to`


```java
  RE addEdge(RV from, RET edgeType, RV to);
```


  returns a new vertex of type `vertexType`


```java
  RV addVertex(RVT vertexType);
```


  These two methods are here at this level just for convenience; they should be moved to `UntypedTransactionalGraph` or something like that.


```java
  void commit();
  void shutdown();
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