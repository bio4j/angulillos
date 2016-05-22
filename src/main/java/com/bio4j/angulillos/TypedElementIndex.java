package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
import java.util.Collection;

public interface TypedElementIndex <
  // element
  F  extends      TypedElement<F,FT, ?,?>,
  FT extends TypedElement.Type<F,FT, ?,?>,
  // property
  P extends Property<FT,X>,
  X
>
extends HasLabel {

  /* Get the indexed property. */
  P property();

  public default UntypedGraph untypedGraph() { return property().elementType().graph().raw(); }

  default FT elementType() { return property().elementType(); }

  /* Query this index by comparing the property value with the given one */
  Stream<F> query(QueryPredicate.Compare predicate, X value);

  /* Query this index by checking whether the property value is in/not in the given collection */
  Stream<F> query(QueryPredicate.Contain predicate, Collection<X> values);

  /* This interface declares that this index is over a property that uniquely classifies a element type for exact match queries */
  interface Unique <
    // element
    F  extends      TypedElement<F,FT, ?,?>,
    FT extends TypedElement.Type<F,FT, ?,?>,
    // property
    P extends Property<FT,X> & Arity.FromAtMostOne,
    X
  >
    extends TypedElementIndex<F,FT, P,X>
  {

    /* Get an element by providing a value of the indexed property */
    default Optional<F> getElement(X byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue).findFirst();
    }
  }

  /* This interface declares that this index is over a property that classifies lists of elements for exact match queries  */
  interface NonUnique <
    // element
    F  extends      TypedElement<F,FT, ?,?>,
    FT extends TypedElement.Type<F,FT, ?,?>,
    // property
    P extends Property<FT,X>,
    X
  >
    extends TypedElementIndex<F,FT, P,X>
  {

    /* Get a list of elements by providing a value of the property */
    default Stream<F> getElements(X byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue);
    }
  }
}
