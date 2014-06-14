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

  public static interface Unique <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends 
    NodeIndex.Unique<N,NT,P,V> 
  {

    /* get a node by providing a value of the indexed property. */
    public N getNode(V byValue);
  }

  public static interface List <
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


  public abstract class Default <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  implements 
    TitanNodeIndex<N,NT,P,V>
  {

    public Default(TitanTypedGraph graph, P property) {

      this.graph = graph;
      this.property = property;
    }

    protected TitanTypedGraph graph;
    protected P property;

    @Override public java.util.List<? extends N> query(com.tinkerpop.blueprints.Compare predicate, V value) {

      java.util.List<N> list = new LinkedList<>();

      Iterator<Vertex> iterator = graph.rawGraph()
        .query().has(
          property.fullName(),
          predicate,
          value
        )
        .vertices().iterator();
      
      while ( iterator.hasNext() ) {

        list.add(property.elementType().from( (TitanVertex) iterator.next() ));
      }

      return list;
    }
  }


  /* Default implementation of a node unique index */
  public final class DefaultUnique <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends
    Default<N,NT,P,V> 
  implements 
    Unique<N,NT,P,V> 
  {

    public DefaultUnique(TitanTypedGraph graph, P property) {

      super(graph,property);
    }

    @Override public N getNode(V byValue) {

      // crappy Java generics force the cast here
      TitanVertex uglyStuff = (TitanVertex) graph.rawGraph()
        .query().has(
          property.fullName(),
          Cmp.EQUAL, 
          byValue
        )
        .vertices().iterator().next();

      return property.elementType().fromTitanVertex(uglyStuff);
    }

  }

  final class DefaultList <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends
    Default<N,NT,P,V>
  implements 
    List<N,NT,P,V> 
  {

    public DefaultList(TitanTypedGraph graph, P property) {

      super(graph,property);
    }

    @Override public java.util.List<N> getNodes(V byValue) {

      java.util.List<N> list = new LinkedList<>();

      Iterator<Vertex> iterator = graph.rawGraph()
        .query().has(
          property.fullName(),
          Cmp.EQUAL,
          byValue
        )
        .vertices().iterator();
      
      while ( iterator.hasNext() ) {

        list.add(property.elementType().from( (TitanVertex) iterator.next() ));
      }

      return list;
    }
  }

}