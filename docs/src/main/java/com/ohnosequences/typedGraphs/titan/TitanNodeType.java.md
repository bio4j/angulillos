
```java
package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.Node;
import com.ohnosequences.typedGraphs.NodeType;
import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanElement;
import com.thinkaurelius.titan.core.KeyMaker;
import com.thinkaurelius.titan.core.*;
```


This should extend `TitanKey` but is not possible due to non-covariant Java generics


```java
public interface TitanNodeType <

  N extends Node<N,NT>,
  NT extends Enum<NT> & NodeType<N,NT>,

  TitanN extends TitanNode<N,NT, TitanN,TitanNT>,
  TitanNT extends Enum<TitanNT> & TitanNodeType<N,NT, TitanN,TitanNT>
>
{
```


The node type which this Titan node type implements.


```java
  public NT type();
```


  The Titan key which classifies this titan node type.


```java
  public TitanKey titanKey();
```


  A builder for Titan nodes of this type. This could be implemented generically _if_ you could easily instantiate generic types in Java. But you can't. Anyway, this should be almost always `return new TitanN(vertex);`


```java
  public TitanNode<N,NT, TitanN,TitanNT> from(TitanVertex vertex);
}
```


------

### Index

+ src
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [Element.java][main/java/com/ohnosequences/typedGraphs/Element.java]
            + [ElementType.java][main/java/com/ohnosequences/typedGraphs/ElementType.java]
            + [Node.java][main/java/com/ohnosequences/typedGraphs/Node.java]
            + [NodeIndex.java][main/java/com/ohnosequences/typedGraphs/NodeIndex.java]
            + [NodeRetriever.java][main/java/com/ohnosequences/typedGraphs/NodeRetriever.java]
            + [NodeType.java][main/java/com/ohnosequences/typedGraphs/NodeType.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [PropertyType.java][main/java/com/ohnosequences/typedGraphs/PropertyType.java]
            + [Relationship.java][main/java/com/ohnosequences/typedGraphs/Relationship.java]
            + [RelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]
            + [RelationshipType.java][main/java/com/ohnosequences/typedGraphs/RelationshipType.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + titan
              + [TitanNode.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]
              + [TitanNodeType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNodeType.java]
              + [TitanPropertyType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanPropertyType.java]
              + [TitanRelationship.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]
              + [TitanRelationshipType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipType.java]
              + [TitanTypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]
            + [TypedGraph.java][main/java/com/ohnosequences/typedGraphs/TypedGraph.java]

[main/java/com/ohnosequences/typedGraphs/Element.java]: ../Element.java.md
[main/java/com/ohnosequences/typedGraphs/ElementType.java]: ../ElementType.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: ../Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: ../NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/NodeRetriever.java]: ../NodeRetriever.java.md
[main/java/com/ohnosequences/typedGraphs/NodeType.java]: ../NodeType.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../Property.java.md
[main/java/com/ohnosequences/typedGraphs/PropertyType.java]: ../PropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: ../RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipType.java]: ../RelationshipType.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeType.java]: TitanNodeType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanPropertyType.java]: TitanPropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipType.java]: TitanRelationshipType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../TypedGraph.java.md