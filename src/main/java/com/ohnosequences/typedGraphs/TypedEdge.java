package com.ohnosequences.typedGraphs;

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

    return graph().source(self());
  }
  /*
    the target vertex of this edge
  */
  default T target() {

    return graph().target(self());
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
    extends TypedElement.Type<R,RT,RG,I,RV,RVT,RE,RET> 
  {

    /*
      the arity for this edge. This corresponds to the edge between the two vertex types. TypedEdges are by default `manyToMany`
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
      S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends TypedEdge.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    > 
    extends
      TypedEdge.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    public interface ToOne <
      // src
      S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends TypedEdge.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      TypedEdge.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    // Bounds over sources
    public interface FromMany <
      // src
      S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends TypedEdge.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      TypedEdge.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    public interface FromOne <
      // src
      S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends TypedEdge.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends 
      TypedEdge.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}


    // all possible combinations

    public interface OneToMany <
      // src
      S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        TypedEdge.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        TypedEdge.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        TypedEdge.Type.OneToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
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
      S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        TypedEdge.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        TypedEdge.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        TypedEdge.Type.OneToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
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
      S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        TypedEdge.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        TypedEdge.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        TypedEdge.Type.ManyToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
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
      S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        TypedEdge.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        TypedEdge.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        TypedEdge.Type.ManyToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
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