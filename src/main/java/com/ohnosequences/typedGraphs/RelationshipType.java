package com.ohnosequences.typedGraphs;

/*
  A Relationship type. Implementing **concrete** classes should be singleton `Enum`s.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface RelationshipType <
  S extends Node<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>
> extends ElementType<R,RT> 
{

  /*
    the arity for this relationship. This corresponds to the relationship between the two node types (as a distributor/span essentially).
    Relationships are by default `manyToMany`
  */
  public default Arity arity() { return Arity.manyToMany; }

  @Override
  public RelationshipType<S,ST,R,RT,T,TT> value();

  public static enum Arity {

    // TODO: explain this
    oneToOne, 
    oneToMany, 
    manyToOne, 
    manyToMany;
  }

  public ST sourceType();
  public TT targetType();

}