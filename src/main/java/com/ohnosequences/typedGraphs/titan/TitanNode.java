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

public abstract class TitanNode <
	N extends TitanNode<N,NT,G>,
	NT extends TitanNode.Type<N,NT,G>,
  G extends TitanTypedGraph<G>
>	
implements
  TitanElement<N,NT,G>,
  Node<N,NT,G,Titan,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  TitanVertex 
{

  // Titan nodes
  protected TitanNode(TitanVertex raw) {

    this.raw = raw;
  }

  protected TitanVertex raw;

  @Override 
  public TitanVertex raw() { 

    return this.raw; 
  }

  public abstract G graph();

  public static interface Type <
    N extends TitanNode<N,NT,G>,
    NT extends TitanNode.Type<N,NT,G>,
    G extends TitanTypedGraph<G>
  > 
  extends
    TitanElement.Type<N,NT,G>,
    Node.Type<N,NT,G,Titan,TitanVertex,TitanKey,TitanEdge,TitanLabel>
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

	// /*
	//  * adds a rel with source this node; note that this method does not set any
	//  * properties.
	//  */
	// public <
 //    // rel
	// 	R extends TitanRelationship<N,NT, R,RT, T,TT, G>,
	// 	RT extends TitanRelationship.Type<N,NT, R,RT, T,TT, G>,
 //    // target node
	// 	T extends TitanNode<T,TT,G>,
	// 	TT extends TitanNode.Type<T,TT,G>
	// > 
	// R addOut(RT relType, T to) {
  		
 //  	TitanEdge rawEdge = this.addEdge(relType.label(), to.raw);
		
	// 	return relType.fromTitanEdge(rawEdge);
	// }

	// /*
	//  * adds a rel with target this node; note that this method does not set any
	//  * properties
	//  */
	// public <
	//   // source node
 //    S extends TitanNode<S,ST,G>, 
 //    ST extends TitanNode.Type<S,ST,G>,
 //    //rel; bound to be FromOne
 //    R extends TitanRelationship<S,ST, R,RT, N,NT,G>, 
 //    RT extends TitanRelationship.Type<S,ST, R,RT, N,NT,G>
	// > 
 //  R addIn(RT relType, S from) {

	// 	TitanEdge rawEdge = from.addEdge(relType.label(), this.raw);

	// 	return relType.fromTitanEdge(rawEdge);
	// }

	// /*
	// 	For when you don't know anything about the arity
	// */
	// public <
 //    //rel
 //    R extends TitanRelationship<N,NT, R,RT, T,TT, G>, 
 //    RT extends TitanRelationship.Type<N,NT,R,RT,T,TT,G>,
 //    // target node
 //    T extends TitanNode<T,TT,G>, 
 //    TT extends TitanNode.Type<T,TT,G>
 //  > 
 //  List<R> out(RT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.OUT, 
	// 		relType.label()
	// 	);

	// 	List<R> list = new LinkedList<>();
	// 	Iterator<TitanEdge> iterator = tEdges.iterator();
	// 	while (iterator.hasNext()) {
	// 		list.add(relType.fromTitanEdge(iterator.next()));
	// 	}

	// 	return list;
	// }

	// public <
	// 	// source node
 //    S extends TitanNode<S,ST,G>, 
 //    ST extends TitanNode.Type<S,ST,G>,
 //    //rel
 //    R extends TitanRelationship<S,ST, R,RT, N,NT,G>, 
 //    RT extends TitanRelationship.Type<S,ST, R,RT, N,NT,G>
	// > 
 //  List<R> in(RT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.IN, 
	// 		relType.label()
	// 	);

	// 	List<R> list = new LinkedList<>();
	// 	Iterator<TitanEdge> iterator = tEdges.iterator();
	// 	while (iterator.hasNext()) {
	// 		list.add(relType.fromTitanEdge(iterator.next()));
	// 	}

	// 	return list;
	// }

 //  public <
 //    //rel; bound to be ToOne
 //    R extends TitanRelationship<N,NT, R,RT, T,TT, G>, 
 //    RT extends TitanRelationship.Type<N,NT,R,RT,T,TT,G> & Relationship.Type.ToOne<N,NT, R,RT, T,TT, G>,
 //    // target node
 //    T extends TitanNode<T,TT,G>, 
 //    TT extends TitanNode.Type<T,TT,G>
 //  > 
	// R outToOne(RT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.OUT,
	// 		relType.label()
	// 	);

	// 	return relType.fromTitanEdge(tEdges.iterator().next());
	// }

 //  public <
 //    //rel; bound to be ToOne
 //    R extends TitanRelationship<N,NT, R,RT, T,TT, G>, 
 //    RT extends TitanRelationship.Type<N,NT,R,RT,T,TT,G> & Relationship.Type.ToOne<N,NT, R,RT, T,TT, G>,
 //    // target node
 //    T extends TitanNode<T,TT,G>, 
 //    TT extends TitanNode.Type<T,TT,G>
 //  > 
 //  T outToOneNode(RT relType) {

 //    Iterable<TitanEdge> tEdges = this.getTitanEdges(
 //      com.tinkerpop.blueprints.Direction.OUT,
 //      relType.label()
 //    );

 //    return relType.fromTitanEdge(tEdges.iterator().next()).target();
 //  }

	// public <
 //    //rel; bound to be ToOne
 //    R extends TitanRelationship<N,NT, R,RT, T,TT, G>, 
 //    RT extends TitanRelationship.Type<N,NT,R,RT,T,TT,G> & Relationship.Type.ToMany<N,NT, R,RT, T,TT, G>,
 //    // target node
 //    T extends TitanNode<T,TT,G>, 
 //    TT extends TitanNode.Type<T,TT,G>
 //  >
	// List<R> outToMany(RT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.OUT, 
	// 		relType.label()
	// 	);

	// 	List<R> list = new LinkedList<>();
	// 	Iterator<TitanEdge> iterator = tEdges.iterator();
	// 	while (iterator.hasNext()) {
	// 		list.add(relType.fromTitanEdge(iterator.next()));
	// 	}

	// 	return list;
	// }

 //  public <
 //    //rel; bound to be ToOne
 //    R extends TitanRelationship<N,NT, R,RT, T,TT, G>, 
 //    RT extends TitanRelationship.Type<N,NT,R,RT,T,TT,G> & Relationship.Type.ToMany<N,NT, R,RT, T,TT, G>,
 //    // target node
 //    T extends TitanNode<T,TT,G>, 
 //    TT extends TitanNode.Type<T,TT,G>
 //  >
 //  List<T> outToManyNodes(RT relType) {

 //    Iterable<TitanEdge> tEdges = this.getTitanEdges(
 //      com.tinkerpop.blueprints.Direction.OUT, 
 //      relType.label()
 //    );

 //    List<T> list = new LinkedList<>();
 //    Iterator<TitanEdge> iterator = tEdges.iterator();
 //    while (iterator.hasNext()) {
 //      list.add(relType.fromTitanEdge(iterator.next()).target());
 //    }

 //    return list;
 //  }

	// public <
 //    // source node
 //    S extends TitanNode<S,ST,G>, 
 //    ST extends TitanNode.Type<S,ST,G>,
 //    //rel; bound to be FromOne
 //    R extends TitanRelationship<S,ST, R,RT, N,NT,G>, 
 //    RT extends TitanRelationship.Type<S,ST, R,RT, N,NT,G> & Relationship.Type.FromOne<S,ST, R,RT, N,NT,G>
 //  >
 //  R inFromOne(RT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.IN,
	// 		relType.label()
	// 	);

	// 	return relType.fromTitanEdge(tEdges.iterator().next());
	// }

 //  public <
 //    // source node
 //    S extends TitanNode<S,ST,G>, 
 //    ST extends TitanNode.Type<S,ST,G>,
 //    //rel; bound to be FromOne
 //    R extends TitanRelationship<S,ST, R,RT, N,NT,G>, 
 //    RT extends TitanRelationship.Type<S,ST, R,RT, N,NT,G> & Relationship.Type.FromOne<S,ST, R,RT, N,NT,G>
 //  >
 //  S inFromOneNode(RT relType) {

 //    Iterable<TitanEdge> tEdges = this.getTitanEdges(
 //      com.tinkerpop.blueprints.Direction.IN,
 //      relType.label()
 //    );

 //    return relType.fromTitanEdge(tEdges.iterator().next()).source();
 //  }

	// public <
 //    // source node
 //    S extends TitanNode<S,ST,G>, 
 //    ST extends TitanNode.Type<S,ST,G>,
 //    //rel; bound to be FromMany
 //    R extends TitanRelationship<S,ST, R,RT, N,NT,G>, 
 //    RT extends TitanRelationship.Type<S,ST, R,RT, N,NT,G> & Relationship.Type.FromMany<S,ST, R,RT, N,NT,G>
 //  >
	// List<R> inFromMany(RT relType) {

	// 	Iterable<TitanEdge> tEdges = this.getTitanEdges(
	// 		com.tinkerpop.blueprints.Direction.IN, 
	// 		relType.label()
	// 	);

	// 	List<R> list = new LinkedList<>();

	// 	Iterator<TitanEdge> iterator = tEdges.iterator();

	// 	while (iterator.hasNext()) {
	// 		// build from raw
	// 		list.add(relType.fromTitanEdge(iterator.next()));
	// 	}

	// 	return list;
	// }

 //  public <
 //    // source node
 //    S extends TitanNode<S,ST,G>, 
 //    ST extends TitanNode.Type<S,ST,G>,
 //    //rel; bound to be FromMany
 //    R extends TitanRelationship<S,ST, R,RT, N,NT,G>, 
 //    RT extends TitanRelationship.Type<S,ST, R,RT, N,NT,G> & Relationship.Type.FromMany<S,ST, R,RT, N,NT,G>
 //  >
 //  List<S> inFromManyNodes(RT relType) {

 //    Iterable<TitanEdge> tEdges = this.getTitanEdges(
 //      com.tinkerpop.blueprints.Direction.IN, 
 //      relType.label()
 //    );

 //    List<S> list = new LinkedList<>();

 //    Iterator<TitanEdge> iterator = tEdges.iterator();

 //    while (iterator.hasNext()) {
 //      // build from raw
 //      list.add(relType.fromTitanEdge(iterator.next()).source());
 //    }

 //    return list;
 //  }


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
		return raw.addEdge(label, vertex);
	}
	@Override public TitanEdge addEdge(String label, TitanVertex vertex){	
		return raw.addEdge(label, vertex);
	}
	@Override public com.thinkaurelius.titan.core.TitanProperty addProperty(TitanKey key, Object attribute){	
		return raw.addProperty(key, attribute);
	}
	@Override public com.thinkaurelius.titan.core.TitanProperty addProperty(String key, Object attribute){	
		return raw.addProperty(key, attribute);
	}
	@Override public TitanVertexQuery query(){	
    return raw.query();
  }
	@Override public Iterable<TitanEdge> getTitanEdges(Direction d, TitanLabel... labels){	
    return raw.getTitanEdges(d, labels);
  }
	@Override public Iterable<TitanRelation> getRelations(){
  	return raw.getRelations();
  }
	@Override public long getEdgeCount(){
  	return raw.getEdgeCount();
  }
	@Override public long getPropertyCount(){
  	return raw.getPropertyCount();
  }
	@Override public boolean isConnected(){
  	return raw.isConnected();
  }
	@Override public Iterable<TitanEdge> getEdges() {
   return raw.getEdges(); 
  }
	@Override public Iterable<Edge> getEdges(Direction d, String... labels){
  	return raw.getEdges(d, labels);
  }
	@Override public Edge addEdge(String arg0, Vertex arg1) {
  	return raw.addEdge(arg0, arg1);	
  }
	@Override public Iterable<Vertex> getVertices(Direction arg0, String... arg1) {		
    return raw.getVertices(arg0, arg1);	
  }
}