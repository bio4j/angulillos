package com.ohnosequences.typedGraphs;

public interface RelationshipIndex <
  S extends Node<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>,
  P extends Property<R,RT>, PT extends PropertyType<R,RT, P,PT, V>,
  V
>
{

  interface Unique <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT, P,PT, V>,
    V
  > extends RelationshipIndex<S,ST,R,RT,T,TT,P,PT,V> 
  {

    /*
    get a node by providing a value of the indexed property.
    */
    public Relationship<S,ST,R,RT,T,TT> getRelationship(V byValue);
  }

  interface List <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT, P,PT, V>,
    V
  > extends RelationshipIndex<S,ST,R,RT,T,TT,P,PT,V> 
  {

    /*
    get a list of nodes by providing a value of the indexed property.
    */
    public java.util.List<? extends Relationship<S,ST,R,RT,T,TT>> getRelationships(V byValue);
  }


}
