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

  /* Get the indexed property. */
  P property();

  public interface QueryPredicate {}

  /* This is an analog of
     - http://thinkaurelius.github.io/titan/javadoc/current/com/thinkaurelius/titan/core/attribute/Cmp.html
     - http://tinkerpop.apache.org/javadocs/3.1.1-incubating/core/org/apache/tinkerpop/gremlin/process/traversal/Compare.html
  */
  public enum ComparePredicate implements QueryPredicate {
    EQUAL,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN,
    LESS_THAN_EQUAL,
    NOT_EQUAL;
  }

  /* Query this index by comparing the property value with the given one */
  Stream<E> compareQuery(ComparePredicate predicate, V value);

  /* This is an analog of
     - http://thinkaurelius.github.io/titan/javadoc/current/com/thinkaurelius/titan/core/attribute/Contain.html
     - http://tinkerpop.apache.org/javadocs/3.1.1-incubating/core/org/apache/tinkerpop/gremlin/process/traversal/Contains.html
  */
  public enum ContainPredicate implements QueryPredicate {
    IN,
    NOT_IN;
  }

  /* Query this index by checking whether the property value is in/not in the given collection */
  Stream<E> containQuery(ContainPredicate predicate, Collection<V> values);


  /* This interface declares that this index is over a property that uniquely classifies a element type for exact match queries; it adds the method `getTypedElement` for that. */
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

    /* Get a element by providing a value of the indexed property */
    default Optional<E> getElement(V byValue) {

      return compareQuery(ComparePredicate.EQUAL, byValue).findFirst();
    }
  }

  /* This interface declares that this index is over a property that classifies lists of elements for exact match queries; it adds the method `getTypedElements` for that.  */
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

      return compareQuery(ComparePredicate.EQUAL, byValue);
    }
  }
}
