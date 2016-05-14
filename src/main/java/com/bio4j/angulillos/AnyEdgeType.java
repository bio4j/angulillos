package com.bio4j.angulillos;

/*
  ## Edges

  A typed edge with explicit source and target.

  - `S` the source TypedVertex, `ST` the source TypedVertex type
  - `E` the edge, `ET` the edge type
  - `T` the target TypedVertex, `TT` the target TypedVertex type
*/
public interface AnyEdgeType extends
  AnyElementType,
  HasFromArity,
  HasToArity
{

  AnyVertexType sourceType();
  AnyVertexType targetType();
}
