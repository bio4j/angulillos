package com.ohnosequences.typedGraphs.neo4j;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.ohnosequences.typedGraphs.*;

import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Direction;

public abstract class Neo4jNode <
  N extends Neo4jNode<N,NT>,
  NT extends Neo4jNode.Type<N,NT>
> 
implements
  Neo4jElement<N,NT>,
  Node<N,NT>, 
  org.neo4j.graphdb.Node
{

  // Neo4j nodes
  protected Neo4jNode(org.neo4j.graphdb.Node raw) {

    this.raw = raw;
  }

  protected org.neo4j.graphdb.Node raw;

  @Override public org.neo4j.graphdb.Node raw() { 

    return this.raw; 
  }  

  public <
    // source node
    S extends Neo4jNode<S,ST>, 
    ST extends Neo4jNode.Type<S,ST>,
    //rel; bound to be FromOne
    R extends Neo4jRelationship<S,ST, R,RT, N,NT>, 
    RT extends Neo4jRelationship.Type<S,ST,R,RT,N,NT>
  > 
  R addIn(RT relType, S from) {

    org.neo4j.graphdb.Relationship rawRel = from.createRelationshipTo(raw(), relType);

    return relType.fromNeo4jRelationship(rawRel);
  }

  /*
   * adds a rel with source this node; note that this method does not set any
   * properties.
   */
  public <
    // rel
    R extends Neo4jRelationship<N,NT, R,RT, T,TT>,
    RT extends Neo4jRelationship.Type<N,NT, R,RT, T,TT>,
    // target node
    T extends Neo4jNode<T,TT>,
    TT extends Neo4jNode.Type<T,TT>
  > 
  R addOut(RT relType, T to) {

    org.neo4j.graphdb.Relationship rawRel = raw().createRelationshipTo(to, relType);

    return relType.fromNeo4jRelationship(rawRel);
  }


  public <
    //rel; bound to be ToOne
    R extends Neo4jRelationship<N,NT, R,RT, T,TT>, 
    RT extends Neo4jRelationship.Type<N,NT,R,RT,T,TT> & Relationship.Type.ToOne<N,NT, R,RT, T,TT>,
    // target node
    T extends Neo4jNode<T,TT>, 
    TT extends Neo4jNode.Type<T,TT>
  > 
  R outToOne(RT relType) {

    Iterable<org.neo4j.graphdb.Relationship> tEdges = this.getRelationships(
      Direction.OUTGOING,
      relType
    );

    return relType.fromNeo4jRelationship(tEdges.iterator().next());
  }

  public <
    //rel; bound to be ToOne
    R extends Neo4jRelationship<N,NT, R,RT, T,TT>, 
    RT extends Neo4jRelationship.Type<N,NT,R,RT,T,TT> & Relationship.Type.ToOne<N,NT, R,RT, T,TT>,
    // target node
    T extends Neo4jNode<T,TT>, 
    TT extends Neo4jNode.Type<T,TT>
  > 
  T outToOneNode(RT relType) {

    Iterable<org.neo4j.graphdb.Relationship> tEdges = this.getRelationships(
      Direction.OUTGOING,
      relType
    );

    return relType.fromNeo4jRelationship(tEdges.iterator().next()).target();
  }

  public <
    //rel; bound to be ToOne
    R extends Neo4jRelationship<N,NT, R,RT, T,TT>, 
    RT extends Neo4jRelationship.Type<N,NT,R,RT,T,TT> & Relationship.Type.ToMany<N,NT, R,RT, T,TT>,
    // target node
    T extends Neo4jNode<T,TT>, 
    TT extends Neo4jNode.Type<T,TT>
  >
  List<R> outToMany(RT relType) {

    Iterable<org.neo4j.graphdb.Relationship> tEdges = this.getRelationships(
      Direction.OUTGOING, 
      relType
    );

    List<R> list = new LinkedList<>();
    Iterator<org.neo4j.graphdb.Relationship> iterator = tEdges.iterator();
    while (iterator.hasNext()) {
      list.add(relType.fromNeo4jRelationship(iterator.next()));
    }

    return list;
  }

  public <
    //rel; bound to be ToOne
    R extends Neo4jRelationship<N,NT, R,RT, T,TT>, 
    RT extends Neo4jRelationship.Type<N,NT,R,RT,T,TT> & Relationship.Type.ToMany<N,NT, R,RT, T,TT>,
    // target node
    T extends Neo4jNode<T,TT>, 
    TT extends Neo4jNode.Type<T,TT>
  >
  List<T> outToManyNodes(RT relType) {

    Iterable<org.neo4j.graphdb.Relationship> tEdges = this.getRelationships(
      Direction.OUTGOING, 
      relType
    );

    List<T> list = new LinkedList<>();
    Iterator<org.neo4j.graphdb.Relationship> iterator = tEdges.iterator();
    while (iterator.hasNext()) {
      list.add(relType.fromNeo4jRelationship(iterator.next()).target());
    }

    return list;
  }

  public <
    // source node
    S extends Neo4jNode<S,ST>, 
    ST extends Neo4jNode.Type<S,ST>,
    //rel; bound to be FromOne
    R extends Neo4jRelationship<S,ST, R,RT, N,NT>, 
    RT extends Neo4jRelationship.Type<S,ST,R,RT,N,NT> & Relationship.Type.FromOne<S,ST, R,RT, N,NT>
  >
  R inFromOne(RT relType) {

    Iterable<org.neo4j.graphdb.Relationship> tEdges = this.getRelationships(
      Direction.INCOMING,
      relType
    );

    return relType.fromNeo4jRelationship(tEdges.iterator().next());
  }

  public <
    // source node
    S extends Neo4jNode<S,ST>, 
    ST extends Neo4jNode.Type<S,ST>,
    //rel; bound to be FromOne
    R extends Neo4jRelationship<S,ST, R,RT, N,NT>, 
    RT extends Neo4jRelationship.Type<S,ST,R,RT,N,NT> & Relationship.Type.FromOne<S,ST, R,RT, N,NT>
  >
  S inFromOneNode(RT relType) {

    Iterable<org.neo4j.graphdb.Relationship> tEdges = this.getRelationships(
      Direction.INCOMING,
      relType
    );

    return relType.fromNeo4jRelationship(tEdges.iterator().next()).source();
  }

  public <
    // source node
    S extends Neo4jNode<S,ST>, 
    ST extends Neo4jNode.Type<S,ST>,
    //rel; bound to be FromMany
    R extends Neo4jRelationship<S,ST, R,RT, N,NT>, 
    RT extends Neo4jRelationship.Type<S,ST,R,RT,N,NT> & Relationship.Type.FromMany<S,ST, R,RT, N,NT>
  >
  List<R> inFromMany(RT relType) {

    Iterable<org.neo4j.graphdb.Relationship> tEdges = this.getRelationships(
      Direction.INCOMING, 
      relType
    );

    List<R> list = new LinkedList<>();

    Iterator<org.neo4j.graphdb.Relationship> iterator = tEdges.iterator();

    while (iterator.hasNext()) {
      // build from raw
      list.add(relType.fromNeo4jRelationship(iterator.next()));
    }

    return list;
  }

  public <
    // source node
    S extends Neo4jNode<S,ST>, 
    ST extends Neo4jNode.Type<S,ST>,
    //rel; bound to be FromMany
    R extends Neo4jRelationship<S,ST, R,RT, N,NT>, 
    RT extends Neo4jRelationship.Type<S,ST,R,RT,N,NT> & Relationship.Type.FromMany<S,ST, R,RT, N,NT>
  >
  List<S> inFromManyNodes(RT relType) {

    Iterable<org.neo4j.graphdb.Relationship> tEdges = this.getRelationships(
      Direction.INCOMING, 
      relType
    );

    List<S> list = new LinkedList<>();

    Iterator<org.neo4j.graphdb.Relationship> iterator = tEdges.iterator();

    while (iterator.hasNext()) {
      // build from raw
      list.add(relType.fromNeo4jRelationship(iterator.next()).source());
    }

    return list;
  }










  interface Type <
    N extends Neo4jNode<N,NT> & Node<N,NT>,
    NT extends Node.Type<N,NT> & Neo4jNode.Type<N,NT>
  > 
  extends
    Neo4jElement.Type<N,NT>,
    Node.Type<N,NT>,
    org.neo4j.graphdb.Label
  {

    @Override public default String name() { return name(); }

    /*
    A builder for Neo4j nodes of this type. This could be implemented generically _if_ you could easily instantiate generic types in Java. But you can't. Anyway, this should be almost always `return new Neo4jN(vertex);`
    */
    @Override public default N from(Object node) {

      if (node instanceof org.neo4j.graphdb.Node) {

        org.neo4j.graphdb.Node uhoh = (org.neo4j.graphdb.Node) node;

        return fromNeo4jNode(uhoh);
      } 

      else {

        throw new IllegalArgumentException("node should be a Neo4j node");
      }
    }

    public N fromNeo4jNode(org.neo4j.graphdb.Node node);
  }






  @Override public long getId() { 

    return raw().getId(); 
  }

  @Override public void delete() {

    raw().delete();    
  }

  @Override public Iterable<org.neo4j.graphdb.Relationship> getRelationships() {

    return raw().getRelationships();
  }

  @Override public boolean hasRelationship() {

    return raw().hasRelationship();
  }

  @Override public Iterable<org.neo4j.graphdb.Relationship> getRelationships( RelationshipType... types ) {

    return raw().getRelationships(types);
  }
    
  @Override public Iterable<org.neo4j.graphdb.Relationship> getRelationships( Direction direction, RelationshipType... types ) {

    return raw().getRelationships(direction, types);
  }

  @Override public boolean hasRelationship( RelationshipType... types ) {

    return raw().hasRelationship(types);
  }

  @Override public boolean hasRelationship( Direction direction, RelationshipType... types ) {

    return raw().hasRelationship(direction, types);
  }
  
  @Override public Iterable<org.neo4j.graphdb.Relationship> getRelationships( Direction dir ) {

    return raw().getRelationships(dir);
  }

  @Override public boolean hasRelationship( Direction dir ) {

    return raw().hasRelationship(dir);
  }

  @Override public Iterable<org.neo4j.graphdb.Relationship> getRelationships( RelationshipType type, Direction dir ) {

    return raw().getRelationships(type, dir);
  }

  @Override public boolean hasRelationship( RelationshipType type, Direction dir ) {

    return raw().hasRelationship(type, dir);
  }

  @Override public org.neo4j.graphdb.Relationship getSingleRelationship( RelationshipType type, Direction dir ) {

    return raw().getSingleRelationship(type, dir);
  }

  @Override public org.neo4j.graphdb.Relationship createRelationshipTo(org.neo4j.graphdb.Node otherNode, RelationshipType type ) {

    return raw().createRelationshipTo(otherNode, type);
  }
 
  @Override public Iterable<RelationshipType> getRelationshipTypes() {

    return raw().getRelationshipTypes();
  }

  @Override public int getDegree() {

    return raw().getDegree();
  }

  @Override public int getDegree( RelationshipType type ) {

    return raw().getDegree(type);
  }

  @Override public int getDegree( Direction direction ) {

    return raw().getDegree(direction);
  }

  @Override public int getDegree( RelationshipType type, Direction direction ) {

    return raw().getDegree(type, direction);
  }
}
