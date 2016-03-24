package com.bio4j.angulillos;


public abstract class SimpleGraph<
  SG extends SimpleGraph<SG,RV,RE>,
  RV,RE
> implements TypedGraph<SG,RV,RE> {

  public abstract SG self();

  private final UntypedGraph<RV,RE> raw;
  @Override public final UntypedGraph<RV,RE> raw() { return this.raw; }

  protected SimpleGraph(UntypedGraph<RV,RE> raw) { this.raw = raw; }

  /* ### Abstract helper classes */

  private abstract class Element<
    F  extends           Element<F,FT, RF>,
    FT extends TypedElement.Type<F,FT, SG,RF>,
    RF
  > implements TypedElement<F,FT, SG,RF> {

    @Override public final SG graph() { return SimpleGraph.this.self(); }

    private final RF raw;
    private final FT type;

    @Override public final RF raw()  { return this.raw; }
    @Override public final FT type() { return this.type; }

    // NOTE: we cannot do the same to `self`, because `super()` constructor cannot refer to `this`
    protected Element(RF raw, FT type) {
      this.raw  = raw;
      this.type = type;
    }
  }

  // NOTE: there is no point in a class for TypedElement.Type, because there is nothing that we can implement


  public class Property<
    FT extends TypedElement.Type<?,FT, SG,?>,
    X
  > implements com.bio4j.angulillos.Property<FT,X> {

    private final FT elementType;
    private final Class<X> valueClass;

    @Override public final FT elementType() { return this.elementType; }
    @Override public final Class<X> valueClass() { return this.valueClass; }

    protected Property(FT elementType, Class<X> valueClass) {
      this.elementType = elementType;
      this.valueClass  = valueClass;
    }
  }

  public abstract class Vertex<
    V  extends     Vertex<V,VT>,
    VT extends VertexType<V,VT>
  > extends Element<V,VT, RV>
    implements TypedVertex<V,VT, SG,RV,RE> {

    protected Vertex(RV raw, VT type) { super(raw, type); }
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
  > extends Element<E,ET, RE>
    implements TypedEdge<S,ST, E,ET, T,TT, SG,RV,RE> {

    protected Edge(RE raw, ET type) { super(raw, type); }
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
