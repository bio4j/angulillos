package com.bio4j.angulillos;

import java.util.Set;
import java.util.HashSet;


/*
  ## Typed graphs

  A `TypedGraph` is, unsurprisingly, the typed version of [UntypedGraph](UntypedGraph.java.md).
*/
abstract class TypedGraph <
  G extends TypedGraph<G,RV,RE>,
  RV,RE
> {

  protected abstract G self();

  private final UntypedGraph<RV,RE> raw;
  public  final UntypedGraph<RV,RE> raw() { return this.raw; }

  protected TypedGraph(UntypedGraph<RV,RE> raw) { this.raw = raw; }


  protected abstract class ElementType<
    FT extends ElementType<FT,RF>,
    RF
  > {
    public abstract FT self();

    /* This is a unique label */
    public final String _label = getClass().getCanonicalName();


    /* Defines a new property on this element type and adds it to the `properties` set */
    public final <X> Property<X> property(String nameSuffix, Class<X> valueClass) {
      return new Property<X>(nameSuffix, valueClass);
    }

    /* This inner class fixes the element type */
    public final class Property<X> {

      public final String _label;
      public final Class<X> valueClass;

      public  final FT elementType() { return ElementType.this.self(); }

      // NOTE: this constructor is private to enforce usage of the factory method `property`
      private Property(String nameSuffix, Class<X> valueClass) {
        this.valueClass  = valueClass;
        this._label = elementType()._label + "." + nameSuffix;

        // if (
        //   ElementType.this.properties.removeIf( (Property<?> p) ->
        //     p._label.equals( this._label )
        //   )
        // ) {
        //   throw new IllegalArgumentException(
        //     "Element type [" +
        //     ElementType.this._label +
        //     "] contains duplicate property: " +
        //     this._label
        //   );
        // } else {
        //   ElementType.this.properties.add(this);
        // }
      }
    }
  }


  /* This set will store all vertex types defined for this graph */
  private Set<VertexType<?>> vertexTypes = new HashSet<>();
  public final Set<VertexType<?>> vertexTypes() { return this.vertexTypes; }

  /* Defines a vertex of _this_ graph with fixed raw types */
  public abstract class VertexType<
    VT extends VertexType<VT>
  > extends ElementType<VT,RV> {
    // NOTE: this initializer block will be inherited and will add each vertex type to the set
    {
      if (
        TypedGraph.this.vertexTypes.removeIf( (VertexType<?> vt) ->
          vt._label.equals( self()._label )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate vertex type: " + self()._label);
      }
      TypedGraph.this.vertexTypes.add(self());
    }

    // @SuppressWarnings("unchecked")
    public final Vertex fromRaw(RV raw) { return new Vertex(raw); }

    public class Vertex implements TypedVertex<Vertex, VT, G,RV,RE> {
      private final RV raw;
      @Override public final RV raw() { return this.raw; }

      public Vertex(RV raw) { this.raw = raw; }

      @Override public Vertex self() { return this; }
      @Override public final VT type() { return VertexType.this.self(); }
      @Override public final G graph() { return TypedGraph.this.self(); }
    }
  }


  /* This set will store all edge types defined for this graph */
  private Set<EdgeType<?,?,?,?,?>> edgeTypes = new HashSet<>();
  public final Set<EdgeType<?,?,?,?,?>> edgeTypes() { return this.edgeTypes; }

  /* Defines an edge between two vertices of _this_ graph */
  protected abstract class EdgeType<
    ST extends TypedGraph<SG,RV,RE>.VertexType<ST>,
    SG extends TypedGraph<SG,RV,RE>,
    ET extends EdgeType<ST,SG, ET, TT,TG>,
    TT extends TypedGraph<TG,RV,RE>.VertexType<TT>,
    TG extends TypedGraph<TG,RV,RE>
  > extends ElementType<ET,RE>
    implements HasArity
  {
    // NOTE: this initializer block will be inherited and will add each edge type to the set
    {
      if (
        TypedGraph.this.edgeTypes.removeIf( (EdgeType<?,?,?,?,?> et) ->
          et._label.equals( self()._label )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate edge type: " + self()._label);
      }
      TypedGraph.this.edgeTypes.add(self());
    }

    private final ST sourceType;
    public  final ST sourceType() { return this.sourceType; }

    private final TT targetType;
    public  final TT targetType() { return this.targetType; }

    protected EdgeType(ST sourceType, TT targetType) {
      this.sourceType = sourceType;
      this.targetType = targetType;
    }

    public final Edge fromRaw(RE raw) { return new Edge(raw); }

    public class Edge implements TypedEdge<Edge, ST,SG, ET,G, TT,TG, RV,RE> {
      private final RE raw;
      @Override public final RE raw() { return this.raw; }

      public Edge self() { return this; }

      private Edge(RE raw) { this.raw = raw; }

      @Override public final ET type() { return EdgeType.this.self(); }
      @Override public final G graph() { return TypedGraph.this.self(); }
    }
  }


  // /* This set will store all vertex indexes defined for this graph */
  // private Set<VertexIndex<?,?,?>> vertexIndexes = new HashSet<>();
  // public final Set<VertexIndex<?,?,?>> vertexIndexes() { return this.vertexIndexes; }
  //
  // protected abstract class VertexIndex<
  //   VT extends G.VertexType<VT>,
  //   P  extends Property<VT,X>,
  //   X
  // > implements TypedIndex<VT, VertexType<VT>.Vertex, G,P,X,RV> {
  //   // NOTE: this initializer block will be inherited and will add each vertex index to the set
  //   {
  //     if (
  //       TypedGraph.this.vertexIndexes.removeIf( (VertexIndex<?,?,?> vi) ->
  //         vi._label().equals( this._label() )
  //       )
  //     ) {
  //       throw new IllegalArgumentException("The graph contains duplicate vertex index: " + this._label());
  //     }
  //     TypedGraph.this.vertexIndexes.add(this);
  //   }
  //
  //   @Override public final G graph() { return TypedGraph.this.self(); }
  // }
  //
  //
  // /* This set will store all edge indexes defined for this graph */
  // private Set<EdgeIndex<?,?,?,?,?>> edgeIndexes = new HashSet<>();
  // public final Set<EdgeIndex<?,?,?,?,?>> edgeIndexes() { return this.edgeIndexes; }
  //
  // protected abstract class EdgeIndex<
  //   ST extends G.VertexType<ST>,
  //   ET extends G.EdgeType<ST,ET,TT>,
  //   TT extends G.VertexType<TT>,
  //   P  extends Property<ET,X>,
  //   X
  // > implements TypedIndex<ET, EdgeType<ST,ET,TT>.Edge, G,P,X,RE> {
  //   // NOTE: this initializer block will be inherited and will add each edge index to the set
  //   {
  //     if (
  //       TypedGraph.this.edgeIndexes.removeIf( (EdgeIndex<?,?,?,?,?> ei) ->
  //         ei._label().equals( this._label() )
  //       )
  //     ) {
  //       throw new IllegalArgumentException("The graph contains duplicate edge index: " + this._label());
  //     }
  //     TypedGraph.this.edgeIndexes.add(this);
  //   }
  //
  //   @Override public final G graph() { return TypedGraph.this.self(); }
  // }

}
