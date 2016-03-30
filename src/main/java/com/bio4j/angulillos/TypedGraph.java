package com.bio4j.angulillos;

import java.util.Set;
import java.util.HashSet;


/*
  ## Typed graphs

  A `TypedGraph` is, unsurprisingly, the typed version of [UntypedGraph](UntypedGraph.java.md).
*/
public abstract class TypedGraph <
  G extends TypedGraph<G,RV,RE>,
  RV,RE
> {

  protected abstract G self();

  private final UntypedGraph<RV,RE> raw;
  public  final UntypedGraph<RV,RE> raw() { return this.raw; }

  protected TypedGraph(UntypedGraph<RV,RE> raw) { this.raw = raw; }


  /* This set will store all vertex types defined for this graph */
  private Set<G.VertexType<?>> vertexTypes = new HashSet<>();
  public final Set<G.VertexType<?>> vertexTypes() { return this.vertexTypes; }

  /* Defines a vertex of _this_ graph with fixed raw types */
  protected abstract class VertexType<
    VT extends G.VertexType<VT>
  > extends com.bio4j.angulillos.VertexType<VT,G,RV,RE> {
    // NOTE: this initializer block will be inherited and will add each vertex type to the set
    {
      if (
        TypedGraph.this.vertexTypes.removeIf( (G.VertexType<?> vt) ->
          vt._label.equals( self()._label )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate vertex type: " + self()._label);
      }
      TypedGraph.this.vertexTypes.add(self());
    }

    @Override public final G graph() { return TypedGraph.this.self(); }
  }


  /* This set will store all edge types defined for this graph */
  private Set<G.EdgeType<?,?,?>> edgeTypes = new HashSet<>();
  public final Set<G.EdgeType<?,?,?>> edgeTypes() { return this.edgeTypes; }

  /* Defines an edge between two vertices of _this_ graph */
  protected abstract class EdgeType<
    ST extends G.VertexType<ST>,
    ET extends G.EdgeType<ST,ET,TT>,
    TT extends G.VertexType<TT>
  > extends com.bio4j.angulillos.EdgeType<ST,G, ET,G, TT,G, RV,RE> {
    // NOTE: this initializer block will be inherited and will add each edge type to the set
    {
      if (
        TypedGraph.this.edgeTypes.removeIf( (G.EdgeType<?,?,?> et) ->
          et._label.equals( self()._label )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate edge type: " + self()._label);
      }
      TypedGraph.this.edgeTypes.add(self());
    }

    protected EdgeType(ST sourceType, TT targetType) { super(sourceType, targetType); }

    @Override public final G graph() { return TypedGraph.this.self(); }
  }


  /* This set will store all vertex indexes defined for this graph */
  private Set<VertexIndex<?,?,?>> vertexIndexes = new HashSet<>();
  public final Set<VertexIndex<?,?,?>> vertexIndexes() { return this.vertexIndexes; }

  protected abstract class VertexIndex<
    VT extends G.VertexType<VT>,
    P  extends Property<VT,X>,
    X
  > implements TypedIndex<VT, VertexType<VT>.Vertex, G,P,X,RV> {
    // NOTE: this initializer block will be inherited and will add each vertex index to the set
    {
      if (
        TypedGraph.this.vertexIndexes.removeIf( (VertexIndex<?,?,?> vi) ->
          vi._label().equals( this._label() )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate vertex index: " + this._label());
      }
      TypedGraph.this.vertexIndexes.add(this);
    }

    @Override public final G graph() { return TypedGraph.this.self(); }
  }


  /* This set will store all edge indexes defined for this graph */
  private Set<EdgeIndex<?,?,?,?,?>> edgeIndexes = new HashSet<>();
  public final Set<EdgeIndex<?,?,?,?,?>> edgeIndexes() { return this.edgeIndexes; }

  protected abstract class EdgeIndex<
    ST extends G.VertexType<ST>,
    ET extends G.EdgeType<ST,ET,TT>,
    TT extends G.VertexType<TT>,
    P  extends Property<ET,X>,
    X
  > implements TypedIndex<ET, EdgeType<ST,ET,TT>.Edge, G,P,X,RE> {
    // NOTE: this initializer block will be inherited and will add each edge index to the set
    {
      if (
        TypedGraph.this.edgeIndexes.removeIf( (EdgeIndex<?,?,?,?,?> ei) ->
          ei._label().equals( this._label() )
        )
      ) {
        throw new IllegalArgumentException("The graph contains duplicate edge index: " + this._label());
      }
      TypedGraph.this.edgeIndexes.add(this);
    }

    @Override public final G graph() { return TypedGraph.this.self(); }
  }
}
