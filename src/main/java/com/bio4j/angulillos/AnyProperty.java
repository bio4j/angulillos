package com.bio4j.angulillos;

/*
  ## Properties

  A property of the [Element](TypedElement.java.md) of type `FT`, with value type `X`.
*/
public interface AnyProperty extends
  HasLabel,
  HasFromArity,
  HasToArity
{

  AnyElementType elementType();
  Class<?> valueClass();
}

// TODO: make it public
interface Property<
  FT extends TypedElement.Type<?,FT,?,?>,
  X
> extends AnyProperty {

  FT elementType();
  Class<X> valueClass();
}
