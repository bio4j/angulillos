package com.ohnosequences.typedGraphs;

/*
  A typed Element. Base class for both Nodes and Relationships

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Element <
  N extends Element<N,NT>, 
  NT extends Enum<NT> & ElementType<N,NT>
> 
{

  /*
    Its type
  */
  public NT type();

  public <
    P extends Property<N,NT>, 
    PT extends PropertyType<N,NT, P,PT, V>, 
    V
  > V get(PT pt);
}
