package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.thinkaurelius.titan.core.TitanEdge;

public abstract class TitanRelationship<
  S extends TitanNode<S,ST>,
  ST extends Enum<ST> & TitanNodeType<S,ST>,
  R extends TitanRelationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & TitanRelationshipType<S,ST,R,RT,T,TT>,
  T extends TitanNode<T,TT>,
  TT extends Enum<TT> & TitanNodeType<T,TT>
> implements Relationship<S,ST, R,RT, T,TT>, TitanEdge {


  protected TitanEdge raw;

  // TODO delegate all TitanEdge methods to raw
  // TODO implement getType() somehow
}