
```java
package com.ohnosequences.typedGraphs;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
```


A `TypedGraph` defines a set of types (nodes, relationships, properties) comprising what you could call a _schema_ for a typed graph. It uses an `UntypedGraph` for storing them.


```java
public interface TypedGraph <
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
{

  I raw();
```


   * adds a rel with source this node; note that this method does not set any
   * properties.


```java
  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // tgt
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  R addEdge(S from, RT relType, T to) {
      
    return relType.from(
      raw().addEdge(
        from.raw(),
        relType.raw(),
        to.raw()
      )
    );
  }
```


  ### properties

  These methods are used for setting and getting properties on vertices and edges.



```java
  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  V getProperty(N node, P property) {

    return raw().<V>getPropertyV(node.raw(), property.name());
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RVT,RE,RET,T,TT,TG>, 
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>,
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  V getProperty(R edge, P property) {

    return raw().<V>getPropertyE(edge.raw(), property.name());
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  >
  void setProperty(N node, P property, V value) {

    raw().setPropertyV(node.raw(), property.name(), value);
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RVT,RE,RET,T,TT,TG>, 
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>,
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  >
  void setProperty(R edge, P property, V value) {

    raw().setPropertyE(edge.raw(), property.name(), value);
  }
```


  ### source and target
  


```java
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RVT,RE,RET,T,TT,TG>, 
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  S source(R edge) {

    return edge.type().sourceType().from (
      raw().source(edge.raw())
    );
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,G,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,G,I,RV,RVT,RE,RET,T,TT,TG>, 
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  >
  T target(R edge) {

    return edge.type().targetType().from (
      raw().target(edge.raw())
    );
  }
```


  ### incident edges from vertices




```java
  ///////////////////////////////////////////////////////////////////////////////////////////////////////

```


  #### out methods



```java
  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> out(N node, RT relType) {

    List<R> rels = new LinkedList<>();

    Iterator<RE> rawTypedEdges = raw().out(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawTypedEdges.hasNext()) {

      rels.add(relType.from(rawTypedEdges.next()));
    }

    return rels;
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outV(N node, RT relType) {

    List<T> nodes = new LinkedList<>();

    Iterator<RV> rawVertices = raw().outV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawVertices.hasNext()) {

      nodes.add(relType.targetType().from(rawVertices.next()));
    }

    return nodes;
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      TypedEdge.Type.ToOne<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  R outOne(N node, RT relType) {

    Iterator<RE> rawTypedEdges = raw().out(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    return relType.from(rawTypedEdges.next());
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      TypedEdge.Type.ToOne<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  T outOneV(N node, RT relType) {

    Iterator<RV> rawVertices = raw().outV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    return relType.targetType().from(rawVertices.next());
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      TypedEdge.Type.ToMany<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> outMany(N node, RT relType) {

    List<R> rels = new LinkedList<>();

    Iterator<RE> rawTypedEdges = raw().out(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawTypedEdges.hasNext()) {

      rels.add(relType.from(rawTypedEdges.next()));
    }

    return rels;
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    RT extends 
      TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      TypedEdge.Type.ToMany<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outManyV(N node, RT relType) {

    List<T> nodes = new LinkedList<>();

    Iterator<RV> rawVertices = raw().outV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawVertices.hasNext()) {

      nodes.add(relType.targetType().from(rawVertices.next()));
    }

    return nodes;
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

```


  #### in methods



```java
  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  List<R> in(RT relType, N node) {

    List<R> rels = new LinkedList<>();

    Iterator<RE> rawTypedEdges = raw().in(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawTypedEdges.hasNext()) {

      rels.add(relType.from(rawTypedEdges.next()));
    }

    return rels;
  }

  default <
     // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  List<S> inV(RT relType, N node) {

    List<S> nodes = new LinkedList<>();

    Iterator<RV> rawVertices = raw().inV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawVertices.hasNext()) {

      nodes.add(relType.sourceType().from(rawVertices.next()));
    }

    return nodes;
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromOne<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  R inOne(RT relType, N node) {

    Iterator<RE> rawTypedEdges = raw().in(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    // just the first one
    return relType.from(rawTypedEdges.next());
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type.FromOne<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  S inOneV(RT relType, N node) {

    Iterator<RV> rawVertices = raw().inV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    // just the first one
    return relType.sourceType().from(rawVertices.next());
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromMany<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  List<R> inMany(RT relType, N node) {

    List<R> rels = new LinkedList<>();

    Iterator<RE> rawTypedEdges = raw().in(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawTypedEdges.hasNext()) {

      rels.add( relType.from( rawTypedEdges.next() ) );
    }

    return rels;
  }

  default <
     // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromMany<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  List<S> inManyV(RT relType, N node) {

    List<S> nodes = new LinkedList<>();

    Iterator<RV> rawVertices = raw().inV(
      node.raw(), 
      relType.raw()
    )
    .iterator();

    while (rawVertices.hasNext()) {

      nodes.add(relType.sourceType().from(rawVertices.next()));
    }

    return nodes;
  }

}
```


------

### Index

+ src
  + test
    + java
      + com
        + ohnosequences
          + typedGraphs
            + go
              + [TitanGoGraph.java][test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]
    + scala
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [TypedGraph.java][main/java/com/ohnosequences/typedGraphs/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/ohnosequences/typedGraphs/TypedEdge.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [TypedElementIndex.java][main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/typedGraphs/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]
            + titan
              + [TitanTypedVertex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertex.java]
              + [TitanTypedEdge.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdge.java]
              + [TitanTypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]
              + [TitanTypedElement.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedElement.java]
              + [TitanRelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]
              + [TitanProperty.java][main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]
            + [TypedVertex.java][main/java/com/ohnosequences/typedGraphs/TypedVertex.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdge.java]: TypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElement.java]: TypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertex.java]: titan/TitanTypedVertex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdge.java]: titan/TitanTypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: titan/TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]: titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedElement.java]: titan/TitanTypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: titan/TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: titan/TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertex.java]: TypedVertex.java.md