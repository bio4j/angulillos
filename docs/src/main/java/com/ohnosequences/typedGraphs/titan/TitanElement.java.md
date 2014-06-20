
```java
package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;
import com.thinkaurelius.titan.core.*;

import java.util.Set;

public interface TitanElement <
  E extends TitanElement<E,ET>, 
  ET extends TitanElement.Type<E,ET>
>
extends
  Element<E,ET>,
  com.thinkaurelius.titan.core.TitanElement  
{

  public com.thinkaurelius.titan.core.TitanElement raw();

  // use get for implementing all the property-name() methods
  @Override public default <P extends Property<E,ET,P,V>, V> V get(P p) {

    return raw().<V>getProperty(p.fullName());
  }

  public default <P extends Property<E,ET,P,V>, V> void set(P p, V value) {

    raw().setProperty(p.fullName(), value);
  }


  public static interface Type <
    E extends TitanElement<E,ET>,
    ET extends TitanElement.Type<E,ET>
  >
  extends
    Element.Type<E,ET> 
  {}



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
```


------

### Index

+ src
  + test
    + java
      + com
        + ohnosequences
          + typedGraphs
            + go
              + [TitanGoGraph.java][test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]
    + scala
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [TypedGraph.java][main/java/com/ohnosequences/typedGraphs/TypedGraph.java]
            + [Relationship.java][main/java/com/ohnosequences/typedGraphs/Relationship.java]
            + [ElementIndex.java][main/java/com/ohnosequences/typedGraphs/ElementIndex.java]
            + [Node.java][main/java/com/ohnosequences/typedGraphs/Node.java]
            + [NodeIndex.java][main/java/com/ohnosequences/typedGraphs/NodeIndex.java]
            + [RelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [NodeQuery.java][main/java/com/ohnosequences/typedGraphs/NodeQuery.java]
            + titan
              + [TitanElement.java][main/java/com/ohnosequences/typedGraphs/titan/TitanElement.java]
              + [TitanRelationship.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]
              + [TitanNodeIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]
              + [TitanTypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]
              + [TitanRelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]
              + [TitanProperty.java][main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]
              + [TitanNode.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]
            + [Element.java][main/java/com/ohnosequences/typedGraphs/Element.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/ElementIndex.java]: ../ElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: ../Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: ../NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: ../RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeQuery.java]: ../NodeQuery.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanElement.java]: TitanElement.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: ../Element.java.md