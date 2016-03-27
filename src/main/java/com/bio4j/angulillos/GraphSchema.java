package com.bio4j.angulillos;


public abstract class GraphSchema<
  G extends GraphSchema<G,RV,RE>,
  RV,RE
> implements TypedGraph<G,RV,RE> {

  public abstract G self();

  private final UntypedGraph<RV,RE> raw;
  @Override public final UntypedGraph<RV,RE> raw() { return this.raw; }

  protected GraphSchema(UntypedGraph<RV,RE> raw) { this.raw = raw; }

  public abstract class VertexType<
    VT extends VertexType<VT>
  > extends com.bio4j.angulillos.VertexType<VT,G,RV,RE> {

    @Override public final G graph() { return GraphSchema.this.self(); }
  }

  public abstract class EdgeType<
    // source
    ST extends com.bio4j.angulillos.VertexType<ST, SG,RV,RE>,
    SG extends TypedGraph<SG,RV,RE>,
    // edge itself
    ET extends EdgeType<ST,SG, ET, TT,TG>,
    // target
    TT extends com.bio4j.angulillos.VertexType<TT, TG,RV,RE>,
    TG extends TypedGraph<TG,RV,RE>
  > extends com.bio4j.angulillos.EdgeType<ST,SG, ET,G, TT,TG, RV,RE> {

    public EdgeType(ST sourceType, TT targetType) { super(sourceType, targetType); }

    @Override public final G graph() { return GraphSchema.this.self(); }
  }

}
