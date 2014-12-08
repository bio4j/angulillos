
```java
package com.bio4j.angulillos;

import java.util.stream.Stream;
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
    default Stream<N> getVertices(V byValue) {

      return getElements(byValue);
    }
  }

}

```


------

### Index

+ src
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