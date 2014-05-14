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
    the arity for this relationship. This corresponds to the relationship between the two node types. Relationships are by default `manyToMany`
  */
  public default Arity arity() { return Arity.manyToMany; }

  @Override public RelationshipType<S,ST,R,RT,T,TT> value();

  public static enum Arity {

    // TODO: explain this
    oneToOne, 
    oneToMany, 
    manyToOne,
    manyToMany;
  }

  public ST sourceType();
  public TT targetType();

  // Bounds over targets
  interface ToMany <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  > extends RelationshipType<S,ST, R,RT, T,TT> 
  {}

  interface ToOne <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  > extends RelationshipType<S,ST, R,RT, T,TT> 
  {}

  // Bounds over sources
  interface FromMany <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  > extends RelationshipType<S,ST, R,RT, T,TT> 
  {}

  interface FromOne <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  > extends RelationshipType<S,ST, R,RT, T,TT> 
  {}


  // all the possible cases, just for convenience
  interface OneToMany <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  > extends FromOne<S,ST, R,RT, T,TT>, ToMany<S,ST, R,RT, T,TT> 
  {

    public default Arity arity() { return Arity.oneToMany; }
  }

  interface OneToOne <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  > extends FromOne<S,ST, R,RT, T,TT>, ToOne<S,ST, R,RT, T,TT> 
  {

    public default Arity arity() { return Arity.oneToOne; }
  }

  interface ManyToOne <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  > extends FromMany<S,ST, R,RT, T,TT>, ToOne<S,ST, R,RT, T,TT> 
  {

    public default Arity arity() { return Arity.manyToOne; }
  }

  interface ManyToMany <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  > extends FromMany<S,ST, R,RT, T,TT>, ToMany<S,ST, R,RT, T,TT> 
  {

    public default Arity arity() { return Arity.manyToMany; }
  }

}