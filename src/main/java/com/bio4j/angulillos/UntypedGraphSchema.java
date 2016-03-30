package com.bio4j.angulillos;


public interface UntypedGraphSchema<SM> {

  <
    VT extends VertexType<?,?,?,?>
  > SM createVertexType(SM schemaManager, VT vertexType);


  <
    ET extends EdgeType<?,?, ?,?, ?,?, ?,?>
  > SM createEdgeType(SM schemaManager, ET edgeType);


  <
    FT extends ElementType<FT,?,?>,
    P extends Property<FT,X>,
    X
  > SM createProperty(SM schemaManager, P property);


  default <
    VT extends VertexType<?,?,?,?>
  > SM createVertexTypeWithProperties(SM schemaManager, VT vertexType) {
    SM sm = createVertexType(schemaManager, vertexType);
    // TODO: some kind of fold here:
    vertexType.properties().forEach( p ->
      createProperty(sm, p)
    );
    return sm;
  }

  default <
    G extends TypedGraph<?,?,?>
  > SM createAllVertexTypes(SM schemaManager, G g) {
    // TODO: some kind of fold here:
    g.vertexTypes().forEach( (VertexType<?,?,?,?> vt) ->
      createVertexTypeWithProperties(schemaManager, vt)
    );
    return schemaManager;
  }

  // etc. for edges and all together

}
