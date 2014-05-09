package com.ohnosequences.typedGraphs;

/*
  The type of an Element.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface ElementType <
  N extends Element<N,T>,
  T extends Enum<T> & ElementType<N,T>
> 
{

  /*
    this is a strong hit for you to implement this as a singleton
  */
  public ElementType<N,T> value();
}