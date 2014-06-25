package com.ohnosequences.typedGraphs;

import java.util.List;
/*
  A typed node. The pattern is the same as for `Element`: you need to define a Node and its type together.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Node <
  N extends Node<N,NT,G>, 
  NT extends Node.Type<N,NT,G>,
  G extends TypedGraph
> 
  extends Element<N,NT,G>
{


  /*
   For when you don't know anything about the arity
  */
  public <
    //rel
    R extends Relationship<N,NT,G, R,RT,RG, T,TT,TG>, 
    RT extends Relationship.Type<N,NT,G, R,RT,RG, T,TT,TG>,
    RG extends G,
    // target node
    T extends Node<T,TT,TG>,
    TT extends Node.Type<T,TT,TG>,
    TG extends TypedGraph
  > 
  List<R> out(RT relType);

  public <
    //rel
    R extends Relationship<N,NT,G, R,RT,RG, T,TT,TG>, 
    RT extends Relationship.Type<N,NT,G, R,RT,RG, T,TT,TG>,
    RG extends G,
    // target node
    T extends Node<T,TT,TG>,
    TT extends Node.Type<T,TT,TG>,
    TG extends TypedGraph
  > 
  List<R> outNodes(RT relType);



  public static interface Type <
    N extends Node<N,NT,G>, 
    NT extends Node.Type<N,NT,G>,
    G extends TypedGraph
  > 
    extends Element.Type<N,NT,G>
  {

    @Override public NT value();
  }
}