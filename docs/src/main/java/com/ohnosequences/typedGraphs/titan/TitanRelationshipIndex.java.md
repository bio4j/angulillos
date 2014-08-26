
```java
// package com.ohnosequences.typedGraphs.titan;

// import com.ohnosequences.typedGraphs.*;

// import com.thinkaurelius.titan.core.attribute.Cmp;
// import com.thinkaurelius.titan.core.*;

// import java.util.List;
// import java.util.LinkedList;
// import java.util.Iterator;
// import com.tinkerpop.blueprints.Edge;

// public interface TitanRelationshipIndex <
//   S extends TitanNode<S,ST>,
//     ST extends TitanNode.Type<S,ST>,
//     R extends TitanRelationship<S,ST,R,RT,T,TT>, 
//     RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
//     T extends TitanNode<T,TT>,
//     TT extends TitanNode.Type<T,TT>,
//     P extends TitanProperty<R,RT,P,V>, V
// > 
// extends 
//   RelationshipIndex<S,ST,R,RT,T,TT,P,V>
// {

//   public static interface Unique <
//     S extends TitanNode<S,ST>,
//     ST extends TitanNode.Type<S,ST>,
//     R extends TitanRelationship<S,ST,R,RT,T,TT>, 
//     RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
//     T extends TitanNode<T,TT>,
//     TT extends TitanNode.Type<T,TT>,
//     P extends TitanProperty<R,RT,P,V>, V
//   > 
//   extends 
//     RelationshipIndex.Unique<S,ST,R,RT,T,TT,P,V> 
//   {

//     /*
//       get a relationship by providing a value of the indexed property.
//     */
//     public R getRelationship(V byValue);
//   }

//   public static interface List <
//     S extends TitanNode<S,ST>,
//     ST extends TitanNode.Type<S,ST>,
//     R extends TitanRelationship<S,ST,R,RT,T,TT>, 
//     RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
//     T extends TitanNode<T,TT>,
//     TT extends TitanNode.Type<T,TT>,
//     P extends TitanProperty<R,RT,P,V>, V
//   > 
//   extends 
//     RelationshipIndex.List<S,ST,R,RT,T,TT,P,V>
//   {

//     /*
//       get a list of relationships by providing a value of the indexed property.
//     */
//     public java.util.List<? extends R> getRelationships(V byValue);
//   }





//   public abstract class Default <
//     S extends TitanNode<S,ST>,
//     ST extends TitanNode.Type<S,ST>,
//     R extends TitanRelationship<S,ST,R,RT,T,TT>, 
//     RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
//     T extends TitanNode<T,TT>,
//     TT extends TitanNode.Type<T,TT>,
//     P extends TitanProperty<R,RT,P,V>, V
//   >
//   implements
//     TitanRelationshipIndex<S,ST,R,RT,T,TT,P,V>
//   {

//     public Default(TitanTypedGraph graph, P property) {

//       this.graph = graph;
//       this.property = property;
//     }

//     protected TitanTypedGraph graph;
//     protected P property;

//     @Override public java.util.List<? extends R> query(com.tinkerpop.blueprints.Compare predicate, V value) {

//       java.util.List<R> list = new LinkedList<>();

//       Iterator<Edge> iterator = graph.rawGraph()
//         .query().has(
//           property.name(),
//           predicate,
//           value
//         )
//         .edges().iterator();
      
//       while ( iterator.hasNext() ) {

//         list.add(property.elementType().fromTitanEdge( (TitanEdge) iterator.next() ));
//       }

//       return list;
//     }
//   }


//   /* Default implementation of a relationship unique index */
//   public final class DefaultUnique <
//     S extends TitanNode<S,ST>,
//     ST extends TitanNode.Type<S,ST>,
//     R extends TitanRelationship<S,ST,R,RT,T,TT>, 
//     RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
//     T extends TitanNode<T,TT>,
//     TT extends TitanNode.Type<T,TT>,
//     P extends TitanProperty<R,RT,P,V>, V
//   > 
//   extends
//     Default<S,ST,R,RT,T,TT,P,V>
//   implements 
//     Unique<S,ST,R,RT,T,TT,P,V> 
//   {

//     public DefaultUnique(TitanTypedGraph graph, P property) {

//       super(graph,property);
//     }

//     public R getRelationship(V byValue) {

//       // crappy Java generics force the cast here
//       TitanEdge uglyStuff = (TitanEdge) graph.rawGraph()
//         .query().has(
//           property.name(),
//           Cmp.EQUAL, 
//           byValue
//         )
//         .edges().iterator().next();

//       return property.elementType().fromTitanEdge(uglyStuff);
//     }

//   }

//   final class DefaultList <
//     S extends TitanNode<S,ST>,
//     ST extends TitanNode.Type<S,ST>,
//     R extends TitanRelationship<S,ST,R,RT,T,TT>, 
//     RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
//     T extends TitanNode<T,TT>,
//     TT extends TitanNode.Type<T,TT>,
//     P extends TitanProperty<R,RT,P,V>, V
//   >
//   extends
//     Default<S,ST,R,RT,T,TT,P,V> 
//   implements 
//     List<S,ST,R,RT,T,TT,P,V> {

//     public DefaultList(TitanTypedGraph graph, P property) {

//       super(graph,property);
//     }

//     public java.util.List<R> getRelationships(V byValue) {

//       java.util.List<R> list = new LinkedList<>();

//       Iterator<Edge> iterator = graph.rawGraph()
//         .query().has(
//           property.name(),
//           Cmp.EQUAL,
//           byValue
//         )
//         .edges().iterator();
      
//       while ( iterator.hasNext() ) {

//         list.add(property.elementType().fromTitanEdge( (TitanEdge) iterator.next() ));
//       }

//       return list;
//     }
//   }

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
            + [TypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/ohnosequences/typedGraphs/TypedEdge.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [TypedElementIndex.java][main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/typedGraphs/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]
            + titan
              + [TitanTypedVertex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertex.java]
              + [TitanTypedEdge.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdge.java]
              + [TitanTypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]
              + [TitanTypedElement.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedElement.java]
              + [TitanRelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]
              + [TitanProperty.java][main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]
            + [TypedVertex.java][main/java/com/ohnosequences/typedGraphs/TypedVertex.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]: ../TypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: ../UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdge.java]: ../TypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]: ../TypedElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../Property.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]: ../TypedVertexQuery.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElement.java]: ../TypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]: ../TypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertex.java]: TitanTypedVertex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdge.java]: TitanTypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]: TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedElement.java]: TitanTypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertex.java]: ../TypedVertex.java.md