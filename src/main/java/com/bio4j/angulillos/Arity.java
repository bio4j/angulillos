package com.bio4j.angulillos;

interface HasArity {

  /* the arity for this edge. This corresponds to the edge between the two vertex types. */
  Arity arity();
}

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
  public interface OneToOne        extends FromOne, ToOne        { default Arity arity() { return Arity.oneToOne; } }
  public interface OneToAtMostOne  extends FromOne, ToAtMostOne  { default Arity arity() { return Arity.oneToAtMostOne; } }
  public interface OneToAtLeastOne extends FromOne, ToAtLeastOne { default Arity arity() { return Arity.oneToAtLeastOne; } }
  public interface OneToAny        extends FromOne               { default Arity arity() { return Arity.oneToAny; } }

  public interface AtMostOneToOne        extends FromAtMostOne, ToOne        { default Arity arity() { return Arity.atMostOneToOne; } }
  public interface AtMostOneToAtMostOne  extends FromAtMostOne, ToAtMostOne  { default Arity arity() { return Arity.atMostOneToAtMostOne; } }
  public interface AtMostOneToAtLeastOne extends FromAtMostOne, ToAtLeastOne { default Arity arity() { return Arity.atMostOneToAtLeastOne; } }
  public interface AtMostOneToAny        extends FromAtMostOne               { default Arity arity() { return Arity.atMostOneToAny; } }

  public interface AtLeastOneToOne        extends FromAtLeastOne, ToOne        { default Arity arity() { return Arity.atLeastOneToOne; } }
  public interface AtLeastOneToAtMostOne  extends FromAtLeastOne, ToAtMostOne  { default Arity arity() { return Arity.atLeastOneToAtMostOne; } }
  public interface AtLeastOneToAtLeastOne extends FromAtLeastOne, ToAtLeastOne { default Arity arity() { return Arity.atLeastOneToAtLeastOne; } }
  public interface AtLeastOneToAny        extends FromAtLeastOne               { default Arity arity() { return Arity.atLeastOneToAny; } }

  public interface AnyToOne        extends ToOne        { default Arity arity() { return Arity.anyToOne; } }
  public interface AnyToAtMostOne  extends ToAtMostOne  { default Arity arity() { return Arity.anyToAtMostOne; } }
  public interface AnyToAtLeastOne extends ToAtLeastOne { default Arity arity() { return Arity.anyToAtLeastOne; } }
  public interface AnyToAny        extends HasArity     { default Arity arity() { return Arity.anyToAny; } }

}
