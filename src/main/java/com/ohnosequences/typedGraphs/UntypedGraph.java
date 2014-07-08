package com.ohnosequences.typedGraphs;

import java.util.List;

/*
  This interface represents the vertex and edge types used by a particular graph storage technology. The four parameters are

  - `RV` the raw type used for vertices (like `TitanVertex`, `Node` in Neo4j, etc)
  - `RVT` the raw type used for vertex types (`String`s, `Label` in Neo4j, etc)
  - `RE` the raw type used for edge (like `TitanEdge`, `Relationship` in Neo4j)
  - `RET` the raw type used for edge types (like `TitanLabel` or `Label` in Neo4j)
*/
public interface UntypedGraph<RV,RVT, RE,RET> {

  // TODO add methods for in out at the level of raw types
  // TODO iterable?
  List<RE> out(RV vertex, RET edgeType);
  List<RV> outV(RV vertex, RET edgeType);

  List<RE> in(RV vertex, RET edgeType);
  List<RV> inV(RV vertex, RET edgeType);

  <V> V getPropertyV(RV vertex, String property);
  <V> V getPropertyE(RE edge, String property);
  <V> void setPropertyV(RV vertex, String property, V value);


  RE addEdge(RV from, RET edgeType, RV to);
}