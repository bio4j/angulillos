package com.ohnosequences.typedGraphs;

import java.util.List;

public interface NodeListIndex <
  N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
  P extends Property<N,NT>, PT extends PropertyType<N,NT, P,PT, V>,
  V
> extends NodeIndex<N,NT,P,PT,V> 
{

  /*
  get a list of nodes by providing a value of the indexed property.
  */
  public List<? extends Node<N,NT>> getNodes(V byValue);
}
