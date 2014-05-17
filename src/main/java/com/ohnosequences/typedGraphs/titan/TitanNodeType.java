package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanElement;
import com.thinkaurelius.titan.core.KeyMaker;
import com.thinkaurelius.titan.core.*;

/*
  This should extend `TitanKey` but is not possible due to non-covariant Java generics
*/
public interface TitanNodeType <

  N extends Node<N,NT>,
  NT extends NodeType<N,NT>,

  TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
  TitanNT extends TitanNodeType<N,NT, TitanN,TitanNT>
> extends NodeType<N,NT>
{

  /*
    The node type which this Titan node type implements.
  */
  public NT type();

  /*
  The Titan key which classifies this titan node type.
  */
  public TitanKey titanKey();

  /*
  A builder for Titan nodes of this type. This could be implemented generically _if_ you could easily instantiate generic types in Java. But you can't. Anyway, this should be almost always `return new TitanN(vertex);`
  */
  @Override public TitanN from(Object vertex);
}