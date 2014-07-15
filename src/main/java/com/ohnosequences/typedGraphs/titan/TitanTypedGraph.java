package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.TypedGraph;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.*;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.*;

public interface TitanTypedGraph <
  G extends TitanTypedGraph<G>
> 
extends
  TypedGraph<G, TitanUntypedGraph, TitanVertex,TitanKey,TitanEdge,TitanLabel>
{

  @Override
  TitanUntypedGraph raw();

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  /*
    creates a key in the graph using the provided `KeyMaker` and `name` if there is no such `TitanKey` with that `name`; otherwise it returns the existing `TitanKey` with the provided `name`.
  */
  public default TitanKey createOrGet(KeyMaker keyMaker, String name) {

    Boolean isNotDefined = true;

    TitanKey key = null;
    // first see if there's such a thing there
    Iterator<TitanKey> definedKeys = raw().titanGraph().getTypes(TitanKey.class).iterator();

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

  /*
    creates a label in the graph using the provided `LabelMaker` and `name` if there is no such `TitanLabel` with that `name`; otherwise it returns the existing `TitanLabel` with the provided `name`.
  */
  public default TitanLabel createOrGet(LabelMaker labelMaker, String name) {

    Boolean isNotDefined = true;

    TitanLabel label = null;

    // first see if there's such a thing there
    Iterator<TitanLabel> definedLabels = raw().titanGraph().getTypes(TitanLabel.class).iterator();

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
    Get a `KeyMaker` already configured for creating the key corresponding to a node type. You can use this for defining the corresponding `TitanTitanTypedVertex.Type`.
  */
  default <
    N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
    P extends TitanProperty<N,NT,G,P,V>, V
  >
  KeyMaker titanKeyMakerForTitanTypedVertexType(P property) {

    // note how here we take the full name so that this is scoped by the node type; see `Property`.
    KeyMaker keyMaker = raw().titanGraph().makeKey(property.name())
      .dataType(property.valueClass())
      .indexed(com.tinkerpop.blueprints.Vertex.class)
      .unique();

    return keyMaker;
  }

  /*
    create a `TitanKey` for a node type, using the default configuration. If a type with the same name is present it will be returned instead.
  */
  default <
    N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
    P extends TitanProperty<N,NT,G,P,V>, V
  > 
  TitanKey titanKeyForTitanTypedVertexType(P property) {

    return createOrGet(titanKeyMakerForTitanTypedVertexType(property), property.name());
  }

  default <
    N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
    P extends TitanProperty<N,NT,G,P,String>
  > 
  TitanKey titanKeyForTitanTypedVertexTypeWithStringId(P property) {

    KeyMaker keyMaker = titanKeyMakerForTitanTypedVertexType(property);

    keyMaker.indexed("search", com.tinkerpop.blueprints.Vertex.class, Parameter.of(Mapping.MAPPING_PREFIX, Mapping.STRING));

    return createOrGet(keyMaker, property.name());
  }

  
  /*Create a LabelMaker with the minimum default for a relationship type; you should use this for defining the corresponding `TitanTitanTypedEdge.Type`. This is a `LabelMaker` so that you can define any custom signature, indexing etc.*/
  default <
    S extends TitanTypedVertex<S,ST,SG>, ST extends TitanTypedVertex.Type<S,ST,SG>, SG extends TitanTypedGraph<SG>,
    R extends TitanTypedEdge<S,ST,SG,R,RT,G,T,TT,TG>, RT extends TitanTypedEdge.Type<S,ST,SG,R,RT,G,T,TT,TG>,
    T extends TitanTypedVertex<T,TT,TG>, TT extends TitanTypedVertex.Type<T,TT,TG>, TG extends TitanTypedGraph<TG>
  >
  LabelMaker titanLabelMakerForTitanTypedEdgeType(RT relationshipType) {

    LabelMaker labelMaker = raw().titanGraph().makeLabel(relationshipType.name())
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
  default <
    S extends TitanTypedVertex<S,ST,SG>, ST extends TitanTypedVertex.Type<S,ST,SG>, SG extends TitanTypedGraph<SG>,
    R extends TitanTypedEdge<S,ST,SG,R,RT,G,T,TT,TG>, RT extends TitanTypedEdge.Type<S,ST,SG,R,RT,G,T,TT,TG>,
    T extends TitanTypedVertex<T,TT,TG>, TT extends TitanTypedVertex.Type<T,TT,TG>, TG extends TitanTypedGraph<TG>
  >
  TitanLabel titanLabelForTitanTypedEdgeType(RT relationshipType) {

    return createOrGet(titanLabelMakerForTitanTypedEdgeType(relationshipType), relationshipType.name());
  }

//   public default <
//     N extends TitanTypedVertex<N,NT,TG>, NT extends TitanTypedVertex.Type<N,NT,TG>,
//     P extends TitanProperty<N,NT,TG,P,V>, V
//   >
//   KeyMaker titanKeyMakerForTitanTypedVertexProperty(P property) {

//     return raw().makeKey(property.name())
//       // .indexed(com.tinkerpop.blueprints.Edge.class)
//       .dataType(property.valueClass());

//   }

//   public default <
//     N extends TitanTypedVertex<N,NT,TG>, NT extends TitanTypedVertex.Type<N,NT,TG>,
//     P extends TitanProperty<N,NT,TG,P,V>, V
//   >
//   TitanKey titanKeyForTitanTypedVertexProperty(P property) {

//     return createOrGet(titanKeyMakerForTitanTypedVertexProperty(property), property.name());
//   }

//   public default <
//     S extends TitanTypedVertex<S,ST,SG>, ST extends TitanTypedVertex.Type<S,ST,SG>,
//     R extends TitanTypedEdge<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends TitanTypedEdge.Type<S,ST,SG,R,RT,RG,T,TT,TG>,
//     T extends TitanTypedVertex<T,TT,TG>, TT extends TitanTypedVertex.Type<T,TT,TG>,
//     P extends TitanProperty<R,RT,P,V>, V
//   >
//   KeyMaker titanKeyMakerForEdgeProperty(P property) {

//     return raw().makeKey(property.name())
//       // .indexed(com.tinkerpop.blueprints.Edge.class)
//       .dataType(property.valueClass());
//   }

//   public default <
//     S extends TitanTypedVertex<S,ST,SG>, ST extends TitanTypedVertex.Type<S,ST,SG>,
//     R extends TitanTypedEdge<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends TitanTypedEdge.Type<S,ST,SG,R,RT,RG,T,TT,TG>,
//     T extends TitanTypedVertex<T,TT,TG>, TT extends TitanTypedVertex.Type<T,TT,TG>,
//     P extends TitanProperty<R,RT,P,V>, V
//   >
//   TitanKey titanKeyForEdgeProperty(P property) {

//     return createOrGet(titanKeyMakerForEdgeProperty(property), property.name());
//   }

//   public default <
//     S extends TitanTypedVertex<S,ST,SG>, ST extends TitanTypedVertex.Type<S,ST,SG>,
//     R extends TitanTypedEdge<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends TitanTypedEdge.Type<S,ST,SG,R,RT,RG,T,TT,TG>,
//     T extends TitanTypedVertex<T,TT,TG>, TT extends TitanTypedVertex.Type<T,TT,TG>,
//     P extends TitanProperty<R,RT,P,V>, V
//   
//   LabelMaker signatureFor(LabelMaker labelMaker, P property) {

//     // create the key for it if not already there
//     TitanType maybeKey = raw().getType(property.name());

//     TitanKey key = null;

//     if (maybeKey instanceof TitanKey && maybeKey != null) {

//       key = (TitanKey) maybeKey;
//     } 
//     else {

//       key = titanKeyMakerForEdgeProperty(property).unique().make();
//     }

//     return labelMaker.signature(key);
//   }

}