package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
import java.util.Collection;

interface TypedElementIndex <
  FT extends ElementType<FT, ?,?>,
  P  extends Property<FT,X>,
  X
>
{

  /* Index name */
  String name();

  // /* The graph */
  // G graph();

  /* Get the indexed property. */
  P property();

  default FT elementType() { return property().elementType(); }


  /* Query this index by comparing the property value with the given one */
  Stream<F> query(QueryPredicate.Compare predicate, X value);

  /* Query this index by checking whether the property value is in/not in the given collection */
  Stream<F> query(QueryPredicate.Contain predicate, Collection<X> values);


  /* This interface declares that this index is over a property that uniquely classifies a element type for exact match queries */
  interface Unique <
    FT extends ElementType<FT, ?,?>,
    P  extends Property<FT,X>,
    X
  >
    extends TypedElementIndex<FT, P,X>
  {

    /* Get an element by providing a value of the indexed property */
    default Optional<F> getElement(X byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue).findFirst();
    }
  }

  /* This interface declares that this index is over a property that classifies lists of elements for exact match queries  */
  interface List <
    FT extends ElementType<FT, ?,?>,
    P  extends Property<FT,X>,
    X
  >
    extends TypedElementIndex<FT, P,X>
  {

    /* Get a list of elements by providing a value of the property */
    default Stream<F> getElements(X byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue);
    }
  }
}
