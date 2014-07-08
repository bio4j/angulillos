
```java
package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.TypedGraph;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.ohnosequences.typedGraphs.TypedGraph;
import com.ohnosequences.typedGraphs.*;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.*;

public interface TitanTypedGraph <
  TG extends TitanTypedGraph<TG>
> 
extends
  TypedGraph<TG,Titan,TitanVertex,TitanKey,TitanEdge,TitanLabel>
{

  public TitanGraph titanGraph();

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

```


creates a key in the graph using the provided `KeyMaker` and `name` if there is no such `TitanKey` with that `name`; otherwise it returns the existing `TitanKey` with the provided `name`.


```java
  public default TitanKey createOrGet(KeyMaker keyMaker, String name) {

    Boolean isNotDefined = true;

    TitanKey key = null;
    // first see if there's such a thing there
    Iterator<TitanKey> definedKeys = titanGraph().getTypes(TitanKey.class).iterator();

    while( definedKeys.hasNext() ) {

      TitanKey someKey = definedKeys.next();

      if ( someKey.getName().equals(name) ) { 
        
        isNotDefined = false;
        key = someKey;
      }
    }

    if( isNotDefined ) {

      key = keyMaker.make();
    }

    return key;
  }
```


creates a label in the graph using the provided `LabelMaker` and `name` if there is no such `TitanLabel` with that `name`; otherwise it returns the existing `TitanLabel` with the provided `name`.


```java
  public default TitanLabel createOrGet(LabelMaker labelMaker, String name) {

    Boolean isNotDefined = true;

    TitanLabel label = null;

    // first see if there's such a thing there
    Iterator<TitanLabel> definedLabels = titanGraph().getTypes(TitanLabel.class).iterator();

    while( definedLabels.hasNext() ) {

      TitanLabel someLabel = definedLabels.next();

      if ( someLabel.getName().equals(name) ) { 
        
        isNotDefined = false;
        label = someLabel;
      }
    }

    if( isNotDefined ) {

      label = labelMaker.make();
    }

    return label;
  }

//   /*
//     Get a `KeyMaker` already configured for creating the key corresponding to a node type. You can use this for defining the corresponding `TitanNode.Type`.
//   */
//   public default <
//     N extends Node<N,NT,TG>, NT extends Node.Type<N,NT,TG>,
//     P extends Property<N,NT,TG,P,V>, V
//   >
//   KeyMaker titanKeyMakerForNodeType(P property) {

//     // note how here we take the full name so that this is scoped by the node type; see `Property`.
//     KeyMaker keyMaker = titanGraph().makeKey(property.fullName())
//       .dataType(property.valueClass())
//       .indexed(com.tinkerpop.blueprints.Vertex.class)
//       .unique();

//     return keyMaker;
//   }

//   /*
//     create a `TitanKey` for a node type, using the default configuration. If a type with the same name is present it will be returned instead.
//   */
//   public default <
//     N extends Node<N,NT,TG>, NT extends Node.Type<N,NT,TG>,
//     P extends Property<N,NT,TG,P,V>, V
//   > TitanKey titanKeyForNodeType(P property) {

//     return createOrGet(titanKeyMakerForNodeType(property), property.fullName());
//   }

//   // public default <
//   //   N extends Node<N,NT,TG>, NT extends Node.Type<N,NT,TG>,
//   //   P extends Property<N,NT,P,String>
//   // > TitanKey titanKeyForNodeTypeWithStringId(P property) {

//   //   KeyMaker keyMaker = titanKeyMakerForNodeType(property);

//   //   keyMaker.indexed("search", com.tinkerpop.blueprints.Vertex.class, Parameter.of(Mapping.MAPPING_PREFIX, Mapping.STRING));

//   //   return createOrGet(keyMaker, property.fullName());
//   // }

  
//     Create a LabelMaker with the minimum default for a relationship type; you should use this for defining the corresponding `TitanRelationship.Type`. This is a `LabelMaker` so that you can define any custom signature, indexing etc.
  
//   public default <
//     S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>,
//     R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type<S,ST,SG,R,RT,RG,T,TT,TG>,
//     T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>
//   >
//   LabelMaker titanLabelMakerForRelationshipType(RT relationshipType) {

//     LabelMaker labelMaker = titanGraph().makeLabel(relationshipType.name())
//       .directed();

//     // define the arity
//     switch (relationshipType.arity()) {

//       case oneToOne:    labelMaker.oneToOne(); 
//       case oneToMany:   labelMaker.oneToMany();
//       case manyToOne:   labelMaker.manyToOne();
//       case manyToMany:  labelMaker.manyToMany();
//     }

//     return labelMaker;
//   }

//   /*
//     create a `TitanLabel` for a relationship type, using the default configuration. If a type with the same name is present it will be returned instead.
//   */
//   public default <
//     S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>,
//     R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type<S,ST,SG,R,RT,RG,T,TT,TG>,
//     T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>
//   >
//   TitanLabel titanLabelForRelationshipType(RT relationshipType) {

//     return createOrGet(titanLabelMakerForRelationshipType(relationshipType), relationshipType.name());
//   }

//   public default <
//     N extends Node<N,NT,TG>, NT extends Node.Type<N,NT,TG>,
//     P extends Property<N,NT,TG,P,V>, V
//   >
//   KeyMaker titanKeyMakerForNodeProperty(P property) {

//     return titanGraph().makeKey(property.fullName())
//       // .indexed(com.tinkerpop.blueprints.Edge.class)
//       .dataType(property.valueClass());

//   }

//   public default <
//     N extends Node<N,NT,TG>, NT extends Node.Type<N,NT,TG>,
//     P extends Property<N,NT,TG,P,V>, V
//   >
//   TitanKey titanKeyForNodeProperty(P property) {

//     return createOrGet(titanKeyMakerForNodeProperty(property), property.fullName());
//   }

//   public default <
//     S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>,
//     R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type<S,ST,SG,R,RT,RG,T,TT,TG>,
//     T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>,
//     P extends Property<R,RT,P,V>, V
//   >
//   KeyMaker titanKeyMakerForEdgeProperty(P property) {

//     return titanGraph().makeKey(property.fullName())
//       // .indexed(com.tinkerpop.blueprints.Edge.class)
//       .dataType(property.valueClass());
//   }

//   public default <
//     S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>,
//     R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type<S,ST,SG,R,RT,RG,T,TT,TG>,
//     T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>,
//     P extends Property<R,RT,P,V>, V
//   >
//   TitanKey titanKeyForEdgeProperty(P property) {

//     return createOrGet(titanKeyMakerForEdgeProperty(property), property.fullName());
//   }

//   public default <
//     S extends Node<S,ST,SG>, ST extends Node.Type<S,ST,SG>,
//     R extends Relationship<S,ST,SG,R,RT,RG,T,TT,TG>, RT extends Relationship.Type<S,ST,SG,R,RT,RG,T,TT,TG>,
//     T extends Node<T,TT,TG>, TT extends Node.Type<T,TT,TG>,
//     P extends Property<R,RT,P,V>, V
//   
//   LabelMaker signatureFor(LabelMaker labelMaker, P property) {

//     // create the key for it if not already there
//     TitanType maybeKey = titanGraph().getType(property.fullName());

//     TitanKey key = null;

//     if (maybeKey instanceof TitanKey && maybeKey != null) {

//       key = (TitanKey) maybeKey;
//     } 
//     else {

//       key = titanKeyMakerForEdgeProperty(property).unique().make();
//     }

//     return labelMaker.signature(key);
//   }

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

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: ../UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/ElementIndex.java]: ../ElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: ../Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: ../NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: ../RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeQuery.java]: ../NodeQuery.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanElement.java]: TitanElement.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: ../Element.java.md