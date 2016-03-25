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
  public class Property<
    FT extends ElementType<?,FT,?>,
    X
  > implements com.bio4j.angulillos.Property<FT,X> {

    private final FT elementType;
    private final String nameSuffix;
    private final Class<X> valueClass;

    @Override public final FT elementType() { return this.elementType; }
    @Override public final Class<X> valueClass() { return this.valueClass; }

    protected Property(FT elementType, String nameSuffix, Class<X> valueClass) {
      this.elementType = elementType;
      this.nameSuffix  = nameSuffix;
      this.valueClass  = valueClass;
    }

    // NOTE: if we don't override it, all properties will have the same name (FQN of this class)
    @Override public String _label() {
      return (elementType()._label() + "." + nameSuffix);
    }
  }


  private abstract class Element<
    F  extends     Element<F,FT, RF>,
    FT extends ElementType<F,FT, RF>,
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

  public abstract class ElementType<
    F  extends     Element<F,FT, RF>,
    FT extends ElementType<F,FT, RF>,
    RF
  > implements TypedElement.Type<F,FT, SG,RF> {

    public abstract FT self();

    public <X> Property<FT,X> property(String nameSuffix, Class<X> valueClass) {
      return new Property<FT,X>(self(), nameSuffix, valueClass);
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
  > extends ElementType<Vertex<VT>,VT, RV>
    implements TypedVertex.Type<Vertex<VT>,VT, SG,RV,RE> {

    @Override public Vertex<VT> fromRaw(RV raw) { return new Vertex<VT>(raw, self()); }
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
  > extends ElementType<Edge<ST,ET,TT>,ET, RE>
    implements TypedEdge.Type<
      Vertex<ST>,     ST,
      Edge<ST,ET,TT>, ET,
      Vertex<TT>,     TT,
      SG,RV,RE
    >
  {
    private final ST sourceType;
    private final TT targetType;

    @Override public final ST sourceType() { return this.sourceType; }
    @Override public final TT targetType() { return this.targetType; }

    protected EdgeType(ST sourceType, TT targetType) {
      this.sourceType = sourceType;
      this.targetType = targetType;
    }

    @Override public Edge<ST,ET,TT> fromRaw(RE raw) { return new Edge<ST,ET,TT>(raw, self()); }
  }

}
