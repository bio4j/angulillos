package com.bio4j.angulillos;

public abstract class TypedLinkGraph <
  SG extends TypedGraph<SG,RV,RE>,
  G  extends TypedLinkGraph<SG,G,TG, RV,RE>,
  TG extends TypedGraph<TG,RV,RE>,
  RV,RE
> extends TypedGraph<G,RV,RE> {

  // NOTE: this raw has to be the same as the SG.raw and TG.raw
  protected TypedLinkGraph(UntypedGraph<RV,RE> raw) { super(raw); }


  /* Refined graph element classes */

  public abstract class LinkEdgeType<
    ST extends com.bio4j.angulillos.VertexType<ST, SG,RV,RE>,
    ET extends G.LinkEdgeType<ST,ET,TT>,
    TT extends com.bio4j.angulillos.VertexType<TT, TG,RV,RE>
  > extends com.bio4j.angulillos.EdgeType<ST,SG, ET,G, TT,TG, RV,RE> {

    protected LinkEdgeType(ST sourceType, TT targetType) { super(sourceType, targetType); }

    @Override public final G graph() { return TypedLinkGraph.this.self(); }
  }

}
