package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;
import com.thinkaurelius.titan.core.*;

import java.util.Set;

public interface TitanElement <
  E extends TitanElement<E,ET,G>, 
  ET extends TitanElement.Type<E,ET,G>,
  G extends TitanTypedGraph<G>
>
extends
  Element<E,ET,G,Titan,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  com.thinkaurelius.titan.core.TitanElement  
{

  @Override
  com.thinkaurelius.titan.core.TitanElement raw();

  public static interface Type <
    E extends TitanElement<E,ET,G>,
    ET extends TitanElement.Type<E,ET,G>,
    G extends TitanTypedGraph<G>
  >
  extends
    Element.Type<E,ET,G,Titan,TitanVertex,TitanKey,TitanEdge,TitanLabel> 
  {


  }

  // fwd titan methods to raw
  @Override public default <O> O getProperty(TitanKey arg0) { 
    return raw().getProperty(arg0); 
  }
  @Override public default <O> O getProperty(String arg0) { 
    return raw().getProperty(arg0); 
  }
  @Override public default boolean hasId() {  
    return raw().hasId(); 
  }
  @Override public default boolean isLoaded() {   
    return raw().isLoaded();  
  }
  @Override public default boolean isNew() {    
    return raw().isNew(); 
  }
  @Override public default boolean isRemoved() {  
    return raw().isRemoved(); 
  }
  @Override public default void remove() {  
    raw().remove(); 
  }
  @Override public default <O> O removeProperty(String arg0) {  
    return raw().removeProperty(arg0);  
  }
  @Override public default <O> O removeProperty(TitanType arg0) { 
    return raw().removeProperty(arg0);
  }
  @Override public default void setProperty(String arg0, Object arg1) {   
    raw().setProperty(arg0, arg1);  
  }
  @Override public default void setProperty(TitanKey arg0, Object arg1) { 
    raw().setProperty(arg0, arg1);  
  }
  @Override public default Set<String> getPropertyKeys() {    
    return raw().getPropertyKeys(); 
  }
  @Override public default int compareTo(com.thinkaurelius.titan.core.TitanElement arg0) {  
    return raw().compareTo(arg0); 
  }
  @Override public default long getID() { 
    return raw().getID(); 
  }
  @Override public default Object getId() { 
    return raw().getId();
  }
}