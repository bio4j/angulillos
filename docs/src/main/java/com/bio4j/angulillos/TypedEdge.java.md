
```java
package com.bio4j.angulillos;
```



## Edges

A typed edge with explicit source and target. 

- `S` the source TypedVertex, `ST` the source TypedVertex type
- `R` the edge, `RT` the edge type
- `T` the target TypedVertex, `TT` the target TypedVertex type

_TODO_ explain why we need three different graphs here.


```java
public interface TypedEdge <
  // src
  S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>, 
  ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
  SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
  // rel
  R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
  RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
  RG extends TypedGraph<RG,I,RV,RVT,RE,RET>, 
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
  // tgt
  T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
  TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
  TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
> 
  extends TypedElement<R,RT,RG,I,RV,RVT,RE,RET> 
{
```


  `raw` gives you the raw edge underlying this instance; see [untyped graph](UntypedGraph.java.md)


```java
  @Override
  RE raw();
```


  the source vertex of this edge


```java
  default S source() {

    return graph().source( self() );
  }
```


  the target vertex of this edge


```java
  default T target() {

    return graph().target( self() );
  }

  @Override
  R self();

  @Override
  default <
    P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>, 
    V
  > 
  V get(P property) {

    return graph().getProperty(self(), property);
  }

  @Override
  default <
    P extends Property<R,RT,P,V,RG,I,RV,RVT,RE,RET>, 
    V
  > 
  void set(P property, V value) {

    graph().setProperty(self(), property, value);
  }


  public interface HasArity {
```


    the arity for this edge. This corresponds to the edge between the two vertex types.


```java
    Type.Arity arity();
  }
  
  public interface Type <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
  extends 
    TypedElement.Type<R,RT,RG,I,RV,RVT,RE,RET>,
    HasArity
  {

    
    
    ST sourceType();
    TT targetType();

    R from(RE edge);

    RET raw();
```


    We have for each side and its dual

    - **always defined / surjective** which determines whether the return type is wrapped in Optional
    - **from one / to one**
    - **from many / to many**

    for in/out we get then
    
    1. `Option[List[X]]`
    2. `Option[X]`
    3. `List[X]`
    4. `X`

    I think that for the names is easier to assume `Option` as default.




```java
    public enum Arity {

      oneToOne,
      oneToOneOptional,
      oneToMany,
      oneToManyOptional, 

      oneOptionalToOne,
      oneOptionalToOneOptional,
      oneOptionalToMany,
      oneOptionalToManyOptional, 
      
      manyToOne,
      manyToOneOptional,
      manyToMany,
      manyToManyOptional,

      manyOptionalToOne,
      manyOptionalToOneOptional,
      manyOptionalToMany,
      manyOptionalToManyOptional;
    }
```


### Arities

We have six basic arities: three for in, three for out.

That an edge type `e` is _always defined_ implies that calling `outV(e)` will always return some, possibly several, vertices

```java
    public interface AlwaysDefined extends HasArity {}
```

An edge type `e` being _to many_ implies that calling `outV(e)` will in general return more than one vertex

```java
    public interface ToMany extends HasArity {}
```

An edge type `e` being _to one_ implies that calling `outV(e)` will return at most one vertex

```java
    public interface ToOne extends HasArity {}
```

An edge type `e` is _surjective_ implies that calling `inV(e)` will always return some, possibly several, vertices

```java
    public interface Surjective extends HasArity {}
```

An edge type `e` being _from many_ implies that calling `inV(e)` will in general return more than one vertex

```java
    public interface FromMany extends HasArity {}
```

An edge type `e` being _from one_ implies that calling `inV(e)` will return at most one vertex

```java
    public interface FromOne extends HasArity {}
```


#### Arity combinations

These are all the possible combinations of the different arities.


```java
    public interface OneToOne extends FromOne, AlwaysDefined, ToOne, Surjective { 
      
      default Arity arity() { return Arity.oneToOne; } 
    }

    public interface OneToOneOptional extends FromOne, AlwaysDefined, ToOne { 
      
      default Arity arity() { return Arity.oneToOneOptional; } 
    }

    public interface  OneToMany extends FromOne, AlwaysDefined, ToMany, Surjective { 

      default Arity arity() { return Arity.oneToMany; } 
    }

    public interface  OneToManyOptional extends FromOne, AlwaysDefined, ToMany { 

      default Arity arity() { return Arity.oneToManyOptional; } 
    }

    public interface  OneOptionalToOne extends FromOne, ToOne, Surjective { 

      default Arity arity() { return Arity.oneOptionalToOne; } 
    }

    public interface  OneOptionalToOneOptional extends FromOne, ToOne { 

      default Arity arity() { return Arity.oneOptionalToOneOptional; } 
    }

    public interface  OneOptionalToMany extends FromOne, ToMany, Surjective { 

      default Arity arity() { return Arity.oneOptionalToMany; } 
    }

    public interface  OneOptionalToManyOptional extends FromOne, ToMany { 

      default Arity arity() { return Arity.oneOptionalToManyOptional; } 
    }

    
    public interface  ManyToOne extends FromMany, AlwaysDefined, ToOne, Surjective { 

      default Arity arity() { return Arity.manyToOne; } 
    }

    public interface  ManyToOneOptional extends FromMany, AlwaysDefined, ToOne { 

      default Arity arity() { return Arity.manyToOneOptional; } 
    }

    public interface  ManyToMany extends FromMany, AlwaysDefined, ToMany, Surjective { 

      default Arity arity() { return Arity.manyToMany; } 
    }

    public interface  ManyToManyOptional extends FromMany, AlwaysDefined, ToMany { 

      default Arity arity() { return Arity.manyToManyOptional; } 
    }


    public interface  ManyOptionalToOne extends FromMany, ToOne, Surjective { 

      default Arity arity() { return Arity.manyOptionalToOne; } 
    }

    public interface  ManyOptionalToOneOptional extends FromMany, ToOne { 

      default Arity arity() { return Arity.manyOptionalToOneOptional; } 
    }

    public interface  ManyOptionalToMany extends FromMany, ToMany, Surjective { 

      default Arity arity() { return Arity.manyOptionalToMany; } 
    }

    public interface  ManyOptionalToManyOptional extends FromMany, ToMany { 

      default Arity arity() { return Arity.manyOptionalToManyOptional; } 
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
        + bio4j
          + angulillos
            + [TwitterGraph.java][test/java/com/bio4j/angulillos/TwitterGraph.java]
            + [TwitterGraphTestSuite.java][test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]
    + resources
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

[test/java/com/bio4j/angulillos/TwitterGraph.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraph.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md
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