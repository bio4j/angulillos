package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.Property;
import com.ohnosequences.typedGraphs.PropertyType;
import com.ohnosequences.typedGraphs.RelTypes;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanElement;

public abstract class TitanNode<
  N extends TitanNode<N,NT>, 
  NT extends  Enum<NT> & TitanNodeType<N,NT>
> implements Node<N,NT>, TitanVertex {

  protected TitanNode(TitanVertex raw) { this.raw = raw; }
  protected TitanVertex raw;

  // TODO delegate all TitanVertex methods to raw

  // TODO move to TitanElement
  @Override
  public <P extends Property<N,NT>, PT extends PropertyType<N,NT, P,PT, V>, V> V get(PT pt) {

    return raw.<V>getProperty(pt.fullName());
  }

  // use get for implementing all the property-name() methods

  public <P extends Property<N,NT>, PT extends PropertyType<N,NT, P,PT, V>, V> void set(PT pt, V value) {

    raw.setProperty(pt.fullName(), value);
  }

  /*
    adds a rel with source this node; note that this method does not set any properties.
  */
  public <
    R extends TitanRelationship<N,NT,R,RT,T,TT>, 
    RT extends Enum<RT> & TitanRelationshipType<N,NT,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends Enum<TT> & TitanNodeType<T,TT>
  > R addOut(RT relType, T to) {

    TitanEdge rawEdge = to.addEdge(relType.value().toString(), this);

    return relType.from(rawEdge);
  }

  /*
    adds a rel with target this node; note that this method does not set any properties
  */
  public <
    S extends TitanNode<S,ST>,
    ST extends Enum<ST> & TitanNodeType<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,N,NT>, 
    RT extends Enum<RT> & TitanRelationshipType<S,ST,R,RT,N,NT>
  > R addIn(RT relType, S from) {

    TitanEdge rawEdge = this.addEdge(relType.value().toString(), from);

    return relType.from(rawEdge);
  }

  // TODO maybe is not even needed
  // what we need is something similar depending on arity
  public abstract <
    R extends TitanRelationship<N,NT,R,RT,T,TT>, 
    RT extends Enum<RT> & TitanRelationshipType<N,NT,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends Enum<TT> & TitanNodeType<T,TT>
  > Iterable<R> out(RT relType);
  // {

    // TODO map relType.from
    // return relType.from(
    //   this.getEdges(com.tinkerpop.blueprints.Direction.OUT, relType.value().toString())
    // );
  // }

  // TODO equivalent methods for oneToMany in, out etc
  protected <
    R extends TitanRelationship<N,NT,R,RT,T,TT>, 
    RT extends Enum<RT> & RelTypes.ToOne<N,NT,R,RT,T,TT> & TitanRelationshipType<N,NT,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends Enum<TT> & TitanNodeType<T,TT>
  > R outToOne(RT relType) {

    // TODO check the arity here, throw expection otherwise
    Iterable<TitanEdge> tEdges = this.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, relType.label());

    return relType.from(
      tEdges.iterator().next()
    );
  }


  // protected <
  //   R extends TitanRelationship<N,NT,R,RT,T,TT>, 
  //   RT extends Enum<RT> & RelTypes.ToMany<N,NT,R,RT,T,TT> & TitanRelationshipType<N,NT,R,RT,T,TT>,
  //   T extends TitanNode<T,TT>,
  //   TT extends Enum<TT> & TitanNodeType<T,TT>
  // > List<R> outToMany(RT relType) {

  //   Iterable<TitanEdge> tEdges = this.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, relType.label());

  //   // TODO map relType.from over tEdges
  // }
}