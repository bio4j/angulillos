package com.bio4j.angulillos;

/*
  A typed edge with typed source and target. 

  - `S` the source TypedVertex, `ST` the source TypedVertex type
  - `R` the edge, `RT` the edge type
  - `T` the target TypedVertex, `TT` the target TypedVertex type

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/

public interface TypedEdge <
  // src
  S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
  ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
  SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
  // rel
  R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
  RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
  RG extends TypedGraph<RG,I,RV,RVT,RE,RET>, 
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
  // tgt
  T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
  TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
  TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
> 
  extends TypedElement<R,RT,RG,I,RV,RVT,RE,RET> 
{

  @Override
  RE raw();
  /*
    the source vertex of this edge
  */
  default S source() {

    return graph().source( self() );
  }
  /*
    the target vertex of this edge
  */
  default T target() {

    return graph().target( self() );
  }

  @Override
  R self();

  @Override
  default <
    P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>, 
    V
  > 
  V get(P property) {

    return graph().getProperty(self(), property);
  }

  @Override
  default <
    P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>, 
    V
  > 
  void set(P property, V value) {

    graph().setProperty(self(), property, value);
  }


  public interface HasArity { 

    Type.Arity arity();
  }
  
  public interface Type <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  extends 
    TypedElement.Type<R,RT,RG,I,RV,RVT,RE,RET>,
    HasArity
  {

    /*
      the arity for this edge. This corresponds to the edge between the two vertex types. TypedEdges are by default `manyToMany`
    */
    
    ST sourceType();
    TT targetType();

    R from(RE edge);

    RET raw();

    /*
    We have for each side and its dual

    - **always defined / surjective** which determines whether the return type is wrapped in Optional
    - **from one / to one**
    - **from many / to many**

    for in/out we get then
    
    1. `Option[List[X]]`
    2. `Option[X]`
    3. `List[X]`
    4. `X`

    I think that for the names is easier to assume `Option` as default.


    */
    public enum Arity {

      oneToOne,
      oneToOneOptional,
      oneToMany,
      oneToManyOptional, 

      oneOptionalToOne,
      oneOptionalToOneOptional,
      oneOptionalToMany,
      oneOptionalToManyOptional, 
      
      manyToOne,
      manyToOneOptional,
      manyToMany,
      manyToManyOptional,

      manyOptionalToOne,
      manyOptionalToOneOptional,
      manyOptionalToMany,
      manyOptionalToManyOptional;
    }

    public interface AlwaysDefined extends HasArity {}
    public interface Surjective extends HasArity {}
    public interface ToMany extends HasArity {}
    public interface ToOne extends HasArity {}
    public interface FromOne extends HasArity {}
    public interface FromMany extends HasArity {}

    public interface OneToOneOptional extends FromOne, AlwaysDefined, ToOne { 
      
      default Arity arity() { return Arity.oneToOneOptional; } 
    }

    public interface  OneToMany extends FromOne, AlwaysDefined, ToMany, Surjective { 

      default Arity arity() { return Arity.oneToMany; } 
    }

    public interface  OneToManyOptional extends FromOne, AlwaysDefined, ToMany { 

      default Arity arity() { return Arity.oneToManyOptional; } 
    }

    public interface  OneOptionalToOne extends FromOne, ToOne, Surjective { 

      default Arity arity() { return Arity.oneOptionalToOne; } 
    }

    public interface  OneOptionalToOneOptional extends FromOne, ToOne { 

      default Arity arity() { return Arity.oneOptionalToOneOptional; } 
    }

    public interface  OneOptionalToMany extends FromOne, ToMany, Surjective { 

      default Arity arity() { return Arity.oneOptionalToMany; } 
    }

    public interface  OneOptionalToManyOptional extends FromOne, ToMany { 

      default Arity arity() { return Arity.oneOptionalToManyOptional; } 
    }

    
    public interface  ManyToOne extends FromMany, AlwaysDefined, ToOne, Surjective { 

      default Arity arity() { return Arity.manyToOne; } 
    }

    public interface  ManyToOneOptional extends FromMany, AlwaysDefined, ToOne { 

      default Arity arity() { return Arity.manyToOneOptional; } 
    }

    public interface  ManyToMany extends FromMany, AlwaysDefined, ToMany, Surjective { 

      default Arity arity() { return Arity.manyToMany; } 
    }

    public interface  ManyToManyOptional extends FromMany, AlwaysDefined, ToMany { 

      default Arity arity() { return Arity.manyToManyOptional; } 
    }


    public interface  ManyOptionalToOne extends FromMany, ToOne, Surjective { 

      default Arity arity() { return Arity.manyOptionalToOne; } 
    }

    public interface  ManyOptionalToOneOptional extends FromMany, ToOne { 

      default Arity arity() { return Arity.manyOptionalToOneOptional; } 
    }

    public interface  ManyOptionalToMany extends FromMany, ToMany, Surjective { 

      default Arity arity() { return Arity.manyOptionalToMany; } 
    }

    public interface  ManyOptionalToManyOptional extends FromMany, ToMany { 

      default Arity arity() { return Arity.manyOptionalToManyOptional; } 
    }
  }
}
