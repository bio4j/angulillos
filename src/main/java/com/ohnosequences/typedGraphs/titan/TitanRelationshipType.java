package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.NodeType;

import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanEdge;

public interface TitanRelationshipType<
  S extends TitanNode<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  R extends TitanRelationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  T extends TitanNode<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>
> extends TitanLabel 
{

  public RT type();

  public TitanNodeType<S,ST> titanSourceType();
  public TitanNodeType<T,TT> titanTargetType();

  public R from(TitanEdge edge);
}