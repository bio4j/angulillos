
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
  implements Relationship<S,ST, R,RT, T,TT>, TitanEdge 
{


  public static interface Type <
    S extends TitanNode<S,ST>, ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>, TT extends TitanNode.Type<T,TT>
  >
    extends Relationship.Type<S,ST,R,RT,T,TT>
  {
```

The Titan label used for this rel type

```java
    public TitanLabel label();

    public R fromTitanEdge(TitanEdge edge);
  }

  // titan rel
  
	protected TitanRelationship(TitanEdge raw) {
		
		this.raw = raw;
	}

	protected TitanEdge raw;

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

  // TODO move to TitanElement
  // use get for implementing all the property-name() methods
  @Override public <P extends Property<R,RT,P,V>, V> V get(P p) {

    return raw.<V>getProperty(p.fullName());
  }

  public <P extends Property<R,RT,P,V>, V> void set(P p, V value) {

    raw.setProperty(p.fullName(), value);
  }

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
  @Override public Object getId() { 
    return raw.getId();
  }
  @Override public <O> O getProperty(TitanKey arg0) { 
    return raw.getProperty(arg0); 
  }
  @Override public <O> O getProperty(String arg0) {   
    return raw.getProperty(arg0); 
  }
  @Override public boolean hasId() {  return raw.hasId(); }
  @Override public boolean isLoaded() {   return raw.isLoaded();  }
  @Override public boolean isNew() {    return raw.isNew(); }
  @Override public boolean isRemoved() {  return raw.isRemoved(); }
  @Override public void remove() {  raw.remove(); }
  @Override public <O> O removeProperty(String arg0) {  return raw.removeProperty(arg0);  }
  @Override public <O> O removeProperty(TitanType arg0) { return raw.removeProperty(arg0);}
  @Override public void setProperty(String arg0, Object arg1) {   raw.setProperty(arg0, arg1);  }
  @Override public void setProperty(TitanKey arg0, Object arg1) { raw.setProperty(arg0, arg1);  }
  @Override public Set<String> getPropertyKeys() {    return raw.getPropertyKeys(); }
  @Override public int compareTo(TitanElement arg0) { return raw.compareTo(arg0); }

  @Override public long getID() { return raw.getID(); }
  @Override public TitanType getType() { return raw.getType(); }
  @Override public Direction getDirection(TitanVertex vertex) { return raw.getDirection(vertex); }
  @Override public TitanVertex getProperty(TitanLabel label) { return raw.getProperty(label); }
  @Override public boolean isEdge() { return raw.isEdge(); }
  @Override public boolean isIncidentOn(TitanVertex vertex) { return raw.isIncidentOn(vertex); }
  @Override public boolean isLoop() { return raw.isLoop(); }
  @Override public boolean isModifiable(){ return raw.isModifiable(); }
  @Override public boolean isProperty(){ return raw.isProperty(); }
  @Override public void setProperty(TitanLabel label, TitanVertex vertex) { raw.setProperty(label,vertex); }

  @Override public String getLabel() { return raw.getLabel(); }
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
    + scala
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [TypedGraph.java][main/java/com/ohnosequences/typedGraphs/TypedGraph.java]
            + [Relationship.java][main/java/com/ohnosequences/typedGraphs/Relationship.java]
            + [Node.java][main/java/com/ohnosequences/typedGraphs/Node.java]
            + [NodeIndex.java][main/java/com/ohnosequences/typedGraphs/NodeIndex.java]
            + [RelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + titan
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
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: ../Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: ../NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: ../RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../Property.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: ../Element.java.md