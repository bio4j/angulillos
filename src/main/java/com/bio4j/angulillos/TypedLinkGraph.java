package com.bio4j.angulillos;

import com.bio4j.angulillos.*;
import java.util.Set;

public abstract class TypedLinkGraph<
  G extends TypedLinkGraph<G,RV,RE>,
  RV,RE
>
extends TypedGraph<G,RV,RE> {

  protected TypedLinkGraph(UntypedGraph<RV,RE> raw) { super(raw); }

  public abstract class LinkEdge<
    SG extends TypedGraph<SG,RV,RE>,
    S  extends TypedGraph<SG,RV,RE>.Vertex<S>,
    E  extends LinkEdge<SG,S, E, TG,T>,
    TG extends TypedGraph<TG,RV,RE>,
    T  extends TypedGraph<TG,RV,RE>.Vertex<T>
  > extends Element<E, LinkEdgeType<SG,S, E, TG,T>, RE>
    implements TypedEdge<
      S, TypedGraph<SG,RV,RE>.VertexType<S>,
      E, LinkEdgeType<SG,S, E, TG,T>,
      T, TypedGraph<TG,RV,RE>.VertexType<T>,
      G,RV,RE
    > {
      protected LinkEdge(RE raw, LinkEdgeType<SG,S, E, TG,T> type) { super(raw, type); }
    }

  public abstract class LinkEdgeType<
    SG extends TypedGraph<SG,RV,RE>,
    S  extends TypedGraph<SG,RV,RE>.Vertex<S>,
    E  extends LinkEdge<SG,S, E, TG,T>,
    TG extends TypedGraph<TG,RV,RE>,
    T  extends TypedGraph<TG,RV,RE>.Vertex<T>
  > extends ElementType<E, LinkEdgeType<SG,S, E, TG,T>, RE>
    implements TypedEdge.Type<
      S, TypedGraph<SG,RV,RE>.VertexType<S>,
      E, LinkEdgeType<SG,S, E, TG,T>,
      T, TypedGraph<TG,RV,RE>.VertexType<T>,
      G,RV,RE
    > {

      protected LinkEdgeType<SG,S, E, TG,T> self() { return this; }

      private final TypedGraph<SG,RV,RE>.VertexType<S> sourceType;
      private final TypedGraph<TG,RV,RE>.VertexType<T> targetType;

      @Override public final TypedGraph<SG,RV,RE>.VertexType<S> sourceType() { return this.sourceType; }
      @Override public final TypedGraph<TG,RV,RE>.VertexType<T> targetType() { return this.targetType; }

      // NOTE: this initializer block will be inherited and will add each edge type to the set
      {
        if (
          TypedLinkGraph.this.edgeTypes.removeIf( (AnyEdgeType et) ->
            et._label().equals( self()._label() )
          )
        ) {
          throw new IllegalArgumentException("The graph contains duplicate edge type: " + self()._label());
        }

        TypedLinkGraph.this.edgeTypes.add(self());
      }

      protected LinkEdgeType(
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
      implements com.bio4j.angulillos.TypedEdgeIndex.Unique<E,LinkEdgeType<SG,S, E, TG,T>,P,X,RV,RE> {

        private final P property;
        public final P property() { return property; }

        protected UniqueIndex(P property) { this.property = property; }

        {
          if(
            TypedLinkGraph.this.uniqueEdgeIndexes.removeIf(
              vt -> vt._label().equals( _label() )
            )
          )
          {
            throw new IllegalArgumentException("The graph contains a duplicate index type: " + _label());
          }
          else {

            TypedLinkGraph.this.uniqueEdgeIndexes.add( this );
          }
        }
      }

      public class NonUniqueIndex<
        P extends Property<X>,
        X
      >
      implements com.bio4j.angulillos.TypedEdgeIndex.NonUnique<E,LinkEdgeType<SG,S, E, TG,T>,P,X,RV,RE> {

        private final P property;
        public final P property() { return property; }

        protected NonUniqueIndex(P property) { this.property = property; }

        {
          if(
            TypedLinkGraph.this.nonUniqueEdgeIndexes.removeIf(
              vt -> vt._label().equals( _label() )
            )
          )
          {
            throw new IllegalArgumentException("The graph contains a duplicate index type: " + _label());
          }
          else {

            TypedLinkGraph.this.nonUniqueEdgeIndexes.add( this );
          }
        }
      }
    }
}
