package com.ohnosequences.typedGraphs.titan;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;
import com.ohnosequences.typedGraphs.RelTypes;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanProperty;
import com.thinkaurelius.titan.core.TitanRelation;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanVertexQuery;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;

public abstract class TitanNode<N extends TitanNode<N, NT>, NT extends Enum<NT> & TitanNodeType<N, NT>>
		implements Node<N, NT>, TitanVertex {

	protected TitanNode(TitanVertex raw) {
		this.raw = raw;
	}

	protected TitanVertex raw;

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
	@Override
	public <P extends Property<N, NT>, PT extends PropertyType<N, NT, P, PT, V>, V> V get(
			PT pt) {

		return raw.<V> getProperty(pt.fullName());
	}

	// use get for implementing all the property-name() methods

	public <P extends Property<N, NT>, PT extends PropertyType<N, NT, P, PT, V>, V> void set(
			PT pt, V value) {

		raw.setProperty(pt.fullName(), value);
	}

	/*
	 * adds a rel with source this node; note that this method does not set any
	 * properties.
	 */
	public <R extends TitanRelationship<N, NT, R, RT, T, TT>, RT extends Enum<RT> & TitanRelationshipType<N, NT, R, RT, T, TT>, T extends TitanNode<T, TT>, TT extends Enum<TT> & TitanNodeType<T, TT>> R addOut(
			RT relType, T to) {

		TitanEdge rawEdge = to.addEdge(relType.value().toString(), this);

		return relType.from(rawEdge);
	}

	/*
	 * adds a rel with target this node; note that this method does not set any
	 * properties
	 */
	public <S extends TitanNode<S, ST>, ST extends Enum<ST> & TitanNodeType<S, ST>, R extends TitanRelationship<S, ST, R, RT, N, NT>, RT extends Enum<RT> & TitanRelationshipType<S, ST, R, RT, N, NT>> R addIn(
			RT relType, S from) {

		TitanEdge rawEdge = this.addEdge(relType.value().toString(), from);

		return relType.from(rawEdge);
	}

	// TODO maybe is not even needed
	// what we need is something similar depending on arity
	public abstract <R extends TitanRelationship<N, NT, R, RT, T, TT>, RT extends Enum<RT> & TitanRelationshipType<N, NT, R, RT, T, TT>, T extends TitanNode<T, TT>, TT extends Enum<TT> & TitanNodeType<T, TT>> Iterable<R> out(
			RT relType);

	// {

	// TODO map relType.from
	// return relType.from(
	// this.getEdges(com.tinkerpop.blueprints.Direction.OUT,
	// relType.value().toString())
	// );
	// }

	// TODO equivalent methods for oneToMany in, out etc
	protected <R extends TitanRelationship<N, NT, R, RT, T, TT>, RT extends Enum<RT> & RelTypes.ToOne<N, NT, R, RT, T, TT> & TitanRelationshipType<N, NT, R, RT, T, TT>, T extends TitanNode<T, TT>, TT extends Enum<TT> & TitanNodeType<T, TT>> R outToOne(
			RT relType) {

		// TODO check the arity here, throw expection otherwise
		Iterable<TitanEdge> tEdges = this.getTitanEdges(
				com.tinkerpop.blueprints.Direction.OUT, relType.label());

		return relType.from(tEdges.iterator().next());
	}

	protected <R extends TitanRelationship<N, NT, R, RT, T, TT>, RT extends Enum<RT> & RelTypes.ToMany<N, NT, R, RT, T, TT> & TitanRelationshipType<N, NT, R, RT, T, TT>, T extends TitanNode<T, TT>, TT extends Enum<TT> & TitanNodeType<T, TT>> List<R> outToMany(
			RT relType) {

		Iterable<TitanEdge> tEdges = this.getTitanEdges(
				com.tinkerpop.blueprints.Direction.OUT, relType.label());
		List<R> list = new LinkedList<>();
		Iterator<TitanEdge> iterator = tEdges.iterator();
		while (iterator.hasNext()) {
			list.add(relType.from(iterator.next()));
		}

		return list;
	}

	protected <S extends TitanNode<S, ST>, ST extends Enum<ST> & TitanNodeType<S, ST>, R extends TitanRelationship<S, ST, R, RT, N, NT>, RT extends Enum<RT> & TitanRelationshipType<S, ST, R, RT, N, NT> & RelTypes.FromOne<S, ST, R, RT, N, NT>> R inFromOne(
			RT relType) {

		// TODO check the arity here, throw expection otherwise
		Iterable<TitanEdge> tEdges = this.getTitanEdges(
				com.tinkerpop.blueprints.Direction.IN, relType.label());

		return relType.from(tEdges.iterator().next());
	}

}