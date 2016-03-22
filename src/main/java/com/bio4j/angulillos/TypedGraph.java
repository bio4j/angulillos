package com.bio4j.angulillos;

import static com.bio4j.angulillos.conversions.*;

import java.util.stream.Stream;
import java.util.Optional;


/*
  ## Typed graphs

  A `TypedGraph` is, unsurprisingly, the typed version of [UntypedGraph](UntypedGraph.java.md).
*/
interface TypedGraph <
  G extends TypedGraph<G,RV,RE>,
  RV,RE
>
{

  UntypedGraph<RV,RE> raw();

  default <
    V extends TypedVertex<V,?,G,RV,RE>
  >
  V addVertex(TypedVertex.Type<V,?,G,RV,RE> vertexType) {

    return vertexType.vertex(
      raw().addVertex( vertexType.name() )
    );
  }

  /* adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G. */
  default <
    S extends TypedVertex<S,?,? extends TypedGraph<?,RV,RE>,RV,RE>,
    R extends TypedEdge<S,?,? extends TypedGraph<?,RV,RE>, R,RT,? extends TypedGraph<?,RV,RE>, T,?,? extends TypedGraph<?,RV,RE>, RV,RE>,
    RT extends TypedEdge.Type<S,?,? extends TypedGraph<?,RV,RE>, R,RT,? extends TypedGraph<?,RV,RE>, T,?,? extends TypedGraph<?,RV,RE>, RV,RE>,
    T extends TypedVertex<T,?,? extends TypedGraph<?,RV,RE>,RV,RE>
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
    NT extends TypedVertex.Type<?,NT,G,RV,RE>,
    V
  >
  V getProperty(TypedVertex<?,NT,G,RV,RE> node, Property<NT,V> property) {

    return raw().<V>getPropertyV(node.raw(), property.name());
  }

  /* Get the value of a property from an edge of G. */
  default <
    RT extends TypedEdge.Type<?,?,?, ?,RT,G, ?,?,?, RV,RE>,
    V
  >
  V getProperty(TypedEdge<?,?,?, ?,RT,G, ?,?,?, RV,RE> edge, Property<RT,V> property) {

    return raw().<V>getPropertyE(edge.raw(), property.name());
  }

  /* Sets the value of a property for a vertex of G. */
  default <
    NT extends TypedVertex.Type<?,NT,G,RV,RE>,
    V
  >
  G setProperty(TypedVertex<?,NT,G,RV,RE> node, Property<NT,V> property, V value) {

    raw().setPropertyV(node.raw(), property.name(), value);
    return node.graph();
  }

  /* Sets the value of a property for an edge of G. */
  default <
    RT extends TypedEdge.Type<?,?,?, ?,RT,G, ?,?,?, RV,RE>,
    V
  >
  G setProperty(TypedEdge<?,?,?, ?,RT,G, ?,?,?, RV,RE> edge, Property<RT,V> property, V value) {

    raw().setPropertyE(edge.raw(), property.name(), value);
    return edge.graph();
  }

  /*
    ### source and target

    gets the source of an edge of G, which could be of a different graph.
  */
  default <
    S extends TypedVertex<S,ST,? extends TypedGraph<?,RV,RE>,RV,RE>,
    ST extends TypedVertex.Type<S,ST,? extends TypedGraph<?,RV,RE>,RV,RE>,
    R extends TypedEdge<S,ST,? extends TypedGraph<?,RV,RE>, R,RT,G, ?,?,?, RV,RE>,
    RT extends TypedEdge.Type<S,ST,? extends TypedGraph<?,RV,RE>, R,RT,G, ?,?,?, RV,RE>
  >
  S source(R edge) {

    return edge.type().sourceType().vertex(
      raw().source(edge.raw())
    );
  }

  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
  //   RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RE,T,TT,TG>,
  //   // tgt
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // T target(R edge) {
  //
  //   return edge.type().targetType().vertex(
  //     raw().target(edge.raw())
  //   );
  // }


  /* ### Incident edges from vertices */

  /*
    #### out methods

    gets the out edges of a vertex N of G.
  */
  default <
    R extends TypedEdge<?,?,G, R,?,G, ?,?,G, RV,RE>
  >
  Stream<R> outE(TypedVertex<?,?,G,RV,RE> node, TypedEdge.Type<?,?,G, R,?,G, ?,?,G, RV,RE> relType) {

    return raw().outE(
      node.raw(),
      relType.name()
    ).map(
      relType::edge
    );
  }

  default <
    R  extends TypedEdge<?,?,G, R,?,?, T,TT,?, RV,RE>,
    T  extends TypedVertex<T,TT,?,RV,RE>,
    TT extends TypedVertex.Type<T,TT,?,RV,RE>
  >
  Stream<T> outV(TypedVertex<?,?,G,RV,RE> node, TypedEdge.Type<?,?,G, R,?,?, T,TT,?, RV,RE> relType) {

    return raw().outV (
      node.raw(),
      relType.name()
    ).map(
      relType.targetType()::vertex
    );
  }

  // default <
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // R outOneE(N node, RT relType) {
  //
  //   // we know it has one!
  //   return relType.edge(
  //     raw().outE(
  //       node.raw(),
  //       relType.name()
  //     )
  //     .findFirst().get()
  //   );
  // }
  //
  // default <
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // T outOneV(N node, RT relType) {
  //
  //   return relType.targetType().vertex(
  //     raw().outV(
  //       node.raw(),
  //       relType.name()
  //     )
  //     .findFirst().get()
  //   );
  // }
  //
  // default <
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToAtMostOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // Optional<R> outOptionalE(N node, RT relType) {
  //
  //   return raw().outE(
  //     node.raw(),
  //     relType.name()
  //   )
  //   .findFirst()
  //   .map(
  //     relType::edge
  //   );
  // }
  // default <
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToAtMostOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // Optional<T> outOptionalV(N node, RT relType) {
  //
  //   return raw().outV(
  //     node.raw(),
  //     relType.name()
  //   )
  //   .findFirst()
  //   .map(
  //     relType.targetType()::vertex
  //   );
  // }
  //
  // default <
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToAtLeastOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // Stream<R> outManyE(N node, RT relType) {
  //
  //   return raw().outE(
  //     node.raw(),
  //     relType.name()
  //   ).map(
  //     relType::edge
  //   );
  // }
  // default <
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  //   //rel
  //   R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG>,
  //   RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RE, T,TT,TG> &
  //     TypedEdge.Type.ToAtLeastOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // target node
  //   T extends TypedVertex<T,TT,TG,I,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  //   TG extends TypedGraph<TG,I,RV,RE>
  // >
  // Stream<T> outManyV(N node, RT relType) {
  //
  //   return raw().outV(
  //     node.raw(),
  //     relType.name()
  //   ).map(
  //     relType.targetType()::vertex
  //   );
  // }
  //
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // /* #### in methods
  // */
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // tgt
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  // >
  // Stream<R> inE(RT relType, N node) {
  //
  //   return raw().inE(
  //     node.raw(),
  //     relType.name()
  //   ).map(
  //     relType::edge
  //   );
  // }
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // tgt
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  // >
  // Stream<S> inV(RT relType, N node) {
  //
  //   return raw().inV(
  //     node.raw(),
  //     relType.name()
  //   ).map(
  //     relType.sourceType()::vertex
  //   );
  // }
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // tgt
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  // >
  // R inOneE(RT relType, N node) {
  //
  //   return relType.edge(
  //     raw().inE(
  //       node.raw(),
  //       relType.name()
  //     )
  //     .findFirst().get()
  //   );
  // }
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // tgt
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  // >
  // S inOneV(RT relType, N node) {
  //
  //   return relType.sourceType().vertex(
  //     raw().inV(
  //       node.raw(),
  //       relType.name()
  //     )
  //     .findFirst().get()
  //   );
  // }
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromAtMostOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // tgt
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  // >
  // Optional<R> inOptionalE(RT relType, N node) {
  //
  //   return raw().inE(
  //     node.raw(),
  //     relType.name()
  //   )
  //   .findFirst()
  //   .map(
  //     relType::edge
  //   );
  // }
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromAtMostOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // tgt
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  // >
  // Optional<S> inOptionalV(RT relType, N node) {
  //
  //   return raw().inV(
  //     node.raw(),
  //     relType.name()
  //   )
  //   .findFirst()
  //   .map(
  //     relType.sourceType()::vertex
  //   );
  // }
  //
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromAtLeastOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // tgt
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  // >
  // Stream<R> inManyE(RT relType, N node) {
  //
  //   return raw().inE(
  //     node.raw(),
  //     relType.name()
  //   ).map(
  //     relType::edge
  //   );
  // }
  // default <
  //   // src
  //   S extends TypedVertex<S,ST,SG,I,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  //   SG extends TypedGraph<SG,I,RV,RE>,
  //   // rel
  //   R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G>,
  //   RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RE, N,NT,G> &
  //     TypedEdge.Type.FromAtLeastOne,
  //   RG extends TypedGraph<RG,I,RV,RE>,
  //   // tgt
  //   N extends TypedVertex<N,NT,G,I,RV,RE>,
  //   NT extends TypedVertex.Type<N,NT,G,I,RV,RE>
  // >
  // Stream<S> inManyV(RT relType, N node) {
  //
  //   return raw().inV(
  //     node.raw(),
  //     relType.name()
  //   ).map(
  //     relType.sourceType()::vertex
  //   );
  // }
}
