package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import com.tinkerpop.blueprints.Vertex;

public interface TitanTypedVertexIndex <
  N extends TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  NT extends TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  P extends Property<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
  G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
> 
extends 
  TypedVertexIndex<N,NT,P,V, G, TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
{

  public static abstract class Default <
    N extends TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  > 
  implements 
    TitanTypedVertexIndex<N,NT,P,V,G>
  {

    public Default(G graph, P property) {

      this.graph = graph;
      this.property = property;
    }

    protected G graph;
    protected P property;

    @Override
    public G graph() {

      return graph;
    }

    @Override public java.util.List<N> query(com.tinkerpop.blueprints.Compare predicate, V value) {

      java.util.List<N> list = new LinkedList<>();

      Iterator<Vertex> iterator = graph().raw().titanGraph()
        .query().has(
          property.name(),
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

  public interface Unique <
    N extends TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  > 
  extends
    TitanTypedVertexIndex<N,NT,P,V,G>,
    TypedVertexIndex.Unique<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  {}

  /* Default implementation of a node unique index */
  public static final class DefaultUnique <
    N extends TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  > 
  extends
    Default<N,NT,P,V,G> 
  implements 
    TitanTypedVertexIndex.Unique<N,NT,P,V,G> 
  {

    public DefaultUnique(G graph, P property) {

      super(graph,property);
    }
  }

  public static interface List <
    N extends TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  > 
  extends
    TitanTypedVertexIndex<N,NT,P,V,G>,
    TypedVertexIndex.List<N,NT,P,V,G, TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  {

  }

  public static final class DefaultList <
    N extends TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  > 
  extends
    Default<N,NT,P,V,G>
  implements 
    TitanTypedVertexIndex.List<N,NT,P,V,G> 
  {

    public DefaultList(G graph, P property) {

      super(graph,property);
    }
  }

}