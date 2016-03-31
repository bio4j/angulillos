package com.bio4j.angulillos;

import java.util.Set;
import java.util.HashSet;

/*
  ## Elements

  This is a base interface for both [Vertices](TypedVertex.java.md) and [Edges](TypedEdge.java.md); it only has that which is common to both:

  1. a reference to its [type](#Element_types) `ET`
  2. methods for [properties](Property.java.md)
  3. a reference to the [graph](TypedGraph.java.md) `G` of which they are elements.

  #### elements and their types

  `E` refers to the element itself, and `ET` its type. You cannot define one without defining the other.
*/
public abstract class TypedElement <
  F  extends TypedElement<F,FT,RF, G,RV,RE>,
  FT extends TypedGraph<G,RV,RE>.ElementType<FT,RF>,
  RF,
  G  extends TypedGraph<G,RV,RE>,
  RV,RE
>
{

  private final RF raw;
  public  final RF raw() { return this.raw; }

  protected TypedElement(RF raw) { this.raw = raw; }

  /* An abstract reference to the instance of the implementing class.
     _This has to be **always** implemented in a non-abstract inheritor as `return this`._
     It just cannot be implemented abstractly.
  */
  protected abstract F self();


  /* The graph in which this element lives */
  public abstract G graph();


  // /* The `get` method lets you get the value of a `property` which this element has. For that, you pass as an argument the [property](Property.java.md). Note that the type bounds only allow properties of this element. */
  // public abstract <X> X get(FT.Property<X> property);
  //
  // /* `set` sets the value of a `property` for this element. Again, you can only set properties that this element has, using values of the corresponding property value type. */
  // public abstract <X> Element set(FT.Property<X> property, X value);
  //
  //
  // /* This set stores all properties that are defined on this element type */
  // private Set<Property<?>> properties = new HashSet<>();
  // public final Set<Property<?>> properties() { return this.properties; }

}
