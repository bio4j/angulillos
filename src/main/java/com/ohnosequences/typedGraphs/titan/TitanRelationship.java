package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Relationship;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Direction;

public abstract class TitanRelationship <
	S extends TitanNode<S,ST>, 
	ST extends Enum<ST> & TitanNodeType<S,ST>,
	R extends TitanRelationship<S,ST, R,RT, T,TT>, 
	RT extends Enum<RT> & TitanRelationshipType<S,ST, R,RT, T,TT>, 
	T extends TitanNode<T,TT>, TT extends Enum<TT> & TitanNodeType<T,TT>
> implements Relationship<S,ST, R,RT, T,TT>, TitanEdge {

	protected TitanRelationship(TitanEdge raw) {
		
		this.raw = raw;
	}

	protected TitanEdge raw;

	public S source() {

		return this.type().sourceType().from(
			raw.getVertex(Direction.OUT)
		);
	}

	public T target() {

		return this.type().targetType().from(
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