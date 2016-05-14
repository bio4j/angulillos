package com.bio4j.angulillos;

public interface Property<
  FT extends TypedElement.Type<?,FT,?,?>,
  X
> extends AnyProperty {

  @Override FT elementType();
  @Override Class<X> valueClass();
}
