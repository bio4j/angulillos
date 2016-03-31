package com.bio4j.angulillos;

/* This is a graph where all edges are internal */
public abstract class SimpleGraph <
  G extends SimpleGraph<G,RV,RE>,
  RV,RE
> extends TypedGraph<G,RV,RE> {

  protected SimpleGraph(UntypedGraph<RV,RE> raw) { super(raw); }


  protected abstract class EdgeType<
    ST extends VertexType<ST>,
    ET extends EdgeType<ST,ET,TT>,
    TT extends VertexType<TT>
  > extends TypedGraph<G,RV,RE>.EdgeType<ST,G, ET, TT,G> {

    protected EdgeType(ST sourceType, TT targetType) { super(sourceType, targetType); }
  }
}
