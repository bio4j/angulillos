package com.bio4j.angulillos;

import java.util.Set;


/*
  ## Typed graphs

  A `TypedGraph` is, unsurprisingly, the typed version of [UntypedGraph](UntypedGraph.java.md).
*/
interface AnyTypedGraph {

  Set<AnyVertexType> vertexTypes();
  Set<AnyEdgeType>   edgeTypes();
  Set<TypedVertexIndex.Unique<?,?,?,?,?,?>>     uniqueVertexIndexes();
  Set<TypedVertexIndex.NonUnique<?,?,?,?,?,?>>  nonUniqueVertexIndexes();
  Set<TypedEdgeIndex.Unique<?,?,?,?,?,?>>       uniqueEdgeIndexes();
  Set<TypedEdgeIndex.NonUnique<?,?,?,?,?,?>>    nonUniqueEdgeIndexes();
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
  protected final Set<AnyVertexType> vertexTypes = new java.util.HashSet<>();
  public  final Set<AnyVertexType> vertexTypes() { return this.vertexTypes; }

  /* This set will store all edge types defined for this graph */
  protected final Set<AnyEdgeType> edgeTypes = new java.util.HashSet<>();
  public  final Set<AnyEdgeType> edgeTypes() { return this.edgeTypes; }

  protected final Set<TypedVertexIndex.Unique<?,?,?,?,?,?>> uniqueVertexIndexes = new java.util.HashSet<>();
  public  final Set<TypedVertexIndex.Unique<?,?,?,?,?,?>> uniqueVertexIndexes() { return this.uniqueVertexIndexes; }

  protected final Set<TypedVertexIndex.NonUnique<?,?,?,?,?,?>> nonUniqueVertexIndexes = new java.util.HashSet<>();
  public  final Set<TypedVertexIndex.NonUnique<?,?,?,?,?,?>> nonUniqueVertexIndexes() { return this.nonUniqueVertexIndexes; }

  protected final Set<TypedEdgeIndex.Unique<?,?,?,?,?,?>> uniqueEdgeIndexes = new java.util.HashSet<>();
  public  final Set<TypedEdgeIndex.Unique<?,?,?,?,?,?>> uniqueEdgeIndexes() { return this.uniqueEdgeIndexes; }

  protected final Set<TypedEdgeIndex.NonUnique<?,?,?,?,?,?>> nonUniqueEdgeIndexes = new java.util.HashSet<>();
  public  final Set<TypedEdgeIndex.NonUnique<?,?,?,?,?,?>> nonUniqueEdgeIndexes() { return this.nonUniqueEdgeIndexes; }

  /* ### Abstract helper classes

     These inner classes are implementations of the corresponding Typed* interfaces.
     They bound raw vertex/edge types that the graph is parametrized by.
  */
  public abstract class Element<
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
    private final Set<AnyProperty> properties = new java.util.HashSet<>();
    public  final Set<AnyProperty> properties() { return this.properties; }


    public abstract class Property<X>
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

    protected final Set<AnyEdgeType> inEdges = new java.util.HashSet<>();
    public  final Set<AnyEdgeType> inEdges() { return this.inEdges; }

    protected final Set<AnyEdgeType> outEdges = new java.util.HashSet<>();
    public  final Set<AnyEdgeType> outEdges() { return this.outEdges; }

    // NOTE: this initializer block will be inherited and will add each vertex type to the set
    {
      if (
        TypedGraph.this.vertexTypes.removeIf( (AnyVertexType vt) ->
          vt._label().equals( self()._label() )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate vertex type: " + self()._label());
      }
      TypedGraph.this.vertexTypes.add( self() );
    }

    public class UniqueIndex<
      P extends Property<X> & Arity.FromAtMostOne,
      X
    >
    implements TypedVertexIndex.Unique<V,VertexType<V>,P,X,RV,RE> {

      private final P property;
      public final P property() { return property; }

      protected UniqueIndex(P property) { this.property = property; }

      {
        if(
          TypedGraph.this.uniqueVertexIndexes.removeIf(
            vt -> vt._label().equals( _label() )
          )
        )
        {
          throw new IllegalArgumentException("The graph contains a duplicate index type: " + _label());
        }
        else {

          TypedGraph.this.uniqueVertexIndexes.add( this );
        }
      }
    }

    public class NonUniqueIndex<
      P extends Property<X>,
      X
    >
    implements TypedVertexIndex.NonUnique<V,VertexType<V>,P,X,RV,RE> {

      private final P property;
      public final P property() { return property; }

      protected NonUniqueIndex(P property) { this.property = property; }

      {
        if(
          TypedGraph.this.nonUniqueVertexIndexes.removeIf(
            vt -> vt._label().equals( _label() )
          )
        )
        {
          throw new IllegalArgumentException("The graph contains a duplicate index type: " + _label());
        }
        else {

          TypedGraph.this.nonUniqueVertexIndexes.add( this );
        }
      }
    }
  }

  public abstract class GenericEdge<
    SG extends TypedGraph<SG,RV,RE>,
    S  extends TypedGraph<SG,RV,RE>.Vertex<S>,
    E  extends GenericEdge<SG,S, E, TG,T>,
    TG extends TypedGraph<TG,RV,RE>,
    T  extends TypedGraph<TG,RV,RE>.Vertex<T>
  > extends Element<E, GenericEdgeType<SG,S, E, TG,T>, RE>
    implements TypedEdge<
      S, TypedGraph<SG,RV,RE>.VertexType<S>,
      E, GenericEdgeType<SG,S, E, TG,T>,
      T, TypedGraph<TG,RV,RE>.VertexType<T>,
      G,RV,RE
    > {

      protected GenericEdge(RE raw, GenericEdgeType<SG,S, E, TG,T> type) { super(raw, type); }
    }

  public abstract class GenericEdgeType<
    SG extends TypedGraph<SG,RV,RE>,
    S  extends TypedGraph<SG,RV,RE>.Vertex<S>,
    E  extends GenericEdge<SG,S, E, TG,T>,
    TG extends TypedGraph<TG,RV,RE>,
    T  extends TypedGraph<TG,RV,RE>.Vertex<T>
  > extends ElementType<E, GenericEdgeType<SG,S, E, TG,T>, RE>
    implements TypedEdge.Type<
      S, TypedGraph<SG,RV,RE>.VertexType<S>,
      E, GenericEdgeType<SG,S, E, TG,T>,
      T, TypedGraph<TG,RV,RE>.VertexType<T>,
      G,RV,RE
    > {

      protected GenericEdgeType<SG,S, E, TG,T> self() { return this; }

      private final TypedGraph<SG,RV,RE>.VertexType<S> sourceType;
      private final TypedGraph<TG,RV,RE>.VertexType<T> targetType;

      @Override public final TypedGraph<SG,RV,RE>.VertexType<S> sourceType() { return this.sourceType; }
      @Override public final TypedGraph<TG,RV,RE>.VertexType<T> targetType() { return this.targetType; }

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

      protected GenericEdgeType(
        TypedGraph<SG,RV,RE>.VertexType<S> sourceType,
        TypedGraph<TG,RV,RE>.VertexType<T> targetType
      ) {

        this.sourceType = sourceType;
        this.targetType = targetType;

        sourceType.outEdges.add( self() );
        targetType.inEdges.add( self() );
      }

      public class UniqueIndex<
        P extends Property<X> & Arity.FromAtMostOne,
        X
      >
      implements com.bio4j.angulillos.TypedEdgeIndex.Unique<E,GenericEdgeType<SG,S, E, TG,T>,P,X,RV,RE> {

        private final P property;
        public final P property() { return property; }

        protected UniqueIndex(P property) { this.property = property; }

        {
          if(
            TypedGraph.this.uniqueEdgeIndexes.removeIf(
              vt -> vt._label().equals( _label() )
            )
          )
          {
            throw new IllegalArgumentException("The graph contains a duplicate index type: " + _label());
          }
          else {

            TypedGraph.this.uniqueEdgeIndexes.add( this );
          }
        }
      }

      public class NonUniqueIndex<
        P extends Property<X>,
        X
      >
      implements com.bio4j.angulillos.TypedEdgeIndex.NonUnique<E,GenericEdgeType<SG,S, E, TG,T>,P,X,RV,RE> {

        private final P property;
        public final P property() { return property; }

        protected NonUniqueIndex(P property) { this.property = property; }

        {
          if(
            TypedGraph.this.nonUniqueEdgeIndexes.removeIf(
              vt -> vt._label().equals( _label() )
            )
          )
          {
            throw new IllegalArgumentException("The graph contains a duplicate index type: " + _label());
          }
          else {

            TypedGraph.this.nonUniqueEdgeIndexes.add( this );
          }
        }
      }
    }


  public abstract class Edge<
    S extends Vertex<S>,
    E extends Edge<S,E,T>,
    T extends Vertex<T>
    > extends GenericEdge<G,S, E, G,T> {

    protected Edge(RE raw, EdgeType<S,E,T> type) { super(raw, type); }
  }

  public abstract class EdgeType<
    S extends Vertex<S>,
    E extends Edge<S,E,T>,
    T extends Vertex<T>
  > extends GenericEdgeType<G,S, E, G,T> {

    protected EdgeType(VertexType<S> sourceType, VertexType<T> targetType) { super(sourceType, targetType); }
  }
}
