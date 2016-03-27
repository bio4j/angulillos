package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;


/*
  ## Typed Vertices

  A typed vertex. A vertex and its type need to be defined at the same time. The vertex keeps a reference of its type, while the type works as a factory for creating vertices with that type.
*/
abstract class VertexType <
  VT extends VertexType<VT, G,RV,RE>,
  G  extends TypedGraph<G,RV,RE>,
  RV,RE
> extends ElementType<VT,G,RV> {

  @Override public Vertex fromRaw(RV raw) { return this.new Vertex(raw); }

  public final class Vertex extends Element {

    public Vertex(RV raw) { super(raw); }


    /* ### Properties */
    @Override public
    <X> X get(Property<VT,X> property) { return graph().getProperty(this, property); }

    @Override public
    <X> Vertex set(Property<VT,X> property, X value) {

      graph().setProperty(this, property, value);
      return this;
    }

    /* #### outE */
    public final <
      ET extends EdgeType<VT,G, ET,EG, TT,TG, RV,RE>,
      EG extends TypedGraph<EG,RV,RE>,
      TT extends VertexType<TT, TG,RV,RE>,
      TG extends TypedGraph<TG,RV,RE>
    >
    Stream<
      EdgeType<VT,G, ET,EG, TT,TG, RV,RE>.Edge
    > outE(ET edgeType) { return graph().outE(this, edgeType); }


    /* #### outV */
    public final <
      ET extends EdgeType<VT,G, ET,EG, TT,TG, RV,RE>,
      EG extends TypedGraph<EG,RV,RE>,
      TT extends VertexType<TT, TG,RV,RE>,
      TG extends TypedGraph<TG,RV,RE>
    >
    Stream<
      VertexType<TT, TG,RV,RE>.Vertex
    > outV(ET edgeType) { return graph().outV(this, edgeType); }

  }

  // /*
  //   ### Create edges in/out of this vertex
  //
  //   There are two methods for creating new edges, into and out of this vertex respectively. Their implementation delegates to the typed graph methods. Note that all graphs are in principle different.
  // */
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  // >
  // E addInEdge(S from, ET edgeType) { return graph().addEdge( from, edgeType, self() ); }
  //
  // default <
  //   E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // E addOutEdge(ET edgeType, T to) { return graph().addEdge( self(), edgeType, to ); }


  // /*
  //   ### Getting incoming and outgoing edges
  //
  //   For when you don't know anything about the arity, we have unbounded in/out methods which return `Stream`s
  // */
  // default <
  //   E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
  //   ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
  //            & TypedEdge.Type.ToAtLeastOne
  // >
  // Stream<E> outAtLeastOneE(ET edgeType) { return graph().outAtLeastOneE(self(), edgeType); }
  //
  // default <
  //   E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
  //   ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
  //            & TypedEdge.Type.ToAtMostOne
  // >
  // Optional<E> outAtMostOneE(ET edgeType) { return graph().outAtMostOneE(self(), edgeType); }
  //
  // default <
  //   E  extends      TypedEdge<V,VT, E,ET, ?,?, ?,RV,RE>,
  //   ET extends TypedEdge.Type<V,VT, E,ET, ?,?, ?,RV,RE>
  //            & TypedEdge.Type.ToOne
  // >
  // E outOneE(ET edgeType) { return graph().outOneE(self(), edgeType); }
  //
  //
  // /* #### inE */
  // default <
  //   E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
  // >
  // Stream<E> inE(ET edgeType) { return graph().inE(self(), edgeType); }
  //
  // default <
  //   E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
  //            & TypedEdge.Type.FromAtLeastOne
  // >
  // Stream<E> inAtLeastOneE(ET edgeType) { return graph().inAtLeastOneE(self(), edgeType); }
  //
  // default <
  //   E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
  //            & TypedEdge.Type.FromAtMostOne
  // >
  // Optional<E> inAtMostOneE(ET edgeType) { return graph().inAtMostOneE(self(), edgeType); }
  //
  // default <
  //   E  extends      TypedEdge<?,?, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<?,?, E,ET, V,VT, ?,RV,RE>
  //            & TypedEdge.Type.FromOne
  // >
  // E inOneE(ET edgeType) { return graph().inOneE(self(), edgeType); }
  //
  // /////////////////////////////////////////////////////////////////////////////////////////////////////
  //
  // default <
  //   E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.ToAtLeastOne,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // Stream<T> outAtLeastOneV(ET edgeType) { return graph().outAtLeastOneV(self(), edgeType); }
  //
  // default <
  //   E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.ToAtMostOne,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // Optional<T> outAtMostOneV(ET edgeType) { return graph().outAtMostOneV(self(), edgeType); }
  //
  // default <
  //   E  extends      TypedEdge<V,VT, E,ET, T,TT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<V,VT, E,ET, T,TT, ?,RV,RE>
  //            & TypedEdge.Type.ToOne,
  //   T  extends      TypedVertex<T,TT, ?,RV,RE>,
  //   TT extends TypedVertex.Type<T,TT, ?,RV,RE>
  // >
  // T outOneV(ET edgeType) { return graph().outOneV(self(), edgeType); }
  //
  //
  // /* #### inV */
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  // >
  // Stream<S> inV(ET edgeType) { return graph().inV(self(), edgeType); }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  //            & TypedEdge.Type.FromAtLeastOne
  // >
  // Stream<S> inAtLeastOneV(ET edgeType) { return graph().inAtLeastOneV(self(), edgeType); }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  //            & TypedEdge.Type.FromAtMostOne
  // >
  // Optional<S> inAtMostOneV(ET edgeType) { return graph().inAtMostOneV(self(), edgeType); }
  //
  // default <
  //   S  extends      TypedVertex<S,ST, ?,RV,RE>,
  //   ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  //   E  extends      TypedEdge<S,ST, E,ET, V,VT, ?,RV,RE>,
  //   ET extends TypedEdge.Type<S,ST, E,ET, V,VT, ?,RV,RE>
  //            & TypedEdge.Type.FromOne
  // >
  // S inOneV(ET edgeType) { return graph().inOneV(self(), edgeType); }

}
