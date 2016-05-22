package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;

/*
  ## Vertex Indices

  A vertex index indexes vertices of a given type through values of one of its properties. This just adds a bound on the indexed type to be a TypedVertex; see `TypedElementIndex`
*/
public interface TypedVertexIndex <
  V  extends      TypedVertex<V,VT, ?,?,?>,
  VT extends TypedVertex.Type<V,VT, ?,?,?>,
  P extends Property<VT,X>,
  X
> extends
  TypedElementIndex<V,VT, P,X>
{

  default VT vertexType() { return elementType(); }

  /* This interface declares that this index is over a property that uniquely classifies a vertex type for exact match queries; it adds the method `getTypedVertex` for that.  */
  interface Unique <
    V  extends      TypedVertex<V,VT, ?,?,?>,
    VT extends TypedVertex.Type<V,VT, ?,?,?>,
    P extends Property<VT,X> & Arity.FromAtMostOne,
    X
  > extends
    TypedVertexIndex<V,VT, P,X>,
    TypedElementIndex.Unique<V,VT, P,X>
  {

    /* get a vertex by providing a value of the indexed property. The default implementation relies on `query`. */
    default Optional<V> getVertex(X byValue) { return getElement(byValue); }
  }

  /* This interface declares that this index is over a property that classifies lists of vertices for exact match queries; it adds the method `getTypedVertexs` for that.  */
  interface NonUnique <
    V  extends      TypedVertex<V,VT, ?,?,?>,
    VT extends TypedVertex.Type<V,VT, ?,?,?>,
    P extends Property<VT,X>,
    X
  > extends
    TypedVertexIndex<V,VT, P,X>,
    TypedElementIndex.NonUnique<V,VT, P,X>
  {

    /* get a list of vertices by providing a value of the property. The default */
    default Stream<V> getVertices(X byValue) { return getElements(byValue); }
  }
}
