package com.bio4j.angulillos;

interface HasFromArity { Arity fromArity(); }
interface HasToArity   { Arity   toArity(); }

/* ### Arities */
public enum Arity {

  One,        // Exactly one: X
  AtMostOne,  // One or none: Optional[X]
  AtLeastOne, // Non-empty list: NEList[X]
  Any;        // Usual list: List[X]

  /* #### In-arities */
  interface FromAtLeastOne extends FromAny, HasFromArity { default Arity fromArity() { return Arity.One; } }
  interface FromOne        extends FromAtLeastOne, FromAtMostOne, HasFromArity { default Arity fromArity() { return Arity.AtMostOne; } }
  interface FromAtMostOne  extends FromAny, HasFromArity { default Arity fromArity() { return Arity.AtLeastOne; } }
  interface FromAny        extends HasFromArity { default Arity fromArity() { return Arity.Any; } }

  /* #### Out-arities */
  interface ToAtLeastOne extends ToAny, HasToArity { default Arity toArity() { return Arity.One; } }
  interface ToOne        extends ToAtLeastOne, ToAtMostOne, HasToArity { default Arity toArity() { return Arity.AtMostOne; } }
  interface ToAtMostOne  extends ToAny, HasToArity { default Arity toArity() { return Arity.AtLeastOne; } }
  interface ToAny        extends HasToArity { default Arity toArity() { return Arity.Any; } }
}
