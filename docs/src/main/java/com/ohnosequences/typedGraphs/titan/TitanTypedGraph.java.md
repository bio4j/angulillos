
```java
package com.ohnosequences.typedGraphs.titan;

import java.util.Set;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.*;
import com.thinkaurelius.titan.core.*;
```


A `TitanTypedGraph` defines a set of types (nodes, relationships, properties) comprising what you could call a _schema_ for a typed graph.



```java
public abstract class TitanTypedGraph implements TypedGraph {

  protected TitanGraph rawGraph;

  protected TitanTypedGraph(TitanGraph rawGraph) { this.rawGraph = rawGraph; }
```


  The set of Titan node types provided by this graph.


```java
  public abstract Set<? extends TitanNodeType> titanNodeTypes();
```


  The set of Titan relationship types provided by this graph.


```java
  public abstract Set<? extends TitanRelationshipType> titanRelationshipTypes();


  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  // defining keys and labels for node and rel types

```


  Create a TitanKey for indexing a node; you should use this for defining the corresponding `TitanNodeType`.


```java
  public <
    N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
    P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
    V
  >
  TitanKey titanKeyForNodeType(PT typeClassifier) {

    // note how here we take the full name so that this is scoped by the node type; see `PropertyType`.
    return rawGraph.makeKey(typeClassifier.fullName())
      .dataType(typeClassifier.valueClass())
      .indexed(com.tinkerpop.blueprints.Vertex.class)
      .unique()
      .make();
  }
```


  Create a LabelMaker with the minimum default for a relationship type; you should use this for defining the corresponding `TitanRelationshipType`. This is a `LabelMaker` so that you can define any custom signature, indexing etc.


```java
  public <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>
  >
  LabelMaker titanLabelForRelationshipType(RT typeClassifier) {

    LabelMaker labelMaker = rawGraph.makeLabel(typeClassifier.name())
      .directed();

    // define the arity
    switch (typeClassifier.arity()) {

      case oneToOne:    labelMaker.oneToOne(); 
      case oneToMany:   labelMaker.oneToMany();
      case manyToOne:   labelMaker.manyToOne();
      case manyToMany:  labelMaker.manyToMany();
    }

    return labelMaker;
  }

  public <
    N extends Node<N,NT>, NT extends Enum<NT> & NodeType<N,NT>,
    P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
    V
  >
  KeyMaker titanKeyForNodeProperty(PT propertyType) {

    return rawGraph.makeKey(propertyType.fullName())
      // .indexed(com.tinkerpop.blueprints.Edge.class)
      .dataType(propertyType.valueClass());

  }

  public <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT,P,PT,V>,
    V
  >
  KeyMaker titanKeyForEdgeProperty(PT propertyType) {

    return rawGraph.makeKey(propertyType.fullName())
      // .indexed(com.tinkerpop.blueprints.Edge.class)
      .dataType(propertyType.valueClass());
  }

  public <
    S extends Node<S,ST>,
    ST extends Enum<ST> & NodeType<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, 
    RT extends Enum<RT> & RelationshipType<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>,
    TT extends Enum<TT> & NodeType<T,TT>,
    P extends Property<R,RT>, PT extends PropertyType<R,RT,P,PT,V>,
    V
  >
  LabelMaker signatureFor(LabelMaker labelMaker, PT propertyType) {

    // create the key for it; TODO check if it's already there, if so then use it
    KeyMaker keyMaker = this.titanKeyForEdgeProperty(propertyType);
    keyMaker.unique();
    // could complain if this is already defined
    TitanKey key = keyMaker.make();

    return labelMaker.signature(key);
  }

  
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