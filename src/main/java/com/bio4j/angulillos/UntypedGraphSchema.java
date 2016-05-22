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
  SM createUniqueVertexIndex(SM schemaManager, TypedVertexIndex.Unique<?,?,?,?,?,?> index);

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
  default
  SM createSchema(SM schemaManager, AnyTypedGraph g) {
    // sm is a mutable accumulator passed around
    SM sm = schemaManager;

    /* Note that the type creation order is critical */

    for (AnyVertexType vt : g.vertexTypes()) {
      sm = createVertexType(sm, vt);
      sm = createProperties(sm, vt.properties());
    }

    for (TypedVertexIndex.Unique<?,?,?,?,?,?> ui: g.uniqueVertexIndexes() ) {
      sm = createUniqueVertexIndex(sm, ui);
    }

    for (AnyEdgeType et : g.edgeTypes()) {
      sm = createEdgeType(sm, et);
      sm = createProperties(sm, et.properties());
    }

    return sm;
  }

}
