
```java
package com.ohnosequences.typedGraphs.test.go;

import com.ohnosequences.typedGraphs.*;
```


This interface contains an example graph types declaration.


```java
public interface GoGraph {
```


A gene ontology term


```java
  interface Term <
    N extends Term<N,NT>, NT extends TermType<N,NT>
  >
    extends Node<N,NT>
  {
```


These two methods are completely optional, they just give you a way of retrieving a particular property at the level of this interface.


```java
    public default String name() { return this.get(type().Name()); }
    public default String id() { return this.get(type().Id()); }
  }

  // properties
    interface id <
      N extends Term<N,NT>,
      NT extends TermType<N,NT>,
      P extends id<N,NT,P>
    > 
      extends Property<N,NT,P,String> 
    {
      @Override public default String name() { return "id"; } 
      @Override public default Class<String> valueClass() { return String.class; }
    }
```


The type of a Term. Nested here we can find the properties of this type. Again, they could be defined anywhere; they are nested just for convenience.


```java
  interface TermType <
    N extends Term<N,NT>, NT extends TermType<N,NT>
  > 
    extends Node.Type<N,NT>
  {

    public <P extends id<N,NT,P>> P Id();
    public <P extends name<N,NT,P>> P Name();
    interface name <
      N extends Term<N,NT>,
      NT extends TermType<N,NT>,
      P extends name<N,NT,P>
    > 
      extends Property<N,NT,P,String> 
    {
      @Override public default String name() { return "name"; } 
      @Override public default Class<String> valueClass() { return String.class; }
    }
  }
```


  The partOf relationship; it goes from terms to terms.


```java
  interface PartOf <
    S extends Term<S,ST>, ST extends TermType<S,ST>,
    R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOfType<S,ST,R,RT,T,TT>,
    T extends Term<T,TT>, TT extends TermType<T,TT>
  >
    extends Relationship<S,ST,R,RT,T,TT>
  {}

  interface PartOfType <
    S extends Term<S,ST>, ST extends TermType<S,ST>,
    R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOfType<S,ST,R,RT,T,TT>,
    T extends Term<T,TT>, TT extends TermType<T,TT>
  >
    extends Relationship.Type.ManyToMany<S,ST,R,RT,T,TT>
  {} 
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

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: TitanGoGraphImpl.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/ElementIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/ElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeQuery.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/NodeQuery.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanElement.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanElement.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Element.java.md