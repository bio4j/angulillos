package com.ohnosequences.typedGraphs.titan;

import java.util.List;

import com.ohnosequences.typedGraphs.Technology;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanKey;

public interface Titan extends Technology<TitanVertex,TitanKey,TitanEdge,TitanLabel> {

  public default List<TitanEdge> rawOut(TitanVertex vertex, TitanLabel edgeType) {

    return vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, edgeType);
  }

  public List<TitanVertex> rawOutNodes(TitanVertex vertex, TitanLabel edgeType);

  public List<TitanEdge> rawIn(TitanVertex vertex, TitanLabel edgeType);
  public List<TitanVertex> rawInNodes(TitanVertex vertex, TitanLabel edgeType);
}