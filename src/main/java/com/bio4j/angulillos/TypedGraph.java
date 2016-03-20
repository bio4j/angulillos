package com.bio4j.angulillos;

import static com.bio4j.angulillos.conversions.*;

import java.util.stream.Stream;
import java.util.Optional;


/*
  ## Typed graphs

  A `TypedGraph` is, unsurprisingly, the typed version of [UntypedGraph](UntypedGraph.java.md).
*/
interface TypedGraph <
  G extends TypedGraph<G,I,RV,RE>,
  I extends UntypedGraph<RV,RE>, RV,RE
>
{

  I raw();

  default <
    V extends TypedVertex<V,VT,G,I,RV,RE>,
    VT extends TypedVertex.Type<V,VT,G,I,RV,RE>
  >
  V addVertex(VT vertexType) {

    return vertexType.vertex(
      raw().addVertex( vertexType.name() )
    );
  }

  /* adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G. */
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RE,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RE,T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  R addEdge(S from, RT relType, T to) {

    return relType.edge(
      raw().addEdge( from.raw(), relType.name(), to.raw() )
    );
  }

  /*
    ### properties

    These methods are used for setting and getting properties on vertices and edges.
  */
  default <
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
    P extends Property<N,NT,P,V,G,I,RV,RE>,
    V
  >
  V getProperty(N node, P property) {

    return raw().<V>getPropertyV(node.raw(), property.name());
  }

  /* Get the value of a property from an edge of G. */
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>,
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  V getProperty(R edge, P property) {

    return raw().<V>getPropertyE(edge.raw(), property.name());
  }

  /* Sets the value of a property for a vertex of G. */
  default <
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
    P extends Property<N,NT,P,V,G,I,RV,RE>,
    V
  >
  G setProperty(N node, P property, V value) {

    raw().setPropertyV(node.raw(), property.name(), value);
    return node.graph();
  }

  /* Sets the value of a property for an edge of G. */
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>,
    P extends Property<R,RT,P,V,G,I,RV,RE>,
    V
  >
  G setProperty(R edge, P property, V value) {

    raw().setPropertyE(edge.raw(), property.name(), value);
    return edge.graph();
  }

  /* ### Source and target

    Gets the source of an edge of G, which could be of a different graph.
  */
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  S source(R edge) {

    return edge.type().sourceType().vertex(
      raw().source(edge.raw())
    );
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  T target(R edge) {

    return edge.type().targetType().vertex(
      raw().target(edge.raw())
    );
  }


  /* ### Incident edges/vertices from vertices */

  /* #### Incoming edges */
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  >
  Stream<R> inE(RT relType, N node) {

    return raw().inE(
      node.raw(),
      relType.name()
    ).map(
      relType::edge
    );
  }

  /* #### Incoming vertices */
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RE>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  >
  Stream<S> inV(RT relType, N node) {

    return raw().inV(
      node.raw(),
      relType.name()
    ).map(
      relType.sourceType()::vertex
    );
  }


  /* #### Outgoing edges */
  default <
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RE>,
    // target node
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  Stream<R> outE(N node, RT relType) {

    return raw().outE(
      node.raw(),
      relType.name()
    ).map(
      relType::edge
    );
  }

  /* #### Outgoing vertices */
  default <
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RE>,
    // target node
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  Stream<T> outV(N node, RT relType) {

    return raw().outV (
      node.raw(),
      relType.name()
    ).map(
      relType.targetType()::vertex
    );
  }

}
