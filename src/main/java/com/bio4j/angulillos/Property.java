package com.bio4j.angulillos;

/*
  ## Properties

  A property of the [Element](TypedElement.java.md) `N`, with value type `V`.
*/
interface Property <
  // the element type
  N extends TypedElement<N,NT,RV,RE>,
  NT extends TypedElement.Type<N,NT,RV,RE>,
  // the property type and its value type
  P extends Property<N,NT,P,V,RV,RE>,
  V,
  // graph
  RV,RE
>
{

  /* the element type which has this property */
  NT elementType();

  /* the class of the property value, so that implementing classes can create values of it */
  Class<V> valueClass();

  /* the name of the property. By default this is the canonical name of the implementing class */
  default String name() { return getClass().getCanonicalName(); }
}
