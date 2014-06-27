package com.ohnosequences.typedGraphs;

import java.util.List;
/*
  A typed node. The pattern is the same as for `Element`: you need to define a Node and its type together.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Node <
  N extends Node<N,NT,G>, 
  NT extends Node.Type<N,NT,G>,
  G extends TypedGraph<G>
> 
  extends Element<N,NT,G>
{

  /*
   For when you don't know anything about the arity
  */
  public default <
    //rel
    R extends Relationship<N,NT,G, R,RT,RG, T,TT,TG>, 
    RT extends Relationship.Type<N,NT,G, R,RT,RG, T,TT,TG>,
    RG extends TypedGraph<RG>,
    // target node
    T extends Node<T,TT,TG>,
    TT extends Node.Type<T,TT,TG>,
    TG extends TypedGraph<TG>
  > 
  List<R> out(RT relType) {

    RG uhoh = relType.graph();

    uhoh.getEdges(relType);

   //   Iterable<TitanEdge> tEdges = this.getTitanEdges(
   //   com.tinkerpop.blueprints.Direction.OUT, 
   //   relType.label()
   // );

   // List<R> list = new LinkedList<>();
   // Iterator<TitanEdge> iterator = tEdges.iterator();
   // while (iterator.hasNext()) {
   //   list.add(relType.fromTitanEdge(iterator.next()));
   // }

   // return list;
  }

  // public <
  //   //rel
  //   R extends Relationship<N,NT,G, R,RT,RG, T,TT,TG>, 
  //   RT extends Relationship.Type<N,NT,G, R,RT,RG, T,TT,TG>,
  //   RG extends TypedGraph<RG>,
  //   // target node
  //   T extends Node<T,TT,TG>,
  //   TT extends Node.Type<T,TT,TG>,
  //   TG extends TypedGraph<TG>
  // > 
  // List<R> outNodes(RT relType);



  public static interface Type <
    N extends Node<N,NT,G>, 
  NT extends Node.Type<N,NT,G>,
  G extends TypedGraph<G>
  > 
    extends Element.Type<N,NT,G>
  {

    @Override public NT value();
  }
}