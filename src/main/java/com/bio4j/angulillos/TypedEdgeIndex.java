package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;

public interface TypedEdgeIndex <
  E  extends      TypedEdge<?,?, E,ET, ?,?, ?,RV,RE>,
  ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,RV,RE>,
  P extends Property<ET,X>,
  X,
  RV,RE
> extends
  TypedElementIndex<E,ET, P,X, RE>
{

  default ET edgeType() { return elementType(); }

  interface Unique <
    E  extends      TypedEdge<?,?, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,RV,RE>,
    P extends Property<ET,X> & Arity.FromAtMostOne,
    X,
    RV,RE
  > extends
    TypedEdgeIndex<E,ET, P,X, RV,RE>,
    TypedElementIndex.Unique<E,ET, P,X, RE>
  {

    /* get a node by providing a value of the indexed property. */
    default Optional<E> getEdge(X byValue) { return getElement(byValue); }
  }

  interface NonUnique <
    E  extends      TypedEdge<?,?, E,ET, ?,?, ?,RV,RE>,
    ET extends TypedEdge.Type<?,?, E,ET, ?,?, ?,RV,RE>,
    P extends Property<ET,X>,
    X,
    RV,RE
  > extends
    TypedEdgeIndex<E,ET, P,X, RV,RE>,
    TypedElementIndex.NonUnique<E,ET, P,X, RE>
  {

    /* get a list of nodes by providing a value of the indexed property. */
    default Stream<E> getEdges(X byValue) { return getElements(byValue); }
  }
}
