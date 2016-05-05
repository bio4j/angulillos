package com.bio4j.angulillos;

/*
  ## Properties

  A property of the [Element](TypedElement.java.md) of type `FT`, with value type `X`.
*/
interface AnyProperty
extends HasLabel, HasFromArity {

  public abstract AnyElementType elementType();
  public abstract Class<?> valueClass();
}

interface Property<
  FT extends TypedElement.Type<?,FT,?,?>,
  X
> extends AnyProperty {

  public abstract FT elementType();
  public abstract Class<X> valueClass();
}
