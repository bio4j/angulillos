
```java
package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import com.tinkerpop.blueprints.Vertex;

public interface TitanNodeIndex <
  N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
  P extends Property<N,NT,P,V>, V
> 
extends 
  NodeIndex<N,NT,P,V>
{

  public static interface Unique <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends 
    NodeIndex.Unique<N,NT,P,V> 
  {
```

get a node by providing a value of the indexed property.

```java
    public N getNode(V byValue);
  }

  public static interface List <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends 
    NodeIndex.List<N,NT,P,V>
  {
```


get a list of nodes by providing a value of the indexed property.


```java
    public java.util.List<? extends N> getNodes(V byValue);
  }


  public abstract class Default <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  implements 
    TitanNodeIndex<N,NT,P,V>
  {

    public Default(TitanTypedGraph graph, P property) {

      this.graph = graph;
      this.property = property;
    }

    protected TitanTypedGraph graph;
    protected P property;

    @Override public java.util.List<? extends N> query(com.tinkerpop.blueprints.Compare predicate, V value) {

      java.util.List<N> list = new LinkedList<>();

      Iterator<Vertex> iterator = graph.rawGraph()
        .query().has(
          property.fullName(),
          predicate,
          value
        )
        .vertices().iterator();
      
      while ( iterator.hasNext() ) {

        list.add(property.elementType().from( (TitanVertex) iterator.next() ));
      }

      return list;
    }
  }
```

Default implementation of a node unique index

```java
  public final class DefaultUnique <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends
    Default<N,NT,P,V> 
  implements 
    Unique<N,NT,P,V> 
  {

    public DefaultUnique(TitanTypedGraph graph, P property) {

      super(graph,property);
    }

    @Override public N getNode(V byValue) {

      // crappy Java generics force the cast here
      TitanVertex uglyStuff = (TitanVertex) graph.rawGraph()
        .query().has(
          property.fullName(),
          Cmp.EQUAL, 
          byValue
        )
        .vertices().iterator().next();

      return property.elementType().fromTitanVertex(uglyStuff);
    }

  }

  final class DefaultList <
    N extends TitanNode<N,NT>, NT extends TitanNode.Type<N,NT>,
    P extends Property<N,NT,P,V>, V
  > 
  extends
    Default<N,NT,P,V>
  implements 
    List<N,NT,P,V> 
  {

    public DefaultList(TitanTypedGraph graph, P property) {

      super(graph,property);
    }

    @Override public java.util.List<N> getNodes(V byValue) {

      java.util.List<N> list = new LinkedList<>();

      Iterator<Vertex> iterator = graph.rawGraph()
        .query().has(
          property.fullName(),
          Cmp.EQUAL,
          byValue
        )
        .vertices().iterator();
      
      while ( iterator.hasNext() ) {

        list.add(property.elementType().from( (TitanVertex) iterator.next() ));
      }

      return list;
    }
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

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: ../Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: ../NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: ../RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../Property.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: ../Element.java.md