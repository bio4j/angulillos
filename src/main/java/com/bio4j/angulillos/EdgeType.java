package com.bio4j.angulillos;

/*
  ## Edges

  A typed edge with explicit source and target.
*/
public abstract class EdgeType <
  // source
  ST extends VertexType<ST, SG,RV,RE>,
  SG extends TypedGraph<SG,RV,RE>,
  // edge itself
  ET extends EdgeType<ST,SG, ET,EG, TT,TG, RV,RE>,
  EG extends TypedGraph<EG,RV,RE>,
  // target
  TT extends VertexType<TT, TG,RV,RE>,
  TG extends TypedGraph<TG,RV,RE>,
  // raws
  RV,RE
> extends ElementType<ET,EG,RE>
  implements HasArity
{

  public final ST sourceType;
  public final TT targetType;

  protected EdgeType(ST sourceType, TT targetType) {
    this.sourceType = sourceType;
    this.targetType = targetType;
  }

  // NOTE: this call is typesafe, but the compiler cannot check it here, because the RV type in self() is not bound to be the same as we use from the enclousing class context
  @SuppressWarnings("unchecked")
  /* This method should be used for constructing _all_ instances of the Edge inner class to get the precise return type */
  public final ET.Edge fromRaw(RE raw) { return self().new Edge(raw); }

  /* This method adds a new edge of this type to the graph */
  public ET.Edge addEdge(
    VertexType<ST, SG,RV,RE>.Vertex source,
    VertexType<TT, TG,RV,RE>.Vertex target
  ) {
    return fromRaw(
      graph().raw().addEdge( source.raw, this._label, target.raw )
    );
  }


  class Edge extends Element {

    private Edge(RE raw) { super(raw); }


    /* ### Properties */
    @Override public
    <X> X get(Property<ET,X> property) {
      return graph.raw().<X>getPropertyE(this.raw, property._label);
    }

    @Override public
    <X> Edge set(Property<ET,X> property, X value) {

      graph.raw().setPropertyE(this.raw, property._label, value);
      return this;
    }


    /* the source vertex of this edge */
    public ST.Vertex source() {
      return sourceType.fromRaw(
        graph.raw().source( this.raw )
      );
    }

    /* the target vertex of this edge */
    public TT.Vertex target() {
      return targetType.fromRaw(
        graph.raw().target( this.raw )
      );
    }

  }

}
