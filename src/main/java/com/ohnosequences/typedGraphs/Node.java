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

  @Override
  RV raw();

  /*
  ### create relationships in/out of this node

  There are two methods for creating new relationships, into and out of this node respectively. Their implementation delegates to the `G` `addRel` method.
  */

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  R addIn(S from, RT relType) {

    return graph().addEdge( from, relType, self() );
  }

  default <
    // rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // tgt
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  >
  R addOut(RT relType, T to) {

    return graph().addEdge( self(), relType, to );
  }

  /*
  ### properties

  */
  @Override
  default <
    P extends Property<N,NT,G,I,RV,RVT,RE,RET,P,V>, 
    V
  > 
  V get(P property) {

    return graph().getProperty(self(), property);
  }

  @Override
  default <
    P extends Property<N,NT,G,I,RV,RVT,RE,RET,P,V>, 
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
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<R> in(RT relType) {

    return relType.graph().in( relType, self() );
  }

  default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> out(RT relType) {

    G relGraph = relType.graph();
    // delegates to graph
    return relGraph.out( self(), relType );
  }

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<S> inV(RT relType) {

    return relType.graph().inV( relType, self() );
  }

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      Relationship.Type.FromMany<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<R> inMany(RT relType) {

    return relType.graph().inMany( relType, self());
  }

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      Relationship.Type.FromMany<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<S> inManyV(RT relType) {

    return relType.graph().inManyV( relType, self() );
  }

  default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outV(RT relType) {

    G relGraph = relType.graph();
    // delegates to graph
    return relGraph.outV( self(), relType );
  }

  default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      Relationship.Type.ToMany<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> outMany(RT relType) {

    return relType.graph().out( self(), relType );
  }

  default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      Relationship.Type.ToMany<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outManyV(RT relType) {

    return relType.graph().outV( self(), relType );
  }

  default <
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      Relationship.Type.ToOne<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  R outOne(RT relType) {

    return relType.graph().outOne( self(), relType );
  }



  interface Type <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>, 
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
    extends Element.Type<N,NT,G,I,RV,RVT,RE,RET>
  {

    @Override
    RVT raw();

    N from(RV vertex);

    @Override 
    NT value();
  }
}