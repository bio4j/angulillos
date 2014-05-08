package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanElement;

/*
  This should extend `TitanKey` but is not possible due to non-covariant Java generics
*/
public interface TitanNodeType<
  N extends TitanNode<N,NT>, 
  NT extends Enum<NT> & NodeType<N,NT>
>
{

  /*
    The node type
  */
  public NT type();

  public TitanKey titanKey();

  public N from(TitanVertex vertex);
}