package com.ohnosequences.typedGraphs;

import java.util.List;

/*
  A `TypedGraph` defines a set of types (nodes, relationships, properties) comprising what you could call a _schema_ for a typed graph. It uses a `Technology` for storing them.
*/
public interface TypedGraph <
  G extends TypedGraph<G,I,RV,RE>,
  I extends Technology<RV,RE>, RV, RE
>
{


  // public RawGraph rawGraph();
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

  public <
    N extends Node<N,NT,G,I,RV,RE>,
    NT extends Node.Type<N,NT,G,I,RV,RE>,
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RE>,
    TT extends Node.Type<T,TT,G,I,RV,RE>
  > 
  List<R> outFrom(N node, RT relType);

  public <
    N extends Node<N,NT,G,I,RV,RE>,
    NT extends Node.Type<N,NT,G,I,RV,RE>,
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RE>,
    TT extends Node.Type<T,TT,G,I,RV,RE>
  > 
  List<T> outNodesFrom(N node, RT relType);
 
}