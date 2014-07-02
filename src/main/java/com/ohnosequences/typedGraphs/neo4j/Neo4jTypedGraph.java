package com.ohnosequences.typedGraphs.neo4j;

import com.ohnosequences.typedGraphs.*;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.schema.ConstraintCreator;
import org.neo4j.graphdb.schema.ConstraintDefinition;

public interface Neo4jTypedGraph extends TypedGraph {

  GraphDatabaseService rawGraph();

  default <
    // the element type
    N extends Neo4jNode<N,NT>, NT extends Neo4jNode.Type<N,NT>,
    // the property (of that element)
    P extends Neo4jProperty<N,NT,P,V>,
    // the value type of this property
    V
  > ConstraintCreator uniqueConstraintFor(P property) {

    return rawGraph().schema().constraintFor(property.elementType())
      .assertPropertyIsUnique(property.name());
  }

  default <
    // the element type
    N extends Neo4jNode<N,NT>, NT extends Neo4jNode.Type<N,NT>,
    // the property (of that element)
    P extends Neo4jProperty<N,NT,P,V>,
    // the value type of this property
    V
  > ConstraintDefinition createOrGetUniqueConstraintFor(P property) {

    ConstraintDefinition maybe = rawGraph().schema().getConstraints(property.elementType()).iterator().next();

    if ( maybe == null ) {

      return uniqueConstraintFor(property).create();
    }
    else {

      return maybe;
    }
  }
}


