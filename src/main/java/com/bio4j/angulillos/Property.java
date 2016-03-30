package com.bio4j.angulillos;

/*
  ## Properties

  A property of the of type `FT`, with value type `X`.
*/
public class Property <
  FT extends ElementType<FT,?,?>,
  X
> {

  public final String _label;

  private final FT elementType;
  public  final FT elementType() { return this.elementType; }

  private final Class<X> valueClass;
  public  final Class<X> valueClass() { return this.valueClass; }


  // NOTE: this constructor is package-private to enforce usage of the factory method in the ElementType class
  Property(FT elementType, String nameSuffix, Class<X> valueClass) {

    this.elementType = elementType;
    this.valueClass  = valueClass;
    this._label = elementType._label + "." + nameSuffix;
  }
}
