package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;

/*
  ## Untyped graph

  This interface represents a way of storing and working with the raw data. For example, if you have a graph database X you could perfectly have an implementation of `UntypedGraph` based on X.

  With respect to the type parameters, they represent the vertex and edge types used by this particular untyped graph. The four parameters are:

  - `RV` for **R**aw **V**ertex, the raw type used for vertices (like `TitanVertex`, `Node` in Neo4j, etc)
  - `RE` for **R**aw **E**dge, the raw type used for edge (like `TitanEdge`, `Relationship` in Neo4j)

  Properties are represented using `String`s. What the methods are supposed to do is I think pretty obvious from their names; there is anyway a short explanation for each.
*/
public interface UntypedGraph<RV,RE> {

  /* #### Methods on vertices */

  /* - Get from `vertex` the value of `property` */
  <X> X getPropertyV(RV vertex, AnyProperty property);
  /* - Set the `value` of `property` in `vertex` */
  <X> RV setPropertyV(RV vertex, AnyProperty property, X value);

  /* - Get the edges of type `edgeType` _out_ of `vertex` */
  Stream<RE> outE(RV vertex, AnyEdgeType edgeType);
  default Stream<RE>  outAtLeastOneE(RV vertex, AnyEdgeType edgeType) { return outE(vertex, edgeType); }
  default Optional<RE> outAtMostOneE(RV vertex, AnyEdgeType edgeType) { return outE(vertex, edgeType).findFirst(); }
  default RE                 outOneE(RV vertex, AnyEdgeType edgeType) { return outE(vertex, edgeType).findFirst().get(); }

  /* - Get the _target_ vertices of the edges of type `edgeType` _out_ of `vertex` */
  Stream<RV> outV(RV vertex, AnyEdgeType edgeType);
  default Stream<RV>  outAtLeastOneV(RV vertex, AnyEdgeType edgeType) { return outV(vertex, edgeType); }
  default Optional<RV> outAtMostOneV(RV vertex, AnyEdgeType edgeType) { return outV(vertex, edgeType).findFirst(); }
  default RV                 outOneV(RV vertex, AnyEdgeType edgeType) { return outV(vertex, edgeType).findFirst().get(); }


  /* - Get the edges of type `edgeType` _into_ `vertex` */
  Stream<RE> inE(RV vertex, AnyEdgeType edgeType);
  default Stream<RE>  inAtLeastOneE(RV vertex, AnyEdgeType edgeType) { return inE(vertex, edgeType); }
  default Optional<RE> inAtMostOneE(RV vertex, AnyEdgeType edgeType) { return inE(vertex, edgeType).findFirst(); }
  default RE                 inOneE(RV vertex, AnyEdgeType edgeType) { return inE(vertex, edgeType).findFirst().get(); }

  /* - Get the _source_ vertices of the edges of type `edgeType` _into_ `vertex` */
  Stream<RV> inV(RV vertex, AnyEdgeType edgeType);
  default Stream<RV>  inAtLeastOneV(RV vertex, AnyEdgeType edgeType) { return inV(vertex, edgeType); }
  default Optional<RV> inAtMostOneV(RV vertex, AnyEdgeType edgeType) { return inV(vertex, edgeType).findFirst(); }
  default RV                 inOneV(RV vertex, AnyEdgeType edgeType) { return inV(vertex, edgeType).findFirst().get(); }


  /* #### Methods on edges */

  /* - Get from `edge` the value of `property` */
  <X> X getPropertyE(RE edge, AnyProperty property);
  /* - Set the `value` of `property` in `edge` */
  <X> RE setPropertyE(RE edge, AnyProperty property, X value);

  /* - Get the source vertex of `edge` */
  RV source(RE edge);
  /* - Get the target vertex of `edge` */
  RV target(RE edge);


  /* #### Create vertices and edges */

  /* - Returns a new edge: source -[edgeType]-> target */
  RE addEdge(RV source, AnyEdgeType edgeType, RV target);
  /* - Returns a new vertex of type `vertexType` */
  RV addVertex(AnyVertexType vertexType);

  public interface Transactional<RV,RE> extends UntypedGraph<RV,RE> {

    void commit();
    void rollback();
    void shutdown();
  }
}
