package com.ohnosequences.typedGraphs;

import java.util.List;

public interface NodeIndex <
  N extends Node<N,NT>, NT extends Node.Type<N,NT>,
  P extends Property<N,NT,P,V>, V
>
{

  interface Unique <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
    extends NodeIndex<N,NT,P,V> 
  {

    /*
      get a node by providing a value of the indexed property.
    */
    public N getNode(V byValue);
  }

  interface List <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
    extends NodeIndex<N,NT,P,V> 
  {

    /*
    get a list of nodes by providing a value of the indexed property.
    */
    public java.util.List<? extends N> getNodes(V byValue);
  }

}
