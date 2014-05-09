package com.ohnosequences.typedGraphs;

/*
  A typed relationship with typed source and target. 

  - `S` the source Node
  - `ST` the source Node type
  - `R` the relationship
  - `RT` the relationship type
  - `T` the target Node
  - `TT` the target Node type

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Relationship <
  S extends Node<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>
> extends Element<R,RT> 
{

  /*
    source node
  */
  public Node<S,ST> source();
  /*
    target node
  */
  public Node<T,TT> target();

  @Override
  public RelationshipType<S,ST, R,RT, T,TT> type();
}
