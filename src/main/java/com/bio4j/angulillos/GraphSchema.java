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

    public <X> Property<X> property(String nameSuffix, Class<X> valueClass) {
      return new Property<X>(nameSuffix, valueClass);
    }

    public class Property<X> extends com.bio4j.angulillos.Property<FT,X> {
      private Property(String nameSuffix, Class<X> valueClass) {
        super(self(), nameSuffix, valueClass);
      }
    }
  }


  public abstract class Vertex<
    V extends Vertex<V>
  > extends Element<V, VertexType<V>, RV>
    implements TypedVertex<V, VertexType<V>, SG,RV,RE> {

    protected Vertex(RV raw, VertexType<V> type) { super(raw, type); }

    // protected abstract class Type extends VertexType<V> {}
  }

  public abstract class VertexType<
    V extends Vertex<V>
  > extends ElementType<V, VertexType<V>, RV>
    implements TypedVertex.Type<V, VertexType<V>, SG,RV,RE> {

    @Override public VertexType<V> self() { return this; }
  }


  public abstract class Edge<
    S extends Vertex<S>,
    E extends Edge<S,E,T>,
    T extends Vertex<T>
  > extends Element<E, EdgeType<S,E,T>, RE>
    implements TypedEdge<
      S, VertexType<S>,
      E, EdgeType<S,E,T>,
      T, VertexType<T>,
      SG,RV,RE
    > {

    protected Edge(RE raw, EdgeType<S,E,T> type) { super(raw, type); }
    // @Override public E self() { return this; }
  }

  public abstract class EdgeType<
    S extends Vertex<S>,
    E extends Edge<S,E,T>,
    T extends Vertex<T>
  > extends ElementType<E, EdgeType<S,E,T>, RE>
    implements TypedEdge.Type<
      S, VertexType<S>,
      E, EdgeType<S,E,T>,
      T, VertexType<T>,
      SG,RV,RE
  > {

    private final VertexType<S> sourceType;
    private final VertexType<T> targetType;

    @Override public final VertexType<S> sourceType() { return this.sourceType; }
    @Override public final VertexType<T> targetType() { return this.targetType; }

    protected EdgeType(VertexType<S> sourceType, VertexType<T> targetType) {
      this.sourceType = sourceType;
      this.targetType = targetType;
    }

    // @Override public E fromRaw(RE raw) { return new Edge<ST,ET,TT>(raw, self()); }
  }

}
