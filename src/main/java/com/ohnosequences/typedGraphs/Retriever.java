package com.ohnosequences.typedGraphs;

import java.util.List;

/*
This class is just a convenience for retrieving node/s given an index.
*/
public class Retriever {

  Retriever() {};
  /*
  Given a unique index and a value of the indexed property, get the node.
  */
  public <
    N extends Node<N,NT>,
    NT extends Enum<NT> & NodeType<N,NT>,
    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>,
    V
  > Node<N,NT> getNodeFrom(NodeIndex.Unique<N,NT,P,PT,V> index, V value) { 

    return index.getNode(value); 
  }

  /*
  Given a list index and a value of the indexed property, get the nodes.
  */
  public <
    N extends Node<N,NT>,
    NT extends Enum<NT> & NodeType<N,NT>,
    P extends Property<N,NT>,
    PT extends PropertyType<N,NT, P,PT, V>,
    V
  > List<? extends Node<N,NT>> getNodesFrom(NodeIndex.List<N,NT,P,PT,V> index, V value) { 

    return index.getNodes(value); 
  }

  public <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT, P,PT, V>,
    V
  > List<? extends Relationship<S,ST,R,RT,T,TT>> getRelationshipsFrom(RelationshipIndex.List<S,ST,R,RT,T,TT,P,PT,V>index, V value) { 

    return index.getRelationships(value); 
  }

  public <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT, P,PT, V>,
    V
  > Relationship<S,ST,R,RT,T,TT> getRelationshipFrom(RelationshipIndex.Unique<S,ST,R,RT,T,TT,P,PT,V> index, V value) { 

    return index.getRelationship(value); 
  }
}