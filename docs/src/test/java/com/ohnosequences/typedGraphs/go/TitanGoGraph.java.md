
```java
package com.ohnosequences.typedGraphs.test.go;

import com.ohnosequences.typedGraphs.*;
import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;
```


Implementing the types with Titan


```java
public abstract class TitanGoGraph 
implements 
  TitanTypedGraph,
  GoGraph 
{

  protected TitanGraph rawGraph;
  TitanGoGraph(TitanGraph rawGraph) { this.rawGraph = rawGraph; }
  @Override public TitanGraph rawGraph() { return rawGraph; }
```


  A gene ontology term implementation. It is a Titan node and implements the `Term` interface. It is defined as an inner class here just for convenience in this small example; it could be anywhere else. it'd just need a reference to this graph for retrieving its type. 


```java
  public final class TitanTerm
  extends
    TitanNode<TitanTerm, TitanTermType>
  implements
    Term<TitanTerm, TitanTermType>
  {
    TitanTerm(TitanVertex vertex) { super(vertex); }
```


Note here how we need a reference to the enclosing graph, which contains the term type value.


```java
    @Override public TitanTermType type() { return TitanGoGraph.this.termT; }
  }
```


The type of a TitanTerm. This an inner class of the graph. The first key here represents the type of the node, while the rest are for properties of this term: `id` and `name` in this case.


```java
  TitanKey termTkey;
  TitanKey termIdKey;
  TitanKey termNameKey;
  TitanTermType termT = new TitanTermType();
  public final class TitanTermType
  implements
    TitanNode.Type<TitanTerm,TitanTermType>,
    TermType<TitanTerm,TitanTermType>
  {
    @Override public TitanKey titanKey() { return TitanGoGraph.this.termTkey; }
    @Override public TitanTermType value() { return TitanGoGraph.this.termT; }
    @Override public TitanTerm fromTitanVertex(TitanVertex vertex) { return new TitanTerm(vertex); }
    // properties
    public id id = new id();
    // no need to worry about the unchecked warning
    @Override public id Id() { return id; }
    public final class id 
    implements
      com.ohnosequences.typedGraphs.titan.TitanProperty<TitanTerm,TitanTermType,id,String>,
      TermType.id<TitanTerm,TitanTermType,id>
    {
      @Override public TitanTermType elementType() { return TitanTermType.this; }
      @Override public TitanKey titanKey() { return TitanGoGraph.this.termIdKey; }
    }
    name name = new name();
    @Override public name Name() { return name; }
    public final class name 
    implements 
      com.ohnosequences.typedGraphs.titan.TitanProperty<TitanTerm,TitanTermType,name,String>,
      TermType.name<TitanTerm,TitanTermType,name>
    {
      @Override public TitanTermType elementType() { return TitanTermType.this; }
      @Override public TitanKey titanKey() { return TitanGoGraph.this.termNameKey; }
    }
  }

  // rels

  // the partOf rel
  public final class TitanPartOf
  extends
    TitanRelationship<TitanTerm,TitanTermType, TitanPartOf,TitanPartOfType, TitanTerm,TitanTermType>
  implements
    PartOf<TitanTerm,TitanTermType, TitanPartOf,TitanPartOfType, TitanTerm,TitanTermType>
  {
    TitanPartOf(TitanEdge edge) { super(edge); }
```


Note here how we need a reference to the enclosing graph, which contains the term type value.


```java
    @Override public TitanPartOfType type() { return TitanGoGraph.this.partOfT; }
  }

  TitanLabel partOfLabel;
  TitanPartOfType partOfT = new TitanPartOfType();
  public final class TitanPartOfType
  implements
    TitanRelationship.Type<TitanTerm,TitanTermType, TitanPartOf,TitanPartOfType, TitanTerm,TitanTermType>,
    PartOfType<TitanTerm,TitanTermType, TitanPartOf,TitanPartOfType, TitanTerm,TitanTermType>
  {
    @Override public TitanLabel label() { return TitanGoGraph.this.partOfLabel; }
    @Override public TitanPartOfType value() { return TitanGoGraph.this.partOfT; }
    @Override public TitanTermType sourceType() { return TitanGoGraph.this.termT; }
    @Override public TitanTermType targetType() { return TitanGoGraph.this.termT; }
    @Override public TitanPartOf fromTitanEdge(TitanEdge edge) { return new TitanPartOf(edge); }
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

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: TitanGoGraphImpl.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Property.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Element.java.md