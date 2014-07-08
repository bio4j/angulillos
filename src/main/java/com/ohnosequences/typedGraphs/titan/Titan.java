package com.ohnosequences.typedGraphs.titan;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.ohnosequences.typedGraphs.UntypedGraph;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanKey;

public interface Titan extends UntypedGraph<TitanVertex,TitanKey,TitanEdge,TitanLabel> {

  @Override
  default <V> V rawGetPropertyFromNode(TitanVertex vertex, String property) {

    return vertex.<V>getProperty(property);
  }

  @Override
  default List<TitanEdge> rawOut(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanEdge> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next());
    }

    return list;
  }

  @Override
  default List<TitanVertex> rawOutNodes(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanVertex> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next().getVertex(com.tinkerpop.blueprints.Direction.IN));
    }

    return list;
  }

  @Override
  default List<TitanEdge> rawIn(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanEdge> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.IN, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next());
    }

    return list;
  }

  @Override
  default List<TitanVertex> rawInNodes(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanVertex> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.IN, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next().getVertex(com.tinkerpop.blueprints.Direction.OUT));
    }

    return list;
  }
}