
```java
package com.bio4j.angulillos;

import java.util.function.BiPredicate;
```


## Vertex queries

This two interfaces are the typed version of Blueprints `VertexQuery`. Given a node, we can use this for querying relationships of a given type in or out of that node.


```java
public interface VertexQueryOut <
  // vertex
  N extends TypedVertex<N,NT,G,I,RV,RE>,
  NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  // edge
  R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>,
  RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>,
  // target vertices
  T extends TypedVertex<T,TT,G,I,RV,RE>,
  TT extends TypedVertex.Type<T,TT,G,I,RV,RE>,
  // the query
  Q extends VertexQueryOut<N,NT, R,RT, T,TT, Q, G,I,RV,RE>,
  // graph
  G extends TypedGraph<G,I,RV,RE>,
  I extends UntypedGraph<RV,RE>, RV,RE
>
{

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q has(P property);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q has(P property, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q has(P property, BiPredicate<V,V> predicate, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q hasNot(P property);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q hasNot(P property, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V extends Comparable<?>
  >
  Q interval(P property, V startValue, V endValue);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q limit(int limit);

  R edges();
  T vertices();
}

interface VertexQueryIn <
  // source vertices
  S extends TypedVertex<S,ST,G,I,RV,RE>,
  ST extends TypedVertex.Type<S,ST,G,I,RV,RE>,
  // edge
  R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RE, N,NT,G>,
  RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RE, N,NT,G>,
  // vertex
  N extends TypedVertex<N,NT,G,I,RV,RE>,
  NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  // the query
  Q extends VertexQueryIn<S,ST, R,RT, N,NT, Q, G,I,RV,RE>,
  // graph
  G extends TypedGraph<G,I,RV,RE>,
  I extends UntypedGraph<RV,RE>, RV,RE
>
{

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q has(P property);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q has(P property, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  // FIXME: predicate should be of the type QueryPredicate
  Q has(P property, BiPredicate<V,V> predicate, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q hasNot(P property);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q hasNot(P property, V value);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V extends Comparable<?>
  >
  Q interval(P property, V startValue, V endValue);

  <
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  Q limit(int limit);


  R edges();
  S vertices();
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