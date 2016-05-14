package com.bio4j.angulillos;

import java.util.Set;

/*
  ## Typed Vertices

  A typed vertex. A vertex and its type need to be defined at the same time. The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
public interface AnyVertexType extends AnyElementType {

  Set<AnyEdgeType> inEdges();
  Set<AnyEdgeType> outEdges();
}
