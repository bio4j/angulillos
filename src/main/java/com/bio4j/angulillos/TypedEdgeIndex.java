package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;

public interface TypedEdgeIndex <
  E  extends      TypedEdge<?,?, E,ET, ?,?, ?,?,?>,
  ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,?,?>,
  P extends Property<ET,X>,
  X
> extends
  TypedElementIndex<E,ET, P,X>
{

  default ET edgeType() { return elementType(); }

  interface Unique <
    E  extends      TypedEdge<?,?, E,ET, ?,?, ?,?,?>,
    ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,?,?>,
    P extends Property<ET,X> & Arity.FromAtMostOne,
    X
  > extends
    TypedEdgeIndex<E,ET, P,X>,
    TypedElementIndex.Unique<E,ET, P,X>
  {

    /* get a node by providing a value of the indexed property. */
    default Optional<E> getEdge(X byValue) { return getElement(byValue); }
  }

  interface NonUnique <
    E  extends      TypedEdge<?,?, E,ET, ?,?, ?,?,?>,
    ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,?,?>,
    P extends Property<ET,X>,
    X
  > extends
    TypedEdgeIndex<E,ET, P,X>,
    TypedElementIndex.NonUnique<E,ET, P,X>
  {

    /* get a list of nodes by providing a value of the indexed property. */
    default Stream<E> getEdges(X byValue) { return getElements(byValue); }
  }


}
