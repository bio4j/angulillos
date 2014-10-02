
```java
package com.ohnosequences.typedGraphs.test.go;

import com.ohnosequences.typedGraphs.*;
```


# Example Graph model

This interface contains an example graph types declaration. The particular graph schema is a simplified version of the Gene Ontology data types.


```java
public abstract class GoGraph<
  // untyped graph
  I extends UntypedGraph<RV,RVT,RE,RET>, 
  // vertices
  RV,RVT,
  // edges
  RE,RET
>
implements
  TypedGraph<
    GoGraph<I,RV,RVT,RE,RET>,
    I,RV,RVT,RE,RET
  > 
{
```


### abstract type witnesses 




```java
  public abstract TermType Term();
  public abstract Term_id Term_id();
  public abstract PartOfType PartOf();
```


### the `Term` type

This inner class represents ...


```java
  public final class TermType
  implements
    TypedVertex.Type <
      // vertex
      Term<I,RV,RVT,RE,RET>,
      // vertex type
      GoGraph<I,RV,RVT,RE,RET>.TermType,
      // graph
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    public TermType(RVT raw) { this.raw = raw; }

    private RVT raw;

    @Override
    public final RVT raw() { return raw; }

    @Override
    public final TermType value() { return graph().Term(); }

    @Override
    public final GoGraph<I,RV,RVT,RE,RET> graph() { return GoGraph.this; }

    @Override
    public final Term<I,RV,RVT,RE,RET> from(RV vertex) { return new Term<I,RV,RVT,RE,RET>(vertex, this); }

    public final Term_id id = GoGraph.this.Term_id();
  }
```


#### `Term` properties

...   


```java
  public final class Term_id
  implements 
    Property <
      Term<I,RV,RVT,RE,RET>, 
      GoGraph<I,RV,RVT,RE,RET>.TermType, 
      Term_id, String, 
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    public Term_id() {}

    @Override
    public final String name() { return "id"; }

    @Override
    public final GoGraph<I,RV,RVT,RE,RET>.TermType elementType() { return GoGraph.this.Term(); }

    @Override
    public final Class<String> valueClass() { return String.class; }
  }
```

gene ontology term

```java
  public static final class Term <I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET>
  implements
    TypedVertex <
      Term<I,RV,RVT,RE,RET>,
      GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    private RV vertex;
    private GoGraph<I,RV,RVT,RE,RET>.TermType type;

    public Term(RV vertex, GoGraph<I,RV,RVT,RE,RET>.TermType type) {

      this.vertex = vertex;
      this.type = type;
    }

    public GoGraph<I,RV,RVT,RE,RET> graph() { return type().graph(); }
    public RV raw() { return this.vertex; }

    public GoGraph<I,RV,RVT,RE,RET>.TermType type() { return type; } 

    public Term<I,RV,RVT,RE,RET> self() { return this; }
```


These two methods are completely optional, they just give you a way of retrieving a particular property at the level of this interface.


```java
    // public final String name() { return this.get(type().Name()); }
    public final String id() { return get( type().id ); }
  }
```


The partOf relationship; it goes from terms to terms.


```java
  public static final class PartOf<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET>
  implements
    TypedEdge<
      Term<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>,

      PartOf<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.PartOfType,
      GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET, 

      Term<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>
    >
  {


    private RE edge;
    private GoGraph<I,RV,RVT,RE,RET>.PartOfType type;
    
    public PartOf(RE edge, GoGraph<I,RV,RVT,RE,RET>.PartOfType type) {

      this.edge = edge;
      this.type = type;
    }

    public GoGraph<I,RV,RVT,RE,RET> graph() { return type().graph(); }
    public RE raw() { return this.edge; }

    public GoGraph<I,RV,RVT,RE,RET>.PartOfType type() { return type; } 

    public PartOf<I,RV,RVT,RE,RET> self() { return this; }
  }

  public final class PartOfType
  implements
    TypedEdge.Type.ManyToMany,
    TypedEdge.Type<
      Term<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>,

      PartOf<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.PartOfType,
      GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET, 

      Term<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>
    >
  {

    private RET raw;

    public PartOfType(RET raw) { this.raw = raw; }


    @Override
    public final GoGraph<I,RV,RVT,RE,RET>.TermType targetType() { return graph().Term(); }

    @Override
    public final GoGraph<I,RV,RVT,RE,RET>.TermType sourceType() { return graph().Term(); }


    @Override
    public final RET raw() { return raw; }

    @Override
    public final PartOfType value() { return graph().PartOf(); }

    @Override
    public final GoGraph<I,RV,RVT,RE,RET> graph() { return GoGraph.this; }

    @Override
    public final PartOf<I,RV,RVT,RE,RET> from(RE edge) { return new PartOf<I,RV,RVT,RE,RET>(edge, this); }
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

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdge.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Property.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElement.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertex.java.md