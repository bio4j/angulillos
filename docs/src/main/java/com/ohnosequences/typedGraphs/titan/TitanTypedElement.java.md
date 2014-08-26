
```java
// package com.ohnosequences.typedGraphs.titan;

// import com.ohnosequences.typedGraphs.TypedElement;
// import com.thinkaurelius.titan.core.*;

// import java.util.Set;

// public interface TitanTypedElement <
//   E extends TitanTypedElement<E,ET,G>, 
//   ET extends TitanTypedElement.Type<E,ET,G>,
//   G extends TitanTypedGraph<G>
// >
// extends
//   TypedElement<E,ET,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
//   TitanElement
// {

//   @Override public TitanElement raw();

//   public interface Type <
//     E extends TitanTypedElement<E,ET,G>,
//     ET extends TitanTypedElement.Type<E,ET,G>,
//     G extends TitanTypedGraph<G>
//   >
//   extends
//     TypedElement.Type<E,ET,G,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
//   {


//   }

//   // fwd titan methods to raw
//   @Override public default <O> O getProperty(TitanKey arg0) { 
//     return raw().getProperty(arg0); 
//   }
//   @Override public default <O> O getProperty(String arg0) { 
//     return raw().getProperty(arg0); 
//   }
//   @Override public default boolean hasId() {  
//     return raw().hasId(); 
//   }
//   @Override public default boolean isLoaded() {   
//     return raw().isLoaded();  
//   }
//   @Override public default boolean isNew() {    
//     return raw().isNew(); 
//   }
//   @Override public default boolean isRemoved() {  
//     return raw().isRemoved(); 
//   }
//   @Override public default void remove() {  
//     raw().remove(); 
//   }
//   @Override public default <O> O removeProperty(String arg0) {  
//     return raw().removeProperty(arg0);  
//   }
//   @Override public default <O> O removeProperty(TitanType arg0) { 
//     return raw().removeProperty(arg0);
//   }
//   @Override public default void setProperty(String arg0, Object arg1) {   
//     raw().setProperty(arg0, arg1);  
//   }
//   @Override public default void setProperty(TitanKey arg0, Object arg1) { 
//     raw().setProperty(arg0, arg1);  
//   }
//   @Override public default Set<String> getPropertyKeys() {    
//     return raw().getPropertyKeys(); 
//   }
//   @Override public default int compareTo(TitanElement arg0) {  
//     return raw().compareTo(arg0); 
//   }
//   @Override public default long getID() { 
//     return raw().getID(); 
//   }
//   @Override public default Object getId() { 
//     return raw().getId();
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