
```java
package com.ohnosequences.angulillos.titan;

import com.ohnosequences.angulillos.*;

import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.*;

import java.util.Optional;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import com.tinkerpop.blueprints.Vertex;

public interface TitanTypedVertexIndex <
  N extends TypedVertex<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  NT extends TypedVertex.Type<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  P extends Property<N,NT,P,V,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
  G extends TypedGraph<G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
  I extends TitanUntypedGraph
> 
extends 
  TypedVertexIndex<N,NT,P,V, G, I,TitanVertex,TitanKey,TitanEdge,TitanLabel>
{

  public static abstract class Default <
    N extends TypedVertex<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    I extends TitanUntypedGraph
  > 
  implements 
    TitanTypedVertexIndex<N,NT,P,V,G,I>
  {

    public Default(G graph, P property) {

      this.graph = graph;
      this.property = property;
    }

    protected G graph;
    protected P property;

    @Override
    public G graph() {

      return graph;
    }

    @Override public Optional<java.util.List<N>> query(com.tinkerpop.blueprints.Compare predicate, V value) {

      java.util.List<N> list = new LinkedList<>();

      Iterator<Vertex> iterator = graph().raw().titanGraph()
        .query().has(
          property.name(),
          predicate,
          value
        )
        .vertices().iterator();
      
      Boolean someResult = iterator.hasNext();

      while ( iterator.hasNext() ) {

        Vertex vrtx = iterator.next();
        NT elmt = property.elementType();

        if ( elmt != null && vrtx != null ) {

          list.add( elmt.from( (TitanVertex) vrtx ) );
        }
      }

      if (someResult ) {

        return Optional.of(list);

      } else {

        return Optional.empty();
      }
    }
  }

  public interface Unique <
    N extends TypedVertex<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    I extends TitanUntypedGraph
  > 
  extends
    TitanTypedVertexIndex<N,NT,P,V,G,I>,
    TypedVertexIndex.Unique<N,NT,P,V,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  {}
```

Default implementation of a node unique index

```java
  public static final class DefaultUnique <
    N extends TypedVertex<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    I extends TitanUntypedGraph
  > 
  extends
    Default<N,NT,P,V,G,I> 
  implements 
    TitanTypedVertexIndex.Unique<N,NT,P,V,G,I> 
  {

    public DefaultUnique(G graph, P property) {

      super(graph,property);
    }
  }

  public static interface List <
    N extends TypedVertex<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    I extends TitanUntypedGraph
  > 
  extends
    TitanTypedVertexIndex<N,NT,P,V,G,I>,
    TypedVertexIndex.List<N,NT,P,V,G, I,TitanVertex,TitanKey,TitanEdge,TitanLabel>
  {

  }

  public static final class DefaultList <
    N extends TypedVertex<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    NT extends TypedVertex.Type<N,NT,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    P extends Property<N,NT,P,V,G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>, V,
    G extends TypedGraph<G,I,TitanVertex,TitanKey,TitanEdge,TitanLabel>,
    I extends TitanUntypedGraph
  > 
  extends
    Default<N,NT,P,V,G,I>
  implements 
    TitanTypedVertexIndex.List<N,NT,P,V,G,I> 
  {

    public DefaultList(G graph, P property) {

      super(graph,property);
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

[test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java]: ../../../../../../test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/angulillos/go/GoGraph.java]: ../../../../../../test/java/com/ohnosequences/angulillos/go/GoGraph.java.md
[test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java]: ../../../../../../test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/angulillos/go/TestTypeNames.java]: ../../../../../../test/java/com/ohnosequences/angulillos/go/TestTypeNames.java.md
[main/java/com/ohnosequences/angulillos/TypedGraph.java]: ../TypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedVertexIndex.java]: ../TypedVertexIndex.java.md
[main/java/com/ohnosequences/angulillos/UntypedGraph.java]: ../UntypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedEdge.java]: ../TypedEdge.java.md
[main/java/com/ohnosequences/angulillos/TypedElementIndex.java]: ../TypedElementIndex.java.md
[main/java/com/ohnosequences/angulillos/Property.java]: ../Property.java.md
[main/java/com/ohnosequences/angulillos/TypedVertexQuery.java]: ../TypedVertexQuery.java.md
[main/java/com/ohnosequences/angulillos/TypedElement.java]: ../TypedElement.java.md
[main/java/com/ohnosequences/angulillos/TypedEdgeIndex.java]: ../TypedEdgeIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanTypedEdgeIndex.java]: TitanTypedEdgeIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanTypedVertexIndex.java]: TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanUntypedGraph.java]: TitanUntypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedVertex.java]: ../TypedVertex.java.md