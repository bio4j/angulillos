
```java
package com.ohnosequences.typedGraphs.titan;

import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;


import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.*;
import com.tinkerpop.blueprints.Direction;

public abstract class TitanRelationship <
  S extends TitanNode<S,ST>, ST extends TitanNode.Type<S,ST>,
  R extends TitanRelationship<S,ST,R,RT,T,TT>, RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
  T extends TitanNode<T,TT>, TT extends TitanNode.Type<T,TT>
> 
implements
  TitanElement<R,RT>,
  Relationship<S,ST, R,RT, T,TT>,
  TitanEdge 
{

  // titan rel 
  protected TitanRelationship(TitanEdge raw) {
    
    this.raw = raw;
  }

  protected TitanEdge raw;

  @Override public TitanEdge raw() {

    return this.raw;
  }

  public static interface Type <
    S extends TitanNode<S,ST>, ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>, TT extends TitanNode.Type<T,TT>
  >
  extends
    TitanElement.Type<R,RT>,  
    Relationship.Type<S,ST,R,RT,T,TT>
  {
```

The Titan label used for this rel type

```java
    public TitanLabel label();

    public R fromTitanEdge(TitanEdge edge);

    @Override public default R from(Object edge) {

      if (edge instanceof TitanEdge) {

        TitanEdge uhoh = (TitanEdge) edge;

        return fromTitanEdge(uhoh);
      } 

      else {

        throw new IllegalArgumentException("edge should be a TitanEdge");
      }
    }
  }

	@Override public S source() {

		return this.type().sourceType().from(
			raw.getVertex(Direction.OUT)
		);
	}

	@Override public T target() {

		return this.type().targetType().from(
			raw.getVertex(Direction.IN)
		);
	}
```


### delegating methods to `TitanEdge`

Here we forward all `TitanEdge`-specific methods to the wrapped `raw` value.


```java
	//////////////////////////////////////////////////////////////////////////////////////////
	// fwd TitanEdge methods to raw
	@Override public TitanLabel getTitanLabel() {
		return raw.getTitanLabel();
	}
	@Override public TitanVertex getVertex(Direction dir){
		return raw.getVertex(dir);
	}
	@Override public TitanVertex getOtherVertex(TitanVertex vertex){
		return raw.getOtherVertex(vertex);
	}
	@Override public boolean isDirected(){
		return raw.isDirected();
	}
	@Override public boolean isUnidirected(){
		return raw.isUnidirected();
	}
  @Override public Set<String> getPropertyKeys() {
    return raw.getPropertyKeys(); 
  }
  @Override public TitanType getType() { 
    return raw.getType(); 
  }
  @Override public Direction getDirection(TitanVertex vertex) { 
    return raw.getDirection(vertex); 
  }
  @Override public TitanVertex getProperty(TitanLabel label) { 
    return raw.getProperty(label); 
  }
  @Override public boolean isEdge() { 
    return raw.isEdge(); 
  }
  @Override public boolean isIncidentOn(TitanVertex vertex) { 
    return raw.isIncidentOn(vertex); 
  }
  @Override public boolean isLoop() { 
    return raw.isLoop(); 
  }
  @Override public boolean isModifiable(){ 
    return raw.isModifiable(); 
  }
  @Override public boolean isProperty(){ 
    return raw.isProperty(); 
  }
  @Override public void setProperty(TitanLabel label, TitanVertex vertex) { 
    raw.setProperty(label,vertex); 
  }
  @Override public String getLabel() { 
    return raw.getLabel(); 
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