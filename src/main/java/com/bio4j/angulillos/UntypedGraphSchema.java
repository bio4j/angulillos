package com.bio4j.angulillos;

import java.util.Set;

public interface UntypedGraphSchema<SM> {

  /* This method should take into account vertex label */
  SM createVertexType(SM schemaManager, AnyVertexType vertexType);

  /* This method should take into account edge label, source/target types and to/from-arities */
  SM createEdgeType(SM schemaManager, AnyEdgeType edgeType);

  /* This method should take into account property's element type and from-arity */
  SM createProperty(SM schemaManager, AnyProperty property);

  // TODO: indexes

  // This is like properties.foldLeft(schemaManager)(createProperty)
  default
  SM createProperties(SM schemaManager, Set<AnyProperty> properties) {
    // sm is a mutable accumulator passed around
    SM sm = schemaManager;

    for (AnyProperty p : properties) {
      sm = createProperty(sm, p);
    }

    return sm;
  }


  /* Creates all graph's vertex/edge types with their properties */
  default <
    G extends AnyTypedGraph
  > SM createAllVertexTypes(SM schemaManager, G g) {
    // sm is a mutable accumulator passed around
    SM sm = schemaManager;

    for (AnyVertexType vt : g.vertexTypes()) {
      sm = createVertexType(sm, vt);
      sm = createProperties(sm, vt.properties());
    }

    for (AnyEdgeType et : g.edgeTypes()) {
      sm = createEdgeType(sm, et);
      sm = createProperties(sm, et.properties());
    }

    return sm;
  }

}
