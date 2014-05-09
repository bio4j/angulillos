package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanEdge;

public interface TitanRelationshipType<

  S extends Node<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  TitanS extends TitanNode<S,ST, TitanS,TitanST>,
  TitanST extends Enum<TitanST> & TitanNodeType<S,ST, TitanS,TitanST>,

  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  TitanR extends TitanRelationship<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT>,
  TitanRT extends Enum<TitanRT> & TitanRelationshipType<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT>,

  T extends Node<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>,
  TitanT extends TitanNode<T,TT, TitanT,TitanTT>,
  TitanTT extends Enum<TitanTT> & TitanNodeType<T,TT, TitanT,TitanTT>

>
{

  public RelationshipType<S,ST,R,RT,T,TT> type();

  public TitanLabel label();

  public TitanNodeType<S,ST, TitanS,TitanST> titanSourceType();
  public TitanNodeType<T,TT, TitanT,TitanTT> titanTargetType();

  public TitanRelationship<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT> from(TitanEdge edge);
}