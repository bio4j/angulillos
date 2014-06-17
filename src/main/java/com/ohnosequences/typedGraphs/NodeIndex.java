package com.ohnosequences.typedGraphs;

import java.util.List;

// TODO move to ElementIndex

/*
  ## Indices

  A node index indexes nodes of a given type through values of one of its properties. This just adds a bound on the indexed type to be a Node; see `ElementIndex`
*/
public interface NodeIndex <
  N extends Node<N,NT>, NT extends Node.Type<N,NT>,
  P extends Property<N,NT,P,V>, V
>
extends
  ElementIndex<N,NT,P,V>
{

  /* This interface declares that this index is over a property that uniquely classifies a node type for exact match queries; it adds the method `getNode` for that.  */
  public static interface Unique <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends 
    NodeIndex<N,NT,P,V>,
    ElementIndex.Unique<N,NT,P,V>
  {

    /* get a node by providing a value of the indexed property. The default implementation relies on `query`. */
    public default N getNode(V byValue) { 

      return getElement(byValue);
    }
  }

  /* This interface declares that this index is over a property that classifies lists of nodes for exact match queries; it adds the method `getNodes` for that.  */
  public static interface List <
    N extends Node<N,NT>, NT extends Node.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends
    NodeIndex<N,NT,P,V>,
    ElementIndex.List<N,NT,P,V>
  {

    /*
    get a list of nodes by providing a value of the property. The default 
    */
    public default java.util.List<? extends N> getNodes(V byValue) {

      return getElements(byValue);
    }
  }

}
