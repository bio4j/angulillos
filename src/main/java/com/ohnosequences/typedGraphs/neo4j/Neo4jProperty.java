package com.ohnosequences.typedGraphs.neo4j;

import com.ohnosequences.typedGraphs.Property;

public interface Neo4jProperty <
  // the element type
  N extends Neo4jElement<N,NT>, NT extends Neo4jElement.Type<N,NT>,
  // the property (of that element)
  P extends Neo4jProperty<N,NT,P,V>,
  // the value type of this property
  V
> 
  extends Property<N,NT,P,V>
{
  // nothing special needed here
}