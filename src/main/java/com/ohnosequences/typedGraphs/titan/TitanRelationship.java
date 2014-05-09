package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Direction;

public abstract class TitanRelationship <

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

> implements Relationship<S,ST, R,RT, T,TT>, TitanEdge {

	protected TitanRelationship(TitanEdge raw) {
		
		this.raw = raw;
	}

	protected TitanEdge raw;
	public abstract TitanRelationshipType<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT> titanType();

	@Override
	public TitanNode<S,ST,TitanS,TitanST> source() {

		return this.titanType().titanSourceType().from(
			raw.getVertex(Direction.OUT)
		);
	}

	@Override
	public TitanNode<T,TT,TitanT,TitanTT> target() {

		return this.titanType().titanTargetType().from(
			raw.getVertex(Direction.IN)
		);
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	// fwd TitanEdge methods to raw

	@Override
	public TitanLabel getTitanLabel() {
		return raw.getTitanLabel();
	}
	@Override
	public TitanVertex getVertex(Direction dir){
		return raw.getVertex(dir);
	}
	@Override
	public TitanVertex getOtherVertex(TitanVertex vertex){
		return raw.getOtherVertex(vertex);
	}
	@Override
	public boolean isDirected(){
		return raw.isDirected();
	}
	@Override
	public boolean isUnidirected(){
		return raw.isUnidirected();
	}
}