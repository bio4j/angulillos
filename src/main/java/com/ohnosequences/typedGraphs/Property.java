package com.ohnosequences.typedGraphs;

/*
  A Property. Just a marker.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/

public interface Property <
  // the element type
  N extends Element<N,NT>, NT extends Element.Type<N,NT>,
  // the property type
  P extends Property<N,NT,P,V>,
  // the value type of this property
  V
> 
{
   /*
    the element type which has this property type
  */
  public NT elementType();
  public Class<V> valueClass();
  public String name();
    
  /*
    the name is by default the name of the element together with that of the unique value here
  */
  public default String fullName() { return elementType().name().concat(".").concat(name()); }
}