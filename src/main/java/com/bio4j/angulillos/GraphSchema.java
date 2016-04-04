package com.bio4j.angulillos;

import java.util.function.*;


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

  // public abstract class ElementType<
  //   FT extends ElementType<FT, RF>,
  //   RF
  // > implements com.bio4j.angulillos.ElementType<FT, SG,RF> {
  //
  //   // public abstract FT self();
  //
  // }

  // private abstract class Element<
  //   F  extends Element<F,RF>,
  //   RF
  // > //extends ElementType<F,RF>
  //   implements com.bio4j.angulillos.TypedElement<F,SG,RF> {
  //
  //   @Override public final SG graph() { return GraphSchema.this.self(); }
  //
  //   private final RF raw;
  //   @Override public final RF raw()  { return this.raw; }
  //
  //   // NOTE: we cannot do the same to `self`, because `super()` constructor cannot refer to `this`
  //   protected Element(RF raw) {
  //     this.raw  = raw;
  //   }
  // }


  public abstract class VertexType<
    V extends Vertex<V>
  > implements com.bio4j.angulillos.VertexType<V, SG,RV,RE> {

    protected VertexType() {};
    // @Override public VertexType<V> self() { return this; }
  }

  public abstract class Vertex<
    V extends Vertex<V>
  > extends VertexType<V>
    implements com.bio4j.angulillos.TypedVertex<V, SG,RV,RE> {

    @Override public final SG graph() { return GraphSchema.this.self(); }

    private final RV raw;
    @Override public final RV raw()  { return this.raw; }

    protected Vertex(RV raw) { this.raw  = raw; }


    public <X> Property<X> property(String nameSuffix, Class<X> valueClass) {
      return new Property<X>(nameSuffix, valueClass);
    }

    public class Property<X> extends com.bio4j.angulillos.Property<V,X>
    implements Supplier<X>,
               Function<X,V> {

      private Property(String nameSuffix, Class<X> valueClass) {
        super(self(), nameSuffix, valueClass);
      }

      @Override public X get() { return self().get(this); }

      public V set(X value) { return self().set(this, value); }
      @Override public V apply(X value) { return set(value); }
    }
  }


  public abstract class EdgeType<
    ST extends Vertex<ST>,
    ET extends Edge<ST,ET,TT>,
    TT extends Vertex<TT>
  > implements com.bio4j.angulillos.EdgeType<ST,ET,TT, SG,RV,RE> {

    private final ST sourceType;
    private final TT targetType;

    @Override public final ST sourceType() { return this.sourceType; }
    @Override public final TT targetType() { return this.targetType; }

    protected EdgeType(ST sourceType, TT targetType) {
      this.sourceType = sourceType;
      this.targetType = targetType;
    }
  }

  public abstract class Edge<
    S extends Vertex<S>,
    E extends Edge<S,E,T>,
    T extends Vertex<T>
  > extends EdgeType<S,E,T>
    implements com.bio4j.angulillos.TypedEdge<S,E,T, SG,RV,RE> {

    private final RE raw;
    @Override public final RE raw()  { return this.raw; }

    protected Edge(S sourceType, RE raw, T targetType) {
      super(sourceType, targetType);
      this.raw = raw;
    }
  }

}
