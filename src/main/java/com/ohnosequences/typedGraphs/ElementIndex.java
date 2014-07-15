package com.ohnosequences.typedGraphs;

import java.util.List;
import java.util.Optional;


public interface ElementIndex <
  E extends Element<E,ET>, ET extends Element.Type<E,ET>,
  P extends Property<E,ET,P,V>, V
>
{

  // TODO add a Q extends Predicate "type member" so that they can declare which kind of predicates they support
  /* query this index using a Blueprints predicate */
  public Optional<java.util.List<E>> query(com.tinkerpop.blueprints.Compare predicate, V value);

  /* This interface declares that this index is over a property that uniquely classifies a element type for exact match queries; it adds the method `getElement` for that.  */
  public static interface Unique <
    E extends Element<E,ET>, ET extends Element.Type<E,ET>,
    P extends Property<E,ET,P,V>, V
  > 
    extends ElementIndex<E,ET,P,V> 
  {

    /* get a element by providing a value of the indexed property. The default implementation relies on `query`. */
    public default Optional<E> getElement(V byValue) { 

      Optional<java.util.List<E>> result = query (
        com.tinkerpop.blueprints.Compare.EQUAL,
        byValue
      );

      if ( result.isPresent() ) {

        return Optional.of( result.get().get(0) );
      } else {

        return Optional.empty();
      }
    }
  }

  /* This interface declares that this index is over a property that classifies lists of elements for exact match queries; it adds the method `getElements` for that.  */
  public static interface List <
    E extends Element<E,ET>, ET extends Element.Type<E,ET>,
    P extends Property<E,ET,P,V>, V
  > 
    extends ElementIndex<E,ET,P,V> 
  {

    /*
    get a list of elements by providing a value of the property. The default 
    */
    public default Optional<java.util.List<E>> getElements(V byValue) {

      return query(
        com.tinkerpop.blueprints.Compare.EQUAL,
        byValue
      );
    }
  }

}