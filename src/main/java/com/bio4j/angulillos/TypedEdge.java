package com.bio4j.angulillos;

/*
  ## Edges

  A typed edge with explicit source and target.
*/
interface TypedEdge <
  E  extends TypedGraph<EG,RV,RE>.EdgeType<ST,SG, ET, TT,TG>.Edge,
  // source
  // S  extends TypedGraph<SG,RV,RE>.VertexType<ST>.Vertex,
  ST extends TypedGraph<SG,RV,RE>.VertexType<ST>,
  SG extends TypedGraph<SG,RV,RE>,
  // edge itself
  ET extends TypedGraph<EG,RV,RE>.EdgeType<ST,SG, ET, TT,TG>,
  EG extends TypedGraph<EG,RV,RE>,
  // target
  // T  extends TypedGraph<TG,RV,RE>.VertexType<TT>.Vertex,
  TT extends TypedGraph<TG,RV,RE>.VertexType<TT>,
  TG extends TypedGraph<TG,RV,RE>,
  // raws
  RV,RE
> extends TypedElement<E,ET,RE, EG,RV,RE> {

  // public abstract ST sourceType();
  // public abstract TT targetType();
  // // NOTE: this constructor is private to enforce the usage of the factory method fromRaw()
  // protected TypedEdge(RE raw) { super(raw); }

  // // NOTE: this call is typesafe, but the compiler cannot check it here, because the RV type in self() is not bound to be the same as we use from the enclousing class context
  // @SuppressWarnings("unchecked")
  // /* This method should be used for constructing _all_ instances of the Edge inner class to get the precise return type */
  // public final ET.Edge fromRaw(RE raw) { return self().new Edge(raw); }
  //
  // /* This method adds a new edge of this type to the graph */
  // public ET.Edge addEdge(
  //   VertexType<ST, SG,RV,RE>.Vertex source,
  //   VertexType<TT, TG,RV,RE>.Vertex target
  // ) {
  //   return fromRaw(
  //     graph().raw().addEdge( source.raw(), this._label, target.raw() )
  //   );
  // }



  // /* ### Properties */
  // @Override public
  // <X> X get(ET.Property<X> property) {
  //   return graph().raw().<X>getPropertyE(this.raw(), property._label);
  // }

  @Override default
  <X> E set(ET.Property<X> property, X value) {

    graph().raw().setPropertyE(this.raw(), property._label, value);
    return self();
  }


  /* the source vertex of this edge */
  default TypedGraph<SG,RV,RE>.VertexType<ST>.Vertex source() {
    return type().sourceType().fromRaw(
      graph().raw().source( this.raw() )
    );
  }

  // /* the target vertex of this edge */
  // public TT.Vertex target() {
  //   return targetType.fromRaw(
  //     graph().raw().target( this.raw() )
  //   );
  // }
  //

}
