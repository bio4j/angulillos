
```java
package com.ohnosequences.typedGraphs;

public interface RelationshipIndex <
  S extends Node<S,ST>,
  ST extends Enum<ST> & NodeType<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, 
  RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>,
  TT extends Enum<TT> & NodeType<T,TT>,
  P extends Property<R,RT>, PT extends PropertyType<R,RT, P,PT, V>,
  V
>
{

  interface Unique <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT, P,PT, V>,
    V
  > extends RelationshipIndex<S,ST,R,RT,T,TT,P,PT,V> 
  {
```


    get a node by providing a value of the indexed property.


```java
    public Relationship<S,ST,R,RT,T,TT> getRelationship(V byValue);
  }

  interface List <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT, P,PT, V>,
    V
  > extends RelationshipIndex<S,ST,R,RT,T,TT,P,PT,V> 
  {
```


    get a list of nodes by providing a value of the indexed property.


```java
    public java.util.List<? extends Relationship<S,ST,R,RT,T,TT>> getRelationships(V byValue);
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