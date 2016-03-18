package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
import java.util.Collection;

public interface TypedElementIndex <
  // element
  E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
  ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
  // property
  P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
  // graph
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
{

  /* Index name */
  String name();

  /* The graph */
  G graph();

  /* Get the indexed property. */
  P property();

  default ET elementType() { return property().elementType(); }


  /* Query this index by comparing the property value with the given one */
  Stream<E> query(QueryPredicate.Compare predicate, V value);

  /* Query this index by checking whether the property value is in/not in the given collection */
  Stream<E> query(QueryPredicate.Contain predicate, Collection<V> values);


  /* This interface declares that this index is over a property that uniquely classifies a element type for exact match queries */
  public interface Unique <
    // element
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    // property
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
    // graph
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  >
    extends TypedElementIndex<E,ET, P,V, G, I,RV,RVT,RE,RET>
  {

    /* Get an element by providing a value of the indexed property */
    default Optional<E> getElement(V byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue).findFirst();
    }
  }

  /* This interface declares that this index is over a property that classifies lists of elements for exact match queries  */
  public interface List <
    // element
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    // property
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
    // graph
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  >
    extends TypedElementIndex<E,ET, P,V, G, I,RV,RVT,RE,RET>
  {

    /* Get a list of elements by providing a value of the property */
    default Stream<E> getElements(V byValue) {

      return query(QueryPredicate.Compare.EQUAL, byValue);
    }
  }
}
