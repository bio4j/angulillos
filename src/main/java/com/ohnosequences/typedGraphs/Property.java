package com.ohnosequences.typedGraphs;

/*
  Properties.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/

public interface Property <
  // the element type
  N extends TypedElement<N,NT,G,I,RV,RVT,RE,RET>, NT extends TypedElement.Type<N,NT,G,I,RV,RVT,RE,RET>,
  // the property type and its value type
  P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, V,
  // graph stuff
  G extends TypedGraph<G,I,RV,RVT,RE,RET>, I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
{
   /* the element type which has this property type */
  NT elementType();
  /* the class of the property value, so that implementing classes can create values of it. */
  Class<V> valueClass();
  /* the name of the property.*/
  default String name() {

    return getClass().getCanonicalName();
  }
}