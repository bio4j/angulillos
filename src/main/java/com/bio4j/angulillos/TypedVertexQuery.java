package com.bio4j.angulillos;

import java.util.function.BiPredicate;

/*
  ## Vertex queries

  This two interfaces are the typed version of Blueprints `VertexQuery`. Given a node, we can use this for querying relationships of a given type in or out of that node.

  **IMPORTANT** Ignored in build. Needs update.
*/
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
