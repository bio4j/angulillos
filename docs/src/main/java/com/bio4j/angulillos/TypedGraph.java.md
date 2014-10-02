
```java
package com.bio4j.angulillos;

import static com.bio4j.angulillos.conversions.*;

import java.util.stream.Stream;
import java.util.Iterator;
```


## Typed graph

A `TypedGraph` is, unsurprisingly, the typed version of [UntypedGraph](UntypedGraph.java.md).


```java
public interface TypedGraph <
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
{

  I raw();

  default <
    V extends TypedVertex<V,VT,G,I,RV,RVT,RE,RET>, 
    VT extends TypedVertex.Type<V,VT,G,I,RV,RVT,RE,RET>
  >
  V addVertex(VT type) {

    return type.from (
      
      raw().addVertex( type.raw() )
    );
  }
```


   * adds an edge; note that this method does not set any properties. As it needs to be called by vertices in possibly different graphs, all the graph bounds are free with respect to G.
   * 


```java
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
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
```


Get the value of a property from an edge of G.


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
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>,
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  V getProperty(R edge, P property) {

    return raw().<V>getPropertyE(edge.raw(), property.name());
  }
```


Sets the value of a property for a vertex of G.


```java
  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  >
  void setProperty(N node, P property, V value) {

    raw().setPropertyV(node.raw(), property.name(), value);
  }
```


Sets the value of a property for an edge of G.


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
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>,
    P extends Property<R,RT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  >
  void setProperty(R edge, P property, V value) {

    raw().setPropertyE(edge.raw(), property.name(), value);
  }
```


### source and target 

gets the source of an edge of G, which could be of a different graph.


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

gets the out edges of a vertex N of G.


```java
  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // target node
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  Stream<R> out(N node, RT relType) {

    return raw().out(
      node.raw(), 
      relType.raw()
    )
    .map( e -> relType.from(e) );
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // target node
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  Stream<T> outV(N node, RT relType) {

    return raw().outV (
      node.raw(), 
      relType.raw()
    )
    .map( v -> relType.targetType().from(v) );
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG> & TypedEdge.Type.ToOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // target node
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  R outOne(N node, RT relType) {

    // we know it has one!
    // TODO Optional
    return relType.from(
      raw().out(
        node.raw(), 
        relType.raw()
      ).iterator().next()
    );
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG> & TypedEdge.Type.ToOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // target node
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  T outOneV(N node, RT relType) {

    return relType.targetType().from(
      raw().outV(
        node.raw(), 
        relType.raw()
      ).iterator().next()
    );

    // Iterator<RV> rawVertices = raw().outV(
    //   node.raw(), 
    //   relType.raw()
    // )
    // .iterator();

    // return relType.targetType().from(rawVertices.next());
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG> & TypedEdge.Type.ToMany,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // target node
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  Stream<R> outMany(N node, RT relType) {

    return raw().out(
      node.raw(), 
      relType.raw()
    )
    .map( e -> relType.from(e) );

    // Stream<R> rels = new LinkedStream<>();

    // Iterator<RE> rawTypedEdges = raw().out(
    //   node.raw(), 
    //   relType.raw()
    // )
    // .iterator();

    // while (rawTypedEdges.hasNext()) {

    //   rels.add(relType.from(rawTypedEdges.next()));
    // }

    // return rels;
  }

  default <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    //rel
    R extends TypedEdge<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,RG,I,RV,RVT,RE,RET, T,TT,TG> & TypedEdge.Type.ToMany,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // target node
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  Stream<T> outManyV(N node, RT relType) {

    return raw().outV(
      node.raw(), 
      relType.raw()
    )
    .map( v -> relType.targetType().from(v) );

      // Stream<T> nodes = new LinkedStream<>();

      // Iterator<RV> rawVertices = raw().outV(
      //   node.raw(), 
      //   relType.raw()
      // )
      // .iterator();

      // while (rawVertices.hasNext()) {

      //   nodes.add(relType.targetType().from(rawVertices.next()));
      // }

      // return nodes;
  }


  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

```


  #### in methods



```java
  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  Stream<R> in(RT relType, N node) {

    return raw().in(
      node.raw(), 
      relType.raw()
    )
    .map( e -> relType.from(e) );

    // Stream<R> rels = new LinkedStream<>();

    // Iterator<RE> rawTypedEdges = raw().in(
    //   node.raw(), 
    //   relType.raw()
    // )
    // .iterator();

    // while (rawTypedEdges.hasNext()) {

    //   rels.add(relType.from(rawTypedEdges.next()));
    // }

    // return rels;
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  Stream<S> inV(RT relType, N node) {

    return raw().inV(
      node.raw(), 
      relType.raw()
    )
    .map( v -> relType.sourceType().from(v) );


    // Stream<S> nodes = new LinkedStream<>();

    // Iterator<RV> rawVertices = raw().inV(
    //   node.raw(), 
    //   relType.raw()
    // )
    // .iterator();

    // while (rawVertices.hasNext()) {

    //   nodes.add(relType.sourceType().from(rawVertices.next()));
    // }

    // return nodes;
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G> & TypedEdge.Type.FromOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  R inOne(RT relType, N node) {

    return relType.from(
      raw().in(
        node.raw(), 
        relType.raw()
      )
      .iterator().next()
    );

    // Iterator<RE> rawTypedEdges = raw().in(
    //   node.raw(), 
    //   relType.raw()
    // )
    // .iterator();

    // // just the first one
    // return relType.from(rawTypedEdges.next());
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G> & TypedEdge.Type.FromOne,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  S inOneV(RT relType, N node) {

    return relType.sourceType().from(
      raw().inV(
        node.raw(), 
        relType.raw()
      )
      .iterator().next()
    );

    // Iterator<RV> rawVertices = raw().inV(
    //   node.raw(), 
    //   relType.raw()
    // )
    // .iterator();

    // // just the first one
    // return relType.sourceType().from(rawVertices.next());
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G> & TypedEdge.Type.FromMany,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  Stream<R> inMany(RT relType, N node) {

    return raw().in(
      node.raw(), 
      relType.raw()
    )
    .map( e -> relType.from(e) );    

    // Stream<R> rels = new LinkedStream<>();

    // Iterator<RE> rawTypedEdges = raw().in(
    //   node.raw(), 
    //   relType.raw()
    // )
    // .iterator();

    // while (rawTypedEdges.hasNext()) {

    //   rels.add( relType.from( rawTypedEdges.next() ) );
    // }

    // return rels;
  }

  default <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>,
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,SG, R,RT,RG,I,RV,RVT,RE,RET, N,NT,G> & TypedEdge.Type.FromMany,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    // tgt
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>,
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>
  > 
  Stream<S> inManyV(RT relType, N node) {

    return raw().inV(
      node.raw(), 
      relType.raw()
    )
    .map( v -> relType.sourceType().from(v) );

    // Stream<S> nodes = new LinkedStream<>();

    // Iterator<RV> rawVertices = raw().inV(
    //   node.raw(), 
    //   relType.raw()
    // )
    // .iterator();

    // while (rawVertices.hasNext()) {

    //   nodes.add(relType.sourceType().from(rawVertices.next()));
    // }

    // return nodes;
  }

}
```


------

### Index

+ src
  + test
    + java
      + com
        + bio4j
          + angulillos
            + go
              + [TitanGoGraph.java][test/java/com/bio4j/angulillos/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/bio4j/angulillos/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/bio4j/angulillos/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/bio4j/angulillos/go/TestTypeNames.java]
    + scala
  + main
    + java
      + com
        + bio4j
          + angulillos
            + [TypedGraph.java][main/java/com/bio4j/angulillos/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/bio4j/angulillos/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/bio4j/angulillos/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/bio4j/angulillos/TypedEdge.java]
            + [conversions.java][main/java/com/bio4j/angulillos/conversions.java]
            + [TypedElementIndex.java][main/java/com/bio4j/angulillos/TypedElementIndex.java]
            + [Property.java][main/java/com/bio4j/angulillos/Property.java]
            + [TypedVertexQuery.java][main/java/com/bio4j/angulillos/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/bio4j/angulillos/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/bio4j/angulillos/TypedEdgeIndex.java]
            + [TypedVertex.java][main/java/com/bio4j/angulillos/TypedVertex.java]

[test/java/com/bio4j/angulillos/go/TitanGoGraph.java]: ../../../../../test/java/com/bio4j/angulillos/go/TitanGoGraph.java.md
[test/java/com/bio4j/angulillos/go/GoGraph.java]: ../../../../../test/java/com/bio4j/angulillos/go/GoGraph.java.md
[test/java/com/bio4j/angulillos/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/bio4j/angulillos/go/TitanGoGraphImpl.java.md
[test/java/com/bio4j/angulillos/go/TestTypeNames.java]: ../../../../../test/java/com/bio4j/angulillos/go/TestTypeNames.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: TypedEdge.java.md
[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: Property.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: TypedElement.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: TypedVertex.java.md