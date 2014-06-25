package com.ohnosequences.typedGraphs.neo4j;

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




  public static interface Type <
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
