package com.bio4j.angulillos;

/*
  ## Edges

  A typed edge with explicit source and target.

  - `S` the source TypedVertex, `ST` the source TypedVertex type
  - `E` the edge, `ET` the edge type
  - `T` the target TypedVertex, `TT` the target TypedVertex type
*/
abstract class EdgeType <
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

  // @Override
  // @SuppressWarnings("unchecked")
  // public final ET.Edge fromRaw(RE raw) { return self().new Edge(raw); }


  class Edge extends Element {

    public Edge(RE raw) { super(raw); }

    /* the source vertex of this edge */
    public final
    ST.Vertex source() { return graph.source( this ); }

    // /* the target vertex of this edge */
    public final
    TT.Vertex target() { return graph.target( this ); }

    @Override public
    <X> X get(Property<ET,X> property) { return graph.getProperty(this, property); }

    @Override public
    <X> Edge set(Property<ET,X> property, X value) {

      graph().setProperty(this, property, value);
      return this;
    }

  }

}
