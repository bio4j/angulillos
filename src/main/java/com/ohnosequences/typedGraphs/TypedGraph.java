package com.ohnosequences.typedGraphs;

import java.util.Set;

/*
  A `TypedGraph` defines a set of types (nodes, relationships, properties) comprising what you could call a _schema_ for a typed graph.
*/
public interface TypedGraph<G extends TypedGraph<G>> {


  protected RawGraph rawGraph();
  // /*
  // This graph could depend on other graphs; for example, one of its relationships could have as target a node from another graph.  
  // */
  // public Set<? extends TypedGraph> dependencies();

  // /*
  // The package in which this graph is defined. This could be helpful for namespacing when working with it and interacting with a concrete store, for example.
  // */
  // public String pkg();

  // /*
  // The set of node types provided by this graph.
  // */
  // public Set<? extends Node.Type> nodeTypes();
  // /*
  // The set of relationship types provided by this graph.
  // */
  // public Set<? extends Relationship.Type> relationshipTypes();
  // /*
  // The set of property types provided by this graph.
  // */
  // public Set<? extends Property> propertyTypes();
  // // public Set<? extends NodeIndex> indexes();


  // public outFrom <
  //   //rel
  //   R extends Relationship<N,NT,G, R,RT,RG, T,TT,TG>, 
  //   RT extends Relationship.Type<N,NT,G, R,RT,RG, T,TT,TG>,
  //   RG extends G,
  //   // target node
  //   T extends Node<T,TT,TG>,
  //   TT extends Node.Type<T,TT,TG>,
  //   TG extends TypedGraph
  // > 
  // List<R> out(RT relType);



  
}