package com.ohnosequences.typedGraphs.titan;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;


import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.*;
import com.tinkerpop.blueprints.Direction;

public abstract class TitanRelationship <
  S extends TitanNode<S,ST>, ST extends TitanNode.Type<S,ST>,
  R extends TitanRelationship<S,ST,R,RT,T,TT>, RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
  T extends TitanNode<T,TT>, TT extends TitanNode.Type<T,TT>
> 
  implements Relationship<S,ST, R,RT, T,TT>, TitanEdge 
{


  public interface Type <
    S extends TitanNode<S,ST>, ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>, TT extends TitanNode.Type<T,TT>
  >
    extends Relationship.Type<S,ST,R,RT,T,TT>
  {

    /*
    The Titan label used for this rel type
    */
    public TitanLabel label();

    public R from(TitanEdge edge);
  }

  // titan rel
  
	protected TitanRelationship(TitanEdge raw) {
		
		this.raw = raw;
	}

	protected TitanEdge raw;

  public abstract TitanTypedGraph graph();

	@Override public S source() {

		return this.type().sourceType().from(
			raw.getVertex(Direction.OUT)
		);
	}

	@Override public T target() {

		return this.type().targetType().from(
			raw.getVertex(Direction.IN)
		);
	}

  // use get for implementing all the property-name() methods
  @Override public <P extends Property<R,RT,P,V>, V> V get(P p) {

    return raw.<V>getProperty(p.fullName());
  }

  public <P extends Property<R,RT,P,V>, V> void set(P p, V value) {

    raw.setProperty(p.fullName(), value);
  }

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
  @Override public Object getId() { 
    return raw.getId();
  }
  @Override public <O> O getProperty(TitanKey arg0) { 
    return raw.getProperty(arg0); 
  }
  @Override public <O> O getProperty(String arg0) {   
    return raw.getProperty(arg0); 
  }
  @Override public boolean hasId() {  return raw.hasId(); }
  @Override public boolean isLoaded() {   return raw.isLoaded();  }
  @Override public boolean isNew() {    return raw.isNew(); }
  @Override public boolean isRemoved() {  return raw.isRemoved(); }
  @Override public void remove() {  raw.remove(); }
  @Override public <O> O removeProperty(String arg0) {  return raw.removeProperty(arg0);  }
  @Override public <O> O removeProperty(TitanType arg0) { return raw.removeProperty(arg0);}
  @Override public void setProperty(String arg0, Object arg1) {   raw.setProperty(arg0, arg1);  }
  @Override public void setProperty(TitanKey arg0, Object arg1) { raw.setProperty(arg0, arg1);  }
  @Override public Set<String> getPropertyKeys() {    return raw.getPropertyKeys(); }
  @Override public int compareTo(TitanElement arg0) { return raw.compareTo(arg0); }

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