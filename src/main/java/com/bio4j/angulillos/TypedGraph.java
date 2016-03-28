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
  VT.Vertex addVertex(VT vertexType) {

    return vertexType.fromRaw(
      raw().addVertex( vertexType._label )
    );
  }

  /* adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G. */
  default <
    ST extends VertexType<ST, ?,RV,RE>,
    ET extends EdgeType<ST,?, ET,G, TT,?, RV,RE>,
    TT extends VertexType<TT, ?,RV,RE>
  >
  ET.Edge addEdge(
    VertexType<ST, ?,RV,RE>.Vertex source,
    ET edgeType,
    VertexType<TT, ?,RV,RE>.Vertex target
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
    ET extends EdgeType<?,?, ET,G, ?,?, RV,RE>,
    X
  >
  X getProperty(EdgeType<?,?, ET,G, ?,?, RV,RE>.Edge edge, Property<ET,X> property) {

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
    ET extends EdgeType<?,?, ET,G, ?,?, RV,RE>,
    X
  >
  G setProperty(EdgeType<?,?, ET,G, ?,?, RV,RE>.Edge edge, Property<ET,X> property, X value) {

    raw().setPropertyE(edge.raw, property._label, value);
    return edge.graph;
  }

  /*
    ### source and target

    gets the source of an edge of G, which could be of a different graph.
  */
  default <
    ST extends VertexType<ST, ?,RV,RE>,
    ET extends EdgeType<ST,?, ET,G, ?,?, RV,RE>
  >
  ST.Vertex source(
    EdgeType<ST,?, ET,G, ?,?, RV,RE>.Edge edge
  ) {

    return edge.type.sourceType.fromRaw(
      raw().source(edge.raw)
    );
  }

  default <
    ET extends EdgeType<?,?, ET,G, TT,?, RV,RE>,
    TT extends VertexType<TT, ?,RV,RE>
  >
  TT.Vertex target(
    EdgeType<?,?, ET,G, TT,?, RV,RE>.Edge edge
  ) {

    return edge.type.targetType.fromRaw(
      raw().target(edge.raw)
    );
  }

}
