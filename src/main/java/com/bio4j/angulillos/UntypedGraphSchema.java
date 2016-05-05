package com.bio4j.angulillos;


public interface UntypedGraphSchema<SM, G extends TypedGraph<G,?,?>> {

  <
    VT extends TypedGraph<G,?,?>.TypedVertex<?>
    // VT extends TypedVertex.Type<?,VT,?,?,?>
  > SM createVertexType(SM schemaManager, VT vertexType);


  <
    ET extends TypedEdge.Type<?,?, ?,?, ?,?, ?,?,?>
  > SM createEdgeType(SM schemaManager, ET edgeType);


  <
    FT extends TypedElement.Type<?,FT,?,?>,
    P extends Property<FT,X>,
    X
  > SM createProperty(SM schemaManager, FT elementType, P property);


  // default <
  //   VT extends TypedVertex.Type<?,VT,?,?,?>
  // > SM createVertexTypeWithProperties(SM schemaManager, VT vertexType) {
  //   SM sm = createVertexType(schemaManager, vertexType);
  //   // TODO: some kind of fold here:
  //   return sm;
  // }

  default SM createAllVertexTypes(SM schemaManager, G g) {
    // TODO: some kind of fold here:
    g.vertexTypes().forEach( vt -> {
        createVertexType(schemaManager, vt);
      }
      // vt.properties().forEach( p ->
      //   createProperty(sm, vertexType, p)
      // );
    );
    return schemaManager;
  }

  // etc. for edges and all together

}
