package com.ohnosequences.typedGraphs;

/*
  A typed Element. Base class for both Nodes and Relationships; essentially they only have

  1. a type
  2. properties

  `N` refers to the type of the element itself, and `ET` its type. You cannot define one without defining the other.

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
    This method let's you get the value of a property which is declared to be of this element. For that, passing the property _type_ should be enough. Note how the type bounds only allow properties of this `Element`
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
      this is a strong hit for you to implement this as a singleton `Enum`; just return the one and only instance here.
    */
    public ET value();

    public default String name() { return value().toString(); }
  }
}
