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
    V  extends      TypedVertex<V,VT,G,RV>,
    VT extends TypedVertex.Type<V,VT,G,RV>
  >
  V addVertex(VT vertexType) {

    return vertexType.fromRaw(
      raw().addVertex( vertexType.name() )
    );
  }

  /* adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G. */
  default <
    S extends TypedVertex<S,?,?,RV>,
    R  extends      TypedEdge<S,?, R,RT, T,?, G,RV,RE>,
    RT extends TypedEdge.Type<S,?, R,RT, T,?, G,RV,RE>,
    T extends TypedVertex<T,?,?,RV>
  >
  R addEdge(S from, RT relType, T to) {

    return relType.fromRaw(
      raw().addEdge( from.raw(), relType.name(), to.raw() )
    );
  }

  /*
    ### properties

    These methods are used for setting and getting properties on vertices and edges.
  */
  default <
    N  extends      TypedVertex<N,NT,G,RV>,
    NT extends TypedVertex.Type<N,NT,G,RV>,
    V
  >
  V getProperty(N node, Property<NT,V> property) {

    return raw().<V>getPropertyV(node.raw(), property.name());
  }

  /* Get the value of a property from an edge of G. */
  default <
    R  extends      TypedEdge<?,?, R,RT, ?,?, G,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, ?,?, G,RV,RE>,
    V
  >
  V getProperty(R edge, Property<RT,V> property) {

    return raw().<V>getPropertyE(edge.raw(), property.name());
  }

  /* Sets the value of a property for a vertex of G. */
  default <
    N  extends      TypedVertex<N,NT,G,RV>,
    NT extends TypedVertex.Type<N,NT,G,RV>,
    V
  >
  G setProperty(N node, Property<NT,V> property, V value) {

    raw().setPropertyV(node.raw(), property.name(), value);
    return node.graph();
  }

  /* Sets the value of a property for an edge of G. */
  default <
    R  extends      TypedEdge<?,?, R,RT, ?,?, G,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, ?,?, G,RV,RE>,
    V
  >
  G setProperty(R edge, Property<RT,V> property, V value) {

    raw().setPropertyE(edge.raw(), property.name(), value);
    return edge.graph();
  }

  /*
    ### source and target

    gets the source of an edge of G, which could be of a different graph.
  */
  default <
    S  extends      TypedVertex<S,ST,?,RV>,
    ST extends TypedVertex.Type<S,ST,?,RV>,
    R  extends      TypedEdge<S,ST, R,RT, ?,?, G,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, ?,?, G,RV,RE>
  >
  S source(R edge) {

    return edge.type().sourceType().fromRaw(
      raw().source(edge.raw())
    );
  }

  default <
    R  extends      TypedEdge<?,?, R,RT, T,TT, G,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, T,TT, G,RV,RE>,
    T  extends      TypedVertex<T,TT,?,RV>,
    TT extends TypedVertex.Type<T,TT,?,RV>
  >
  T target(R edge) {

    return edge.type().targetType().fromRaw(
      raw().target(edge.raw())
    );
  }


  /* ### Incident edges from vertices */

  /*
    #### out methods

    gets the out edges of a vertex N of G.
  */
  default <
    R  extends      TypedEdge<?,?, R,RT, ?,?, G,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, ?,?, G,RV,RE>
  >
  Stream<R> outE(TypedVertex<?,?,G,RV> node, RT relType) {

    return raw().outE(
      node.raw(),
      relType.name()
    ).map(
      relType::fromRaw
    );
  }

  default <
    R  extends      TypedEdge<?,?, R,RT, T,TT, G,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, T,TT, G,RV,RE>,
    T  extends TypedVertex<T,TT,?,RV>,
    TT extends TypedVertex.Type<T,TT,?,RV>
  >
  Stream<T> outV(TypedVertex<?,?,G,RV> node, RT relType) {

    return raw().outV (
      node.raw(),
      relType.name()
    ).map(
      relType.targetType()::fromRaw
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
  //   return relType.fromRaw(
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
  //   return relType.targetType().fromRaw(
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
  //     relType::fromRaw
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
  //     relType.targetType()::fromRaw
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
  //     relType::fromRaw
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
  //     relType.targetType()::fromRaw
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
  //     relType::fromRaw
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
  //     relType.sourceType()::fromRaw
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
  //   return relType.fromRaw(
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
  //   return relType.sourceType().fromRaw(
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
  //     relType::fromRaw
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
  //     relType.sourceType()::fromRaw
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
  //     relType::fromRaw
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
  //     relType.sourceType()::fromRaw
  //   );
  // }
}
