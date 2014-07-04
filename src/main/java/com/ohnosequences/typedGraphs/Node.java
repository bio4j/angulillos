package com.ohnosequences.typedGraphs;

import java.util.List;
/*
  A typed node. The pattern is the same as for `Element`: you need to define a Node and its type together.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Node <
  N extends Node<N,NT>, 
  NT extends Node.Type<N,NT>
> 
  extends Element<N,NT>
{
  
  public static interface Type <
    N extends Node<N,NT>,
    NT extends Node.Type<N,NT>
  > 
    extends Element.Type<N,NT> 
  {

    @Override public NT value();
  }
}