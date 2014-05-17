package com.ohnosequences.typedGraphs;

public interface NodeType <
  N extends Node<N,T>,
  T extends NodeType<N,T>
> extends ElementType<N,T> 
{

  @Override public NodeType<N,T> value();

  public Node<N,T> from(Object vertex);
}