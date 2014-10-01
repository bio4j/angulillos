
```java
package com.bio4j.angulillos;

import java.util.List;
import java.util.Optional;
// TODO move to TypedElementIndex

```


## Indices

A vertex index indexes vertices of a given type through values of one of its properties. This just adds a bound on the indexed type to be a TypedVertex; see `TypedElementIndex`


```java
public interface TypedVertexIndex <
  N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>, 
  NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
  P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, V,
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
extends
  TypedElementIndex<N,NT,P,V,G,I,RV,RVT,RE,RET>
{

  G graph();
```

This interface declares that this index is over a property that uniquely classifies a vertex type for exact match queries; it adds the method `getTypedVertex` for that.

```java
  public interface Unique <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>, 
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, V,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
  extends 
    TypedVertexIndex<N,NT,P,V,G,I,RV,RVT,RE,RET>,
    TypedElementIndex.Unique<N,NT,P,V,G,I,RV,RVT,RE,RET>
  {
```

get a vertex by providing a value of the indexed property. The default implementation relies on `query`.

```java
    default Optional<N> getVertex(V byValue) { 

      return getElement(byValue);
    }
  }
```

This interface declares that this index is over a property that classifies lists of vertices for exact match queries; it adds the method `getTypedVertexs` for that.

```java
  public interface List <
    N extends TypedVertex<N,NT,G,I,RV,RVT,RE,RET>, 
    NT extends TypedVertex.Type<N,NT,G,I,RV,RVT,RE,RET>,
    P extends Property<N,NT,P,V,G,I,RV,RVT,RE,RET>, V,
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
  extends
    TypedVertexIndex<N,NT,P,V,G,I,RV,RVT,RE,RET>,
    TypedElementIndex.List<N,NT,P,V,G,I,RV,RVT,RE,RET>
  {
```


    get a list of vertices by providing a value of the property. The default 


```java
    default Optional<java.util.List<N>> getVertices(V byValue) {

      return getElements(byValue);
    }
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
          + angulillos
            + go
              + [TitanGoGraph.java][test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/ohnosequences/angulillos/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/ohnosequences/angulillos/go/TestTypeNames.java]
    + scala
  + main
    + java
      + com
        + ohnosequences
          + angulillos
            + [TypedGraph.java][main/java/com/ohnosequences/angulillos/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/ohnosequences/angulillos/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/ohnosequences/angulillos/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/ohnosequences/angulillos/TypedEdge.java]
            + [TypedElementIndex.java][main/java/com/ohnosequences/angulillos/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/angulillos/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/angulillos/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/angulillos/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/angulillos/TypedEdgeIndex.java]
            + titan
              + [TitanTypedEdgeIndex.java][main/java/com/ohnosequences/angulillos/titan/TitanTypedEdgeIndex.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/angulillos/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/angulillos/titan/TitanUntypedGraph.java]
            + [TypedVertex.java][main/java/com/ohnosequences/angulillos/TypedVertex.java]

[test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java]: ../../../../../test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/angulillos/go/GoGraph.java]: ../../../../../test/java/com/ohnosequences/angulillos/go/GoGraph.java.md
[test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/angulillos/go/TestTypeNames.java]: ../../../../../test/java/com/ohnosequences/angulillos/go/TestTypeNames.java.md
[main/java/com/ohnosequences/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/ohnosequences/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedEdge.java]: TypedEdge.java.md
[main/java/com/ohnosequences/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/ohnosequences/angulillos/Property.java]: Property.java.md
[main/java/com/ohnosequences/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/ohnosequences/angulillos/TypedElement.java]: TypedElement.java.md
[main/java/com/ohnosequences/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanTypedEdgeIndex.java]: titan/TitanTypedEdgeIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanTypedVertexIndex.java]: titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanUntypedGraph.java]: titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedVertex.java]: TypedVertex.java.md