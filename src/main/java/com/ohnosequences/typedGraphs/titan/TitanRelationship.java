// package com.ohnosequences.typedGraphs.titan;

// import java.util.Set;
// import java.util.List;
// import java.util.LinkedList;
// import java.util.Iterator;


// import com.ohnosequences.typedGraphs.*;

// import com.thinkaurelius.titan.core.*;
// import com.tinkerpop.blueprints.Direction;

// public abstract class TitanRelationship <
//   S extends TitanNode<S,ST,G>, ST extends TitanNode.Type<S,ST,G>,
//   R extends TitanRelationship<S,ST,R,RT,T,TT,G>, RT extends TitanRelationship.Type<S,ST,R,RT,T,TT,G>,
//   T extends TitanNode<T,TT,G>, TT extends TitanNode.Type<T,TT,G>,
//   G extends TitanTypedGraph
// > 
// implements
//   TitanElement<R,RT,G>,
//   Relationship<S,ST, R,RT, T,TT, G>,
//   TitanEdge
// {

//   // titan rel 
//   protected TitanRelationship(TitanEdge raw) {
    
//     this.raw = raw;
//   }

//   protected TitanEdge raw;

//   @Override public TitanEdge raw() {

//     return this.raw;
//   }

//   public static interface Type <
//     S extends TitanNode<S,ST,G>, ST extends TitanNode.Type<S,ST,G>,
//     R extends TitanRelationship<S,ST,R,RT,T,TT,G>, RT extends TitanRelationship.Type<S,ST,R,RT,T,TT,G>,
//     T extends TitanNode<T,TT,G>, TT extends TitanNode.Type<T,TT,G>
//   >
//   extends
//     TitanElement.Type<R,RT,G>,  
//     Relationship.Type<S,ST,R,RT,T,TT,G>
//   {

//     /* The Titan label used for this rel type */
//     public TitanLabel label();

//     public R fromTitanEdge(TitanEdge edge);

//     @Override public default R from(Object edge) {

//       if (edge instanceof TitanEdge) {

//         TitanEdge uhoh = (TitanEdge) edge;

//         return fromTitanEdge(uhoh);
//       } 

//       else {

//         throw new IllegalArgumentException("edge should be a TitanEdge");
//       }
//     }
//   }

// 	@Override public S source() {

// 		return this.type().sourceType().from(
// 			raw.getVertex(Direction.OUT)
// 		);
// 	}

// 	@Override public T target() {

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