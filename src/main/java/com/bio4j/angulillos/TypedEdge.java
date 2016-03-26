package com.bio4j.angulillos;

/*
  ## Edges

  A typed edge with explicit source and target.

  - `S` the source TypedVertex, `ST` the source TypedVertex type
  - `E` the edge, `ET` the edge type
  - `T` the target TypedVertex, `TT` the target TypedVertex type
*/
abstract class EdgeType <
  ST extends VertexType<ST, ?,RV,RE>,
  ET extends EdgeType<ST,ET,TT, G,RV,RE>,
  TT extends VertexType<TT, ?,RV,RE>,
  // graph & raws
  G extends TypedGraph<G,RV,RE>,
  RV,RE
> extends ElementType<ET,G,RE>
  implements HasArity
{

  public final ST sourceType;
  public final TT targetType;

  protected EdgeType(ST sourceType, TT targetType) {
    this.sourceType = sourceType;
    this.targetType = targetType;
  }

  @Override public Edge fromRaw(RE raw) { return this.new Edge(raw); }


  class Edge extends Element {

    public Edge(RE raw) { super(raw); }

    /* the source vertex of this edge */
    // public final <
    //   SG extends TypedGraph<SG,RV,RE>
    // > VertexType<ST, SG,RV,RE>.Vertex source() { return graph.source( this ); }

    // /* the target vertex of this edge */
    // public final T target() { return graph.target( self() ); }

    @Override public
    <X> X get(Property<ET,X> property) { return graph.getProperty(this, property); }

    @Override public
    <X> Edge set(Property<ET,X> property, X value) {

      graph().setProperty(this, property, value);
      return this;
    }

  }

}
