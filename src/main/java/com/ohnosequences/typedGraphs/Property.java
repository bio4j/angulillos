package com.ohnosequences.typedGraphs;

/*
  Properties.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/

public interface Property <
  // the element type
  N extends Element<N,NT,G,I,RV,RVT,RE,RET>, NT extends Element.Type<N,NT,G,I,RV,RVT,RE,RET>,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>, I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
  // the property type
  P extends Property<N,NT,G,I,RV,RVT,RE,RET,P,V>,
  // the value type of this property
  V
> 
{
   /* the element type which has this property type */
  NT elementType();
  /* the class of the property value, so that implementing classes can create values of it. */
  Class<V> valueClass();
  /* the name of the property.*/
  String name();
  
  // TODO move to just name
  /* This is what could be called a fully qualified name. It is by default the name of the element together with that of the property. */
  default String fullName() { return elementType().name().concat(".").concat(name()); }
}