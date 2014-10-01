
```java
package com.bio4j.angulillos;

import java.util.List;
import java.util.Optional;

public interface TypedElementIndex <
  // element
  E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
  ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
  // property
  P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
  // graph
  G extends TypedGraph<G,I,RV,RVT,RE,RET>,
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
>
{
```

query this index using a Blueprints predicate

```java
  Optional<java.util.List<E>> query(com.tinkerpop.blueprints.Compare predicate, V value);
```

This interface declares that this index is over a property that uniquely classifies a element type for exact match queries; it adds the method `getTypedElement` for that.

```java
  public interface Unique <
    // element
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    // property
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
    // graph
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
    extends TypedElementIndex<E,ET, P,V, G, I,RV,RVT,RE,RET> 
  {
```

get a element by providing a value of the indexed property. The default implementation relies on `query`.

```java
    default Optional<E> getElement(V byValue) { 

      Optional<java.util.List<E>> result = query (
        com.tinkerpop.blueprints.Compare.EQUAL,
        byValue
      );

      if ( result.isPresent() ) {

	      java.util.List<E> list = result.get();

	      if(list.isEmpty()){
		      return Optional.empty();
	      }else{
		      return Optional.of( list.get(0) );
	      }

      } else {

        return Optional.empty();
      }
    }
  }
```

This interface declares that this index is over a property that classifies lists of elements for exact match queries; it adds the method `getTypedElements` for that.

```java
  public interface List <
    // element
    E extends TypedElement<E,ET,G,I,RV,RVT,RE,RET>,
    ET extends TypedElement.Type<E,ET,G,I,RV,RVT,RE,RET>,
    // property
    P extends Property<E,ET,P,V,G,I,RV,RVT,RE,RET>, V,
    // graph
    G extends TypedGraph<G,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET
  > 
    extends TypedElementIndex<E,ET, P,V, G, I,RV,RVT,RE,RET> 
  {
```

get a list of elements by providing a value of the property. The default ...

```java
    default Optional<java.util.List<E>> getElements(V byValue) {

      return query(
        com.tinkerpop.blueprints.Compare.EQUAL,
        byValue
      );
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