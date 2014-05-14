package com.ohnosequences.typedGraphs;

import java.util.Set;

/*
A module represents essentially the types of a typed graph.

*/
public interface Module {
  
  /*
  This module could depend on other modules; for example, one of its rels could have as target a node from another module.  
  */
  public Set<Module> dependencies();

  /*
  The package in which this module is defined. This could be helpful for namespacing when working with it and interacting with a concrete store, for example.
  */
  public String pkg();

  /*
  The set of node types provided by this module.
  */
  public Set<? extends NodeType> nodeTypes();
  /*
  The set of relationship types provided by this module.
  */
  public Set<? extends RelationshipType> relationshipTypes();
  /*
  The set of property types provided by this module
  */
  public Set<? extends PropertyType> propertyTypes();
  // public Set<? extends NodeIndex> indexes(); 
}