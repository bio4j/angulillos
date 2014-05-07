package com.ohnosequences.typedGraphs;

public interface NodeType <
  N extends Node<N,T>,
  T extends Enum<T> & NodeType<N,T>
> extends ElementType<N,T> 
{}