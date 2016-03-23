package com.bio4j.angulillos;

/*
  ## Properties

  A property of the [Element](TypedElement.java.md) of type `ET`, with value type `X`.
*/
interface Property <
  ElemType extends TypedElement.Type<?,ElemType,?,?>,
  X
>
{

  /* the element type which has this property */
  ElemType elementType();

  /* the class of the property value, so that implementing classes can create values of it */
  Class<X> valueClass();

  /* the name of the property. By default this is the canonical name of the implementing class */
  default String name() { return getClass().getCanonicalName(); }
}
