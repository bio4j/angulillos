package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Element;
import com.ohnosequences.typedGraphs.Element.Type;

import com.ohnosequences.typedGraphs.Property;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;


public interface TitanProperty <
  // the element type
  N extends TitanElement<N,NT,TG>, NT extends TitanElement.Type<N,NT,TG>, TG extends TitanTypedGraph<TG>,
  // the property (of that element)
  P extends TitanProperty<N,NT,TG,P,V>,
  // the value type of this property
  V
> 
  extends Property<N,NT,TG,Titan,TitanVertex,TitanKey,TitanEdge,TitanLabel,P,V>
{

  /*
    The Titan key used for this property type
  */
  public TitanKey titanKey();
}