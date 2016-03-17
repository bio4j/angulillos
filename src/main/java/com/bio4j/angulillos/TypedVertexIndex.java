package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
// TODO move to TypedElementIndex

/*
  ## Vertex Indices

  A vertex index indexes vertices of a given type through values of one of its properties. This just adds a bound on the indexed type to be a TypedVertex; see `TypedElementIndex`
*/
public interface TypedVertexIndex <
  N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
  NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
  P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, V,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
extends
  TypedElementIndex<N,NT,P,V,G,I,RV,RVT,RE,RET>
{

  default NT vertexType() { return elementType(); }

  /* This interface declares that this index is over a property that uniquely classifies a vertex type for exact match queries; it adds the method `getTypedVertex` for that.  */
  public interface Unique <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, V,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  >
  extends
    TypedVertexIndex<N,NT,P,V,G,I,RV,RVT,RE,RET>,
    TypedElementIndex.Unique<N,NT,P,V,G,I,RV,RVT,RE,RET>
  {

    /* get a vertex by providing a value of the indexed property. The default implementation relies on `query`. */
    default Optional<N> getVertex(V byValue) { return getElement(byValue); }
  }

  /* This interface declares that this index is over a property that classifies lists of vertices for exact match queries; it adds the method `getTypedVertexs` for that.  */
  public interface List <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, V,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  >
  extends
    TypedVertexIndex<N,NT,P,V,G,I,RV,RVT,RE,RET>,
    TypedElementIndex.List<N,NT,P,V,G,I,RV,RVT,RE,RET>
  {

    /* get a list of vertices by providing a value of the property. The default */
    default Stream<N> getVertices(V byValue) { return getElements(byValue); }
  }

}
