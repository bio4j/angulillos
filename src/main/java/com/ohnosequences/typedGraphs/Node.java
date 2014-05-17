package com.ohnosequences.typedGraphs;

import java.util.List;

/*
  A typed node. The pattern is the same as for `Element`: you need to define a Node and its type together

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Node <
  N extends Node<N,NT>, 
  NT extends NodeType<N,NT>
> extends Element<N,NT>
{

  /*
  Here we refine the type to be a node type.
  */
  @Override public NodeType<N,NT> type();

  public <
    //rel
    R extends Relationship<N,NT, R,RT, T,TT>, 
    RT extends RelationshipType<N,NT,R,RT,T,TT>,
    // target node
    T extends Node<T,TT>, 
    TT extends NodeType<T,TT>
  > 
  List<? extends R> out(Class<RT> relType) throws InstantiationException, IllegalAccessException;



}