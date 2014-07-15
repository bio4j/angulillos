package com.ohnosequences.typedGraphs;

import java.util.Optional;

public interface RelationshipIndex <
  S extends Node<S,ST>,
  ST extends Node.Type<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Relationship.Type<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>,
  TT extends Node.Type<T,TT>,
  P extends Property<R,RT,P,V>, V
>
extends
  ElementIndex<R,RT,P,V>
{

  public static interface Unique <
    S extends Node<S,ST>,
    ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  > 
  extends 
    RelationshipIndex<S,ST,R,RT,T,TT,P,V>,
    ElementIndex.Unique<R,RT,P,V>
  {

    /* get a node by providing a value of the indexed property. */
    public default Optional<R> getRelationship(V byValue) { return getElement(byValue); }
  }

  public static interface List <
    S extends Node<S,ST>,
    ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  > 
  extends 
    RelationshipIndex<S,ST,R,RT,T,TT,P,V>,
    ElementIndex.List<R,RT,P,V>
  {

    /* get a list of nodes by providing a value of the indexed property. */
    public default Optional<java.util.List<R>> getRelationships(V byValue) { return getElements(byValue); }
  }


}
