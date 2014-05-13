package com.ohnosequences.typedGraphs;

/*
  A typed node. The pattern is the same as for `Element`: you need to define a Node and its type together

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Node <
  N extends Node<N,T>, 
  T extends Enum<T> & NodeType<N,T>
> extends Element<N,T>
{

  /*
  Here we refine the type to be a node type.
  */
  @Override public NodeType<N,T> type();
}