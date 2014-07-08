package com.ohnosequences.typedGraphs;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/*
  A `TypedGraph` defines a set of types (nodes, relationships, properties) comprising what you could call a _schema_ for a typed graph. It uses a `UntypedGraph` for storing them.
*/
public interface TypedGraph <
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
{

  I raw();

  /*
   * adds a rel with source this node; note that this method does not set any
   * properties.
   */
  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // tgt
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  R addEdge(S from, RT relType, T to) {
      
    return relType.from(
      raw().addEdge(
        from.raw(),
        relType.raw(),
        to.raw()
      )
    );
  }

  /*
  ### properties

  These methods are used for setting and getting properties on vertices and edges.

  */
  default <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,G,I,RV,RVT,RE,RET,P,V>, 
    V
  > 
  V getProperty(N node, P property) {

    return raw().<V>getPropertyV(node.raw(), property.name());
  }

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // property
    P extends Property<R,RT,G,I,RV,RVT,RE,RET,P,V>, V,
    // tgt
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  V getProperty(R edge, P property) {

    return raw().<V>getPropertyE(edge.raw(), property.name());
  }

  default <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,G,I,RV,RVT,RE,RET,P,V>, 
    V
  >
  void setProperty(N node, P property, V value) {

    raw().setPropertyV(node.raw(), property.name(), value);
  }

  /*
  ### incident edges from vertices


  */
  ///////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
  #### out methods

  */
  default <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> out(N node, RT relType) {

    List<R> rels = new LinkedList<>();

    Iterator<RE> rawEdges = raw().out(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawEdges.hasNext()) {

      rels.add(relType.from(rawEdges.next()));
    }

    return rels;
  }

  default <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outV(N node, RT relType) {

    List<T> nodes = new LinkedList<>();

    Iterator<RV> rawVertices = raw().outV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawVertices.hasNext()) {

      nodes.add(relType.targetType().from(rawVertices.next()));
    }

    return nodes;
  }

  default <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  R outOne(N node, RT relType) {

    Iterator<RE> rawEdges = raw().out(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    return relType.from(rawEdges.next());
  }

  default <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  T outOneV(N node, RT relType) {

    Iterator<RV> rawVertices = raw().outV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    return relType.targetType().from(rawVertices.next());
  }

  default <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> outMany(N node, RT relType) {

    List<R> rels = new LinkedList<>();

    Iterator<RE> rawEdges = raw().out(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawEdges.hasNext()) {

      rels.add(relType.from(rawEdges.next()));
    }

    return rels;
  }

  default <
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends Relationship<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends Relationship.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends Node<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outManyV(N node, RT relType) {

    List<T> nodes = new LinkedList<>();

    Iterator<RV> rawVertices = raw().outV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawVertices.hasNext()) {

      nodes.add(relType.targetType().from(rawVertices.next()));
    }

    return nodes;
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
  #### in methods

  */

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // tgt
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  List<R> in(RT relType, N node) {

    List<R> rels = new LinkedList<>();

    Iterator<RE> rawEdges = raw().in(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawEdges.hasNext()) {

      rels.add(relType.from(rawEdges.next()));
    }

    return rels;
  }

  default <
     // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  List<S> inV(RT relType, N node) {

    List<S> nodes = new LinkedList<>();

    Iterator<RV> rawVertices = raw().inV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawVertices.hasNext()) {

      nodes.add(relType.sourceType().from(rawVertices.next()));
    }

    return nodes;
  }

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // tgt
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  R inOne(RT relType, N node) {

    Iterator<RE> rawEdges = raw().in(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    // just the first one
    return relType.from(rawEdges.next());
  }

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // tgt
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  S inOneV(RT relType, N node) {

    Iterator<RV> rawVertices = raw().inV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    // just the first one
    return relType.sourceType().from(rawVertices.next());
  }

  default <
    // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // tgt
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  List<R> inMany(RT relType, N node) {

    List<R> rels = new LinkedList<>();

    Iterator<RE> rawEdges = raw().in(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawEdges.hasNext()) {

      rels.add(relType.from(rawEdges.next()));
    }

    return rels;
  }

  default <
     // src
    S extends Node<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends Relationship.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    N extends Node<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends Node.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  List<S> inManyV(RT relType, N node) {

    List<S> nodes = new LinkedList<>();

    Iterator<RV> rawVertices = raw().inV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawVertices.hasNext()) {

      nodes.add(relType.sourceType().from(rawVertices.next()));
    }

    return nodes;
  }


  
 
}