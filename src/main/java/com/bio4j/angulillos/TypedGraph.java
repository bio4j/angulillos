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
    V  extends      TypedVertex<V,VT, G,RV,RE>,
    VT extends TypedVertex.Type<V,VT, G,RV,RE>
  >
  V addVertex(VT vertexType) {

    return vertexType.fromRaw(
      raw().addVertex( vertexType.name() )
    );
  }

  /* adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G. */
  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
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
    N  extends      TypedVertex<N,NT, G,RV,RE>,
    NT extends TypedVertex.Type<N,NT, G,RV,RE>,
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
    N  extends      TypedVertex<N,NT, G,RV,RE>,
    NT extends TypedVertex.Type<N,NT, G,RV,RE>,
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
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
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
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  T target(R edge) {

    return edge.type().targetType().fromRaw(
      raw().target(edge.raw())
    );
  }


  /* #### Outgoing edges */
  default <
    S  extends      TypedVertex<S,ST,G,RV,RE>,
    ST extends TypedVertex.Type<S,ST,G,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, ?,?, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, ?,?, ?,RV,RE>
  >
  Stream<R> outE(S source, RT relType) {

    return raw().outE(
      source.raw(),
      relType.name()
    ).map(
      relType::fromRaw
    );
  }

  default <
    S  extends      TypedVertex<S,ST, G,RV,RE>,
    ST extends TypedVertex.Type<S,ST, G,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, ?,?, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, ?,?, ?,RV,RE>
             & TypedEdge.Type.ToAtLeastOne
  >
  Stream<R> outAtLeastOneE(S source, RT relType) {

    return outE(source, relType);
  }

  default <
    S  extends      TypedVertex<S,ST, G,RV,RE>,
    ST extends TypedVertex.Type<S,ST, G,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, ?,?, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, ?,?, ?,RV,RE>
             & TypedEdge.Type.ToAtMostOne
  >
  Optional<R> outAtMostOneE(S source, RT relType) {

    return outE(source, relType).findFirst();
  }

  default <
    S  extends      TypedVertex<S,ST, G,RV,RE>,
    ST extends TypedVertex.Type<S,ST, G,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, ?,?, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, ?,?, ?,RV,RE>
             & TypedEdge.Type.ToOne
  >
  R outOneE(S source, RT relType) {

    return outE(source, relType).findFirst().get();
  }

  /* #### Incoming edges */
  default <
    R  extends      TypedEdge<?,?, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT,G,RV,RE>,
    TT extends TypedVertex.Type<T,TT,G,RV,RE>
  >
  Stream<R> inE(T node, RT relType) {

    return raw().inE(
      node.raw(),
      relType.name()
    ).map(
      relType::fromRaw
    );
  }

  default <
    R  extends      TypedEdge<?,?, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.FromAtLeastOne,
    T  extends      TypedVertex<T,TT, G,RV,RE>,
    TT extends TypedVertex.Type<T,TT, G,RV,RE>
  >
  Stream<R> inAtLeastOneE(T target, RT relType) { return inE(target, relType); }

  default <
    R  extends      TypedEdge<?,?, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.FromAtMostOne,
    T  extends      TypedVertex<T,TT, G,RV,RE>,
    TT extends TypedVertex.Type<T,TT, G,RV,RE>
  >
  Optional<R> inAtMostOneE(T target, RT relType) { return inE(target, relType).findFirst(); }

  default <
    R  extends      TypedEdge<?,?, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<?,?, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.FromOne,
    T  extends      TypedVertex<T,TT, G,RV,RE>,
    TT extends TypedVertex.Type<T,TT, G,RV,RE>
  >
  R inOneE(T target, RT relType) { return inE(target, relType).findFirst().get(); }


  /* #### Outgoing vertices */
  default <
    S  extends      TypedVertex<S,ST, G,RV,RE>,
    ST extends TypedVertex.Type<S,ST, G,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Stream<T> outV(S source, RT relType) {

    return raw().outV(
      source.raw(),
      relType.name()
    ).map(
      relType.targetType()::fromRaw
    );
  }

  default <
    S  extends      TypedVertex<S,ST, G,RV,RE>,
    ST extends TypedVertex.Type<S,ST, G,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.ToAtLeastOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Stream<T> outAtLeastOneV(S source, RT relType) { return outV(source, relType); }

  default <
    S  extends      TypedVertex<S,ST, G,RV,RE>,
    ST extends TypedVertex.Type<S,ST, G,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.ToAtMostOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  Optional<T> outAtMostOneV(S source, RT relType) { return outV(source, relType).findFirst(); }

  default <
    S  extends      TypedVertex<S,ST, G,RV,RE>,
    ST extends TypedVertex.Type<S,ST, G,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.ToOne,
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  >
  T outOneV(S source, RT relType) { return outV(source, relType).findFirst().get(); }


  /* #### Incoming vertices */
  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>,
    T  extends      TypedVertex<T,TT, G,RV,RE>,
    TT extends TypedVertex.Type<T,TT, G,RV,RE>
  >
  Stream<S> inV(T target, RT relType) {

    return raw().inV(
      target.raw(),
      relType.name()
    ).map(
      relType.sourceType()::fromRaw
    );
  }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.FromAtLeastOne,
    T  extends      TypedVertex<T,TT, G,RV,RE>,
    TT extends TypedVertex.Type<T,TT, G,RV,RE>
  >
  Stream<S> inAtLeastOneV(T target, RT relType) { return inV(target, relType); }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.FromAtMostOne,
    T  extends      TypedVertex<T,TT, G,RV,RE>,
    TT extends TypedVertex.Type<T,TT, G,RV,RE>
  >
  Optional<S> inAtMostOneV(T target, RT relType) { return inV(target, relType).findFirst(); }

  default <
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    R  extends      TypedEdge<S,ST, R,RT, T,TT, ?,RV,RE>,
    RT extends TypedEdge.Type<S,ST, R,RT, T,TT, ?,RV,RE>
             & TypedEdge.Type.FromOne,
    T  extends      TypedVertex<T,TT, G,RV,RE>,
    TT extends TypedVertex.Type<T,TT, G,RV,RE>
  >
  S inOneV(T target, RT relType) { return inV(target, relType).findFirst().get(); }

}
