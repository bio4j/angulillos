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
abstract class ElementType <
  FT extends ElementType<FT,G,RF>,
  G  extends TypedGraph<G,?,?>,
  RF
>
{
  /* This is a unique label */
  public final String _label = getClass().getCanonicalName();

  /* The graph in which this element lives */
  public abstract G graph();

  /* An abstract reference to the instance of the implementing class. This should return `this` in all cases; it just cannot be implemented at this level */
  public abstract FT self();

  /* Constructs a new element of this element type */
  // public abstract Element fromRaw(RF raw);

  /* Defines a new property on this element type */
  public final <X> Property<FT,X> property(String nameSuffix, Class<X> valueClass) {
    return new Property<FT,X>(self(), nameSuffix, valueClass);
  }


  abstract class Element {
    public final RF raw;

    public Element(RF raw) { this.raw = raw; }

    public final FT type = ElementType.this.self();
    public final G graph = type.graph();

    /* The `get` method lets you get the value of a `property` which this element has. For that, you pass as an argument the [property](Property.java.md). Note that the type bounds only allow properties of this element. */
    public abstract <X> X get(Property<FT,X> property);

    /* `set` sets the value of a `property` for this element. Again, you can only set properties that this element has, using values of the corresponding property value type. */
    public abstract <X> Element set(Property<FT,X> property, X value);
  }

}
