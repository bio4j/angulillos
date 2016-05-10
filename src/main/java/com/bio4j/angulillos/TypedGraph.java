package com.bio4j.angulillos;

import java.util.Set;


/*
  ## Typed graphs

  A `TypedGraph` is, unsurprisingly, the typed version of [UntypedGraph](UntypedGraph.java.md).
*/
interface AnyTypedGraph {

  public Set<AnyVertexType> vertexTypes();
  public Set<AnyEdgeType>   edgeTypes();
}

public abstract class TypedGraph<
  G extends TypedGraph<G,RV,RE>,
  RV,RE
> implements AnyTypedGraph {

  public abstract G self();

  private final UntypedGraph<RV,RE> raw;
  public  final UntypedGraph<RV,RE> raw() { return this.raw; }

  protected TypedGraph(UntypedGraph<RV,RE> raw) { this.raw = raw; }

  /* This set will store all vertex types defined for this graph */
  private Set<AnyVertexType> vertexTypes = new java.util.HashSet<>();
  public final Set<AnyVertexType> vertexTypes() { return this.vertexTypes; }

  /* This set will store all edge types defined for this graph */
  private Set<AnyEdgeType> edgeTypes = new java.util.HashSet<>();
  public final Set<AnyEdgeType> edgeTypes() { return this.edgeTypes; }


  /* ### Abstract helper classes

     These inner classes are implementations of the corresponding Typed* interfaces.
     They bound raw vertex/edge types that the graph is parametrized by.
  */
  private abstract class Element<
    F  extends     Element<F,FT, RF>,
    FT extends ElementType<F,FT, RF>,
    RF
  > implements TypedElement<F,FT, G,RF> {

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
  > implements TypedElement.Type<F,FT, G,RF> {

    @Override public final G graph() { return TypedGraph.this.self(); }

    public abstract F fromRaw(RF raw);

    protected abstract FT self();

    /* This set stores all properties that are defined on this element type */
    private Set<AnyProperty> properties = new java.util.HashSet<>();
    public final Set<AnyProperty> properties() { return this.properties; }


    private abstract class Property<X>
    implements com.bio4j.angulillos.Property<FT,X> {
      // NOTE: this initializer block will be inherited and will add each vertex type to the set
      {
        if (
          ElementType.this.properties.removeIf( (AnyProperty p) ->
            p._label().equals( this._label() )
          )
        ) {
          throw new IllegalArgumentException(
            "Element type [" +
            ElementType.this._label() +
            "] contains duplicate property: " +
            this._label()
          );
        }
        ElementType.this.properties.add(this);
      }

      private final Class<X> valueClass;

      @Override public final FT elementType() { return self(); }
      @Override public final Class<X> valueClass() { return this.valueClass; }

      protected Property(Class<X> valueClass) {
        this.valueClass  = valueClass;
      }
    }

    public abstract class UniqueProperty<X>
    extends Property<X> implements Arity.FromAtMostOne {
      protected UniqueProperty(Class<X> valueClass) { super(valueClass); }
    }

    public abstract class NonUniqueProperty<X>
    extends Property<X> implements Arity.FromAny {
      protected NonUniqueProperty(Class<X> valueClass) { super(valueClass); }
    }
  }


  public abstract class Vertex<
    V extends Vertex<V>
  > extends Element<V, VertexType<V>, RV>
    implements TypedVertex<V, VertexType<V>, G,RV,RE> {

    protected Vertex(RV raw, VertexType<V> type) { super(raw, type); }
  }

  public abstract class VertexType<
    V extends Vertex<V>
  > extends ElementType<V, VertexType<V>, RV>
    implements TypedVertex.Type<V, VertexType<V>, G,RV,RE>
  {
    protected VertexType<V> self() { return this; }

    private Set<AnyEdgeType> inEdges = new java.util.HashSet<>();
    private Set<AnyEdgeType> outEdges = new java.util.HashSet<>();

    public final Set<AnyEdgeType> inEdges() { return this.inEdges; }
    public final Set<AnyEdgeType> outEdges() { return this.outEdges; }

    // NOTE: this initializer block will be inherited and will add each vertex type to the set
    {
      if (
        TypedGraph.this.vertexTypes.removeIf( (AnyVertexType vt) ->
          vt._label().equals( self()._label() )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate vertex type: " + self()._label());
      }
      TypedGraph.this.vertexTypes.add(self());
    }
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
      G,RV,RE
    > {

    protected Edge(RE raw, EdgeType<S,E,T> type) { super(raw, type); }
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
      G,RV,RE
  > {
    protected EdgeType<S,E,T> self() { return this; }

    private final VertexType<S> sourceType;
    private final VertexType<T> targetType;

    @Override public final VertexType<S> sourceType() { return this.sourceType; }
    @Override public final VertexType<T> targetType() { return this.targetType; }

    // NOTE: this initializer block will be inherited and will add each edge type to the set
    {
      if (
        TypedGraph.this.edgeTypes.removeIf( (AnyEdgeType et) ->
          et._label().equals( self()._label() )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate edge type: " + self()._label());
      }

      TypedGraph.this.edgeTypes.add(self());
    }

    protected EdgeType(VertexType<S> sourceType, VertexType<T> targetType) {
      this.sourceType = sourceType;
      this.targetType = targetType;

      sourceType.outEdges.add(self());
      targetType.inEdges.add(self());
    }
  }

}
