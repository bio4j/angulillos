
```java
package com.ohnosequences.typedGraphs;

import java.util.Optional;

public interface TypedEdgeIndex <
  // src
  S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
  ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
  SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
  // rel
  R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
  RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
  // property
  P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>,
  V,
  RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
  // tgt
  T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
  TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
  TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
>
extends
  TypedElementIndex<R,RT,P,V,RG,I,RV,RVT,RE,RET>
{

  RG graph();

  public interface Unique <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    // property
    P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>,
    V,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  extends 
    TypedEdgeIndex<S,ST,SG, R,RT, P,V, RG,I,RV,RVT,RE,RET, T,TT,TG>,
    TypedElementIndex.Unique<R,RT, P,V, RG,I,RV,RVT,RE,RET>
  {
```

get a node by providing a value of the indexed property.

```java
    default Optional<R> getEdge(V byValue) { return getElement(byValue); }
  }

  public interface List <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    // property
    P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>,
    V,
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  extends 
    TypedEdgeIndex<S,ST,SG, R,RT,P,V, RG,I,RV,RVT,RE,RET, T,TT,TG>,
    TypedElementIndex.List<R,RT, P,V, RG,I,RV,RVT,RE,RET>
  {
```

get a list of nodes by providing a value of the indexed property.

```java
    default Optional<java.util.List<R>> getEdges(V byValue) { return getElements(byValue); }
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
            + [TypedElementIndex.java][main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/typedGraphs/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]
            + titan
              + [TitanTypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]
            + [TypedVertex.java][main/java/com/ohnosequences/typedGraphs/TypedVertex.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdge.java]: TypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElement.java]: TypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java]: titan/TitanTypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]: titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertex.java]: TypedVertex.java.md