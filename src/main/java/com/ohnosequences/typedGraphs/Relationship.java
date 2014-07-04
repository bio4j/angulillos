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
  // rel
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
    // src
    S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends Relationship.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
    // tgt
    T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
    extends Element.Type<R,RT,RG,I,RV,RVT,RE,RET> 
  {

    /*
      the arity for this relationship. This corresponds to the relationship between the two node types. Relationships are by default `manyToMany`
    */
    default Arity arity() { 

      return Arity.manyToMany;
    }

    public enum Arity {

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

    // Bounds over targets
    public interface ToMany <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends Relationship.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    > 
    extends
      Relationship.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    public interface ToOne <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends Relationship.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      Relationship.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    // Bounds over sources
    public interface FromMany <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends Relationship.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      Relationship.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    public interface FromOne <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends Relationship.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends 
      Relationship.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}


    // all possible combinations

    public interface OneToMany <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        Relationship.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.OneToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      FromOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>,
      ToMany<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {

      default Arity arity() {

        return Arity.oneToMany;
      }
    }

    public interface OneToOne <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        Relationship.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.OneToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      FromOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>,
      ToOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {

      default Arity arity() {

        return Arity.oneToOne;
      }
    }
    
    public interface ManyToMany <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        Relationship.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ManyToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      FromOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>,
      ToMany<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {

      default Arity arity() {

        return Arity.manyToMany;
      }
    }

    public interface ManyToOne <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        Relationship.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ManyToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      FromOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>,
      ToMany<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {

      default Arity arity() {

        return Arity.manyToOne;
      }
    }
  }
}