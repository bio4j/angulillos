package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
// TODO move to TypedElementIndex

/*
  ## Vertex Indices

  A vertex index indexes vertices of a given type through values of one of its properties. This just adds a bound on the indexed type to be a TypedVertex; see `TypedElementIndex`
*/
interface TypedVertexIndex <
  N extends TypedVertex<N,NT,G,I,RV,RE>,
  NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
  P extends Property<N,NT,P,V,G,I,RV,RE>, V,
  G extends TypedGraph<G,I,RV,RE>,
  I extends UntypedGraph<RV,RE>, RV,RE
>
extends
  TypedElementIndex<N,NT,P,V,G,I,RV,RE>
{

  default NT vertexType() { return elementType(); }

  /* This interface declares that this index is over a property that uniquely classifies a vertex type for exact match queries; it adds the method `getTypedVertex` for that.  */
  interface Unique <
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
    P extends Property<N,NT,P,V,G,I,RV,RE>, V,
    G extends TypedGraph<G,I,RV,RE>,
    I extends UntypedGraph<RV,RE>, RV,RE
  >
  extends
    TypedVertexIndex<N,NT,P,V,G,I,RV,RE>,
    TypedElementIndex.Unique<N,NT,P,V,G,I,RV,RE>
  {

    /* get a vertex by providing a value of the indexed property. The default implementation relies on `query`. */
    default Optional<N> getVertex(V byValue) { return getElement(byValue); }
  }

  /* This interface declares that this index is over a property that classifies lists of vertices for exact match queries; it adds the method `getTypedVertexs` for that.  */
  interface List <
    N extends TypedVertex<N,NT,G,I,RV,RE>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RE>,
    P extends Property<N,NT,P,V,G,I,RV,RE>, V,
    G extends TypedGraph<G,I,RV,RE>,
    I extends UntypedGraph<RV,RE>, RV,RE
  >
  extends
    TypedVertexIndex<N,NT,P,V,G,I,RV,RE>,
    TypedElementIndex.List<N,NT,P,V,G,I,RV,RE>
  {

    /* get a list of vertices by providing a value of the property. The default */
    default Stream<N> getVertices(V byValue) { return getElements(byValue); }
  }

}
