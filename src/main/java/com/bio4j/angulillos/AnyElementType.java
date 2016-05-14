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
