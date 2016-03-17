package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;

/*
  ## Untyped graph

  This interface represents a way of storing and working with the raw data. For example, if you have a graph database X you could perfectly have an implementation of `UntypedGraph` based on X.

  With respect to the type parameters, they represent the vertex and edge types used by this particular untyped graph. The four parameters are:

  - `RV` for **R**aw **V**ertex, the raw type used for vertices (like `TitanVertex`, `Node` in Neo4j, etc)
  - `RVT` for **R**aw **V**ertex **T**ype, the raw type used for vertex types (`String`s, `Label` in Neo4j, etc)
  - `RE` for **R**aw **E**dge, the raw type used for edge (like `TitanEdge`, `Relationship` in Neo4j)
  - `RET` for **R**aw **E**dge **T**ype, the raw type used for edge types (like `EdgeLabel`, or `Label` in Neo4j)

  Properties are represented using `String`s. What the methods are supposed to do is I think pretty obvious from their names; there is anyway a short explanation for each.
*/
public interface UntypedGraph<RV,RVT,RE,RET> {

  /* #### Methods on vertices */

  /* - Get from `vertex` the value of `property` */
  <V> V getPropertyV(RV vertex, String property);
  /* - Set the `value` of `property` in `vertex` */
  <V> RV setPropertyV(RV vertex, String property, V value);

  /* - Get the edges of type `edgeType` _out_ of `vertex` */
  Stream<RE> outE(RV vertex, RET edgeType);
  /* - Get the _target_ vertices of the edges of type `edgeType` _out_ of `vertex` */
  Stream<RV> outV(RV vertex, RET edgeType);

  /* - Get the edges of type `edgeType` _into_ `vertex` */
  Stream<RE> inE(RV vertex, RET edgeType);
  /* - Get the _source_ vertices of the edges of type `edgeType` _into_ `vertex` */
  Stream<RV> inV(RV vertex, RET edgeType);


  /* #### Methods on edges */

  /* - Get from `edge` the value of `property` */
  <V> V getPropertyE(RE edge, String property);
  /* - Set the `value` of `property` in `edge` */
  <V> RE setPropertyE(RE edge, String property, V value);

  /* - Get the source vertex of `edge` */
  RV source(RE edge);
  /* - Get the target vertex of `edge` */
  RV target(RE edge);


  /* #### Create vertices and edges */

  /* - Returns a new edge of type `edgeType`, having source `from` and target `to` */
  RE addEdge(RV from, RET edgeType, RV to);
  /* - Returns a new vertex of type `vertexType` */
  RV addVertex(RVT vertexType);

}
