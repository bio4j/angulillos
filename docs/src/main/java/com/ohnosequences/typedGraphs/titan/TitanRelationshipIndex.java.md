
```java
package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import com.tinkerpop.blueprints.Edge;

public interface TitanRelationshipIndex <
  S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
> 
extends 
  RelationshipIndex<S,ST,R,RT,T,TT,P,V>
{

  public static interface Unique <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  > 
  extends 
    RelationshipIndex.Unique<S,ST,R,RT,T,TT,P,V> 
  {
```


get a relationship by providing a value of the indexed property.


```java
    public R getRelationship(V byValue);
  }

  public static interface List <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  > 
  extends 
    RelationshipIndex.List<S,ST,R,RT,T,TT,P,V>
  {
```


get a list of relationships by providing a value of the indexed property.


```java
    public java.util.List<? extends R> getRelationships(V byValue);
  }





  public abstract class Default <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  >
  implements
    TitanRelationshipIndex<S,ST,R,RT,T,TT,P,V>
  {

    public Default(TitanTypedGraph graph, P property) {

      this.graph = graph;
      this.property = property;
    }

    protected TitanTypedGraph graph;
    protected P property;

    @Override public java.util.List<? extends R> query(com.tinkerpop.blueprints.Compare predicate, V value) {

      java.util.List<R> list = new LinkedList<>();

      Iterator<Edge> iterator = graph.rawGraph()
        .query().has(
          property.fullName(),
          predicate,
          value
        )
        .edges().iterator();
      
      while ( iterator.hasNext() ) {

        list.add(property.elementType().fromTitanEdge( (TitanEdge) iterator.next() ));
      }

      return list;
    }
  }
```

Default implementation of a relationship unique index

```java
  public final class DefaultUnique <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  > 
  extends
    Default<S,ST,R,RT,T,TT,P,V>
  implements 
    Unique<S,ST,R,RT,T,TT,P,V> 
  {

    public DefaultUnique(TitanTypedGraph graph, P property) {

      super(graph,property);
    }

    public R getRelationship(V byValue) {

      // crappy Java generics force the cast here
      TitanEdge uglyStuff = (TitanEdge) graph.rawGraph()
        .query().has(
          property.fullName(),
          Cmp.EQUAL, 
          byValue
        )
        .edges().iterator().next();

      return property.elementType().fromTitanEdge(uglyStuff);
    }

  }

  final class DefaultList <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  >
  extends
    Default<S,ST,R,RT,T,TT,P,V> 
  implements 
    List<S,ST,R,RT,T,TT,P,V> {

    public DefaultList(TitanTypedGraph graph, P property) {

      super(graph,property);
    }

    public java.util.List<R> getRelationships(V byValue) {

      java.util.List<R> list = new LinkedList<>();

      Iterator<Edge> iterator = graph.rawGraph()
        .query().has(
          property.fullName(),
          Cmp.EQUAL,
          byValue
        )
        .edges().iterator();
      
      while ( iterator.hasNext() ) {

        list.add(property.elementType().fromTitanEdge( (TitanEdge) iterator.next() ));
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
              + [TestTypeNames.java][test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]
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

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: ../Relationship.java.md
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
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: ../Element.java.md