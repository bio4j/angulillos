package com.bio4j.angulillos;

/*
  ## Elements

  This is a base interface for both [Vertices](TypedVertex.java.md) and [Edges](TypedEdge.java.md); it only has that which is common to both:

  1. a reference to its [type](#Element_types) `ET`
  2. methods for [properties](Property.java.md)
  3. a reference to the [graph](TypedGraph.java.md) `G` of which they are elements.

  #### elements and their types

  `E` refers to the element itself, and `ET` its type. You cannot define one without defining the other.
*/
interface TypedElement <
  E  extends      TypedElement<E,ET,G,ER>,
  ET extends TypedElement.Type<E,ET,G,ER>,
  G  extends TypedGraph<G,?,?>,
  ER
>
{

  /* The type of this element */
  ET type();

  /* An abstract reference to the instance of the implementing class. This should return `this` in all cases; it just cannot be implemented at this level. */
  E self();

  /* `raw` should return a reference to the instance of the corresponding raw type underlying this element. The return type should be `RV + RE` but Java does not have sum types, and it will collapse anyway at the level of Vertex and Edge so `Object` is not that bad here. */
  ER raw();

  /* The graph in which this element lives. */
  G graph();

  /* The `get` method lets you get the value of a `property` which this element has. For that, you pass as an argument the [property](Property.java.md). Note that the type bounds only allow properties of this element. */
  <V> V get(Property<ET,V> property);

  /* `set` sets the value of a `property` for this element. Again, you can only set properties that this element has, using values of the corresponding property value type. */
  <V> E set(Property<ET,V> property, V value);

  /*
    ### Element types

    Element types are also used as factories for constructing instances of the corresponding elements.
  */
  interface Type <
    E  extends      TypedElement<E,ET,G,ER>,
    ET extends TypedElement.Type<E,ET,G,ER>,
    G  extends TypedGraph<G,?,?>,
    ER
  >
  {
    /* Constructs a value of the typed element of this type */
    E fromRaw(ER rawElem);

    // NOTE: this should be final, but interface cannot have final methods
    default String name() { return getClass().getCanonicalName(); }
  }
}
