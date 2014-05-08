package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanEdge;

public interface TitanRelationshipType<
  S extends TitanNode<S,ST>,
  ST extends Enum<ST> & TitanNodeType<S,ST>,
  R extends TitanRelationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & TitanRelationshipType<S,ST,R,RT,T,TT>,
  T extends TitanNode<T,TT>,
  TT extends Enum<TT> & TitanNodeType<T,TT>
> extends RelationshipType<S,ST, R,RT, T,TT> {


  public TitanLabel label();
  // TODO delegate all TitanEdge methods to raw
  // TODO implement getType() somehow

  public R from(TitanEdge edge);
}