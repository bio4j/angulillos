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
  FT extends TypedElement.Type<?,FT,?,?>,
  X
> { //implements com.bio4j.angulillos.Property<FT,X> {

  // public  final String _label;
  public String _label() { return getClass().getCanonicalName(); }

  private final FT elementType;
  private final Class<X> valueClass;

  public final FT elementType() { return this.elementType; }
  public final Class<X> valueClass() { return this.valueClass; }

  protected Property(FT elementType, Class<X> valueClass) {
    this.elementType = elementType;
    this.valueClass  = valueClass;
    // this._label = elementType()._label() + "." + nameSuffix;
  }
}
