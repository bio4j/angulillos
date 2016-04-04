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

  // default <
  //   V  extends      TypedVertex<V,VT, G,RV,RE>,
  //   VT extends TypedVertex.Type<V,VT, G,RV,RE>
  // >
  // V addVertex(VT vertexType) {
  //
  //   return vertexType.fromRaw(
  //     raw().addVertex( vertexType._label() )
  //   );
  // }
  //
  // /* adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G. */
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // E addEdge(S from, ET edgeType, T to) {
  //
  //   return edgeType.fromRaw(
  //     raw().addEdge( from.raw(), edgeType._label(), to.raw() )
  //   );
  // }

  /*
    ### properties
      foobarbuh
    These methods are used for setting and getting properties on vertices and edges.
  */
  default <
    V  extends TypedVertex<V, G,RV,RE>,
    X
  >
  X getProperty(V vertex, Property<V,X> property) {

    return raw().<X>getPropertyV(vertex.raw(), property._label);
  }

  /* Get the value of a property from an edge of G. */
  default <
    E  extends TypedEdge<?,E,?, G,RV,RE>,
    X
  >
  X getProperty(E edge, Property<E,X> property) {

    return raw().<X>getPropertyE(edge.raw(), property._label);
  }

  /* Sets the value of a property for a vertex of G. */
  default <
    V  extends TypedVertex<V, G,RV,RE>,
    X
  >
  G setProperty(V vertex, Property<V,X> property, X value) {

    raw().setPropertyV(vertex.raw(), property._label, value);
    return vertex.graph();
  }

  /* Sets the value of a property for an edge of G. */
  default <
    E  extends TypedEdge<?,E,?, G,RV,RE>,
    X
  >
  G setProperty(E edge, Property<E,X> property, X value) {

    raw().setPropertyE(edge.raw(), property._label, value);
    return edge.graph();
  }

  /*
    ### source and target

    gets the source of an edge of G, which could be of a different graph.
  */
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, ?,?, G,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, ?,?, G,RV,RE>
  // >
  // S source(E edge) {
  //
  //   return edge.type().sourceType().fromRaw(
  //     raw().source(edge.raw())
  //   );
  // }
  //
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


  // /* #### Outgoing edges */
  // default <
  //   S  extends      TypedVertex<S,ST,G,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST,G,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, ?,?, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, ?,?, ?,RV,RE>
  // >
  // Stream<E> outE(S source, ET edgeType) {
  //
  //   return raw().outE(
  //     source.raw(),
  //     edgeType._label()
  //   ).map(
  //     edgeType::fromRaw
  //   );
  // }
  //
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
  //
  // /* #### Outgoing vertices */
  // default <
  //   S  extends      TypedVertex<S,ST, G,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, G,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, T,TT, ?,RV,RE>,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // Stream<T> outV(S source, ET edgeType) {
  //
  //   return raw().outV(
  //     source.raw(),
  //     edgeType._label()
  //   ).map(
  //     edgeType.targetType()::fromRaw
  //   );
  // }
  //
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
