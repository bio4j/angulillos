package com.ohnosequences.typedGraphs;

public interface NodeUniqueIndex <
  N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
  P extends Property<N,NT>, PT extends PropertyType<N,NT, P,PT, V>,
  V
> extends NodeIndex<N,NT,P,PT,V> 
{

  /*
  get a node by providing a value of the indexed property.
  */
  public Node<N,NT> getNode(V byValue);
}