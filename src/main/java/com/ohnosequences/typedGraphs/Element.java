package com.ohnosequences.typedGraphs;

/*
  A typed Element. Base class for both `Node`s and `Relationship`s; essentially they only have

  1. their type
  2. and properties

  `E` refers to the element itself, and `ET` its type. You cannot define one without defining the other.

  `G` and `I` refer to the graph in which this element lives.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Element <
  E extends Element<E,ET,G,I,RV,RVT,RE,RET>,
  ET extends Element.Type<E,ET,G,I,RV,RVT,RE,RET>,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
{

  /*
    the type of this element
  */
  ET type();

  E self();

  /*
    the graph in which this element lives
  */
  G graph();

  I rawGraph();

  /*
    This method let's you get the value of a property which this element has. For that, you pass as an argument the property _type_. The type bounds only allow properties of this `Element`
  */
  <
    P extends Property<E,ET,G,I,RV,RVT,RE,RET,P,V>, 
    V
  > 
  V get(P p);

  /*
  The type of an Element. You can refine this through interfaces.

    @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
  */
  interface Type <
    E extends Element<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends Element.Type<E,ET,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
  {

    /*
      values of an Element Type act as witnesses for that type; they will all be treated as equal.
    */
    ET value();

    default String name() { return getClass().getCanonicalName(); }

    G graph();

    // shouldn't be Object but hey you know
    // public E from(Object stuff) throws IllegalArgumentException;
  }
}
