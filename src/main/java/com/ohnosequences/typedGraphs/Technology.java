package com.ohnosequences.typedGraphs;

/*
  This interface represents the vertex and edge types used by a particular graph storage technology. The four parameters are

  - `RV` the raw type used for vertices (like `TitanVertex`, `Node` in Neo4j, etc)
  - `RVT` the raw type used for vertex types (`String`s, `Label` in Neo4j, etc)
  - `RE`
  - `RET`
*/
public interface Technology<RV,RVT, RE,RET> {

  // TODO add methods for in out at the level of raw types
  // TODO iterable?
  public Iterable<RE> rawOut(RV vertex, RET edgeType);
  public Iterable<RV> rawOutNodes(RV vertex, RET edgeType);

  public Iterable<RE> rawIn(RV vertex, RET edgeType);
  public Iterable<RV> rawInNodes(RV vertex, RET edgeType);
}