package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.attribute.Cmp;

import java.util.List;

public interface TitanNodeIndex <
  N extends Node<N,NT>,
  NT extends Enum<NT> & NodeType<N,NT>,

  TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
  TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>,

  P extends Property<N,NT>,
  PT extends PropertyType<N,NT, P,PT, V>, 
  V
> extends NodeIndex<N,NT,P,PT,V>
{

  interface Unique <
    N extends Node<N,NT>,
    NT extends Enum<NT> & NodeType<N,NT>,

    TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
    TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>,

    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>, 
    V
  > extends NodeIndex.Unique<N,NT,P,PT,V> 
  {

    /*
    get a node by providing a value of the indexed property.
    */
    public Node<N,NT> getNode(V byValue);
  }

  interface List <
    N extends Node<N,NT>,
    NT extends Enum<NT> & NodeType<N,NT>,

    TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
    TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>,

    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>, 
    V
  > extends NodeIndex.List<N,NT,P,PT,V> 
  {

    /*
    get a list of nodes by providing a value of the indexed property.
    */
    public java.util.List<? extends Node<N,NT>> getNodes(V byValue);
  }

  class Default <
    N extends Node<N,NT>,
    NT extends Enum<NT> & NodeType<N,NT>,

    TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
    TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>,

    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>, 
    V
  > implements Unique<N,NT,TitanN,TitanNT,P,PT,V> {

    private TitanTypedGraph graph;
    private TitanNodeType<N,NT, TitanN,TitanNT> nodeType;

    public Node<N,NT> getNode(V byValue) {

      // crappy Java generics
      // return nodeType.from(graph.rawGraph.query().has(nodeType.titanKey().getName(),Cmp.EQUAL,byValue).vertices().iterator().next());

      return null;
    }

  }

}