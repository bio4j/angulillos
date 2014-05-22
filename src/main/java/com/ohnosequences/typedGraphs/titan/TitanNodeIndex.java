package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import com.tinkerpop.blueprints.Vertex;

public interface TitanNodeIndex <
  N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
  P extends Property<N,NT,P,V>, V
> 
extends 
  NodeIndex<N,NT,P,V>
{

  interface Unique <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends 
    NodeIndex.Unique<N,NT,P,V> 
  {

    /*
      get a node by providing a value of the indexed property.
    */
    public N getNode(V byValue);
  }

  interface List <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends 
    NodeIndex.List<N,NT,P,V>
  {

    /*
      get a list of nodes by providing a value of the indexed property.
    */
    public java.util.List<? extends N> getNodes(V byValue);
  }

  final class DefaultUnique <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  implements 
    Unique<N,NT,P,V> 
  {

    public DefaultUnique(TitanTypedGraph graph, NT nodeType) {

      this.graph = graph;
      this.nodeType = nodeType;
    }

    private TitanTypedGraph graph;
    private NT nodeType;

    public N getNode(V byValue) {

      // crappy Java generics
      TitanVertex uglyStuff = (TitanVertex) graph.rawGraph().query().has(nodeType.titanKey().getName(),Cmp.EQUAL,byValue).vertices().iterator().next();

      return nodeType.fromTitanVertex(uglyStuff);
    }

  }

  final class DefaultList <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  implements 
    List<N,NT,P,V> {

    public DefaultList(TitanTypedGraph graph, NT nodeType) {

      this.graph = graph;
      this.nodeType = nodeType;
    }

    private TitanTypedGraph graph;
    private NT nodeType;

    public java.util.List<N> getNodes(V byValue) {

      java.util.List<N> list = new LinkedList<>();
      Iterator<Vertex> iterator = graph.rawGraph().query().has(nodeType.titanKey().getName(),Cmp.EQUAL,byValue).vertices().iterator();
      
      while (iterator.hasNext()) {

        list.add(nodeType.from( (TitanVertex) iterator.next() ));
      }

      return list;
    }

  }

}