package com.ohnosequences.typedGraphs.titan;

import java.util.Set;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.*;
import com.thinkaurelius.titan.core.*;

/*
A `TitanTypedGraph` defines a set of types (nodes, relationships, properties) comprising what you could call a _schema_ for a typed graph.

*/
public abstract class TitanTypedGraph implements TypedGraph {

  public TitanGraph rawGraph;

  protected TitanTypedGraph(TitanGraph rawGraph) { this.rawGraph = rawGraph; }

  /*
  The set of Titan node types provided by this graph.
  */
  public abstract Set<? extends TitanNode.Type> titanNodeTypes();
  /*
  The set of Titan relationship types provided by this graph.
  */
  public abstract Set<? extends TitanRelationship.Type> titanRelationshipTypes();


  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  // defining keys and labels for node and rel types
  
  /*
  Create a TitanKey for indexing a node; you should use this for defining the corresponding `TitanNode.Type`.
  */
  public <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  >
  TitanKey titanKeyForNodeType(P property) {

    // note how here we take the full name so that this is scoped by the node type; see `Property`.
    return rawGraph.makeKey(property.fullName())
      .dataType(property.valueClass())
      .indexed(com.tinkerpop.blueprints.Vertex.class)
      .unique()
      .make();
  }

  /*
  Create a LabelMaker with the minimum default for a relationship type; you should use this for defining the corresponding `TitanRelationship.Type`. This is a `LabelMaker` so that you can define any custom signature, indexing etc.
  */
  public <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>
  >
  LabelMaker titanLabelForRelationshipType(RT relationshipType) {

    LabelMaker labelMaker = rawGraph.makeLabel(relationshipType.name())
      .directed();

    // define the arity
    switch (relationshipType.arity()) {

      case oneToOne:    labelMaker.oneToOne(); 
      case oneToMany:   labelMaker.oneToMany();
      case manyToOne:   labelMaker.manyToOne();
      case manyToMany:  labelMaker.manyToMany();
    }

    return labelMaker;
  }

  public <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  >
  KeyMaker titanKeyForNodeProperty(P property) {

    return rawGraph.makeKey(property.fullName())
      // .indexed(com.tinkerpop.blueprints.Edge.class)
      .dataType(property.valueClass());

  }

  public <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  >
  KeyMaker titanKeyForEdgeProperty(P property) {

    return rawGraph.makeKey(property.fullName())
      // .indexed(com.tinkerpop.blueprints.Edge.class)
      .dataType(property.valueClass());
  }

  public <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  >
  LabelMaker signatureFor(LabelMaker labelMaker, P property) {

    // create the key for it; TODO check if it's already there, if so then use it
    KeyMaker keyMaker = this.titanKeyForEdgeProperty(property);
    keyMaker.unique();
    // could complain if this is already defined
    TitanKey key = keyMaker.make();

    return labelMaker.signature(key);
  }


}