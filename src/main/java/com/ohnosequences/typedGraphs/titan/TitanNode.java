package com.ohnosequences.typedGraphs.titan;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;

import com.ohnosequences.typedGraphs.RelTypes;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanProperty;
import com.thinkaurelius.titan.core.TitanRelation;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanVertexQuery;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;

public abstract class TitanNode<
	N extends TitanNode<N, NT>, 
	NT extends Enum<NT> & NodeType<N, NT>
>	implements Node<N, NT>, TitanVertex {

	protected TitanNode(TitanVertex raw) {
		this.raw = raw;
	}

	protected TitanVertex raw;

	public abstract TitanNodeType<N,NT> titanType();

	// use get for implementing all the property-name() methods
	@Override
	public <
		P extends Property<N, NT>,
		PT extends PropertyType<N,NT, P,PT, V>, 
		V
	> V get(PT pt) {

		return raw.<V> getProperty(pt.fullName());
	}

	public <
		P extends Property<N, NT>,
		PT extends PropertyType<N,NT, P,PT, V>, 
		V
	> void set(PT pt, V value) {

		raw.setProperty(pt.fullName(), value);
	}

	/*
	 * adds a rel with source this node; note that this method does not set any
	 * properties.
	 */
	public <
		R extends TitanRelationship<N,NT, R,RT, T,TT>, 
		RT extends Enum<RT> & RelationshipType<N,NT, R,RT, T,TT>,
		T extends TitanNode<T,TT>, 
		TT extends Enum<TT> & NodeType<T,TT>,
		TRT extends TitanRelationshipType<N,NT, R,RT, T,TT>
	> R addOut(TRT relType, T to) {

		TitanEdge rawEdge = to.addEdge(relType.type().value().toString(), this);

		return relType.from(rawEdge);
	}

	/*
	 * adds a rel with target this node; note that this method does not set any
	 * properties
	 */
	public <
		S extends TitanNode<S,ST>, 
		ST extends Enum<ST> & NodeType<S,ST>, 
		R extends TitanRelationship<S,ST, R,RT, N,NT>,
		RT extends Enum<RT> & RelationshipType<S,ST, R,RT, N,NT>,
		TRT extends TitanRelationshipType<S,ST, R,RT, N,NT>
	> R addIn(TRT relType, S from) {

		TitanEdge rawEdge = this.addEdge(relType.type().value().toString(), from);

		return relType.from(rawEdge);
	}

	/*
		For when you don't know anything about the arity
	*/
	public <
		R extends TitanRelationship<N,NT, R,RT, T,TT>, 
		RT extends Enum<RT> & RelationshipType<N,NT, R,RT, T,TT>,
		T extends TitanNode<T,TT>, 
		TT extends Enum<TT> & NodeType<T,TT>,
		TRT extends TitanRelationshipType<N,NT, R,RT, T,TT>
	> List<R> out(TRT relType) {

		Iterable<TitanEdge> tEdges = this.getTitanEdges(
			com.tinkerpop.blueprints.Direction.OUT, 
			relType.label()
		);

		List<R> list = new LinkedList<>();
		Iterator<TitanEdge> iterator = tEdges.iterator();
		while (iterator.hasNext()) {
			list.add(relType.from(iterator.next()));
		}

		return list;
	}

	public <
		S extends TitanNode<S,ST>, 
		ST extends Enum<ST> & NodeType<S,ST>, 
		R extends TitanRelationship<S,ST, R,RT, N,NT>,
		RT extends Enum<RT> & RelationshipType<S,ST, R,RT, N,NT> & RelTypes.FromMany<S,ST, R,RT, N,NT>,
		TRT extends TitanRelationshipType<S,ST, R,RT, N,NT>
	> List<R> in(TRT relType) {

		Iterable<TitanEdge> tEdges = this.getTitanEdges(
			com.tinkerpop.blueprints.Direction.IN, 
			relType.label()
		);

		List<R> list = new LinkedList<>();
		Iterator<TitanEdge> iterator = tEdges.iterator();
		while (iterator.hasNext()) {
			list.add(relType.from(iterator.next()));
		}

		return list;
	}

	public <
		R extends TitanRelationship<N,NT, R,RT, T,TT>, 
		RT extends Enum<RT> & RelationshipType<N,NT, R,RT, T,TT> & RelTypes.ToOne<N,NT, R,RT, T,TT>,
		T extends TitanNode<T,TT>, 
		TT extends Enum<TT> & NodeType<T,TT>,
		TRT extends TitanRelationshipType<N,NT, R,RT, T,TT>
	> R outToOne(TRT relType) {

		Iterable<TitanEdge> tEdges = this.getTitanEdges(
			com.tinkerpop.blueprints.Direction.OUT,
			relType.label()
		);

		return relType.from(tEdges.iterator().next());
	}

	public <
		R extends TitanRelationship<N,NT, R,RT, T,TT>, 
		RT extends Enum<RT> & RelationshipType<N,NT, R,RT, T,TT> & RelTypes.ToMany<N,NT, R,RT, T,TT>,
		T extends TitanNode<T,TT>, 
		TT extends Enum<TT> & NodeType<T,TT>,
		TRT extends TitanRelationshipType<N,NT, R,RT, T,TT>
	> List<R> outToMany(TRT relType) {

		Iterable<TitanEdge> tEdges = this.getTitanEdges(
			com.tinkerpop.blueprints.Direction.OUT, 
			relType.label()
		);

		List<R> list = new LinkedList<>();
		Iterator<TitanEdge> iterator = tEdges.iterator();
		while (iterator.hasNext()) {
			list.add(relType.from(iterator.next()));
		}

		return list;
	}

	public <
		S extends TitanNode<S,ST>, 
		ST extends Enum<ST> & NodeType<S,ST>, 
		R extends TitanRelationship<S,ST, R,RT, N,NT>,
		RT extends Enum<RT> & RelationshipType<S,ST, R,RT, N,NT> & RelTypes.FromMany<S,ST, R,RT, N,NT>,
		TRT extends TitanRelationshipType<S,ST, R,RT, N,NT>
	> R inFromOne(TRT relType) {

		Iterable<TitanEdge> tEdges = this.getTitanEdges(
			com.tinkerpop.blueprints.Direction.IN,
			relType.label()
		);

		return relType.from(tEdges.iterator().next());
	}

	public <
		S extends TitanNode<S,ST>, 
		ST extends Enum<ST> & NodeType<S,ST>, 
		R extends TitanRelationship<S,ST, R,RT, N,NT>,
		RT extends Enum<RT> & RelationshipType<S,ST, R,RT, N,NT> & RelTypes.FromMany<S,ST, R,RT, N,NT>,
		TRT extends TitanRelationshipType<S,ST, R,RT, N,NT>
	> List<R> inFromMany(TRT relType) {

		Iterable<TitanEdge> tEdges = this.getTitanEdges(
			com.tinkerpop.blueprints.Direction.IN, 
			relType.label()
		);

		List<R> list = new LinkedList<>();

		Iterator<TitanEdge> iterator = tEdges.iterator();

		while (iterator.hasNext()) {
			// build from raw
			list.add(relType.from(iterator.next()));
		}

		return list;
	}


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

	// TODO move to TitanElement

}