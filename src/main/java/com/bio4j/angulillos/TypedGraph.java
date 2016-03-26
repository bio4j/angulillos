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
    VT extends VertexType<VT, G,RV,RE>
  >
  VertexType<VT,G,RV,RE>.Vertex addVertex(VT vertexType) {

    return vertexType.fromRaw(
      raw().addVertex( vertexType._label )
    );
  }

  /* adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G. */
  default <
    ST extends VertexType<ST, ?,RV,RE>,
    ET extends EdgeType<ST,ET,TT, G,RV,RE>,
    TT extends VertexType<TT, ?,RV,RE>
  >
  EdgeType<ST,ET,TT, G,RV,RE>.Edge addEdge(
    VertexType<ST, ?,RV,RE>.Vertex source,
    ET edgeType,
    VertexType<ST, ?,RV,RE>.Vertex target
  ) {

    return edgeType.fromRaw(
      raw().addEdge( source.raw, edgeType._label, target.raw )
    );
  }

  /*
    ### properties

    These methods are used for setting and getting properties on vertices and edges.
  */
  default <
    VT extends VertexType<VT,G,RV,RE>,
    X
  >
  X getProperty(VertexType<VT,G,RV,RE>.Vertex vertex, Property<VT,X> property) {

    return raw().<X>getPropertyV(vertex.raw, property._label);
  }

  /* Get the value of a property from an edge of G. */
  default <
    ET extends EdgeType<?,ET,?, G,RV,RE>,
    X
  >
  X getProperty(EdgeType<?,ET,?, G,RV,RE>.Edge edge, Property<ET,X> property) {

    return raw().<X>getPropertyE(edge.raw, property._label);
  }

  /* Sets the value of a property for a vertex of G. */
  default <
    VT extends VertexType<VT,G,RV,RE>,
    X
  >
  G setProperty(VertexType<VT,G,RV,RE>.Vertex vertex, Property<VT,X> property, X value) {

    raw().setPropertyV(vertex.raw, property._label, value);
    return vertex.graph;
  }

  /* Sets the value of a property for an edge of G. */
  default <
    ET extends EdgeType<?,ET,?, G,RV,RE>,
    X
  >
  G setProperty(EdgeType<?,ET,?, G,RV,RE>.Edge edge, Property<ET,X> property, X value) {

    raw().setPropertyE(edge.raw, property._label, value);
    return edge.graph;
  }

  /*
    ### source and target

    gets the source of an edge of G, which could be of a different graph.
  */
  default <
    ST extends VertexType<ST, SG,RV,RE>,
    SG extends TypedGraph<SG,RV,RE>,
    ET extends EdgeType<ST,ET,?, G,RV,RE>
  >
  VertexType<ST, SG,RV,RE>.Vertex source(
    EdgeType<ST,ET,?, G,RV,RE>.Edge edge
  ) {

    return edge.type.sourceType.fromRaw(
      raw().source(edge.raw)
    );
  }

  // default <
  //   E  extends      TypedEdge<?,?, E,ET, T,TT, G,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, T,TT, G,RV,RE>,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // T target(E edge) {
  //
  //   return edge.type().targetType().fromRaw(
  //     raw().target(edge.raw())
  //   );
  // }


  /* #### Outgoing edges */
  default <
    ST extends VertexType<ST, G,RV,RE>,
    ET extends EdgeType<ST,ET,TT, EG,RV,RE>,
    TT extends VertexType<TT, ?,RV,RE>,
    EG extends TypedGraph<EG,RV,RE>
  >
  Stream<
    EdgeType<ST,ET,TT, EG,RV,RE>.Edge
  > outE(VertexType<ST, G,RV,RE>.Vertex source, ET edgeType) {

    return raw().outE(
      source.raw,
      edgeType._label
    ).map(
      edgeType::fromRaw
    );
  }

  // default <
  //   S  extends      TypedVertex<S,ST, G,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, G,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, ?,?, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, ?,?, ?,RV,RE>
  //            & TypedEdge.Type.ToAtLeastOne
  // >
  // Stream<E> outAtLeastOneE(S source, ET edgeType) {
  //
  //   return outE(source, edgeType);
  // }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, G,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, G,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, ?,?, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, ?,?, ?,RV,RE>
  //            & TypedEdge.Type.ToAtMostOne
  // >
  // Optional<E> outAtMostOneE(S source, ET edgeType) {
  //
  //   return outE(source, edgeType).findFirst();
  // }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, G,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, G,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, ?,?, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, ?,?, ?,RV,RE>
  //            & TypedEdge.Type.ToOne
  // >
  // E outOneE(S source, ET edgeType) {
  //
  //   return outE(source, edgeType).findFirst().get();
  // }
  //
  // /* #### Incoming edges */
  // default <
  //   E  extends      TypedEdge<?,?, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, T,TT, ?,RV,RE>,
  //   T  extends      TypedVertex<T,TT,G,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT,G,RV,RE>
  // >
  // Stream<E> inE(T vertex, ET edgeType) {
  //
  //   return raw().inE(
  //     vertex.raw(),
  //     edgeType._label()
  //   ).map(
  //     edgeType::fromRaw
  //   );
  // }
  //
  // default <
  //   E  extends      TypedEdge<?,?, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.FromAtLeastOne,
  //   T  extends      TypedVertex<T,TT, G,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, G,RV,RE>
  // >
  // Stream<E> inAtLeastOneE(T target, ET edgeType) { return inE(target, edgeType); }
  //
  // default <
  //   E  extends      TypedEdge<?,?, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.FromAtMostOne,
  //   T  extends      TypedVertex<T,TT, G,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, G,RV,RE>
  // >
  // Optional<E> inAtMostOneE(T target, ET edgeType) { return inE(target, edgeType).findFirst(); }
  //
  // default <
  //   E  extends      TypedEdge<?,?, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.FromOne,
  //   T  extends      TypedVertex<T,TT, G,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, G,RV,RE>
  // >
  // E inOneE(T target, ET edgeType) { return inE(target, edgeType).findFirst().get(); }
  //

  /* #### Outgoing vertices */
  default <
    ST extends VertexType<ST, G,RV,RE>,
    ET extends EdgeType<ST,ET,TT, ?,RV,RE>,
    TT extends VertexType<TT, TG,RV,RE>,
    TG extends TypedGraph<TG,RV,RE>
  >
  Stream<
    VertexType<TT, TG,RV,RE>.Vertex
  > outV(VertexType<ST, G,RV,RE>.Vertex source, ET edgeType) {

    return raw().outV(
      source.raw,
      edgeType._label
    ).map( rawT ->
      edgeType.targetType.fromRaw(rawT)
    );
  }

  // default <
  //   S  extends      TypedVertex<S,ST, G,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, G,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.ToAtLeastOne,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // Stream<T> outAtLeastOneV(S source, ET edgeType) { return outV(source, edgeType); }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, G,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, G,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.ToAtMostOne,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // Optional<T> outAtMostOneV(S source, ET edgeType) { return outV(source, edgeType).findFirst(); }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, G,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, G,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.ToOne,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // T outOneV(S source, ET edgeType) { return outV(source, edgeType).findFirst().get(); }
  //
  //
  // /* #### Incoming vertices */
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   T  extends      TypedVertex<T,TT, G,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, G,RV,RE>
  // >
  // Stream<S> inV(T target, ET edgeType) {
  //
  //   return raw().inV(
  //     target.raw(),
  //     edgeType._label()
  //   ).map(
  //     edgeType.sourceType()::fromRaw
  //   );
  // }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.FromAtLeastOne,
  //   T  extends      TypedVertex<T,TT, G,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, G,RV,RE>
  // >
  // Stream<S> inAtLeastOneV(T target, ET edgeType) { return inV(target, edgeType); }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.FromAtMostOne,
  //   T  extends      TypedVertex<T,TT, G,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, G,RV,RE>
  // >
  // Optional<S> inAtMostOneV(T target, ET edgeType) { return inV(target, edgeType).findFirst(); }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.FromOne,
  //   T  extends      TypedVertex<T,TT, G,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, G,RV,RE>
  // >
  // S inOneV(T target, ET edgeType) { return inV(target, edgeType).findFirst().get(); }

}
