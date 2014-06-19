package com.ohnosequences.typedGraphs;

/*
  A typed Element. Base class for both `Node`s and `Relationship`s; essentially they only have

  1. their type
  2. and properties

  `E` refers to the element itself, and `ET` its type. You cannot define one without defining the other.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Element <
  E extends Element<E,ET>, 
  ET extends Element.Type<E,ET>
> 
{

  /*
    the type of this element
  */
  public ET type();

  /*
    This method let's you get the value of a property which this element has. For that, you pass as an argument the property _type_.The type bounds only allow properties of this `Element`
  */
  public <
    P extends Property<E,ET,P,V>, 
    V
  > 
  V get(P p);

  /*
  The type of an Element. You can refine this through interfaces.

    @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
  */
  public static interface Type <
    E extends Element<E,ET>,
    ET extends Element.Type<E,ET>
  > 
  {

    /*
      values of an Element Type act as witnesses for that type; they will all be treated as equal.
    */
    public ET value();

    public default String name() { return getClass().getCanonicalName(); }

    // shouldn't be Object but hey you know
    public E from(Object stuff) throws IllegalArgumentException;
  }
}
