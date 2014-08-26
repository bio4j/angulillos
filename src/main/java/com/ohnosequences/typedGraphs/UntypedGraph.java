package com.ohnosequences.typedGraphs;

import java.util.List;

/*
  This interface represents the vertex and edge types used by a particular graph storage technology. The four parameters are

  - `RV` the raw type used for vertices (like `TitanVertex`, `Node` in Neo4j, etc)
  - `RVT` the raw type used for vertex types (`String`s, `Label` in Neo4j, etc)
  - `RE` the raw type used for edge (like `TitanEdge`, `Relationship` in Neo4j)
  - `RET` the raw type used for edge types (like `TitanLabel`, or `Label` in Neo4j)
*/
public interface UntypedGraph<RV,RVT, RE,RET> {

  // TODO Stream
  List<RE> out(RV vertex, RET edgeType);
  List<RV> outV(RV vertex, RET edgeType);

  List<RE> in(RV vertex, RET edgeType);
  List<RV> inV(RV vertex, RET edgeType);

  RV source(RE edge);
  RV target(RE edge);

  <V> V getPropertyV(RV vertex, String property);
  <V> void setPropertyV(RV vertex, String property, V value);

  <V> V getPropertyE(RE edge, String property);
  <V> void setPropertyE(RE vertex, String property, V value);

  /*
    Adding stuff
  */
  RE addEdge(RV from, RET edgeType, RV to);
  RV addVertex(RVT type);

  void commit();
  void shutdown();
}