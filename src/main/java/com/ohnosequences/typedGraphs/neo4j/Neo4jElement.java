package com.ohnosequences.typedGraphs.neo4j;

import com.ohnosequences.typedGraphs.*;
import org.neo4j.graphdb.*;

public interface Neo4jElement <
  E extends Neo4jElement<E,ET>, 
  ET extends Neo4jElement.Type<E,ET>
>
extends
  Element<E,ET>,
  PropertyContainer  
{

  public PropertyContainer raw();

  // use get for implementing all the property-name() methods
  @Override public default <P extends Property<E,ET,P,V>, V> V get(P p) {

    // no generic get property method in Neo4j, casting needed
    return (V) raw().getProperty(p.fullName());
  }

  public default <P extends Property<E,ET,P,V>, V> void set(P p, V value) {

    raw().setProperty(p.fullName(), value);
  }

  public static interface Type <
    E extends Neo4jElement<E,ET>,
    ET extends Neo4jElement.Type<E,ET>
  >
  extends
    Element.Type<E,ET> 
  {}


  // fwd neo4j methods to raw
  @Override public default Object getProperty(String key) { 

    return raw().getProperty(key);
  }

  @Override public default GraphDatabaseService getGraphDatabase() { 

    return raw().getGraphDatabase();
  }

  @Override public default Object getProperty(String key, Object defaultValue) {

    return raw().getProperty(key, defaultValue);
  }

  @Override public default Iterable<String> getPropertyKeys() {  

    return raw().getPropertyKeys(); 
  }

  @Override public default boolean hasProperty(String key) {

    return raw().hasProperty(key);
  }

  @Override public default Object removeProperty(String arg0) {  

    return raw().removeProperty(arg0);  
  }

  @Override public default void setProperty(String key, Object value) {

    raw().setProperty(key, value);
  }
}