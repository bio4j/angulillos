package com.ohnosequences.typedGraphs.titan;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.ohnosequences.typedGraphs.*;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanGraph;
import com.thinkaurelius.titan.core.*;

public interface TitanUntypedGraph extends UntypedGraph<TitanVertex,TitanKey,TitanEdge,TitanLabel> {

  TitanGraph titanGraph();

  default void commit() { titanGraph().commit(); }

  default void shutdown() { titanGraph().shutdown(); }

  @Override
  default TitanEdge addEdge(TitanVertex from, TitanLabel edgeType, TitanVertex to) {

    return from.addEdge( edgeType, to );
  }

  @Override
  default TitanVertex addVertex(TitanKey type) {

    return (TitanVertex) titanGraph().addVertex(null); 
  }

  @Override
  default <V> V getPropertyV(TitanVertex vertex, String property) {

    return vertex.<V>getProperty(property);
  }

  @Override
  default <V> void setPropertyV(TitanVertex vertex, String property, V value) {

    vertex.setProperty(property, value);
  }

  @Override
  default <V> V getPropertyE(TitanEdge edge, String property) {

    return edge.<V>getProperty(property);
  }

  @Override
  default <V> void setPropertyE(TitanEdge edge, String property, V value) {

    edge.setProperty(property, value);
  }

  @Override
  default TitanVertex source(TitanEdge edge) {

    return edge.getVertex(com.tinkerpop.blueprints.Direction.OUT);
  }

  @Override
  default TitanVertex target(TitanEdge edge) {

    return edge.getVertex(com.tinkerpop.blueprints.Direction.IN);
  }

  @Override
  default List<TitanEdge> out(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanEdge> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next());
    }

    return list;
  }

  @Override
  default List<TitanVertex> outV(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanVertex> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next().getVertex(com.tinkerpop.blueprints.Direction.IN));
    }

    return list;
  }

  @Override
  default List<TitanEdge> in(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanEdge> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.IN, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next());
    }

    return list;
  }

  @Override
  default List<TitanVertex> inV(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanVertex> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.IN, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next().getVertex(com.tinkerpop.blueprints.Direction.OUT));
    }

    return list;
  }


  // create types
  /*
    creates a key in the graph using the provided `KeyMaker` and `name` if there is no such `TitanKey` with that `name`; otherwise it returns the existing `TitanKey` with the provided `name`.
  */
  default TitanKey createOrGet(KeyMaker keyMaker, String name) {

    Boolean isNotDefined = true;

    TitanKey key = null;
    // first see if there's such a thing there
    Iterator<TitanKey> definedKeys = titanGraph().getTypes(TitanKey.class).iterator();

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
    Iterator<TitanLabel> definedLabels = titanGraph().getTypes(TitanLabel.class).iterator();

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
    Get a `KeyMaker` already configured for creating the key corresponding to a node type. You can use this for defining the corresponding `...`.
  */
  default <
    N extends TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  >
  KeyMaker titanKeyMakerForVertexType(P property) {

    // note how here we take the full name so that this is scoped by the node type; see `Property`.
    KeyMaker keyMaker = titanGraph().makeKey(property.name())
      .dataType(property.valueClass())
      .indexed(com.tinkerpop.blueprints.Vertex.class)
      .unique();

    return keyMaker;
  }

  /*
    create a `TitanKey` for a node type, using the default configuration. If a type with the same name is present it will be returned instead.
  */
  default <
    N extends TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  > 
  TitanKey titanKeyForVertexType(P property) {

    return createOrGet(titanKeyMakerForVertexType(property), property.name());
  }

  /*
    Create a LabelMaker with the minimum default for a relationship type; you should use this for defining the corresponding `TitanTitanTypedEdge.Type`. This is a `LabelMaker` so that you can define any custom signature, indexing etc.
  */
  default <
    // src
    S extends TypedVertex<S,ST,SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
    ST extends TypedVertex.Type<S,ST,SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
    SG extends TypedGraph<SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    // edge
    R extends TypedEdge<S,ST,SG,R,RT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel,T,TT,TG>, 
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel,T,TT,TG>,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    //tgt
    T extends TypedVertex<T,TT,TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
    TT extends TypedVertex.Type<T,TT,TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    TG extends TypedGraph<TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  >
  LabelMaker titanLabelMakerForEdgeType(RT relationshipType) {

    LabelMaker labelMaker = titanGraph().makeLabel(relationshipType.name())
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
    // src
    S extends TypedVertex<S,ST,SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
    ST extends TypedVertex.Type<S,ST,SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
    SG extends TypedGraph<SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    // edge
    R extends TypedEdge<S,ST,SG,R,RT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel,T,TT,TG>, 
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel,T,TT,TG>,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    //tgt
    T extends TypedVertex<T,TT,TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
    TT extends TypedVertex.Type<T,TT,TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    TG extends TypedGraph<TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  >
  TitanLabel titanLabelForEdgeType(RT relationshipType) {

    return createOrGet(titanLabelMakerForEdgeType(relationshipType), relationshipType.name());
  }

  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
  //   ST extends TypedVertex.Type<S,ST,SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
  //   SG extends TypedGraph<SG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  //   // edge
  //   R extends TypedEdge<S,ST,SG,R,RT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel,T,TT,TG>, 
  //   RT extends TypedEdge.Type<S,ST,SG,R,RT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel,T,TT,TG>,
  //   G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  //   //tgt
  //   T extends TypedVertex<T,TT,TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, 
  //   TT extends TypedVertex.Type<T,TT,TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  //   TG extends TypedGraph<TG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  // >
  // TitanLabel titanLabelForEdgeType(String relationshipType) {

  //   return createOrGet(titanLabelMakerForEdgeType(relationshipType), relationshipType.name());
  // }

}