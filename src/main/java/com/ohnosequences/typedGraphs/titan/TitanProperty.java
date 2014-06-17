package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Element;
import com.ohnosequences.typedGraphs.Element.Type;

import com.ohnosequences.typedGraphs.Property;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;

public interface TitanProperty <
  // the element type
  N extends TitanElement<N,NT>, NT extends TitanElement.Type<N,NT>,
  // the property (of that element)
  P extends TitanProperty<N,NT,P,V>,
  // the value type of this property
  V
> 
  extends Property<N,NT,P,V>
{

  /*
    The Titan key used for this property type
  */
  public TitanKey titanKey();
}