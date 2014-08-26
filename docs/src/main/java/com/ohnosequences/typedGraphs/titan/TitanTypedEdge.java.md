
```java
// package com.ohnosequences.typedGraphs.titan;

// import java.util.Set;
// import java.util.List;
// import java.util.LinkedList;
// import java.util.Iterator;


// import com.ohnosequences.typedGraphs.*;

// import com.thinkaurelius.titan.core.*;
// import com.tinkerpop.blueprints.Direction;

// public abstract class TitanTypedEdge <
//   S extends TitanTypedVertex<S,ST,SG>,
//   ST extends TitanTypedVertex.Type<S,ST,SG>,
//   SG extends TitanTypedGraph<SG>,

//   R extends TitanTypedEdge<S,ST,SG, R,RT,RG, T,TT,TG>,
//   RT extends TitanTypedEdge.Type<S,ST,SG, R,RT,RG, T,TT,TG>,
//   RG extends TitanTypedGraph<RG>,

//   T extends TitanTypedVertex<T,TT,TG>,
//   TT extends TitanTypedVertex.Type<T,TT,TG>,
//   TG extends TitanTypedGraph<TG>
// > 
// implements
//   TitanTypedElement<R,RT,RG>,
//   TypedEdge<S,ST,SG, R,RT, RG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel, T,TT,TG>,
//   TitanEdge
// {

//   // titan rel 
//   protected TitanTypedEdge(TitanEdge raw) {
    
//     this.raw = raw;
//   }

//   protected TitanEdge raw;

//   @Override 
//   public TitanEdge raw() {

//     return this.raw;
//   }

//   public interface Type <
//     S extends TitanTypedVertex<S,ST,SG>,
//     ST extends TitanTypedVertex.Type<S,ST,SG>,
//     SG extends TitanTypedGraph<SG>,

//     R extends TitanTypedEdge<S,ST,SG, R,RT,RG, T,TT,TG>,
//     RT extends TitanTypedEdge.Type<S,ST,SG, R,RT,RG, T,TT,TG>,
//     RG extends TitanTypedGraph<RG>,

//     T extends TitanTypedVertex<T,TT,TG>, TT extends TitanTypedVertex.Type<T,TT,TG>,
//     TG extends TitanTypedGraph<TG>
//   >
//   extends
//     TitanTypedElement.Type<R,RT,RG>,  
//     TypedEdge.Type<S,ST,SG, R,RT, RG,TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel, T,TT,TG>
//   {

//     /* The Titan label used for this rel type */
//     public TitanLabel label();

//     @Override
//     public R from(TitanEdge edge);
//   }

// 	@Override 
//   public S source() {

// 		return this.type().sourceType().from(
// 			raw.getVertex(Direction.OUT)
// 		);
// 	}

// 	@Override 
//   public T target() {

// 		return this.type().targetType().from(
// 			raw.getVertex(Direction.IN)
// 		);
// 	}

//   /*
//     ### delegating methods to `TitanEdge`

//     Here we forward all `TitanEdge`-specific methods to the wrapped `raw` value.
//   */
// 	//////////////////////////////////////////////////////////////////////////////////////////
// 	// fwd TitanEdge methods to raw
// 	@Override public TitanLabel getTitanLabel() {
// 		return raw.getTitanLabel();
// 	}
// 	@Override public TitanVertex getVertex(Direction dir){
// 		return raw.getVertex(dir);
// 	}
// 	@Override public TitanVertex getOtherVertex(TitanVertex vertex){
// 		return raw.getOtherVertex(vertex);
// 	}
// 	@Override public boolean isDirected(){
// 		return raw.isDirected();
// 	}
// 	@Override public boolean isUnidirected(){
// 		return raw.isUnidirected();
// 	}
//   @Override public Set<String> getPropertyKeys() {
//     return raw.getPropertyKeys(); 
//   }
//   @Override public TitanType getType() { 
//     return raw.getType(); 
//   }
//   @Override public Direction getDirection(TitanVertex vertex) { 
//     return raw.getDirection(vertex); 
//   }
//   @Override public TitanVertex getProperty(TitanLabel label) { 
//     return raw.getProperty(label); 
//   }
//   @Override public boolean isEdge() { 
//     return raw.isEdge(); 
//   }
//   @Override public boolean isIncidentOn(TitanVertex vertex) { 
//     return raw.isIncidentOn(vertex); 
//   }
//   @Override public boolean isLoop() { 
//     return raw.isLoop(); 
//   }
//   @Override public boolean isModifiable(){ 
//     return raw.isModifiable(); 
//   }
//   @Override public boolean isProperty(){ 
//     return raw.isProperty(); 
//   }
//   @Override public void setProperty(TitanLabel label, TitanVertex vertex) { 
//     raw.setProperty(label,vertex); 
//   }
//   @Override public String getLabel() { 
//     return raw.getLabel(); 
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