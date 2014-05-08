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

  protected TitanRelationship(TitanEdge raw) { this.raw = raw; }
  protected TitanEdge raw;

  // TODO delegate all TitanEdge methods to raw

  // no way to implement the source/target methods at this level. It needs to be done for each impl class.
  // but is easy, just 
  // source = return new Whatever(raw.getVertex(Direction.OUT));
  // target = return new Other(raw.getVertex(Direction.IN));
  // TODO example

  public static <
    S extends TitanNode<S,ST>,
    ST extends Enum<ST> & TitanNodeType<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & TitanRelationshipType<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends Enum<TT> & TitanNodeType<T,TT>
  > R CREATE(RT relType, TitanEdge raw) {

    return relType.from(raw);
  }
}