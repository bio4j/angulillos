package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Element;
import com.ohnosequences.typedGraphs.Element.Type;

import com.ohnosequences.typedGraphs.Property;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanElement;

public abstract class TitanProperty <
  // the element type
  N extends Element<N,NT>, NT extends Element.Type<N,NT>,
  // the property (of that element)
  P extends Property<N,NT,P,V>,
  // the value type of this property
  V
> 
  extends Property<N,NT,P,V>
{

  protected TitanProperty(NT elementType, String name, Class<V> clazz, TitanKey titanKey) {

    super(elementType,name,clazz);

    this.titanKey = titanKey;
  }

  /*
    The Titan key used for this property type
  */
  protected TitanKey titanKey;

  public TitanKey titanKey() { return titanKey; }
}