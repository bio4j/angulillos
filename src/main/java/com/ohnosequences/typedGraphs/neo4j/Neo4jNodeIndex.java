package com.ohnosequences.typedGraphs.neo4j;

import com.ohnosequences.typedGraphs.*;

import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.ResourceIterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public interface Neo4jNodeIndex <
  N extends Neo4jNode<N,NT>, NT extends Neo4jNode.Type<N,NT>,
  P extends Neo4jProperty<N,NT,P,V>, V
> 
extends 
  NodeIndex<N,NT,P,V>
{

  // TODO do something with this
  @Override
  default java.util.List<? extends N> query(com.tinkerpop.blueprints.Compare predicate, V value) { 

    throw new UnsupportedOperationException();
  }

  static abstract class Default <
    N extends Neo4jNode<N,NT>, NT extends Neo4jNode.Type<N,NT>,
    P extends Neo4jProperty<N,NT,P,V>, V
  > 
  implements 
    Neo4jNodeIndex<N,NT,P,V>
  {

    public Default(Neo4jTypedGraph graph, P property) {

      this.graph = graph;
      this.property = property;
    }

    protected Neo4jTypedGraph graph;
    protected P property;
  }

  static interface Unique <
    N extends Neo4jNode<N,NT>, NT extends Neo4jNode.Type<N,NT>,
    P extends Neo4jProperty<N,NT,P,V>, V
  > 
  extends
    Neo4jNodeIndex<N,NT,P,V>,
    NodeIndex.Unique<N,NT,P,V>
  {

  }

  /* Default implementation of a node unique index */
  static final class DefaultUnique <
    N extends Neo4jNode<N,NT>, NT extends Neo4jNode.Type<N,NT>,
    P extends Neo4jProperty<N,NT,P,V>, V
  > 
  extends
    Default<N,NT,P,V> 
  implements 
    Neo4jNodeIndex.Unique<N,NT,P,V> 
  {

    public DefaultUnique(Neo4jTypedGraph graph, P property) {

      super(graph,property);
    }

    @Override
    public N getNode(V id) { 
      
      ResourceIterable<org.neo4j.graphdb.Node> nodes = graph.rawGraph()
      .findNodesByLabelAndProperty(
        property.elementType(),
        property.name(),
        id
      );
      ResourceIterator<org.neo4j.graphdb.Node> itNodes = nodes.iterator();

      N node = property.elementType().from(itNodes.next());

      itNodes.close();

      return node;
    }
  }
}