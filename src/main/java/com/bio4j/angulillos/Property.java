package com.bio4j.angulillos;

/*
  ## Properties

  A property of the of type `FT`, with value type `X`.
*/
public class Property <
  FT extends ElementType<FT,?,?>,
  X
>
{
  // private final String nameSuffix;

  public final String _label;

  public final FT elementType;
  public final Class<X> valueClass;

  protected Property(FT elementType, String nameSuffix, Class<X> valueClass) {
    this.elementType = elementType;
    // this.nameSuffix  = nameSuffix;
    this.valueClass  = valueClass;
    this._label = elementType._label + "." + nameSuffix;
  }
}
