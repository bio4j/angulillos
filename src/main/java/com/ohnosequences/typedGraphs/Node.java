package com.ohnosequences.typedGraphs;

import java.util.List;
/*
  A typed node. The pattern is the same as for `Element`: you need to define a Node and its type together.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Node <
  N extends Node<N,NT,G,I,RV,RVT,RE,RET>, 
  NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
  extends Element<N,NT,G,I,RV,RVT,RE,RET>
{

  public RV raw();

  /*
   For when you don't know anything about the arity
  */
  public default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> out(RT relType) {

    G relGraph = relType.graph();
    // delegates to unsafe op
    return relGraph.outFrom(self(), relType);
  }

  public default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outNodes(RT relType) {

    G relGraph = relType.graph();
    // delegates to unsafe op
    return relGraph.outNodesFrom(self(), relType);
  }



  public static interface Type <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>, 
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
    extends Element.Type<N,NT,G,I,RV,RVT,RE,RET>
  {

    @Override public NT value();

    public RVT raw();

    public N from(RV vertex);
  }
}