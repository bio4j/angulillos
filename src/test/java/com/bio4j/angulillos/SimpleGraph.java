package com.bio4j.angulillos;

// import com.bio4j.angulillos.*;

public abstract class SimpleGraph<
  SG extends SimpleGraph<SG,RG, RV,RE>,
  RG extends UntypedGraph<RV,RE>,
  RV,RE
> implements
  TypedGraph<SG,RV,RE>
{

  abstract SG graphSelf();

  // protected RG rawGraph = null;
  // public SimpleGraph(RG graph) { rawGraph = graph; }
  // @Override public RG raw() { return rawGraph; }

  /* ### Abstract helper classes

     These classes bound the types to be from this graph
  */

  // public abstract class ElementType<
  //   E  extends     SG.Element<E, ET>,
  //   ET extends SG.ElementType<E, ET>
  // > implements
  //   TypedElement.Type<E,ET,SG, I, RV,RE>
  // {
  //   @Override public final SG graph() { return graphSelf(); }
  // }
  //
  // public abstract class Element<
  //   E extends SG.Element<E,ET>,
  //   ET extends SG.ElementType<E,ET>
  // >
  // implements
  //   TypedElement<E,ET,SG, I, RV,RE>
  // {
  //   @Override public final SG graph() { return graphSelf(); }
  // }

  public class Property<
    FT extends TypedElement.Type<?,FT, SG,?>,
    X
  > implements
    com.bio4j.angulillos.Property<FT,X>
  {
    private final FT elementType;
    private final Class<X> valueClass;

    @Override public final FT elementType() { return elementType; }
    @Override public final Class<X> valueClass() { return this.valueClass; }

    protected Property(FT elementType, Class<X> valueClass) {
      this.elementType = elementType;
      this.valueClass  = valueClass;
    }
  }

  public abstract class Vertex<
    V  extends     Vertex<V,VT>,
    VT extends VertexType<V,VT>
  > implements
    TypedVertex<V,VT, SG,RV,RE>
  {
    @Override public final SG graph() { return graphSelf(); }

    private final RV raw;
    private final VT type;

    @Override public final RV raw()  { return this.raw; }
    @Override public final VT type() { return this.type; }

    // NOTE: we cannot do the same to self, because `super()` constructor cannot refer to `this`
    protected Vertex(RV raw, VT type) {
      this.raw  = raw;
      this.type = type;
    }
  }

  public abstract class VertexType<
    V  extends     Vertex<V,VT>,
    VT extends VertexType<V,VT>
  > implements
    com.bio4j.angulillos.TypedVertex.Type<V,VT, SG,RV,RE> {}


  public abstract class Edge<
    S  extends     Vertex<S,ST>,
    ST extends VertexType<S,ST>,
    E  extends     Edge<S,ST, E,ET, T,TT>,
    ET extends EdgeType<S,ST, E,ET, T,TT>,
    T  extends     Vertex<T,TT>,
    TT extends VertexType<T,TT>
  > implements
    com.bio4j.angulillos.TypedEdge<S,ST, E,ET, T,TT, SG,RV,RE>
  {
    @Override public final SG graph() { return graphSelf(); }

    private final RE edge;
    private final ET type;

    protected Edge(RE edge, ET type) {
      this.edge = edge;
      this.type = type;
    }

    @Override public final RE raw() { return this.edge; }
    @Override public final ET type() { return type; }
  }

  public abstract class EdgeType<
    S  extends     Vertex<S,ST>,
    ST extends VertexType<S,ST>,
    E  extends     Edge<S,ST, E,ET, T,TT>,
    ET extends EdgeType<S,ST, E,ET, T,TT>,
    T  extends     Vertex<T,TT>,
    TT extends VertexType<T,TT>
  > implements
    com.bio4j.angulillos.TypedEdge.Type<S,ST, E,ET, T,TT, SG,RV,RE>
  {
    private final ST srcT;
    private final TT tgtT;

    protected EdgeType(ST srcT, TT tgtT) {
      this.srcT = srcT;
      this.tgtT = tgtT;
    }

    @Override public final ST sourceType() { return srcT; }
    @Override public final TT targetType() { return tgtT; }
  }

}
