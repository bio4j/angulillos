package com.ohnosequences.typedGraphs.titan;

import java.util.Set;
import java.util.Iterator;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.*;
import com.thinkaurelius.titan.core.*;

/*
A `TitanTypedGraph` defines a set of types (nodes, relationships, properties) comprising what you could call a _schema_ for a typed graph.

It could probably extend TitanGraph by delegation.
*/
public interface TitanTypedGraph extends TypedGraph {

  public TitanGraph rawGraph();

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public default TitanKey createOrGet(KeyMaker keyMaker, String name) {

    Boolean isNotDefined = true;

    TitanKey key = null;
    // first see if there's such a thing there
    Iterator<TitanKey> definedKeys = rawGraph().getTypes(TitanKey.class).iterator();

    while( definedKeys.hasNext() ) {

      TitanKey someKey = definedKeys.next();

      if ( someKey.getName().equals(name) ) { 
        
        isNotDefined = false;
        key = someKey;
      }
    }

    if( isNotDefined ) {

      key = keyMaker.make();
    }

    return key;
  }

  public default TitanLabel createOrGet(LabelMaker labelMaker, String name) {

    Boolean isNotDefined = true;

    TitanLabel label = null;

    // first see if there's such a thing there
    Iterator<TitanLabel> definedLabels = rawGraph().getTypes(TitanLabel.class).iterator();

    while( definedLabels.hasNext() ) {

      TitanLabel someLabel = definedLabels.next();

      if ( someLabel.getName().equals(name) ) { 
        
        isNotDefined = false;
        label = someLabel;
      }
    }

    if( isNotDefined ) {

      label = labelMaker.make();
    }

    return label;
  }

  /*
    Get a `KeyMaker` already configured for creating the key corresponding to a node type. You can use this for defining the corresponding `TitanNode.Type`.
  */
  public default <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  >
  KeyMaker titanKeyMakerForNodeType(P property) {

    // note how here we take the full name so that this is scoped by the node type; see `Property`.
    return rawGraph().makeKey(property.fullName())
      .dataType(property.valueClass())
      .indexed(com.tinkerpop.blueprints.Vertex.class)
      .unique();
  }

  /*
    create a `TitanKey` for a node type, using the default configuration. If a type with the same name is present it will be returned instead.
  */
  public default <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > TitanKey titanKeyForNodeType(P property) {

    return createOrGet(titanKeyMakerForNodeType(property), property.fullName());
  }

  /*
    Create a LabelMaker with the minimum default for a relationship type; you should use this for defining the corresponding `TitanRelationship.Type`. This is a `LabelMaker` so that you can define any custom signature, indexing etc.
  */
  public default <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>
  >
  LabelMaker titanLabelMakerForRelationshipType(RT relationshipType) {

    LabelMaker labelMaker = rawGraph().makeLabel(relationshipType.name())
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

  /*
    create a `TitanLabel` for a relationship type, using the default configuration. If a type with the same name is present it will be returned instead.
  */
  public default <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>
  >
  TitanLabel titanLabelForRelationshipType(RT relationshipType) {

    return createOrGet(titanLabelMakerForRelationshipType(relationshipType), relationshipType.name());
  }

  public default <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  >
  KeyMaker titanKeyMakerForNodeProperty(P property) {

    return rawGraph().makeKey(property.fullName())
      // .indexed(com.tinkerpop.blueprints.Edge.class)
      .dataType(property.valueClass());

  }

  public default <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  >
  TitanKey titanKeyForNodeProperty(P property) {

    return createOrGet(titanKeyMakerForNodeProperty(property), property.fullName());
  }

  public default <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  >
  KeyMaker titanKeyMakerForEdgeProperty(P property) {

    return rawGraph().makeKey(property.fullName())
      // .indexed(com.tinkerpop.blueprints.Edge.class)
      .dataType(property.valueClass());
  }

  public default <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  >
  TitanKey titanKeyForEdgeProperty(P property) {

    return createOrGet(titanKeyMakerForEdgeProperty(property), property.fullName());
  }

  public default <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  >
  LabelMaker signatureFor(LabelMaker labelMaker, P property) {

    // create the key for it if not already there
    TitanType maybeKey = rawGraph().getType(property.fullName());

    TitanKey key = null;

    if (maybeKey instanceof TitanKey && maybeKey != null) {

      key = (TitanKey) maybeKey;
    } 
    else {

      key = titanKeyMakerForEdgeProperty(property).unique().make();
    }

    return labelMaker.signature(key);
  }


}