package com.bio4j.angulillos;

import java.util.Set;

/*
  ## Elements

  This is a base interface for both [Vertices](TypedVertex.java.md) and [Edges](TypedEdge.java.md); it only has that which is common to both:

  1. a reference to its [type](#Element_types) `ET`
  2. methods for [properties](Property.java.md)
  3. a reference to the [graph](TypedGraph.java.md) `G` of which they are elements.

  #### elements and their types

  `E` refers to the element itself, and `ET` its type. You cannot define one without defining the other.
*/
public interface AnyElementType extends HasLabel {

  AnyTypedGraph graph();
  Set<AnyProperty> properties();
}

interface TypedElement <
  F  extends      TypedElement<F,FT, G,RF>,
  FT extends TypedElement.Type<F,FT, G,RF>,
  G    extends TypedGraph<G,?,?>,
  RF
>
{

  /*
    ### Element types

    Element types are also used as factories for constructing instances of the corresponding elements.
  */
  interface Type <
    F  extends      TypedElement<F,FT, G,RF>,
    FT extends TypedElement.Type<F,FT, G,RF>,
    G    extends TypedGraph<G,?,?>,
    RF
  > extends AnyElementType {
    public G graph();

    /* Constructs a value of the typed element of this type */
    F fromRaw(RF rawElem);
  }


  /* The type of this element */
  FT type();

  /* An abstract reference to the instance of the implementing class. This should return `this` in all cases; it just cannot be implemented at this level. */
  F self();

  /* `raw` should return a reference to the instance of the corresponding raw type underlying this element. The return type should be `RV + RE` but Java does not have sum types, and it will collapse anyway at the level of Vertex and Edge so `Object` is not that bad here. */
  RF raw();

  /* The graph in which this element lives. */
  default G graph() { return type().graph(); }

  /* The `get` method lets you get the value of a `property` which this element has. For that, you pass as an argument the [property](Property.java.md). Note that the type bounds only allow properties of this element, which are declared to be always defined. */
  <X, P extends Property<FT,X> & Arity.ToAtLeastOne> X get(P property);

  /* This get method is available for any property of this element, irrespectively of its arity. Note that we assume throughout that properties are single-valued, being implicitly `Arity.ToAtMostOne` */
  <X> java.util.Optional<X> getOpt(Property<FT,X> property);

  /* `set` sets the value of a `property` for this element. Again, you can only set properties that this element has, using values of the corresponding property value type. */
  <X> F set(Property<FT,X> property, X value);

}
