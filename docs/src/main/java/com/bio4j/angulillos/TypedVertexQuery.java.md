
```java
package com.bio4j.angulillos;

import com.tinkerpop.blueprints.Predicate;
```


## node queries

This two interfaces are the typed version of Blueprints `VertexQuery`. Given a node, we can use this for querying relationships of a given type in or out of that node.


```java
interface VertexQueryOut <
  // vertex
  N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
  NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
  // edge
  R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
  RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
  // target vertices
  T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
  TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>,
  // the query
  Q extends VertexQueryOut<N,NT, R,RT, T,TT, Q, G,I,RV,RVT,RE,RET>,
  // graph
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET
>
{

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  > 
  Q has(P property);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q has(P property, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q has(P property, Predicate predicate, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q hasNot(P property);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q hasNot(P property, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V extends Comparable<?>
  >
  Q interval(P property, V startValue, V endValue);
  
  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q limit(int limit);

  R edges();
  T vertices();
}

interface VertexQueryIn <
  // source vertices
  S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
  ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
  // edge
  R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
  RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
  // vertex
  N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
  NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
  // the query
  Q extends VertexQueryIn<S,ST, R,RT, N,NT, Q, G,I,RV,RVT,RE,RET>,
  // graph
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT,RE,RET
>
{

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  > 
  Q has(P property);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q has(P property, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q has(P property, Predicate predicate, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q hasNot(P property);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q hasNot(P property, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V extends Comparable<?>
  >
  Q interval(P property, V startValue, V endValue);
  
  <
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>,
    V
  >
  Q limit(int limit);


  R edges();
  S vertices();
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
            + go
              + [TitanGoGraph.java][test/java/com/bio4j/angulillos/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/bio4j/angulillos/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/bio4j/angulillos/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/bio4j/angulillos/go/TestTypeNames.java]
    + scala
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

[test/java/com/bio4j/angulillos/go/TitanGoGraph.java]: ../../../../../test/java/com/bio4j/angulillos/go/TitanGoGraph.java.md
[test/java/com/bio4j/angulillos/go/GoGraph.java]: ../../../../../test/java/com/bio4j/angulillos/go/GoGraph.java.md
[test/java/com/bio4j/angulillos/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/bio4j/angulillos/go/TitanGoGraphImpl.java.md
[test/java/com/bio4j/angulillos/go/TestTypeNames.java]: ../../../../../test/java/com/bio4j/angulillos/go/TestTypeNames.java.md
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