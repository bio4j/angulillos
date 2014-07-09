package com.ohnosequences.typedGraphs.titan;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;


import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.*;
import com.tinkerpop.blueprints.Direction;

public abstract class TitanTypedEdge <
  S extends TitanTypedVertex<S,ST,SG>,
  ST extends TitanTypedVertex.Type<S,ST,SG>,
  SG extends TitanTypedGraph<SG>,

  R extends TitanTypedEdge<S,ST,SG, R,RT,RG, T,TT,TG>,
  RT extends TitanTypedEdge.Type<S,ST,SG, R,RT,RG, T,TT,TG>,
  RG extends TitanTypedGraph<RG>,

  T extends TitanTypedVertex<T,TT,TG>,
  TT extends TitanTypedVertex.Type<T,TT,TG>,
  TG extends TitanTypedGraph<TG>
> 
implements
  TitanTypedElement<R,RT,RG>,
  TypedEdge<S,ST,SG, R,RT, RG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel, T,TT,TG>,
  TitanEdge
{

  // titan rel 
  protected TitanTypedEdge(TitanEdge raw) {
    
    this.raw = raw;
  }

  protected TitanEdge raw;

  @Override public TitanEdge raw() {

    return this.raw;
  }

  public interface Type <
    S extends TitanTypedVertex<S,ST,SG>,
    ST extends TitanTypedVertex.Type<S,ST,SG>,
    SG extends TitanTypedGraph<SG>,

    R extends TitanTypedEdge<S,ST,SG, R,RT,RG, T,TT,TG>,
    RT extends TitanTypedEdge.Type<S,ST,SG, R,RT,RG, T,TT,TG>,
    RG extends TitanTypedGraph<RG>,

    T extends TitanTypedVertex<T,TT,TG>, TT extends TitanTypedVertex.Type<T,TT,TG>,
    TG extends TitanTypedGraph<TG>
  >
  extends
    TitanTypedElement.Type<R,RT,RG>,  
    TypedEdge.Type<S,ST,SG, R,RT, RG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel, T,TT,TG>
  {

    /* The Titan label used for this rel type */
    public TitanLabel label();

    @Override
    public R from(TitanEdge edge);
  }

	@Override 
  public S source() {

		return this.type().sourceType().from(
			raw.getVertex(Direction.OUT)
		);
	}

	@Override 
  public T target() {

		return this.type().targetType().from(
			raw.getVertex(Direction.IN)
		);
	}

  /*
    ### delegating methods to `TitanEdge`

    Here we forward all `TitanEdge`-specific methods to the wrapped `raw` value.
  */
	//////////////////////////////////////////////////////////////////////////////////////////
	// fwd TitanEdge methods to raw
	@Override public TitanLabel getTitanLabel() {
		return raw.getTitanLabel();
	}
	@Override public TitanVertex getVertex(Direction dir){
		return raw.getVertex(dir);
	}
	@Override public TitanVertex getOtherVertex(TitanVertex vertex){
		return raw.getOtherVertex(vertex);
	}
	@Override public boolean isDirected(){
		return raw.isDirected();
	}
	@Override public boolean isUnidirected(){
		return raw.isUnidirected();
	}
  @Override public Set<String> getPropertyKeys() {
    return raw.getPropertyKeys(); 
  }
  @Override public TitanType getType() { 
    return raw.getType(); 
  }
  @Override public Direction getDirection(TitanVertex vertex) { 
    return raw.getDirection(vertex); 
  }
  @Override public TitanVertex getProperty(TitanLabel label) { 
    return raw.getProperty(label); 
  }
  @Override public boolean isEdge() { 
    return raw.isEdge(); 
  }
  @Override public boolean isIncidentOn(TitanVertex vertex) { 
    return raw.isIncidentOn(vertex); 
  }
  @Override public boolean isLoop() { 
    return raw.isLoop(); 
  }
  @Override public boolean isModifiable(){ 
    return raw.isModifiable(); 
  }
  @Override public boolean isProperty(){ 
    return raw.isProperty(); 
  }
  @Override public void setProperty(TitanLabel label, TitanVertex vertex) { 
    raw.setProperty(label,vertex); 
  }
  @Override public String getLabel() { 
    return raw.getLabel(); 
  }
}