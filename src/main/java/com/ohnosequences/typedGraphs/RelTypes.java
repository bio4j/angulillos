package com.ohnosequences.typedGraphs;

// TODO get rid of this, moved to the base interface
public interface RelTypes {

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

