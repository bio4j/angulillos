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
  public interface FromAtLeastOne extends FromAny, HasFromArity { default Arity fromArity() { return Arity.AtLeastOne; } }
  public interface FromOne        extends FromAtLeastOne, FromAtMostOne, HasFromArity { default Arity fromArity() { return Arity.One; } }
  public interface FromAtMostOne  extends FromAny, HasFromArity { default Arity fromArity() { return Arity.AtMostOne; } }
  public interface FromAny        extends HasFromArity { default Arity fromArity() { return Arity.Any; } }

  /* #### Out-arities */
  public interface ToAtLeastOne extends ToAny, HasToArity { default Arity toArity() { return Arity.AtLeastOne; } }
  public interface ToOne        extends ToAtLeastOne, ToAtMostOne, HasToArity { default Arity toArity() { return Arity.One; } }
  public interface ToAtMostOne  extends ToAny, HasToArity { default Arity toArity() { return Arity.AtMostOne; } }
  public interface ToAny        extends HasToArity { default Arity toArity() { return Arity.Any; } }
}
