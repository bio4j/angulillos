package com.ohnosequences.typedGraphs;

import java.util.List;

/*
This class is just a convenience for retrieving node/s given an index. I don't know why it is abstract, honestly.
*/
public abstract class NodeRetriever <
  N extends Node<N,NT>,
  NT extends Enum<NT> & NodeType<N,NT>
>
{

  /*
  Given a unique index and a value of the indexed property, get the node.
  */
  public <
    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>,
    V
  > Node<N,NT> getNodeFrom(NodeIndex.Unique<N,NT,P,PT,V> index, V value) { 

    return index.getNode(value); 
  }

  /*
  Given a list index and a value of the indexed property, get the nodes.
  */
  public <
    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>,
    V
  > List<? extends Node<N,NT>> getNodesFrom(NodeIndex.List<N,NT,P,PT,V> index, V value) { 

    return index.getNodes(value); 
  }
}