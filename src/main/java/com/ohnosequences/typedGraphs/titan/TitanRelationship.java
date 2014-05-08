package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.NodeType;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Direction;

public abstract class TitanRelationship <
	S extends TitanNode<S,ST>, 
	ST extends Enum<ST> & NodeType<S,ST>,
	R extends TitanRelationship<S,ST, R,RT, T,TT>, 
	RT extends Enum<RT> & RelationshipType<S,ST, R,RT, T,TT>, 
	T extends TitanNode<T,TT>,
	TT extends Enum<TT> & NodeType<T,TT>
> implements Relationship<S,ST, R,RT, T,TT>, TitanEdge {

	protected TitanRelationship(TitanEdge raw) {
		
		this.raw = raw;
	}

	protected TitanEdge raw;
	public abstract TitanRelationshipType<S,ST,R,RT,T,TT> titanType();

	public S source() {

		return this.titanType().titanSourceType().from(
			raw.getVertex(Direction.OUT)
		);
	}

	public T target() {

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