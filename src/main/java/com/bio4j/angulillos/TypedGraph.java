package com.bio4j.angulillos;


/*
  ## Typed graphs

  A `TypedGraph` is, unsurprisingly, the typed version of [UntypedGraph](UntypedGraph.java.md).
*/
public abstract class TypedGraph <
  G extends TypedGraph<G,RV,RE>,
  RV,RE
> {

  public abstract G self();

  private final UntypedGraph<RV,RE> raw;
  public  final UntypedGraph<RV,RE> raw() { return this.raw; }

  protected TypedGraph(UntypedGraph<RV,RE> raw) { this.raw = raw; }


  /* Refined graph element classes */

  /* Defines an vertex of _this_ graph with fixed raw types */
  public abstract class VertexType<
    VT extends G.VertexType<VT>
  > extends com.bio4j.angulillos.VertexType<VT,G,RV,RE> {

    @Override public final G graph() { return TypedGraph.this.self(); }
  }

  /* Defines an edge between two vertices of _this_ graph */
  public abstract class EdgeType<
    ST extends G.VertexType<ST>,
    ET extends G.EdgeType<ST,ET,TT>,
    TT extends G.VertexType<TT>
  > extends com.bio4j.angulillos.EdgeType<ST,G, ET,G, TT,G, RV,RE> {

    protected EdgeType(ST sourceType, TT targetType) { super(sourceType, targetType); }

    @Override public final G graph() { return TypedGraph.this.self(); }
  }

}
