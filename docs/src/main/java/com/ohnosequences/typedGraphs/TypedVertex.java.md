
```java
package com.ohnosequences.typedGraphs;

import java.util.List;

/**
*  
*  ## Typed vertices
*  
*  A typed node. The pattern is the same as for `TypedElement`: you need to define a TypedVertex and its type together.
* 
*  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>
**/
public interface TypedVertex <
  N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>, 
  NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
> 
  extends TypedElement<N,NT,G,I,RV,RVT,RE,RET>
{

  @Override
  RV raw();
```


  ### create relationships in/out of this node

  There are two methods for creating new relationships, into and out of this node respectively. Their implementation delegates to the `G` `addRel` method.


```java
  default <
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>, 
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  R addInEdge(S from, RT relType) {

    return graph().addEdge( from, relType, self() );
  }

  default <
    // rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // tgt
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  >
  R addOutEdge(RT relType, T to) {

    return graph().addEdge( self(), relType, to );
  }
```


  ### properties



```java
  @Override
  default <
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  V get(P property) {

    return graph().getProperty(self(), property);
  }

  @Override
  default <
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, 
    V
  > 
  void set(P property, V value) {

    graph().setProperty(self(), property, value);
  }
```



  ### getting incoming and outgoing relationships

  For when you don't know anything about the arity, we have unbounded in/out methods which return `List`s.


```java
  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<R> in(RT relType) {

    return relType.graph().in( relType, self() );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> out(RT relType) {

    return relType.graph().out( self(), relType );
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<S> inV(RT relType) {

    return relType.graph().inV( relType, self() );
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromOne<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  R inOne(RT relType) {

    return relType.graph().inOne( relType, self());
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromOne<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  S inOneV(RT relType) {

    return relType.graph().inOneV( relType, self());
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromMany<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<R> inMany(RT relType) {

    return relType.graph().inMany( relType, self());
  }

  default <
    // src
    S extends TypedVertex<S,ST,G,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,G,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>,
    RT extends 
      TypedEdge.Type<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G> &
      TypedEdge.Type.FromMany<S,ST,G, R,RT,G,I,RV,RVT,RE,RET, N,NT,G>
  > 
  List<S> inManyV(RT relType) {

    return relType.graph().inManyV( relType, self() );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<T> outV(RT relType) {

    G relGraph = relType.graph();
    // delegates to graph
    return relGraph.outV( self(), relType );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends TypedEdge.Type.ToMany<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  List<R> outMany(RT relType) {

    return relType.graph().out( self(), relType );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      TypedEdge.Type.ToMany<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  >
  List<T> outManyV(RT relType) {

    return relType.graph().outV( self(), relType );
  }

  default <
    //rel
    R extends TypedEdge<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>, 
    RT extends 
      TypedEdge.Type<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G> &
      TypedEdge.Type.ToOne<N,NT,G, R,RT,G,I,RV,RVT,RE,RET, T,TT,G>,
    // target node
    T extends TypedVertex<T,TT,G,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,G,I,RV,RVT,RE,RET>
  > 
  R outOne(RT relType) {

    return relType.graph().outOne( self(), relType );
  }



  interface Type <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>, 
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
    extends TypedElement.Type<N,NT,G,I,RV,RVT,RE,RET>
  {

    @Override
    RVT raw();

    N from(RV vertex);

    @Override 
    NT value();
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