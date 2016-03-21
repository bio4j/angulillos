package com.bio4j.angulillos;

/*
  ## Properties

  A property of the [Element](TypedElement.java.md) `N`, with value type `V`.
*/
interface Property <
  ET extends TypedElement.Type,
  V
>
{

  /* the element type which has this property */
  ET elementType();

  /* the class of the property value, so that implementing classes can create values of it */
  Class<V> valueClass();

  /* the name of the property. By default this is the canonical name of the implementing class */
  default String name() { return getClass().getCanonicalName(); }
}
