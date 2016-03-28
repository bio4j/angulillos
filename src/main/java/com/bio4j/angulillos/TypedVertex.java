package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;


/*
  ## Typed Vertices

  A typed vertex. A vertex and its type need to be defined at the same time. The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
abstract class VertexType <
  VT extends VertexType<VT, G,RV,RE>,
  G  extends TypedGraph<G,RV,RE>,
  RV,RE
> extends ElementType<VT,G,RV> {

  // NOTE: this call is typesafe, but the compiler cannot check it here, because the RV type in self() is not bound to be the same as we use from the enclousing class context
  @SuppressWarnings("unchecked")
  /* This method should be used for constructing _all_ instances of the Vertex inner class to get the precise return type */
  public final VT.Vertex fromRaw(RV raw) { return self().new Vertex(raw); }

  public final class Vertex extends Element {

    private Vertex(RV raw) { super(raw); }


    /* ### Properties */
    @Override public
    <X> X get(Property<VT,X> property) { return graph.getProperty(this, property); }

    @Override public
    <X> Vertex set(Property<VT,X> property, X value) {

      graph.setProperty(this, property, value);
      return this;
    }


    /* #### Outgoing edges */

    public <
      ET extends EdgeType<VT,G, ET,?, ?,?, RV,RE>
    >
    Stream<ET.Edge> outE(ET edgeType) {
      return graph.raw()
        .outE(this.raw, edgeType._label)
        .map( edgeType::fromRaw );
    }

    public <
      ET extends EdgeType<VT,G, ET,?, ?,?, RV,RE> & ToAtLeastOne
    >
    Stream<ET.Edge> outAtLeastOneE(ET edgeType) {
      return graph.raw()
        .outAtLeastOneE(this.raw, edgeType._label)
        .map( edgeType::fromRaw );
    }

    public <
      ET extends EdgeType<VT,G, ET,?, ?,?, RV,RE> & ToAtMostOne
    >
    Optional<ET.Edge> outAtMostOneE(ET edgeType) {
      return graph.raw()
        .outAtMostOneE(this.raw, edgeType._label)
        .map( edgeType::fromRaw );
    }

    public <
      ET extends EdgeType<VT,G, ET,?, ?,?, RV,RE> & ToOne
    >
    ET.Edge outOneE(ET edgeType) {
      return edgeType.fromRaw(
        graph.raw().outOneE(this.raw, edgeType._label)
      );
    }

    /* #### Incoming edges */

    public <
      ET extends EdgeType<?,?, ET,?, VT,G, RV,RE>
    >
    Stream<ET.Edge> inE(ET edgeType) {
      return graph.raw()
        .inE(this.raw, edgeType._label)
        .map( edgeType::fromRaw );
    }

    public <
      ET extends EdgeType<?,?, ET,?, VT,G, RV,RE> & FromAtLeastOne
    >
    Stream<ET.Edge> inAtLeastOneE(ET edgeType) {
      return graph.raw()
        .inAtLeastOneE(this.raw, edgeType._label)
        .map( edgeType::fromRaw );
    }

    public <
      ET extends EdgeType<?,?, ET,?, VT,G, RV,RE> & FromAtMostOne
    >
    Optional<ET.Edge> inAtMostOneE(ET edgeType) {
      return graph.raw()
        .inAtMostOneE(this.raw, edgeType._label)
        .map( edgeType::fromRaw );
    }

    public <
      ET extends EdgeType<?,?, ET,?, VT,G, RV,RE> & FromOne
    >
    ET.Edge inOneE(ET edgeType) {
      return edgeType.fromRaw(
        graph.raw().inOneE(this.raw, edgeType._label)
      );
    }


    /* #### Outgoing vertices */

    public <
      ET extends EdgeType<VT,G, ET,?, TT,?, RV,RE>,
      TT extends VertexType<TT, ?,RV,RE>
    >
    Stream<TT.Vertex> outV(ET edgeType) {
      return graph.raw()
        .outV(this.raw, edgeType._label)
        .map( edgeType.targetType::fromRaw );
    }

    public <
      ET extends EdgeType<VT,G, ET,?, TT,?, RV,RE> & ToAtLeastOne,
      TT extends VertexType<TT, ?,RV,RE>
    >
    Stream<TT.Vertex> outAtLeastOneV(ET edgeType) {
      return graph.raw()
        .outAtLeastOneV(this.raw, edgeType._label)
        .map( edgeType.targetType::fromRaw );
    }

    public <
      ET extends EdgeType<VT,G, ET,?, TT,?, RV,RE> & ToAtMostOne,
      TT extends VertexType<TT, ?,RV,RE>
    >
    Optional<TT.Vertex> outAtMostOneV(ET edgeType) {
      return graph.raw()
        .outAtMostOneV(this.raw, edgeType._label)
        .map( edgeType.targetType::fromRaw );
    }

    public <
      ET extends EdgeType<VT,G, ET,?, TT,?, RV,RE> & ToOne,
      TT extends VertexType<TT, ?,RV,RE>
    >
    TT.Vertex outOneV(ET edgeType) {
      return edgeType.targetType.fromRaw(
        graph.raw().outOneV(this.raw, edgeType._label)
      );
    }

    /* #### Incoming vertices */

    public <
      ST extends VertexType<ST, ?,RV,RE>,
      ET extends EdgeType<ST,?, ET,?, VT,G, RV,RE>
    >
    Stream<ST.Vertex> inV(ET edgeType) {
      return graph.raw()
        .inV(this.raw, edgeType._label)
        .map( edgeType.sourceType::fromRaw );
    }

    public <
      ST extends VertexType<ST, ?,RV,RE>,
      ET extends EdgeType<ST,?, ET,?, VT,G, RV,RE> & FromAtLeastOne
    >
    Stream<ST.Vertex> inAtLeastOneV(ET edgeType) {
      return graph.raw()
        .inAtLeastOneV(this.raw, edgeType._label)
        .map( edgeType.sourceType::fromRaw );
    }

    public <
      ST extends VertexType<ST, ?,RV,RE>,
      ET extends EdgeType<ST,?, ET,?, VT,G, RV,RE> & FromAtMostOne
    >
    Optional<ST.Vertex> inAtMostOneV(ET edgeType) {
      return graph.raw()
        .inAtMostOneV(this.raw, edgeType._label)
        .map( edgeType.sourceType::fromRaw );
    }

    public <
      ST extends VertexType<ST, ?,RV,RE>,
      ET extends EdgeType<ST,?, ET,?, VT,G, RV,RE> & FromOne
    >
    ST.Vertex inOneV(ET edgeType) {
      return edgeType.sourceType.fromRaw(
        graph.raw().inOneV(this.raw, edgeType._label)
      );
    }

  }

}
