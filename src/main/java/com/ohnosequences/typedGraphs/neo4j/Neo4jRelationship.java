package com.ohnosequences.typedGraphs.neo4j;

import com.ohnosequences.typedGraphs.*;


public abstract class Neo4jRelationship <
  S extends Neo4jNode<S,ST>, ST extends Neo4jNode.Type<S,ST>,
  R extends Neo4jRelationship<S,ST,R,RT,T,TT>, RT extends Neo4jRelationship.Type<S,ST,R,RT,T,TT>,
  T extends Neo4jNode<T,TT>, TT extends Neo4jNode.Type<T,TT>
> 
implements
  Neo4jElement<R,RT>,
  Relationship<S,ST, R,RT, T,TT>,
  org.neo4j.graphdb.Relationship
{

  // Neo4j nodes
  protected Neo4jRelationship(org.neo4j.graphdb.Relationship raw) {

    this.raw = raw;
  }

  protected org.neo4j.graphdb.Relationship raw;

  @Override public org.neo4j.graphdb.Relationship raw() { 

    return this.raw; 
  }

  @Override public S source() { 

    return type().sourceType().from(raw().getStartNode());
  }

  @Override public T target() { 

    return type().targetType().from(raw().getEndNode());
  }

  public static interface Type <
    S extends Neo4jNode<S,ST>, ST extends Neo4jNode.Type<S,ST>,
    R extends Neo4jRelationship<S,ST,R,RT,T,TT>, RT extends Neo4jRelationship.Type<S,ST,R,RT,T,TT>,
    T extends Neo4jNode<T,TT>, TT extends Neo4jNode.Type<T,TT>
  >
  extends
    Neo4jElement.Type<R,RT>,  
    Relationship.Type<S,ST,R,RT,T,TT>,
    org.neo4j.graphdb.RelationshipType
  {

    @Override public default String name() { return name(); }

    public R fromNeo4jRelationship(org.neo4j.graphdb.Relationship rel);

    @Override public default R from(Object rel) {

      if (rel instanceof org.neo4j.graphdb.Relationship) {

        org.neo4j.graphdb.Relationship uhoh = (org.neo4j.graphdb.Relationship) rel;

        return fromNeo4jRelationship(uhoh);
      } 

      else {

        throw new IllegalArgumentException("rel should be a Neo4j Relationship");
      }
    }
  }

  @Override public long getId() {

    return raw().getId();
  }

  @Override public void delete() {

    raw().delete();
  }

  @Override public org.neo4j.graphdb.Node getStartNode() {

    return raw().getStartNode();
  }

  @Override public org.neo4j.graphdb.Node getEndNode() {

    return raw().getEndNode();
  }

  @Override public org.neo4j.graphdb.Node getOtherNode( org.neo4j.graphdb.Node node ) {

    return raw().getOtherNode(node);
  }

  @Override public org.neo4j.graphdb.Node[] getNodes() {

    return raw().getNodes();
  }

  @Override public org.neo4j.graphdb.RelationshipType getType() {

    return raw().getType();
  }

  @Override public boolean isType( org.neo4j.graphdb.RelationshipType type ) {

    return raw().isType(type);
  }

}