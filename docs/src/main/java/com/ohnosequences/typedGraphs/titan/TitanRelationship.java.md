
```java
package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Relationship;
import com.ohnosequences.typedGraphs.RelationshipType;
import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;

import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanVertex;
import com.tinkerpop.blueprints.Direction;

public abstract class TitanRelationship <

  S extends Node<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  TitanS extends TitanNode<S,ST, TitanS,TitanST>,
  TitanST extends Enum<TitanST> & TitanNodeType<S,ST, TitanS,TitanST>,

  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  TitanR extends TitanRelationship<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT>,
  TitanRT extends Enum<TitanRT> & TitanRelationshipType<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT>,

  T extends Node<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>,
  TitanT extends TitanNode<T,TT, TitanT,TitanTT>,
  TitanTT extends Enum<TitanTT> & TitanNodeType<T,TT, TitanT,TitanTT>

> implements Relationship<S,ST, R,RT, T,TT>, TitanEdge {

	protected TitanRelationship(TitanEdge raw) {
		
		this.raw = raw;
	}

	protected TitanEdge raw;
	public abstract TitanRelationshipType<S,ST,TitanS,TitanST, R,RT,TitanR,TitanRT, T,TT,TitanT,TitanTT> titanType();

	@Override
	public TitanNode<S,ST,TitanS,TitanST> source() {

		return this.titanType().titanSourceType().from(
			raw.getVertex(Direction.OUT)
		);
	}

	@Override
	public TitanNode<T,TT,TitanT,TitanTT> target() {

		return this.titanType().titanTargetType().from(
			raw.getVertex(Direction.IN)
		);
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	// fwd TitanEdge methods to raw

	@Override
	public TitanLabel getTitanLabel() {
		return raw.getTitanLabel();
	}
	@Override
	public TitanVertex getVertex(Direction dir){
		return raw.getVertex(dir);
	}
	@Override
	public TitanVertex getOtherVertex(TitanVertex vertex){
		return raw.getOtherVertex(vertex);
	}
	@Override
	public boolean isDirected(){
		return raw.isDirected();
	}
	@Override
	public boolean isUnidirected(){
		return raw.isUnidirected();
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
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [RelTypes.java][main/java/com/ohnosequences/typedGraphs/RelTypes.java]
            + [Relationship.java][main/java/com/ohnosequences/typedGraphs/Relationship.java]
            + [ElementType.java][main/java/com/ohnosequences/typedGraphs/ElementType.java]
            + [NodeType.java][main/java/com/ohnosequences/typedGraphs/NodeType.java]
            + [Node.java][main/java/com/ohnosequences/typedGraphs/Node.java]
            + [NodeIndex.java][main/java/com/ohnosequences/typedGraphs/NodeIndex.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [NodeUniqueIndex.java][main/java/com/ohnosequences/typedGraphs/NodeUniqueIndex.java]
            + [NodeListIndex.java][main/java/com/ohnosequences/typedGraphs/NodeListIndex.java]
            + [NodeRetriever.java][main/java/com/ohnosequences/typedGraphs/NodeRetriever.java]
            + [Module.java][main/java/com/ohnosequences/typedGraphs/Module.java]
            + titan
              + [TitanPropertyType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanPropertyType.java]
              + [TitanRelationship.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]
              + [TitanRelationshipType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipType.java]
              + [TitanNodeType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNodeType.java]
              + [TitanNode.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]
            + [Element.java][main/java/com/ohnosequences/typedGraphs/Element.java]
            + [PropertyType.java][main/java/com/ohnosequences/typedGraphs/PropertyType.java]
            + [RelationshipType.java][main/java/com/ohnosequences/typedGraphs/RelationshipType.java]

[main/java/com/ohnosequences/typedGraphs/RelTypes.java]: ../RelTypes.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/ElementType.java]: ../ElementType.java.md
[main/java/com/ohnosequences/typedGraphs/NodeType.java]: ../NodeType.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: ../Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: ../NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeUniqueIndex.java]: ../NodeUniqueIndex.java.md
[main/java/com/ohnosequences/typedGraphs/NodeListIndex.java]: ../NodeListIndex.java.md
[main/java/com/ohnosequences/typedGraphs/NodeRetriever.java]: ../NodeRetriever.java.md
[main/java/com/ohnosequences/typedGraphs/Module.java]: ../Module.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanPropertyType.java]: TitanPropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipType.java]: TitanRelationshipType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeType.java]: TitanNodeType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: ../Element.java.md
[main/java/com/ohnosequences/typedGraphs/PropertyType.java]: ../PropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipType.java]: ../RelationshipType.java.md