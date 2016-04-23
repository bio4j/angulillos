package com.bio4j.angulillos;

/*
  ## Properties

  A property of the [Element](TypedElement.java.md) of type `FT`, with value type `X`.
*/
interface Property<
  FT extends TypedElement.Type<?,FT,?,?>,
  X
> {

  default public String _label() { return getClass().getCanonicalName(); }

  public abstract FT elementType();
  public abstract Class<X> valueClass();
}
