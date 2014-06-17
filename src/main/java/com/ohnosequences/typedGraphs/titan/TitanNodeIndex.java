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

    @Override public java.util.List<N> query(com.tinkerpop.blueprints.Compare predicate, V value) {

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
    NodeIndex.Unique<N,NT,P,V> 
  {

    public DefaultUnique(TitanTypedGraph graph, P property) {

      super(graph,property);
    }
  }

  final class DefaultList <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends
    Default<N,NT,P,V>
  implements 
    NodeIndex.List<N,NT,P,V> 
  {

    public DefaultList(TitanTypedGraph graph, P property) {

      super(graph,property);
    }
  }

}