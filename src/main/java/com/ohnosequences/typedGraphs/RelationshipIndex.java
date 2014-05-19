package com.ohnosequences.typedGraphs;

public interface RelationshipIndex <
  S extends Node<S,ST>,
  ST extends Node.Type<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Relationship.Type<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>,
  TT extends Node.Type<T,TT>,
  P extends Property<R,RT,P,V>, V
>
{

  interface Unique <
    S extends Node<S,ST>,
    ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  > 
    extends RelationshipIndex<S,ST,R,RT,T,TT,P,V> 
  {

    /*
    get a node by providing a value of the indexed property.
    */
    public R getRelationship(V byValue);
  }

  interface List <
    S extends Node<S,ST>,
    ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Node.Type<T,TT>,
    P extends Property<R,RT,P,V>, V
  > 
    extends RelationshipIndex<S,ST,R,RT,T,TT,P,V> 
  {

    /*
    get a list of nodes by providing a value of the indexed property.
    */
    public java.util.List<? extends R> getRelationships(V byValue);
  }


}
