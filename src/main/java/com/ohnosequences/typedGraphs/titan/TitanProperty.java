package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Property;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;


public interface TitanProperty <
  // the element type
  N extends TitanTypedElement<N,NT,TG>, NT extends TitanTypedElement.Type<N,NT,TG>, TG extends TitanTypedGraph<TG>,
  // the property (of that element)
  P extends TitanProperty<N,NT,TG,P,V>,
  // the value type of this property
  V
> 
  extends Property<N,NT, P,V, TG, TitanUntypedGraph, TitanVertex,TitanKey,TitanEdge,TitanLabel>
{

  /*
    The Titan key used for this property type
  */
  public TitanKey titanKey();
}