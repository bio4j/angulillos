// package com.ohnosequences.typedGraphs;

// import com.tinkerpop.blueprints.Predicate;

// /*
//   ## node queries

//   This two interfaces are the typed version of Blueprints `VertexQuery`. Given a node, we can use this for querying relationships of a given type in or out of that node.
// */
// interface NodeQueryOut < 
//   N extends Node<N,NT,G>, NT extends Node.Type<N,NT,G>, G extends TypedGraph
//   R extends Relationship<N,NT,G,R,RT,G,T,TT>, RT extends Relationship.Type<N,NT,G,R,RT,G,T,TT>,
//   T extends Node<T,TT>, TT extends Node.Type<T,TT>,
//   Q extends NodeQueryOut<N,NT,G,R,RT,G,T,TT,Q>
// >
// {

//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property);
//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property, V value);
//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property, Predicate predicate, V value);

//   public <P extends Property<R,RT,G,P,V>, V> Q hasNot(P property);
//   public <P extends Property<R,RT,G,P,V>, V> Q hasNot(P property, V value);

//   public <P extends Property<R,RT,G,P,V>, V extends Comparable<?>> Q interval(P property, V startValue, V endValue);
  
//   public <P extends Property<R,RT,G,P,V>, V> Q limit(int limit);

//   public R edges();
//   public T vertices();
// }

// interface NodeQueryIn < 
//   S extends Node<S,ST>, ST extends Node.Type<S,ST>,
//   R extends Relationship<S,ST,R,RT,G,N,NT,G>, RT extends Relationship.Type<S,ST,R,RT,G,N,NT,G>,
//   N extends Node<N,NT,G>, NT extends Node.Type<N,NT,G>,
//   Q extends NodeQueryIn<S,ST,R,RT,G,N,NT,G,Q>
// >
// {

//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property);
//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property, V value);
//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property, Predicate predicate, V value);

//   public <P extends Property<R,RT,G,P,V>, V> Q hasNot(P property);
//   public <P extends Property<R,RT,G,P,V>, V> Q hasNot(P property, V value);

//   public <P extends Property<R,RT,G,P,V>, V extends Comparable<?>> Q interval(P property, V startValue, V endValue);
  
//   public <P extends Property<R,RT,G,P,V>, V> Q limit(int limit);

//   public R edges();
//   public S vertices();
// }

