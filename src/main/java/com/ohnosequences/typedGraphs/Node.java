package com.ohnosequences.typedGraphs;

/*
  A typed node.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Node <
  N extends Node<N,T>, 
  T extends Enum<T> & NodeType<N,T>
> extends Element<N,T>
{

  @Override
  public NodeType<N,T> type();
}