package com.ohnosequences.typedGraphs;

import java.util.Set;

/*
  A `TypedGraph` defines a set of types (nodes, relationships, properties) comprising what you could call a _schema_ for a typed graph.
*/
public interface TypedGraph {

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

  
}