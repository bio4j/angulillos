package com.ohnosequences.typedGraphs;

/*
  A typed relationship with typed source and target. 

  - `S` the source Node, `ST` the source Node type
  - `R` the relationship, `RT` the relationship type
  - `T` the target Node, `TT` the target Node type

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Relationship <
  S extends Node<S,ST>, ST extends Node.Type<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>, TT extends Node.Type<T,TT>
> 
  extends Element<R,RT> 
{

  /*
    the source node of this relationship
  */
  public S source();
  /*
    the target node of this relationship
  */
  public T target();

  public interface Type <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>
  > 
    extends Element.Type<R,RT> 
  {

    /*
      the arity for this relationship. This corresponds to the relationship between the two node types. Relationships are by default `manyToMany`
    */
    public default Arity arity() { return Arity.manyToMany; }

    public static enum Arity {

      // TODO: explain this
      oneToOne, 
      oneToMany, 
      manyToOne,
      manyToMany;
    }

    public ST sourceType();
    public TT targetType();


    //////////////////////////////////////////////////////////////

    // Bounds over targets
    interface ToMany <
      S extends Node<S,ST>, ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type.ToMany<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>, TT extends Node.Type<T,TT>
    > 
      extends Relationship.Type<S,ST, R,RT, T,TT> 
    {}

    interface ToOne <
      S extends Node<S,ST>, ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type.ToOne<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>, TT extends Node.Type<T,TT>
    >
      extends Relationship.Type<S,ST, R,RT, T,TT> 
    {}

    // Bounds over sources
    interface FromMany <
      S extends Node<S,ST>, ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type.FromMany<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>, TT extends Node.Type<T,TT>
    > 
      extends Relationship.Type<S,ST, R,RT, T,TT> 
    {}

    interface FromOne <
      S extends Node<S,ST>, ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type.FromOne<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>, TT extends Node.Type<T,TT>
    > 
      extends Relationship.Type<S,ST, R,RT, T,TT> 
    {}


    // all the possible cases, just for convenience
    interface OneToMany <
      S extends Node<S,ST>,
      ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, 
      RT extends Relationship.Type.FromOne<S,ST,R,RT,T,TT> & Relationship.Type.ToMany<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>,
      TT extends Node.Type<T,TT>
    > 
      extends FromOne<S,ST, R,RT, T,TT>, ToMany<S,ST, R,RT, T,TT> 
    {

      public default Arity arity() { return Arity.oneToMany; }
    }

    interface OneToOne <
      S extends Node<S,ST>,
      ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, 
      RT extends Relationship.Type.FromOne<S,ST,R,RT,T,TT> & Relationship.Type.ToOne<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>,
      TT extends Node.Type<T,TT>
    > 
      extends FromOne<S,ST, R,RT, T,TT>, ToOne<S,ST, R,RT, T,TT> 
    {

      public default Arity arity() { return Arity.oneToOne; }
    }

    interface ManyToOne <
      S extends Node<S,ST>,
      ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, 
      RT extends Relationship.Type.FromMany<S,ST,R,RT,T,TT> & Relationship.Type.ToOne<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>,
      TT extends Node.Type<T,TT>
    > 
      extends FromMany<S,ST, R,RT, T,TT>, ToOne<S,ST, R,RT, T,TT> 
    {

      public default Arity arity() { return Arity.manyToOne; }
    }

    interface ManyToMany <
      S extends Node<S,ST>,
      ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, 
      RT extends Relationship.Type.FromMany<S,ST,R,RT,T,TT> & Relationship.Type.ToMany<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>,
      TT extends Node.Type<T,TT>
    > 
      extends FromMany<S,ST, R,RT, T,TT>, ToMany<S,ST, R,RT, T,TT> 
    {

      public default Arity arity() { return Arity.manyToMany; }
    }

  }
}