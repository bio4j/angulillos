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
- `RET` for **R**aw **E**dge **T**ype, the raw type used for edge types (like `TitanLabel`, or `Label` in Neo4j)

Properties are represented using `String`s.
*/
public interface UntypedGraph<RV,RVT,RE,RET> {

  /*
  What the methods are supposed to do is I think pretty obvious from their names; there is anyway a short explanation for each.
  */

  /*
  #### methods on vertices
  */

  /*
  get from `vertex` the value of `property`
  */
  <V> V getPropertyV(RV vertex, String property);
  /*
  set the `value` of `property` in `vertex`
  */
  <V> void setPropertyV(RV vertex, String property, V value);

  /*
  get the edges of type `edgeType` _out_ of `vertex`
  */
  Optional<Stream<RE>> out(RV vertex, RET edgeType);
  /*
  get the _target_ vertices of the edges of type `edgeType` _out_ of `vertex`
  */
  Optional<Stream<RV>> outV(RV vertex, RET edgeType);

  /*
  get the edges of type `edgeType` _into_ `vertex`
  */
  Optional<Stream<RE>> in(RV vertex, RET edgeType);
  /*
  get the _source_ vertices of the edges of type `edgeType` _into_ `vertex`
  */
  Optional<Stream<RV>> inV(RV vertex, RET edgeType);

  /*
  #### methods on edges
  */

  /*
  get from `edge` the value of `property`
  */
  <V> V getPropertyE(RE edge, String property);
  /*
  set the `value` of `property` in `edge`
  */
  <V> void setPropertyE(RE vertex, String property, V value);

  /*
  get the source vertex of `edge`
  */
  RV source(RE edge);
  /*
  get the target vertex of `edge`
  */
  RV target(RE edge);

  /*
  #### create vertices and edges
  */

  /*
  returns a new edge of type `edgeType`, having source `from` and target `to`
  */
  RE addEdge(RV from, RET edgeType, RV to);
  /*
  returns a new vertex of type `vertexType`
  */
  RV addVertex(RVT vertexType);

  /*
  These two methods are here at this level just for convenience; they should be moved to `UntypedTransactionalGraph` or something like that.
  */
  void commit();
  void shutdown();
}