package com.bio4j.angulillos;

/*
  ## Edges

  A typed edge with explicit source and target.

  - `S` the source TypedVertex, `ST` the source TypedVertex type
  - `R` the edge, `RT` the edge type
  - `T` the target TypedVertex, `TT` the target TypedVertex type
*/
interface TypedEdge <
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

  /* `raw` gives you the raw edge underlying this instance; see [untyped graph](UntypedGraph.java.md) */
  @Override
  RE raw();

  /* the source vertex of this edge */
  default S source() { return graph().source( self() ); }

  /* the target vertex of this edge */
  default T target() { return graph().target( self() ); }

  @Override
  R self();

  @Override
  default <
    P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>,
    V
  >
  V get(P property) { return graph().getProperty(self(), property); }

  @Override
  default <
    P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>,
    V
  >
  R set(P property, V value) {

    graph().setProperty(self(), property, value);
    return self();
  }


  interface HasArity {

    /* the arity for this edge. This corresponds to the edge between the two vertex types. */
    Type.Arity arity();
  }

  interface Type <
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
  > extends
    TypedElement.Type<R,RT,RG,I,RV,RVT,RE,RET>,
    HasArity
  {
    @Override
    RET raw();

    ST sourceType();
    TT targetType();

    /* Constructs a value of the typed edge of this type */
    R edge(RE rawEdge);

    /*
      ### Arities

      We have six basic arities: three for in, three for out.
    */
    public enum Arity {

      oneToOne,
      oneToAtMostOne,
      oneToAtLeastOne,
      oneToAny,

      atMostOneToOne,
      atMostOneToAtMostOne,
      atMostOneToAtLeastOne,
      atMostOneToAny,

      atLeastOneToOne,
      atLeastOneToAtMostOne,
      atLeastOneToAtLeastOne,
      atLeastOneToAny,

      anyToOne,
      anyToAtMostOne,
      anyToAtLeastOne,
      anyToAny;
    }

    /* #### In-arities */

    /* An edge type `e` being _surjective_ implies that calling `inV(e)` will always return some, possibly several, vertices */
    interface FromAtLeastOne extends HasArity {}
    /* An edge type `e` being _from many_ implies that calling `inV(e)` will in general return more than one vertex */
    interface FromOne extends HasArity {}
    /* An edge type `e` being _from one_ implies that calling `inV(e)` will return at most one vertex */
    interface FromAtMostOne extends HasArity {}

    /* #### Out-arities */

    /* That an edge type `e` being _always defined_ implies that calling `outV(e)` will always return some, possibly several, vertices */
    interface ToAtLeastOne extends HasArity {}
    /* An edge type `e` being _to many_ implies that calling `outV(e)` will in general return more than one vertex */
    interface ToOne extends HasArity {}
    /* An edge type `e` being _to one_ implies that calling `outV(e)` will return at most one vertex */
    interface ToAtMostOne extends HasArity {}


    /*
      #### Arity combinations

      These are all the possible combinations of the different arities. In the first line under `extends` you see those that correspond to `in`, and in the second one those that correspond to `out`
    */
    interface OneToOne        extends FromOne, ToOne        { default Arity arity() { return Arity.oneToOne; } }
    interface OneToAtMostOne  extends FromOne, ToAtMostOne  { default Arity arity() { return Arity.oneToAtMostOne; } }
    interface OneToAtLeastOne extends FromOne, ToAtLeastOne { default Arity arity() { return Arity.oneToAtLeastOne; } }
    interface OneToAny        extends FromOne               { default Arity arity() { return Arity.oneToAny; } }

    interface AtMostOneToOne        extends FromAtMostOne, ToOne        { default Arity arity() { return Arity.atMostOneToOne; } }
    interface AtMostOneToAtMostOne  extends FromAtMostOne, ToAtMostOne  { default Arity arity() { return Arity.atMostOneToAtMostOne; } }
    interface AtMostOneToAtLeastOne extends FromAtMostOne, ToAtLeastOne { default Arity arity() { return Arity.atMostOneToAtLeastOne; } }
    interface AtMostOneToAny        extends FromAtMostOne               { default Arity arity() { return Arity.atMostOneToAny; } }

    interface AtLeastOneToOne        extends FromAtLeastOne, ToOne        { default Arity arity() { return Arity.atLeastOneToOne; } }
    interface AtLeastOneToAtMostOne  extends FromAtLeastOne, ToAtMostOne  { default Arity arity() { return Arity.atLeastOneToAtMostOne; } }
    interface AtLeastOneToAtLeastOne extends FromAtLeastOne, ToAtLeastOne { default Arity arity() { return Arity.atLeastOneToAtLeastOne; } }
    interface AtLeastOneToAny        extends FromAtLeastOne               { default Arity arity() { return Arity.atLeastOneToAny; } }

    interface AnyToOne        extends ToOne        { default Arity arity() { return Arity.anyToOne; } }
    interface AnyToAtMostOne  extends ToAtMostOne  { default Arity arity() { return Arity.anyToAtMostOne; } }
    interface AnyToAtLeastOne extends ToAtLeastOne { default Arity arity() { return Arity.anyToAtLeastOne; } }
    interface AnyToAny        extends HasArity     { default Arity arity() { return Arity.anyToAny; } }

  }
}
