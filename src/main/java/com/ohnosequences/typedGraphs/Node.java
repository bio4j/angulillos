package com.ohnosequences.typedGraphs;

import java.util.List;
/*
  A typed node. The pattern is the same as for `Element`: you need to define a Node and its type together.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Node <
  N extends Node<N,NT,G,I,RV,RE>, 
  NT extends Node.Type<N,NT,G,I,RV,RE>,
  G extends TypedGraph<G,I,RV,RE>,
  I extends Technology<RV,RE>, RV, RE
> 
  extends Element<N,NT,G,I,RV,RE>
{

  public RV raw();

  /*
   For when you don't know anything about the arity
  */
  public default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RE>,
    TT extends Node.Type<T,TT,G,I,RV,RE>
  > 
  List<R> out(RT relType) {

    G relGraph = relType.graph();
    // delegates to unsafe op
    return relGraph.outFrom(self(), relType);
  }

  public default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RE, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RE>,
    TT extends Node.Type<T,TT,G,I,RV,RE>
  > 
  List<T> outNodes(RT relType) {

    G relGraph = relType.graph();
    // delegates to unsafe op
    return relGraph.outNodesFrom(self(), relType);
  }



  public static interface Type <
    N extends Node<N,NT,G,I,RV,RE>, 
    NT extends Node.Type<N,NT,G,I,RV,RE>,
    G extends TypedGraph<G,I,RV,RE>,
    I extends Technology<RV,RE>, RV, RE
  > 
    extends Element.Type<N,NT,G,I,RV,RE>
  {

    @Override public NT value();
  }
}