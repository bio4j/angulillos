package com.ohnosequences.typedGraphs.titan;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.*;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

public abstract class TitanTypedVertex <
	N extends TitanTypedVertex<N,NT,G>,
	NT extends TitanTypedVertex.Type<N,NT,G>,
  G extends TitanTypedGraph<G>
>	
implements
  TitanTypedElement<N,NT,G>,
  TypedVertex<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  TitanVertex 
{

  // Titan nodes
  protected TitanTypedVertex(TitanVertex raw) {

    this.raw = raw;
  }

  protected TitanVertex raw;

  @Override 
  public TitanVertex raw() { 

    return this.raw; 
  }

  public abstract G graph();

  public interface Type <
    N extends TitanTypedVertex<N,NT,G>,
    NT extends TitanTypedVertex.Type<N,NT,G>,
    G extends TitanTypedGraph<G>
  > 
  extends
    TitanTypedElement.Type<N,NT,G>,
    TypedVertex.Type<N,NT,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  {

    /*
    The Titan key which classifies this titan node type.
    */
    TitanKey titanKey();

    /*
    A builder for Titan nodes of this type. This could be implemented generically _if_ you could easily instantiate generic types in Java. But you can't. Anyway, this should be almost always `return new TitanN(vertex);`
    */
    @Override 
    N from(TitanVertex vertex);
  }

  /*
    ### delegating methods to `TitanVertex`

    Here we forward all `TitanVertex`-specific methods to the wrapped `raw` value.
  */
	///////////////////////////////////////////////////////////////////////////////////////////////////////////

  @Override public Iterable<com.thinkaurelius.titan.core.TitanProperty> getProperties() {  
    return raw().getProperties();
  }
  @Override public Iterable<com.thinkaurelius.titan.core.TitanProperty> getProperties(TitanKey key){  
    return raw().getProperties(key);
  }
  @Override public Iterable<com.thinkaurelius.titan.core.TitanProperty> getProperties(String key){  
    return raw().getProperties(key);
  }
  @Override public boolean isModified(){  
    return raw().isModified();
  }
	@Override public TitanEdge addEdge(TitanLabel label, TitanVertex vertex){		
		return raw().addEdge(label, vertex);
	}
	@Override public TitanEdge addEdge(String label, TitanVertex vertex){	
		return raw().addEdge(label, vertex);
	}
	@Override public com.thinkaurelius.titan.core.TitanProperty addProperty(TitanKey key, Object attribute){	
		return raw().addProperty(key, attribute);
	}
	@Override public com.thinkaurelius.titan.core.TitanProperty addProperty(String key, Object attribute){	
		return raw().addProperty(key, attribute);
	}
	@Override public TitanVertexQuery query(){	
    return raw().query();
  }
	@Override public Iterable<TitanEdge> getTitanEdges(Direction d, TitanLabel... labels){	
    return raw().getTitanEdges(d, labels);
  }
	@Override public Iterable<TitanRelation> getRelations(){
  	return raw().getRelations();
  }
	@Override public long getEdgeCount(){
  	return raw().getEdgeCount();
  }
	@Override public long getPropertyCount(){
  	return raw().getPropertyCount();
  }
	@Override public boolean isConnected(){
  	return raw().isConnected();
  }
	@Override public Iterable<TitanEdge> getEdges() {
   return raw().getEdges(); 
  }
	@Override public Iterable<Edge> getEdges(Direction d, String... labels){
  	return raw().getEdges(d, labels);
  }
	@Override public Edge addEdge(String arg0, Vertex arg1) {
  	return raw().addEdge(arg0, arg1);	
  }
	@Override public Iterable<Vertex> getVertices(Direction arg0, String... arg1) {		
    return raw().getVertices(arg0, arg1);	
  }
}