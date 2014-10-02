package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;

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

  /* query this index using a Blueprints predicate */
  // Optional is too much here; empty Streams are ok
  Stream<E> query(com.tinkerpop.blueprints.Compare predicate, V value);

  /* This interface declares that this index is over a property that uniquely classifies a element type for exact match queries; it adds the method `getTypedElement` for that.  */
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

    /* get a element by providing a value of the indexed property. The default implementation relies on `query`. */
    default Optional<E> getElement(V byValue) { 

      Stream<E> strm = query (
        com.tinkerpop.blueprints.Compare.EQUAL,
        byValue
      );

      return strm.findFirst();
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

    /* get a list of elements by providing a value of the property. The default ... */
    default Stream<E> getElements(V byValue) {

      return query(
        com.tinkerpop.blueprints.Compare.EQUAL,
        byValue
      );
    }
  }

}