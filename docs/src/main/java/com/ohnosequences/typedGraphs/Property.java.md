
```java
package com.ohnosequences.typedGraphs;
```


A Property. Just a marker.

@author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>


```java
public interface Property <
  N extends Element<N,T>, 
  T extends Enum<T> & ElementType<N,T>
> 
{}

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
            + [TypedGraph.java][main/java/com/ohnosequences/typedGraphs/TypedGraph.java]
            + [Relationship.java][main/java/com/ohnosequences/typedGraphs/Relationship.java]
            + [ElementType.java][main/java/com/ohnosequences/typedGraphs/ElementType.java]
            + [NodeType.java][main/java/com/ohnosequences/typedGraphs/NodeType.java]
            + [Node.java][main/java/com/ohnosequences/typedGraphs/Node.java]
            + [NodeIndex.java][main/java/com/ohnosequences/typedGraphs/NodeIndex.java]
            + [RelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [NodeRetriever.java][main/java/com/ohnosequences/typedGraphs/NodeRetriever.java]
            + titan
              + [TitanPropertyType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanPropertyType.java]
              + [TitanRelationship.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]
              + [TitanTypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]
              + [TitanRelationshipType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipType.java]
              + [TitanNodeType.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNodeType.java]
              + [TitanNode.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]
            + [Element.java][main/java/com/ohnosequences/typedGraphs/Element.java]
            + [PropertyType.java][main/java/com/ohnosequences/typedGraphs/PropertyType.java]
            + [RelationshipType.java][main/java/com/ohnosequences/typedGraphs/RelationshipType.java]

[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/ElementType.java]: ElementType.java.md
[main/java/com/ohnosequences/typedGraphs/NodeType.java]: NodeType.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeRetriever.java]: NodeRetriever.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanPropertyType.java]: titan/TitanPropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: titan/TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: titan/TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipType.java]: titan/TitanRelationshipType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeType.java]: titan/TitanNodeType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: titan/TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: Element.java.md
[main/java/com/ohnosequences/typedGraphs/PropertyType.java]: PropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipType.java]: RelationshipType.java.md