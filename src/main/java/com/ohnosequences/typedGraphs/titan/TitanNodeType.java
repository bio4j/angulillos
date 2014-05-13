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

  // index a node with its P property
  public static <
    N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
    P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
    V
  > 
  TitanKey defaultKey(PT propertyType, TitanGraph graph) {

    return graph.makeKey(propertyType.fullName())
      .dataType(propertyType.valueClass())
      .indexed(com.tinkerpop.blueprints.Vertex.class)
      .unique()
      .make();
  }
}