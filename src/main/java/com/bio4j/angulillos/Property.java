package com.bio4j.angulillos;

/*
  ## Properties

  A property of the [Element](TypedElement.java.md) of type `ET`, with value type `X`.
*/
// interface Property <
//   ElemType extends TypedElement.Type<?,ElemType,?,?>,
//   X
// >
// {
//
//   /* the element type which has this property */
//   ElemType elementType();
//
//   /* the class of the property value, so that implementing classes can create values of it */
//   Class<X> valueClass();
//
//   /* the name of the property. By default this is the canonical name of the implementing class */
//   default String _label() { return getClass().getCanonicalName(); }
// }

public class Property<
  F extends TypedElement<F,?,?>,
  X
> { //implements com.bio4j.angulillos.Property<F,X> {

  public  final String _label;
  private final F element;
  private final Class<X> valueClass;

  public final F element() { return this.element; }
  public final Class<X> valueClass() { return this.valueClass; }

  protected Property(F element, String nameSuffix, Class<X> valueClass) {
    this.element = element;
    this.valueClass  = valueClass;
    this._label = element()._label() + "." + nameSuffix;
  }
}
