package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanElement;

/*
  This should extend `TitanKey` but is not possible due to non-covariant Java generics
*/
public interface TitanNodeType <

  N extends Node<N,NT>,
  NT extends Enum<NT> & NodeType<N,NT>,

  TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
  TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>
>
{

  /*
    The node type
  */
  public NT type();

  public TitanKey titanKey();

  public TitanNode<N,NT, TitanN,TitanNT> from(TitanVertex vertex);
}