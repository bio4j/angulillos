
```java
package com.ohnosequences.typedGraphs;

import java.util.Set;
```


A module represents essentially the types of a typed graph.



```java
public interface Module {
```


  This module could depend on other modules; for example, one of its rels could have as target a node from another module.  


```java
  public Set<Module> dependencies();
```


  The package in which this module is defined. This could be helpful for namespacing when working with it and interacting with a concrete store, for example.


```java
  public String pkg();
```


  The set of node types provided by this module.


```java
  public Set<? extends NodeType> nodeTypes();
```


  The set of relationship types provided by this module.


```java
  public Set<? extends RelationshipType> relationshipTypes();
```


  The set of property types provided by this module


```java
  public Set<? extends PropertyType> propertyTypes();
  // public Set<? extends NodeIndex> indexes(); 
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

[main/java/com/ohnosequences/typedGraphs/RelTypes.java]: RelTypes.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/ElementType.java]: ElementType.java.md
[main/java/com/ohnosequences/typedGraphs/NodeType.java]: NodeType.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeUniqueIndex.java]: NodeUniqueIndex.java.md
[main/java/com/ohnosequences/typedGraphs/NodeListIndex.java]: NodeListIndex.java.md
[main/java/com/ohnosequences/typedGraphs/NodeRetriever.java]: NodeRetriever.java.md
[main/java/com/ohnosequences/typedGraphs/Module.java]: Module.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanPropertyType.java]: titan/TitanPropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: titan/TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipType.java]: titan/TitanRelationshipType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeType.java]: titan/TitanNodeType.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: titan/TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: Element.java.md
[main/java/com/ohnosequences/typedGraphs/PropertyType.java]: PropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipType.java]: RelationshipType.java.md