package com.ohnosequences.typedGraphs;

/*
  A typed relationship with typed source and target. 

  - `S` the source Node, `ST` the source Node type
  - `R` the relationship, `RT` the relationship type
  - `T` the target Node, `TT` the target Node type

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Relationship <
  // src
  S extends Node<S,ST,SG,I,RV,RVT,RE,RET>, 
  ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
  SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
  //  rel
  R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
  RT extends Relationship.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
  RG extends TypedGraph<RG,I,RV,RVT,RE,RET>, 
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
  // tgt
  T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
  TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
  TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
> 
  extends Element<R,RT,RG,I,RV,RVT,RE,RET> 
{

  RV raw();
  /*
    the source node of this relationship
  */
  S source();
  /*
    the target node of this relationship
  */
  T target();

  interface Type <
    S extends Node<S,ST,SG,I,RV,RVT,RE,RET>, ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, RT extends Relationship.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>, I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
    T extends Node<T,TT,TG,I,RV,RVT,RE,RET>, TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
    extends Element.Type<R,RT,RG,I,RV,RVT,RE,RET> 
  {

    /*
      the arity for this relationship. This corresponds to the relationship between the two node types. Relationships are by default `manyToMany`
    */
    default Arity arity() { return Arity.manyToMany; }

    enum Arity {

      // TODO: explain this
      oneToOne, 
      oneToMany, 
      manyToOne,
      manyToMany;
    }

    ST sourceType();
    TT targetType();

    R from(RE edge);

    RET raw();


    //////////////////////////////////////////////////////////////

    // // Bounds over targets
    // interface ToMany <
    //   S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>, SG extends TypedGraph,
    //   R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type.ToMany<S,ST,SG,R,RT,RG,T,TT,TG>, RG extends TypedGraph,
    //   T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>, TG extends TypedGraph
    // > 
    //   extends Relationship.Type<S,ST,SG, R,RT,RG, T,TT,TG> 
    // {}

    // interface ToOne <
    //   S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>, SG extends TypedGraph,
    //   R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type.ToOne<S,ST,SG,R,RT,RG,T,TT,TG>, RG extends TypedGraph,
    //   T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>, TG extends TypedGraph
    // >
    //   extends Relationship.Type<S,ST,SG, R,RT,RG, T,TT,TG> 
    // {}

    // // Bounds over sources
    // interface FromMany <
    //   S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>, SG extends TypedGraph,
    //   R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type.FromMany<S,ST,SG,R,RT,RG,T,TT,TG>, RG extends TypedGraph,
    //   T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>, TG extends TypedGraph
    // >
    //   extends Relationship.Type<S,ST,SG, R,RT,RG, T,TT,TG> 
    // {}

    // interface FromOne <
    //   S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>, SG extends TypedGraph,
    //   R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type.FromOne<S,ST,SG,R,RT,RG,T,TT,TG>, RG extends TypedGraph,
    //   T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>, TG extends TypedGraph
    // > 
    //   extends Relationship.Type<S,ST,SG, R,RT,RG, T,TT,TG> 
    // {}

    // all the possible cases, just for convenience
    // interface OneToMany <
    //   S extends Node<S,ST,SG>,
    //   ST extends Node.Type<S,ST,SG>,
    //   R extends Relationship<S,ST,R,RT,T,TT,G>, 
    //   RT extends Relationship.Type.FromOne<S,ST,R,RT,T,TT,G> & Relationship.Type.ToMany<S,ST,R,RT,T,TT,G>,
    //   T extends Node<T,TT,G>,
    //   TT extends Node.Type<T,TT,G>,
    //   G extends TypedGraph
    // > 
    //   extends FromOne<S,ST,SG, R,RT,RG, T,TT,TG>, ToMany<S,ST,SG, R,RT,RG, T,TT,TG> 
    // {

    //   public default Arity arity() { return Arity.oneToMany; }
    // }

    // interface OneToOne <
    //   S extends Node<S,ST,SG>,
    //   ST extends Node.Type<S,ST,SG>,
    //   R extends Relationship<S,ST,R,RT,T,TT,G>, 
    //   RT extends Relationship.Type.FromOne<S,ST,R,RT,T,TT,G> & Relationship.Type.ToOne<S,ST,R,RT,T,TT,G>,
    //   T extends Node<T,TT,G>,
    //   TT extends Node.Type<T,TT,G>,
    //   G extends TypedGraph
    // > 
    //   extends FromOne<S,ST,SG, R,RT,RG, T,TT,TG>, ToOne<S,ST,SG, R,RT,RG, T,TT,TG> 
    // {

    //   public default Arity arity() { return Arity.oneToOne; }
    // }

    // interface ManyToOne <
    //   S extends Node<S,ST,SG>,
    //   ST extends Node.Type<S,ST,SG>,
    //   R extends Relationship<S,ST,R,RT,T,TT,G>, 
    //   RT extends Relationship.Type.FromMany<S,ST,R,RT,T,TT,G> & Relationship.Type.ToOne<S,ST,R,RT,T,TT,G>,
    //   T extends Node<T,TT,G>,
    //   TT extends Node.Type<T,TT,G>,
    //   G extends TypedGraph
    // > 
    //   extends FromMany<S,ST,SG, R,RT,RG, T,TT,TG>, ToOne<S,ST,SG, R,RT,RG, T,TT,TG> 
    // {

    //   public default Arity arity() { return Arity.manyToOne; }
    // }

    // interface ManyToMany <
    //   S extends Node<S,ST,SG>,
    //   ST extends Node.Type<S,ST,SG>,
    //   R extends Relationship<S,ST,R,RT,T,TT,G>, 
    //   RT extends Relationship.Type.FromMany<S,ST,R,RT,T,TT,G> & Relationship.Type.ToMany<S,ST,R,RT,T,TT,G>,
    //   T extends Node<T,TT,G>,
    //   TT extends Node.Type<T,TT,G>,
    //   G extends TypedGraph
    // > 
    //   extends FromMany<S,ST,SG, R,RT,RG, T,TT,TG>, ToMany<S,ST,SG, R,RT,RG, T,TT,TG> 
    // {

    //   public default Arity arity() { return Arity.manyToMany; }
    // }

  }
}