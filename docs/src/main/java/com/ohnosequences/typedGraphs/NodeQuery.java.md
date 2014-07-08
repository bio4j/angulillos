
```java
// package com.ohnosequences.typedGraphs;

// import com.tinkerpop.blueprints.Predicate;

// /*
//   ## node queries

//   This two interfaces are the typed version of Blueprints `VertexQuery`. Given a node, we can use this for querying relationships of a given type in or out of that node.
// */
// interface NodeQueryOut < 
//   N extends Node<N,NT,G>, NT extends Node.Type<N,NT,G>, G extends TypedGraph
//   R extends Relationship<N,NT,G,R,RT,G,T,TT>, RT extends Relationship.Type<N,NT,G,R,RT,G,T,TT>,
//   T extends Node<T,TT>, TT extends Node.Type<T,TT>,
//   Q extends NodeQueryOut<N,NT,G,R,RT,G,T,TT,Q>
// >
// {

//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property);
//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property, V value);
//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property, Predicate predicate, V value);

//   public <P extends Property<R,RT,G,P,V>, V> Q hasNot(P property);
//   public <P extends Property<R,RT,G,P,V>, V> Q hasNot(P property, V value);

//   public <P extends Property<R,RT,G,P,V>, V extends Comparable<?>> Q interval(P property, V startValue, V endValue);
  
//   public <P extends Property<R,RT,G,P,V>, V> Q limit(int limit);

//   public R edges();
//   public T vertices();
// }

// interface NodeQueryIn < 
//   S extends Node<S,ST>, ST extends Node.Type<S,ST>,
//   R extends Relationship<S,ST,R,RT,G,N,NT,G>, RT extends Relationship.Type<S,ST,R,RT,G,N,NT,G>,
//   N extends Node<N,NT,G>, NT extends Node.Type<N,NT,G>,
//   Q extends NodeQueryIn<S,ST,R,RT,G,N,NT,G,Q>
// >
// {

//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property);
//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property, V value);
//   public <P extends Property<R,RT,G,P,V>, V> Q has(P property, Predicate predicate, V value);

//   public <P extends Property<R,RT,G,P,V>, V> Q hasNot(P property);
//   public <P extends Property<R,RT,G,P,V>, V> Q hasNot(P property, V value);

//   public <P extends Property<R,RT,G,P,V>, V extends Comparable<?>> Q interval(P property, V startValue, V endValue);
  
//   public <P extends Property<R,RT,G,P,V>, V> Q limit(int limit);

//   public R edges();
//   public S vertices();
// }


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
            + [UntypedGraph.java][main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]
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
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]
              + [TitanRelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]
              + [TitanProperty.java][main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]
              + [TitanNode.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]
            + [Element.java][main/java/com/ohnosequences/typedGraphs/Element.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/ElementIndex.java]: ElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeQuery.java]: NodeQuery.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanElement.java]: titan/TitanElement.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: titan/TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: titan/TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: titan/TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: titan/TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: titan/TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: titan/TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: Element.java.md