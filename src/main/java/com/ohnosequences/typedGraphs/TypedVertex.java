package com.ohnosequences.typedGraphs;

import java.util.List;
/*
  A typed node. The pattern is the same as for `TypedElement`: you need to define a TypedVertex and its type together.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface TypedVertex <
  N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>, 
  NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
  extends TypedElement<N,NT,G,I,RV,RVT,RE,RET>
{

  @Override
  RV raw();

  /*
  ### create relationships in/out of this node

  There are two methods for creating new relationships, into and out of this node respectively. Their implementation delegates to the `G` `addRel` method.
  */

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  R addInTypedEdge(S from, RT relType) {

    return graph().addTypedEdge( from, relType, self() );
  }

  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // tgt
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  >
  R addOutTypedEdge(RT relType, T to) {

    return graph().addTypedEdge( self(), relType, to );
  }

  /*
  ### properties

  */
  @Override
  default <
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  V get(P property) {

    return graph().getProperty(self(), property);
  }

  @Override
  default <
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  void set(P property, V value) {

    graph().setProperty(self(), property, value);
  }


  /*

  ### getting incoming and outgoing relationships

  For when you don't know anything about the arity, we have unbounded in/out methods which return `List`s.
  */

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<R> in(RT relType) {

    return relType.graph().in( relType, self() );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> out(RT relType) {

    return relType.graph().out( self(), relType );
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<S> inV(RT relType) {

    return relType.graph().inV( relType, self() );
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromMany<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<R> inMany(RT relType) {

    return relType.graph().inMany( relType, self());
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromMany<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<S> inManyV(RT relType) {

    return relType.graph().inManyV( relType, self() );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outV(RT relType) {

    G relGraph = relType.graph();
    // delegates to graph
    return relGraph.outV( self(), relType );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends TypedEdge.Type.ToMany<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> outMany(RT relType) {

    return relType.graph().out( self(), relType );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      TypedEdge.Type.ToMany<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  >
  List<T> outManyV(RT relType) {

    return relType.graph().outV( self(), relType );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      TypedEdge.Type.ToOne<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  R outOne(RT relType) {

    return relType.graph().outOne( self(), relType );
  }



  interface Type <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>, 
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
    extends TypedElement.Type<N,NT,G,I,RV,RVT,RE,RET>
  {

    @Override
    RVT raw();

    N from(RV vertex);

    @Override 
    NT value();
  }
}