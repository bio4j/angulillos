package com.ohnosequences.typedGraphs.titan;

import java.util.Set;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanElement;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanProperty;
import com.thinkaurelius.titan.core.TitanRelation;
import com.thinkaurelius.titan.core.TitanType;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanVertexQuery;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;

/*
	# Titan-based impls

  - `N,NT` represent the node and its type being implemented through this Titan vertex
  - `TitanN, TitanNT` represent corresponding Titan entities that need to be defined together

  ``` java
  TitanNode <
    N extends Node<N,NT>,
    NT extends Enum<NT> & NodeType<N,NT>,
    TitanN extends TitanNode<N,NT,TitanN,TitanNT>,
    TitanNT extends TitanNodeType<N,NT, TitanN, TitanT>
  > implements Node<N,NT>, TitanVertex
  ```
*/
public abstract class TitanNode <

	N extends Node<N,NT>,
	NT extends Enum<NT> & NodeType<N,NT>,

  TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
  TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>

	>	implements Node<N,NT>, TitanVertex {

	protected TitanNode(TitanVertex raw) {
		this.raw = raw;
	}

	protected TitanVertex raw;

	public abstract TitanNodeType<N,NT, TitanN,TitanNT> titanType();

	// use get for implementing all the property-name() methods
	@Override
	public <
		P extends Property<N,NT>,
		PT extends PropertyType<N,NT, P,PT, V>, 
		V
	> V get(PT pt) {

		return raw.<V> getProperty(pt.fullName());
	}

	public <
		P extends Property<N,NT>,
		PT extends PropertyType<N,NT, P,PT, V>, 
		V
	> void set(PT pt, V value) {

		raw.setProperty(pt.fullName(), value);
	}

	// /*
	//  * adds a rel with source this node; note that this method does not set any
	//  * properties.
	//  */
	// public <
	// 	R extends TitanRelationship<N,NT, R,RT, T,TT>, 
	// 	RT extends Enum<RT> & RelationshipType<N,NT, R,RT, T,TT>,
	// 	T extends TitanNode<T,TT>, 
	// 	TT extends Enum<TT> & NodeType<T,TT>,
	// 	TRT extends TitanRelationshipType<N,NT, R,RT, T,TT>
	// > R addOut(TRT relType, T to) {

	// 	TitanEdge rawEdge = to.addEdge(relType.type().value().toString(), this);

	// 	return relType.from(rawEdge);
	// }

	// /*
	//  * adds a rel with target this node; note that this method does not set any
	//  * properties
	//  */
	// public <
	// 	S extends TitanNode<S,ST>, 
	// 	ST extends Enum<ST> & NodeType<S,ST>, 
	// 	R extends TitanRelationship<S,ST, R,RT, N,NT>,
	// 	RT extends Enum<RT> & RelationshipType<S,ST, R,RT, N,NT>,
	// 	TRT extends TitanRelationshipType<S,ST, R,RT, N,NT>
	// > R addIn(TRT relType, S from) {

	// 	TitanEdge rawEdge = this.addEdge(relType.type().value().toString(), from);

	// 	return relType.from(rawEdge);
	// }

	// /*
	// 	For when you don't know anything about the arity
	// */
	// public <
	// 	R extends TitanRelationship<N,NT, R,RT, T,TT>, 
	// 	RT extends Enum<RT> & RelationshipType<N,NT, R,RT, T,TT>,
	// 	T extends TitanNode<T,TT>, 
	// 	TT extends Enum<TT> & NodeType<T,TT>,
	// 	TRT extends TitanRelationshipType<N,NT, R,RT, T,TT>
	// > List<R> out(TRT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.OUT, 
	// 		relType.label()
	// 	);

	// 	List<R> list = new LinkedList<>();
	// 	Iterator<TitanEdge> iterator = tEdges.iterator();
	// 	while (iterator.hasNext()) {
	// 		list.add(relType.from(iterator.next()));
	// 	}

	// 	return list;
	// }

	// public <
	// 	S extends TitanNode<S,ST>, 
	// 	ST extends Enum<ST> & NodeType<S,ST>, 
	// 	R extends TitanRelationship<S,ST, R,RT, N,NT>,
	// 	RT extends Enum<RT> & RelationshipType<S,ST, R,RT, N,NT> & RelTypes.FromMany<S,ST, R,RT, N,NT>,
	// 	TRT extends TitanRelationshipType<S,ST, R,RT, N,NT>
	// > List<R> in(TRT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.IN, 
	// 		relType.label()
	// 	);

	// 	List<R> list = new LinkedList<>();
	// 	Iterator<TitanEdge> iterator = tEdges.iterator();
	// 	while (iterator.hasNext()) {
	// 		list.add(relType.from(iterator.next()));
	// 	}

	// 	return list;
	// }

	// public <
	// 	R extends TitanRelationship<N,NT, R,RT, T,TT>, 
	// 	RT extends Enum<RT> & RelationshipType<N,NT, R,RT, T,TT> & RelTypes.ToOne<N,NT, R,RT, T,TT>,
	// 	T extends TitanNode<T,TT>, 
	// 	TT extends Enum<TT> & NodeType<T,TT>,
	// 	TRT extends TitanRelationshipType<N,NT, R,RT, T,TT>
	// > R outToOne(TRT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.OUT,
	// 		relType.label()
	// 	);

	// 	return relType.from(tEdges.iterator().next());
	// }

	// public <
	// 	R extends TitanRelationship<N,NT, R,RT, T,TT>, 
	// 	RT extends Enum<RT> & RelationshipType<N,NT, R,RT, T,TT> & RelTypes.ToMany<N,NT, R,RT, T,TT>,
	// 	T extends TitanNode<T,TT>, 
	// 	TT extends Enum<TT> & NodeType<T,TT>,
	// 	TRT extends TitanRelationshipType<N,NT, R,RT, T,TT>
	// > List<R> outToMany(TRT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.OUT, 
	// 		relType.label()
	// 	);

	// 	List<R> list = new LinkedList<>();
	// 	Iterator<TitanEdge> iterator = tEdges.iterator();
	// 	while (iterator.hasNext()) {
	// 		list.add(relType.from(iterator.next()));
	// 	}

	// 	return list;
	// }

	// public <
	// 	S extends TitanNode<S,ST>, 
	// 	ST extends Enum<ST> & NodeType<S,ST>, 
	// 	R extends TitanRelationship<S,ST, R,RT, N,NT>,
	// 	RT extends Enum<RT> & RelationshipType<S,ST, R,RT, N,NT> & RelTypes.FromMany<S,ST, R,RT, N,NT>,
	// 	TRT extends TitanRelationshipType<S,ST, R,RT, N,NT>
	// > R inFromOne(TRT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.IN,
	// 		relType.label()
	// 	);

	// 	return relType.from(tEdges.iterator().next());
	// }

	// public <
	// 	S extends TitanNode<S,ST>, 
	// 	ST extends Enum<ST> & NodeType<S,ST>, 
	// 	R extends TitanRelationship<S,ST, R,RT, N,NT>,
	// 	RT extends Enum<RT> & RelationshipType<S,ST, R,RT, N,NT> & RelTypes.FromMany<S,ST, R,RT, N,NT>,
	// 	TRT extends TitanRelationshipType<S,ST, R,RT, N,NT>
	// > List<R> inFromMany(TRT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.IN, 
	// 		relType.label()
	// 	);

	// 	List<R> list = new LinkedList<>();

	// 	Iterator<TitanEdge> iterator = tEdges.iterator();

	// 	while (iterator.hasNext()) {
	// 		// build from raw
	// 		list.add(relType.from(iterator.next()));
	// 	}

	// 	return list;
	// }


	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	// TitanVertex methods, fwded to raw
	@Override
	public TitanEdge addEdge(TitanLabel label, TitanVertex vertex){	return raw.addEdge(label, vertex);}
	@Override
	public TitanEdge addEdge(String label, TitanVertex vertex){	return raw.addEdge(label, vertex);}
	@Override
	public TitanProperty addProperty(TitanKey key, Object attribute){	return raw.addProperty(key, attribute);}
	@Override
	public TitanProperty addProperty(String key, Object attribute){	return raw.addProperty(key, attribute);}
	@Override
	public TitanVertexQuery query(){	return raw.query();}
	@Override
	public Iterable<TitanProperty> getProperties(){	return raw.getProperties();}
	@Override
	public Iterable<TitanProperty> getProperties(TitanKey key){	return raw.getProperties(key);}
	@Override
	public Iterable<TitanProperty> getProperties(String key){	return raw.getProperties(key);}
	@Override
	public Iterable<TitanEdge> getTitanEdges(Direction d, TitanLabel... labels){	return raw.getTitanEdges(d, labels);}
	@Override
	public Iterable<TitanRelation> getRelations(){	return raw.getRelations();}
	@Override
	public long getEdgeCount(){	return raw.getEdgeCount();}
	@Override
	public long getPropertyCount(){	return raw.getPropertyCount();}
	@Override
	public boolean isConnected(){	return raw.isConnected();}
	@Override
	public boolean isModified(){	return raw.isModified();}
	@Override
	public Iterable<TitanEdge> getEdges() { return raw.getEdges(); }
	@Override
	public Iterable<Edge> getEdges(Direction d, String... labels){	return raw.getEdges(d, labels);}
	@Override
	public long getID() {	return raw.getID();	}
	@Override
	public Object getId() {	return raw.getId();}
	@Override
	public <O> O getProperty(TitanKey arg0) {	return raw.getProperty(arg0);	}
	@Override
	public <O> O getProperty(String arg0) {		return raw.getProperty(arg0);	}
	@Override
	public boolean hasId() {	return raw.hasId();	}
	@Override
	public boolean isLoaded() {		return raw.isLoaded();	}
	@Override
	public boolean isNew() {		return raw.isNew();	}
	@Override
	public boolean isRemoved() {	return raw.isRemoved();	}
	@Override
	public void remove() {	raw.remove();	}
	@Override
	public <O> O removeProperty(String arg0) {	return raw.removeProperty(arg0);	}
	@Override
	public <O> O removeProperty(TitanType arg0) {	return raw.removeProperty(arg0);}
	@Override
	public void setProperty(String arg0, Object arg1) {		raw.setProperty(arg0, arg1);	}
	@Override
	public void setProperty(TitanKey arg0, Object arg1) {	raw.setProperty(arg0, arg1);	}
	@Override
	public Set<String> getPropertyKeys() {		return raw.getPropertyKeys();	}
	@Override
	public int compareTo(TitanElement arg0) {	return raw.compareTo(arg0);	}
	@Override
	public Edge addEdge(String arg0, Vertex arg1) {	return raw.addEdge(arg0, arg1);	}
	@Override
	public Iterable<Vertex> getVertices(Direction arg0, String... arg1) {		return raw.getVertices(arg0, arg1);	}
	
	// TODO move to TitanElement

}