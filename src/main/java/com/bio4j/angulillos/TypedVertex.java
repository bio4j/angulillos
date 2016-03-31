package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;


/*
  ## Vertex Type

  The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
interface TypedVertex <
  V  extends TypedGraph<G,RV,RE>.VertexType<VT>.Vertex,
  VT extends TypedGraph<G,RV,RE>.VertexType<VT>,
  G  extends TypedGraph<G,RV,RE>,
  RV,RE
> extends TypedElement<V,VT,RV, G,RV,RE> {

  // // NOTE: this is suuposed to enforce defining a non-public constructor for each vertex type to prevent creating instances of them outside of the graph
  // // But if it has no parameters, it will be overriden by default constructor
  // protected TypedVertex(RV raw) { super(raw); }

  // public final V vertex(VT type, RV raw) { type.new Vertex(raw); }

  // // NOTE: this call is typesafe, but the compiler cannot check it here, because the RV type in self() is not bound to be the same as we use from the enclousing class context
  // @SuppressWarnings("unchecked")
  // /* This method should be used for constructing _all_ instances of the Vertex inner class to get the precise return type */
  // public final VT.Vertex fromRaw(RV raw) { return self().new Vertex(raw); }


  // /* This method adds a new vertex of this type to the graph */
  // public VT.Vertex addVertex() {
  //   return fromRaw(
  //     graph().raw().addVertex( this._label )
  //   );
  // }


  // /* ### Properties */
  // @Override public
  // <X> X get(VT.Property<X> property) {
  //   return graph().raw().<X>getPropertyV(this.raw(), property._label);
  // }

  // @Override
  default
  <X> V set(VT.Property<X> property, X value) {

    graph().raw().setPropertyV(raw(), property._label, value);
    return self();
  }


  // /* #### Outgoing edges */
  //
  // public <
  //   ET extends EdgeType<VT,G, ET,?, ?,?, RV,RE>
  // >
  // Stream<ET.Edge> outE(ET edgeType) {
  //   return graph().raw()
  //     .outE(this.raw(), edgeType._label)
  //     .map( edgeType::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<VT,G, ET,?, ?,?, RV,RE> & ToAtLeastOne
  // >
  // Stream<ET.Edge> outAtLeastOneE(ET edgeType) {
  //   return graph().raw()
  //     .outAtLeastOneE(this.raw(), edgeType._label)
  //     .map( edgeType::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<VT,G, ET,?, ?,?, RV,RE> & ToAtMostOne
  // >
  // Optional<ET.Edge> outAtMostOneE(ET edgeType) {
  //   return graph().raw()
  //     .outAtMostOneE(this.raw(), edgeType._label)
  //     .map( edgeType::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<VT,G, ET,?, ?,?, RV,RE> & ToOne
  // >
  // ET.Edge outOneE(ET edgeType) {
  //   return edgeType.fromRaw(
  //     graph().raw().outOneE(this.raw(), edgeType._label)
  //   );
  // }
  //
  // /* #### Incoming edges */
  //
  // public <
  //   ET extends EdgeType<?,?, ET,?, VT,G, RV,RE>
  // >
  // Stream<ET.Edge> inE(ET edgeType) {
  //   return graph().raw()
  //     .inE(this.raw(), edgeType._label)
  //     .map( edgeType::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<?,?, ET,?, VT,G, RV,RE> & FromAtLeastOne
  // >
  // Stream<ET.Edge> inAtLeastOneE(ET edgeType) {
  //   return graph().raw()
  //     .inAtLeastOneE(this.raw(), edgeType._label)
  //     .map( edgeType::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<?,?, ET,?, VT,G, RV,RE> & FromAtMostOne
  // >
  // Optional<ET.Edge> inAtMostOneE(ET edgeType) {
  //   return graph().raw()
  //     .inAtMostOneE(this.raw(), edgeType._label)
  //     .map( edgeType::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<?,?, ET,?, VT,G, RV,RE> & FromOne
  // >
  // ET.Edge inOneE(ET edgeType) {
  //   return edgeType.fromRaw(
  //     graph().raw().inOneE(this.raw(), edgeType._label)
  //   );
  // }
  //
  //
  // /* #### Outgoing vertices */
  //
  // public <
  //   ET extends EdgeType<VT,G, ET,?, TT,?, RV,RE>,
  //   TT extends VertexType<TT, ?,RV,RE>
  // >
  // Stream<TT.Vertex> outV(ET edgeType) {
  //   return graph().raw()
  //     .outV(this.raw(), edgeType._label)
  //     .map( edgeType.targetType()::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<VT,G, ET,?, TT,?, RV,RE> & ToAtLeastOne,
  //   TT extends VertexType<TT, ?,RV,RE>
  // >
  // Stream<TT.Vertex> outAtLeastOneV(ET edgeType) {
  //   return graph().raw()
  //     .outAtLeastOneV(this.raw(), edgeType._label)
  //     .map( edgeType.targetType()::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<VT,G, ET,?, TT,?, RV,RE> & ToAtMostOne,
  //   TT extends VertexType<TT, ?,RV,RE>
  // >
  // Optional<TT.Vertex> outAtMostOneV(ET edgeType) {
  //   return graph().raw()
  //     .outAtMostOneV(this.raw(), edgeType._label)
  //     .map( edgeType.targetType()::fromRaw );
  // }
  //
  // public <
  //   ET extends EdgeType<VT,G, ET,?, TT,?, RV,RE> & ToOne,
  //   TT extends VertexType<TT, ?,RV,RE>
  // >
  // TT.Vertex outOneV(ET edgeType) {
  //   return edgeType.targetType().fromRaw(
  //     graph().raw().outOneV(this.raw(), edgeType._label)
  //   );
  // }
  //
  // /* #### Incoming vertices */
  //
  // public <
  //   ST extends VertexType<ST, ?,RV,RE>,
  //   ET extends EdgeType<ST,?, ET,?, VT,G, RV,RE>
  // >
  // Stream<ST.Vertex> inV(ET edgeType) {
  //   return graph().raw()
  //     .inV(this.raw(), edgeType._label)
  //     .map( edgeType.sourceType()::fromRaw );
  // }
  //
  // public <
  //   ST extends VertexType<ST, ?,RV,RE>,
  //   ET extends EdgeType<ST,?, ET,?, VT,G, RV,RE> & FromAtLeastOne
  // >
  // Stream<ST.Vertex> inAtLeastOneV(ET edgeType) {
  //   return graph().raw()
  //     .inAtLeastOneV(this.raw(), edgeType._label)
  //     .map( edgeType.sourceType()::fromRaw );
  // }
  //
  // public <
  //   ST extends VertexType<ST, ?,RV,RE>,
  //   ET extends EdgeType<ST,?, ET,?, VT,G, RV,RE> & FromAtMostOne
  // >
  // Optional<ST.Vertex> inAtMostOneV(ET edgeType) {
  //   return graph().raw()
  //     .inAtMostOneV(this.raw(), edgeType._label)
  //     .map( edgeType.sourceType()::fromRaw );
  // }
  //
  // public <
  //   ST extends VertexType<ST, ?,RV,RE>,
  //   ET extends EdgeType<ST,?, ET,?, VT,G, RV,RE> & FromOne
  // >
  // ST.Vertex inOneV(ET edgeType) {
  //   return edgeType.sourceType().fromRaw(
  //     graph().raw().inOneV(this.raw(), edgeType._label)
  //   );
  // }

}
