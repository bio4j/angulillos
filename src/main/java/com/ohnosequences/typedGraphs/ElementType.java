package com.ohnosequences.typedGraphs;

/*
  The type of an Element. You can refine this through interfaces, but you cannot use classes for it; only `Enum`s.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface ElementType <
  N extends Element<N,T>,
  T extends Enum<T> & ElementType<N,T>
> 
{

  /*
    this is a strong hit for you to implement this as a singleton `Enum`; just return the one and only instance here.
  */
  public ElementType<N,T> value();
}