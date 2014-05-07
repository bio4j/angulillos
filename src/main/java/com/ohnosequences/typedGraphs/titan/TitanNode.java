package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanElement;

public abstract class TitanNode<
  N extends TitanNode<N,T>, 
  T extends  Enum<T> & TitanNodeType<N,T>
> implements Node<N,T>, TitanVertex {


  protected TitanVertex raw;

  // TODO delegate all TitanVertex methods to raw
  // TODO implement getType() somehow
}