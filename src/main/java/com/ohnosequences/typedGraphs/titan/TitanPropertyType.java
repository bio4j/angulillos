package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Element;
import com.ohnosequences.typedGraphs.ElementType;

import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanElement;

/*
  This should extend `TitanKey` but is not possible due to non-covariant Java generics
*/
public abstract class TitanPropertyType <
  // the element type
  N extends Element<N,NT>, NT extends Enum<NT> & ElementType<N,NT>,
  // the property (of that element)
  P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
  // the value type of this property
  V
> implements TitanKey
{

  
  protected TitanPropertyType(PropertyType<N,NT, P,PT, V> propertyType, TitanKey titanKey) {

    this.propertyType = propertyType;
    this.titanKey = titanKey;
  }

  protected PropertyType<N,NT, P,PT, V> propertyType;
  /*
    The Titan key used for this property type
  */
  protected TitanKey titanKey;
  
}