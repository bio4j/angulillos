package com.ohnosequences.typedGraphs;

import java.util.List;
import java.util.Optional;

/*
This class is just a convenience for retrieving node/s given an index.
*/
public class Retriever {

  public Retriever() {};
  /*
  Given a unique index and a value of the indexed property, get the node.
  */
  public <
    N extends Node<N,NT>,NT extends Enum<NT> & Node.Type<N,NT>,
    P extends Property<N,NT,P,V>,V
  > 
  Optional<N> getNodeFrom(NodeIndex.Unique<N,NT,P,V> index, V value) { 

    return index.getNode(value); 
  }

  /*
  Given a list index and a value of the indexed property, get the nodes.
  */
  public <
    N extends Node<N,NT>,NT extends Enum<NT> & Node.Type<N,NT>,
    P extends Property<N,NT,P,V>,V
  > 
  Optional<List<N>> getNodesFrom(NodeIndex.List<N,NT,P,V> index, V value) { 

    return index.getNodes(value); 
  }

  public <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  > 
  Optional<List<R>> getRelationshipsFrom(RelationshipIndex.List<S,ST,R,RT,T,TT,P,V>index, V value) { 

    return index.getRelationships(value); 
  }

  public <
    S extends Node<S,ST>,ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>,V
  > 
  Optional<R> getRelationshipFrom(RelationshipIndex.Unique<S,ST,R,RT,T,TT,P,V> index, V value) { 

    return index.getRelationship(value); 
  }
}