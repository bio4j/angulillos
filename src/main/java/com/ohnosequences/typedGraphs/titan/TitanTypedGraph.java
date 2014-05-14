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
  public abstract Set<? extends TitanNodeType> titanNodeTypes();
  /*
  The set of Titan relationship types provided by this graph.
  */
  public abstract Set<? extends TitanRelationshipType> titanRelationshipTypes();


  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  // defining keys and labels for node and rel types
  
  /*
  Create a TitanKey for indexing a node; you should use this for defining the corresponding `TitanNodeType`.
  */
  public <
    N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
    P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
    V
  >
  TitanKey titanKeyForNodeType(PT typeClassifier) {

    // note how here we take the full name so that this is scoped by the node type; see `PropertyType`.
    return rawGraph.makeKey(typeClassifier.fullName())
      .dataType(typeClassifier.valueClass())
      .indexed(com.tinkerpop.blueprints.Vertex.class)
      .unique()
      .make();
  }

  /*
  Create a LabelMaker with the minimum default for a relationship type; you should use this for defining the corresponding `TitanRelationshipType`. This is a `LabelMaker` so that you can define any custom signature, indexing etc.
  */
  public <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  >
  LabelMaker titanLabelForRelationshipType(RT typeClassifier) {

    LabelMaker labelMaker = rawGraph.makeLabel(typeClassifier.name())
      .directed();

    // define the arity
    switch (typeClassifier.arity()) {

      case oneToOne:    labelMaker.oneToOne(); 
      case oneToMany:   labelMaker.oneToMany();
      case manyToOne:   labelMaker.manyToOne();
      case manyToMany:  labelMaker.manyToMany();
    }

    return labelMaker;
  }

  public <
    N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
    P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
    V
  >
  KeyMaker titanKeyForNodeProperty(PT propertyType) {

    return rawGraph.makeKey(propertyType.fullName())
      // .indexed(com.tinkerpop.blueprints.Edge.class)
      .dataType(propertyType.valueClass());

  }

  public <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT,P,PT,V>,
    V
  >
  KeyMaker titanKeyForEdgeProperty(PT propertyType) {

    return rawGraph.makeKey(propertyType.fullName())
      // .indexed(com.tinkerpop.blueprints.Edge.class)
      .dataType(propertyType.valueClass());
  }

  public <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT,P,PT,V>,
    V
  >
  LabelMaker signatureFor(LabelMaker labelMaker, PT propertyType) {

    // create the key for it; TODO check if it's already there, if so then use it
    KeyMaker keyMaker = this.titanKeyForEdgeProperty(propertyType);
    keyMaker.unique();
    // could complain if this is already defined
    TitanKey key = keyMaker.make();

    return labelMaker.signature(key);
  }


}