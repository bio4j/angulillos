package com.ohnosequences.typedGraphs;

/*
  A Property. Just a marker.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/

public abstract class Property <
  // the element type
  N extends Element<N,NT>, NT extends Element.Type<N,NT>,
  // the property type
  P extends Property<N,NT,P,V>,
  // the value type of this property
  V
> 
{
    
  protected Property(NT elementType, String name, Class<V> clazz) {

    this.elementType = elementType;
    this.name = name;
    this.clazz = clazz;
  }

  private NT elementType;
  private String name;
  private Class<V> clazz;

  /*
    the name is by default the name of the element together with that of the unique value here
  */
  public String fullName() { return elementType.name().concat(".").concat(name); }

  /*
    the element type which has this property type
  */
  public NT elementType() { return this.elementType; }

  // just in case
  public Class<V> valueClass() { return this.clazz; }
  public String name() { return this.name; }
}