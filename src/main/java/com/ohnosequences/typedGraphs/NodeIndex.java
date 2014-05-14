package com.ohnosequences.typedGraphs;

public interface NodeIndex <
  N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
  P extends Property<N,NT>, PT extends PropertyType<N,NT, P,PT, V>,
  V
>
{}
