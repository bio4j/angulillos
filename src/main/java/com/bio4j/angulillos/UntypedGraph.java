package com.bio4j.angulillos;

import java.util.stream.Stream;


/*
  ## Untyped graph

  This interface represents a way of storing and working with the raw data. For example, if you have a graph database X you could perfectly have an implementation of `UntypedGraph` based on X.

  With respect to the type parameters, they represent the vertex and edge types used by this particular untyped graph. The four parameters are:

  - `RV` for **R**aw **V**ertex, the raw type used for vertices (like `TitanVertex`, `Node` in Neo4j, etc)
  - `RE` for **R**aw **E**dge, the raw type used for edge (like `TitanEdge`, `Relationship` in Neo4j)

  Properties are represented using `String`s. What the methods are supposed to do is I think pretty obvious from their names; there is anyway a short explanation for each.
*/
interface UntypedGraph<RV,RE> {

  /* #### Methods on vertices */

  /* - Get from `vertex` the value of `property` */
  <X> X getPropertyV(RV vertex, String property);
  /* - Set the `value` of `property` in `vertex` */
  <X> RV setPropertyV(RV vertex, String property, X value);

  /* - Get the edges of type `edgeType` _out_ of `vertex` */
  Stream<RE> outE(RV vertex, String edgeLabel);
  /* - Get the _target_ vertices of the edges of type `edgeType` _out_ of `vertex` */
  Stream<RV> outV(RV vertex, String edgeLabel);

  /* - Get the edges of type `edgeType` _into_ `vertex` */
  Stream<RE> inE(RV vertex, String edgeLabel);
  /* - Get the _source_ vertices of the edges of type `edgeType` _into_ `vertex` */
  Stream<RV> inV(RV vertex, String edgeLabel);


  /* #### Methods on edges */

  /* - Get from `edge` the value of `property` */
  <X> X getPropertyE(RE edge, String property);
  /* - Set the `value` of `property` in `edge` */
  <X> RE setPropertyE(RE edge, String property, X value);

  /* - Get the source vertex of `edge` */
  RV source(RE edge);
  /* - Get the target vertex of `edge` */
  RV target(RE edge);


  /* #### Create vertices and edges */

  /* - Returns a new edge: source -[edgeLabel]-> target */
  RE addEdge(RV source, String edgeLabel, RV target);
  /* - Returns a new vertex of type `vertexType` */
  RV addVertex(String vertexLabel);


  /* These two methods are here at this level just for convenience;
     they should be moved to `UntypedTransactionalGraph` or something like that. */
  void commit();
  void shutdown();

}
