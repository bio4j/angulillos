package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import com.tinkerpop.blueprints.Vertex;

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

  class DefaultUnique <
    N extends Node<N,NT>,
    NT extends Enum<NT> & NodeType<N,NT>,

    TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
    TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>,

    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>, 
    V
  > implements Unique<N,NT,TitanN,TitanNT,P,PT,V> {

    public DefaultUnique(TitanTypedGraph graph, TitanNodeType<N,NT, TitanN,TitanNT> nodeType) {

      this.graph = graph;
      this.nodeType = nodeType;
    }

    private TitanTypedGraph graph;
    private TitanNodeType<N,NT, TitanN,TitanNT> nodeType;

    public Node<N,NT> getNode(V byValue) {

      // crappy Java generics
      TitanVertex uglyStuff = (TitanVertex) graph.rawGraph.query().has(nodeType.titanKey().getName(),Cmp.EQUAL,byValue).vertices().iterator().next();

      return nodeType.from(uglyStuff);
    }

  }

  class DefaultList <
    N extends Node<N,NT>,
    NT extends Enum<NT> & NodeType<N,NT>,

    TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
    TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>,

    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>, 
    V
  > implements List<N,NT,TitanN,TitanNT,P,PT,V> {

    public DefaultList(TitanTypedGraph graph, TitanNodeType<N,NT, TitanN,TitanNT> nodeType) {

      this.graph = graph;
      this.nodeType = nodeType;
    }

    private TitanTypedGraph graph;
    private TitanNodeType<N,NT, TitanN,TitanNT> nodeType;

    public java.util.List<Node<N,NT>> getNodes(V byValue) {

      java.util.List<Node<N,NT>> list = new LinkedList<>();
      Iterator<Vertex> iterator = graph.rawGraph.query().has(nodeType.titanKey().getName(),Cmp.EQUAL,byValue).vertices().iterator();
      
      while (iterator.hasNext()) {

        list.add(nodeType.from( (TitanVertex) iterator.next() ));
      }

      return list;
    }

  }

}