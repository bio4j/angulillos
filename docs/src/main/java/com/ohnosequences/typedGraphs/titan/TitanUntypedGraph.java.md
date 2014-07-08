
```java
package com.ohnosequences.typedGraphs.titan;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.ohnosequences.typedGraphs.UntypedGraph;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;
import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanKey;

public interface TitanUntypedGraph extends UntypedGraph<TitanVertex,TitanKey,TitanEdge,TitanLabel> {

  @Override
  default <V> V getPropertyV(TitanVertex vertex, String property) {

    return vertex.<V>getProperty(property);
  }

  @Override
  default List<TitanEdge> out(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanEdge> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next());
    }

    return list;
  }

  @Override
  default List<TitanVertex> outV(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanVertex> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.OUT, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next().getVertex(com.tinkerpop.blueprints.Direction.IN));
    }

    return list;
  }

  @Override
  default List<TitanEdge> in(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanEdge> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.IN, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next());
    }

    return list;
  }

  @Override
  default List<TitanVertex> inV(TitanVertex vertex, TitanLabel edgeType) {

    List<TitanVertex> list = new LinkedList<>();

    Iterator<TitanEdge> iterE = vertex.getTitanEdges(com.tinkerpop.blueprints.Direction.IN, edgeType).iterator();
    
    while (iterE.hasNext()) {

      list.add(iterE.next().getVertex(com.tinkerpop.blueprints.Direction.OUT));
    }

    return list;
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