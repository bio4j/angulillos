package com.bio4j.angulillos;

import static com.bio4j.angulillos.conversions.*;

import java.util.stream.Stream;
import java.util.Optional;


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

  public abstract class VertexType<
    VT extends VertexType<VT>
  > extends com.bio4j.angulillos.VertexType<VT,G,RV,RE> {

    @Override public final G graph() { return TypedGraph.this.self(); }
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

    protected EdgeType(ST sourceType, TT targetType) { super(sourceType, targetType); }

    @Override public final G graph() { return TypedGraph.this.self(); }
  }

}
