package com.ohnosequences.typedGraphs.titan;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;


import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;

import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Direction;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanElement;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanProperty;
import com.thinkaurelius.titan.core.TitanRelation;
import com.thinkaurelius.titan.core.TitanType;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanVertexQuery;

public abstract class TitanRelationship <

  S extends Node<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  TitanS extends TitanNode<S,ST, TitanS,TitanST>,
  TitanST extends  TitanNodeType<S,ST, TitanS,TitanST>,

  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  TitanR extends TitanRelationship<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT>,
  TitanRT extends TitanRelationshipType<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT>,

  T extends Node<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>,
  TitanT extends TitanNode<T,TT, TitanT,TitanTT>,
  TitanTT extends TitanNodeType<T,TT, TitanT,TitanTT>

> implements Relationship<S,ST, R,RT, T,TT>, TitanEdge {

	protected TitanRelationship(TitanEdge raw) {
		
		this.raw = raw;
	}

	protected TitanEdge raw;

  public abstract TitanTypedGraph graph();
	public abstract TitanRelationshipType<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT> titanType();

	@Override public TitanS source() {

		return this.titanType().titanSourceType().from(
			raw.getVertex(Direction.OUT)
		);
	}

	@Override public TitanT target() {

		return this.titanType().titanTargetType().from(
			raw.getVertex(Direction.IN)
		);
	}

  // use get for implementing all the property-name() methods
  @Override
  public <
    P extends Property<R,RT>,
    PT extends PropertyType<R,RT, P,PT, V>, 
    V
  > V get(PT pt) {

    return raw.<V>getProperty(pt.fullName());
  }

  public <
    P extends Property<R,RT>,
    PT extends PropertyType<R,RT, P,PT, V>, 
    V
  > void set(PT pt, V value) {

    raw.setProperty(pt.fullName(), value);
  }

	//////////////////////////////////////////////////////////////////////////////////////////

	// fwd TitanEdge methods to raw

	@Override
	public TitanLabel getTitanLabel() {
		return raw.getTitanLabel();
	}
	@Override
	public TitanVertex getVertex(Direction dir){
		return raw.getVertex(dir);
	}
	@Override
	public TitanVertex getOtherVertex(TitanVertex vertex){
		return raw.getOtherVertex(vertex);
	}
	@Override
	public boolean isDirected(){
		return raw.isDirected();
	}
	@Override
	public boolean isUnidirected(){
		return raw.isUnidirected();
	}

  @Override
  public Object getId() { return raw.getId();}
  @Override
  public <O> O getProperty(TitanKey arg0) { return raw.getProperty(arg0); }
  @Override
  public <O> O getProperty(String arg0) {   return raw.getProperty(arg0); }
  @Override
  public boolean hasId() {  return raw.hasId(); }
  @Override
  public boolean isLoaded() {   return raw.isLoaded();  }
  @Override
  public boolean isNew() {    return raw.isNew(); }
  @Override
  public boolean isRemoved() {  return raw.isRemoved(); }
  @Override
  public void remove() {  raw.remove(); }
  @Override
  public <O> O removeProperty(String arg0) {  return raw.removeProperty(arg0);  }
  @Override
  public <O> O removeProperty(TitanType arg0) { return raw.removeProperty(arg0);}
  @Override
  public void setProperty(String arg0, Object arg1) {   raw.setProperty(arg0, arg1);  }
  @Override
  public void setProperty(TitanKey arg0, Object arg1) { raw.setProperty(arg0, arg1);  }
  @Override
  public Set<String> getPropertyKeys() {    return raw.getPropertyKeys(); }
  @Override
  public int compareTo(TitanElement arg0) { return raw.compareTo(arg0); }

  @Override public long getID() { return raw.getID(); }
  @Override public TitanType getType() { return raw.getType(); }
  @Override public Direction getDirection(TitanVertex vertex) { return raw.getDirection(vertex); }
  @Override public TitanVertex getProperty(TitanLabel label) { return raw.getProperty(label); }
  @Override public boolean isEdge() { return raw.isEdge(); }
  @Override public boolean isIncidentOn(TitanVertex vertex) { return raw.isIncidentOn(vertex); }
  @Override public boolean isLoop() { return raw.isLoop(); }
  @Override public boolean isModifiable(){ return raw.isModifiable(); }
  @Override public boolean isProperty(){ return raw.isProperty(); }
  @Override public void setProperty(TitanLabel label, TitanVertex vertex) { raw.setProperty(label,vertex); }

  @Override public String getLabel() { return raw.getLabel(); }
}