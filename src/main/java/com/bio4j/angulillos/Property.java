package com.bio4j.angulillos;

/*
  ## Properties

  A property of the [Element](TypedElement.java.md) `N`, with value type `V`.
*/
interface Property <
  // the element type
  N extends TypedElement<N,NT,G,I,RV,RE>, NT extends TypedElement.Type<N,NT,G,I,RV,RE>,
  // the property type and its value type
  P extends Property<N,NT,P,V,G,I,RV,RE>, V,
  // graph
  G extends TypedGraph<G,I,RV,RE>, I extends UntypedGraph<RV,RE>, RV,RE
>
{

  /* the element type which has this property */
  NT elementType();

  /* the class of the property value, so that implementing classes can create values of it */
  Class<V> valueClass();

  /* the name of the property. By default this is the canonical name of the implementing class */
  default String name() { return getClass().getCanonicalName(); }
}
