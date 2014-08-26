
```java
// package com.ohnosequences.typedGraphs.titan;

// import com.ohnosequences.typedGraphs.*;

// import com.thinkaurelius.titan.core.attribute.Cmp;
// import com.thinkaurelius.titan.core.*;

// import java.util.List;
// import java.util.LinkedList;
// import java.util.Iterator;
// import com.tinkerpop.blueprints.Vertex;

// public interface TitanTypedVertexIndex <
//   N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
//   P extends TitanProperty<N,NT,G,P,V>, V,
//   G extends TitanTypedGraph<G>
// > 
// extends 
//   TypedVertexIndex<N,NT,P,V, G, TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
// {

//   public static abstract class Default <
//     N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
//     P extends TitanProperty<N,NT,G,P,V>, V,
//     G extends TitanTypedGraph<G>
//   > 
//   implements 
//     TitanTypedVertexIndex<N,NT,P,V,G>
//   {

//     public Default(G graph, P property) {

//       this.graph = graph;
//       this.property = property;
//     }

//     protected G graph;
//     protected P property;

//     @Override
//     public G graph() {

//       return graph;
//     }

//     @Override public java.util.List<N> query(com.tinkerpop.blueprints.Compare predicate, V value) {

//       java.util.List<N> list = new LinkedList<>();

//       Iterator<Vertex> iterator = graph().raw().titanGraph()
//         .query().has(
//           property.name(),
//           predicate,
//           value
//         )
//         .vertices().iterator();
      
//       while ( iterator.hasNext() ) {

//         list.add(property.elementType().from( (TitanVertex) iterator.next() ));
//       }

//       return list;
//     }
//   }

//   public interface Unique <
//     N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
//     P extends TitanProperty<N,NT,G,P,V>, V,
//     G extends TitanTypedGraph<G>
//   > 
//   extends
//     TitanTypedVertexIndex<N,NT,P,V,G>,
//     TypedVertexIndex.Unique<N,NT,P,V,G, TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
//   {

//   }

//   /* Default implementation of a node unique index */
//   public static final class DefaultUnique <
//     N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
//     P extends TitanProperty<N,NT,G,P,V>, V,
//     G extends TitanTypedGraph<G>
//   > 
//   extends
//     Default<N,NT,P,V,G> 
//   implements 
//     TitanTypedVertexIndex.Unique<N,NT,P,V,G> 
//   {

//     public DefaultUnique(G graph, P property) {

//       super(graph,property);
//     }
//   }

//   public static interface List <
//     N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
//     P extends TitanProperty<N,NT,G,P,V>, V,
//     G extends TitanTypedGraph<G>
//   > 
//   extends
//     TitanTypedVertexIndex<N,NT,P,V,G>,
//     TypedVertexIndex.List<N,NT,P,V,G, TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
//   {

//   }

//   public static final class DefaultList <
//     N extends TitanTypedVertex<N,NT,G>, NT extends TitanTypedVertex.Type<N,NT,G>,
//     P extends TitanProperty<N,NT,G,P,V>, V,
//     G extends TitanTypedGraph<G>
//   > 
//   extends
//     Default<N,NT,P,V,G>
//   implements 
//     TitanTypedVertexIndex.List<N,NT,P,V,G> 
//   {

//     public DefaultList(G graph, P property) {

//       super(graph,property);
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