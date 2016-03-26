package com.bio4j.angulillos;


public abstract class GraphSchema<
  SG extends GraphSchema<SG,RV,RE>,
  RV,RE
> implements TypedGraph<SG,RV,RE> {

  public abstract SG self();

  private final UntypedGraph<RV,RE> raw;
  @Override public final UntypedGraph<RV,RE> raw() { return this.raw; }

  protected GraphSchema(UntypedGraph<RV,RE> raw) { this.raw = raw; }

  public abstract class VertexType<
    VT extends VertexType<VT>
  > extends com.bio4j.angulillos.VertexType<VT,SG,RV,RE> {

    @Override public final SG graph() { return GraphSchema.this.self(); }
  }

  public abstract class EdgeType<
    ST extends com.bio4j.angulillos.VertexType<ST, ?,RV,RE>,
    ET extends EdgeType<ST,ET,TT>,
    TT extends com.bio4j.angulillos.VertexType<TT, ?,RV,RE>
  > extends com.bio4j.angulillos.EdgeType<ST,ET,TT, SG,RV,RE> {

    public EdgeType(ST sourceType, TT targetType) { super(sourceType, targetType); }

    @Override public final SG graph() { return GraphSchema.this.self(); }
  }

}
