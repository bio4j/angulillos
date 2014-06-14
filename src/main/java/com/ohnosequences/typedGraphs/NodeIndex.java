package com.ohnosequences.typedGraphs;

import java.util.List;

/*
  A node index indexes nodes of a given type through values of one of its properties. They can be unique or 
*/
public interface NodeIndex <
  N extends Node<N,NT>, NT extends Node.Type<N,NT>,
  P extends Property<N,NT,P,V>, V
>
{

  /* query this index using a Blueprints predicate */
  public java.util.List<? extends N> query(com.tinkerpop.blueprints.Compare predicate, V value);

  /* For indexing over a property that uniquely indexes a node type for exact match queries. */
  public static interface Unique <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
    extends NodeIndex<N,NT,P,V> 
  {

    /* get a node by providing a value of the property. */
    public N getNode(V byValue);
  }

  public static interface List <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
    extends NodeIndex<N,NT,P,V> 
  {

    /*
    get a list of nodes by providing a value of the property.
    */
    public java.util.List<? extends N> getNodes(V byValue);
  }

}
