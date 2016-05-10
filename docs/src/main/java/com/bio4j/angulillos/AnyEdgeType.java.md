
```java
package com.bio4j.angulillos;
```


## Edges

A typed edge with explicit source and target.

- `S` the source TypedVertex, `ST` the source TypedVertex type
- `E` the edge, `ET` the edge type
- `T` the target TypedVertex, `TT` the target TypedVertex type


```java
public interface AnyEdgeType extends
  AnyElementType,
  HasFromArity,
  HasToArity
{

  AnyVertexType sourceType();
  AnyVertexType targetType();
}

// TODO: make it public
interface TypedEdge <
  // source vertex
  S  extends      TypedVertex<S,ST, ?,RV,RE>,
  ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
  // edge
  E  extends      TypedEdge<S,ST, E,ET, T,TT, G,RV,RE>,
  ET extends TypedEdge.Type<S,ST, E,ET, T,TT, G,RV,RE>,
  // target vertex
  T  extends      TypedVertex<T,TT, ?,RV,RE>,
  TT extends TypedVertex.Type<T,TT, ?,RV,RE>,
  // graph & raws
  G extends TypedGraph<G,RV,RE>,
  RV,RE
>
  extends TypedElement<E,ET,G,RE>
{

  interface Type <
    // source vertex
    S  extends      TypedVertex<S,ST, ?,RV,RE>,
    ST extends TypedVertex.Type<S,ST, ?,RV,RE>,
    // edge
    E  extends      TypedEdge<S,ST, E,ET, T,TT, G,RV,RE>,
    ET extends TypedEdge.Type<S,ST, E,ET, T,TT, G,RV,RE>,
    // target vertex
    T  extends      TypedVertex<T,TT, ?,RV,RE>,
    TT extends TypedVertex.Type<T,TT, ?,RV,RE>,
    // graph & raws
    G extends TypedGraph<G,RV,RE>,
    RV,RE
  > extends
    TypedElement.Type<E,ET,G,RE>,
    AnyEdgeType
  {
    ST sourceType();
    TT targetType();
```

adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G.

```java
    default E addEdge(S from, T to) {

      return this.fromRaw(
        graph().raw().addEdge( from.raw(), this, to.raw() )
      );
    }

  }
```

the source vertex of this edge

```java
  default S source() {
    return type().sourceType().fromRaw(
      graph().raw().source( raw() )
    );
  }
```

the target vertex of this edge

```java
  default T target() {
    return type().targetType().fromRaw(
      graph().raw().target( raw() )
    );
  }


  @Override default
  <X> X get(Property<ET,X> property) {
    return graph().raw().<X>getPropertyE(raw(), property);
  }

  @Override default
  <X> E set(Property<ET,X> property, X value) {

    graph().raw().setPropertyE(raw(), property, value);
    return self();
  }

}

```




[main/java/com/bio4j/angulillos/AnyEdgeType.java]: AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/AnyElementType.java]: AnyElementType.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: AnyVertexType.java.md
[main/java/com/bio4j/angulillos/Arity.java]: Arity.java.md
[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/Labeled.java]: Labeled.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/bio4j/angulillos/UntypedGraphSchema.java]: UntypedGraphSchema.java.md
[test/java/com/bio4j/angulillos/Twitter.java]: ../../../../../test/java/com/bio4j/angulillos/Twitter.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md