package com.ohnosequences.typedGraphs;

/*
  A typed Element. Base class for both Nodes and Relationships; essentially they only have

  1. a type
  2. 

  `N` refers to the type of the element itself, and `NT` its type. You cannot define one without defining the other.

  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
*/
public interface Element <
  N extends Element<N,NT>, 
  NT extends ElementType<N,NT>
> 
{

  /*
    the type of this element
  */
  public ElementType<N,NT> type();

  /*
    This method let's you get the value of a property which is declared to be of this element. For that, passing the property _type_ should be enough. Note how the type bounds would only allow properties of this `Element`
  */
  public <
    P extends Property<N,NT>, 
    PT extends PropertyType<N,NT, P,PT, V>, 
    V
  > V get(PT pt);
}
