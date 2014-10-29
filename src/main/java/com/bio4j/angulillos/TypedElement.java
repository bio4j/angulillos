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
public interface TypedElement <
  E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
  ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
{

  /*
  the type of this element
  */
  ET type();

  /*
  an abstract reference to the instance of the implementing class. This should return `this` in all cases; it just cannot be implemented at this level.
  */
  E self();

  /*
  `raw` should return a reference to the instance of the corresponding raw type underlying this element. The return type should be `RV + RE` but Java does not have sum types, and it will collapse anyway at the level of Vertex and Edge so `Object` is not that bad here.
  */
  Object raw();

  /*
  `graph` returns the graph in which this element lives.
  */
  G graph();

  /* 
  The `get` method lets you get the value of a `property` which this element has. For that, you pass as an argument the [property](Property.java.md). Note that the type bounds only allow properties of this element.
  */
  <
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  V get(P property);

  /*
  `set` sets the value of a `property` for this element. Again, you can only set properties that this element has, using values of the corresponding property value type.
  */
  <
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, 
    V
  >
  void set(P property, V value);

  /*

  ### Element types

  Element types are also used as factories for constructing instances of the corresponding elements

  */
  public interface Type <
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
  {

    /*
    values of an element type act as witnesses for the element having that type; they will all be treated as equal.
    */
    ET value();

    Object raw();

    default String name() { 

      return getClass().getCanonicalName();
    }

    G graph();
  }
}
