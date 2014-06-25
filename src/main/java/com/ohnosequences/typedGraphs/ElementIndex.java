// package com.ohnosequences.typedGraphs;

// import java.util.List;

// public interface ElementIndex <
//   E extends Element<E,ET,G>, ET extends Element.Type<E,ET,G>, G extends TypedGraph,
//   P extends Property<E,ET,G,P,V>, V
// >
// {

//   // TODO add a Q extends Predicate "type member" so that they can declare which kind of predicates they support
//   /* query this index using a Blueprints predicate */
//   public java.util.List<? extends E> query(com.tinkerpop.blueprints.Compare predicate, V value);

//   /* This interface declares that this index is over a property that uniquely classifies a element type for exact match queries; it adds the method `getElement` for that.  */
//   public static interface Unique <
//     E extends Element<E,ET,G>, ET extends Element.Type<E,ET,G>, G extends TypedGraph,
//     P extends Property<E,ET,G,P,V>, V
//   > 
//     extends ElementIndex<E,ET,G,P,V> 
//   {

//      get a element by providing a value of the indexed property. The default implementation relies on `query`. 
//     public default E getElement(V byValue) { 

//       return query(
//         com.tinkerpop.blueprints.Compare.EQUAL,
//         byValue
//       ).get(0);
//     }
//   }

//   /* This interface declares that this index is over a property that classifies lists of elements for exact match queries; it adds the method `getElements` for that.  */
//   public static interface List <
//     E extends Element<E,ET,G>, ET extends Element.Type<E,ET,G>, G extends TypedGraph,
//     P extends Property<E,ET,G,P,V>, V
//   > 
//     extends ElementIndex<E,ET,G,P,V> 
//   {

//     /*
//     get a list of elements by providing a value of the property. The default 
//     */
//     public default java.util.List<? extends E> getElements(V byValue) {

//       return query(
//         com.tinkerpop.blueprints.Compare.EQUAL,
//         byValue
//       );
//     }
//   }

// }