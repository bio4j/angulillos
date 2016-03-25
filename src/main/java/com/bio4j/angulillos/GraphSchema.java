package com.bio4j.angulillos;


public abstract class GraphSchema<
  SG extends GraphSchema<SG,RV,RE>,
  RV,RE
> implements TypedGraph<SG,RV,RE> {

  public abstract SG self();

  private final UntypedGraph<RV,RE> raw;
  @Override public final UntypedGraph<RV,RE> raw() { return this.raw; }

  protected GraphSchema(UntypedGraph<RV,RE> raw) { this.raw = raw; }

  /* ### Abstract helper classes

     These inner classes are implementations of the corresponding Typed* interfaces.
     They bound raw vertex/edge types that the graph is parametrized by.
  */

  private abstract class Element<
    F  extends           Element<F,FT, RF>,
    FT extends TypedElement.Type<F,FT, SG,RF>,
    RF
  > implements TypedElement<F,FT, SG,RF> {

    @Override public final SG graph() { return GraphSchema.this.self(); }

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

  public class Vertex<
    VT extends VertexType<VT>
  > extends Element<Vertex<VT>,VT, RV>
    implements TypedVertex<Vertex<VT>,VT, SG,RV,RE> {

    protected Vertex(RV raw, VT type) { super(raw, type); }
    @Override public Vertex<VT> self() { return this; }
  }

  public abstract class VertexType<
    VT extends VertexType<VT>
  > implements TypedVertex.Type<Vertex<VT>,VT, SG,RV,RE> {

    private final VT self;

    protected VertexType(VT self) { this.self = self; }

    @Override public Vertex<VT> fromRaw(RV raw) { return new Vertex<VT>(raw, this.self); }
  }


  public class Edge<
    ST extends VertexType<ST>,
    ET extends EdgeType<ST,ET,TT>,
    TT extends VertexType<TT>
  > extends Element<Edge<ST,ET,TT>,ET, RE>
    implements TypedEdge<
      Vertex<ST>,     ST,
      Edge<ST,ET,TT>, ET,
      Vertex<TT>,     TT,
      SG,RV,RE
    > {

    protected Edge(RE raw, ET type) { super(raw, type); }
    @Override public Edge<ST,ET,TT> self() { return this; }
  }

  public abstract class EdgeType<
    ST extends VertexType<ST>,
    ET extends EdgeType<ST,ET,TT>,
    TT extends VertexType<TT>
  > implements
    TypedEdge.Type<
      Vertex<ST>,     ST,
      Edge<ST,ET,TT>, ET,
      Vertex<TT>,     TT,
      SG,RV,RE
    >
  {
    private final ST sourceType;
    private final ET self;
    private final TT targetType;

    @Override public final ST sourceType() { return this.sourceType; }
    @Override public final TT targetType() { return this.targetType; }

    protected EdgeType(ST sourceType, ET self, TT targetType) {
      this.sourceType = sourceType;
      this.self = self;
      this.targetType = targetType;
    }

    @Override public Edge<ST,ET,TT> fromRaw(RE raw) { return new Edge<ST,ET,TT>(raw, this.self); }
  }

}
