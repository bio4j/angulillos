
```java
package com.bio4j.angulillos;
```


## Edges

A typed edge with explicit source and target.

- `S` the source TypedVertex, `ST` the source TypedVertex type
- `R` the edge, `RT` the edge type
- `T` the target TypedVertex, `TT` the target TypedVertex type


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


### Arities

We have six basic arities: three for in, three for out.


```java
    public enum Arity {

      oneToOne,
      oneToAtMostOne,
      oneToAtLeastOne,
      oneToAny,

      atMostOneToOne,
      atMostOneToAtMostOne,
      atMostOneToAtLeastOne,
      atMostOneToAny,

      atLeastOneToOne,
      atLeastOneToAtMostOne,
      atLeastOneToAtLeastOne,
      atLeastOneToAny,

      anyToOne,
      anyToAtMostOne,
      anyToAtLeastOne,
      // whatever
      anyToAny;
    }
```

#### in arities

An edge type `e` being _surjective_ implies that calling `inV(e)` will always return some, possibly several, vertices

```java
    public interface FromAtLeastOne extends HasArity {}
```

An edge type `e` being _from many_ implies that calling `inV(e)` will in general return more than one vertex

```java
    public interface FromOne extends HasArity {}
```

An edge type `e` being _from one_ implies that calling `inV(e)` will return at most one vertex

```java
    public interface FromAtMostOne extends HasArity {}
```

#### out arities

That an edge type `e` being _always defined_ implies that calling `outV(e)` will always return some, possibly several, vertices

```java
    public interface ToAtLeastOne extends HasArity {}
```

An edge type `e` being _to many_ implies that calling `outV(e)` will in general return more than one vertex

```java
    public interface ToOne extends HasArity {}
```

An edge type `e` being _to one_ implies that calling `outV(e)` will return at most one vertex

```java
    public interface ToAtMostOne extends HasArity {}
```


#### Arity combinations

These are all the possible combinations of the different arities. In the first line under `extends` you see those that correspond to `in`, and in the second one those that correspond to `out`


```java
    // oneTo
    public interface OneToOne
      extends
        FromOne,
        ToOne
    {
      default Arity arity() { return Arity.oneToOne; }
    }
    public interface OneToAtMostOne
      extends
        FromOne,
        ToAtMostOne
    {
      default Arity arity() { return Arity.oneToAtMostOne; }
    }
    public interface  OneToAtLeastOne
      extends
        FromOne,
        ToAtLeastOne
    {
      default Arity arity() { return Arity.oneToAtLeastOne; }
    }
    public interface  OneToAny
      extends
        FromOne
    {
      default Arity arity() { return Arity.oneToAny; }
    }

    // atMostOneTo
    public interface  AtMostOneToOne
      extends
        FromAtMostOne,
        ToOne
    {
      default Arity arity() { return Arity.atMostOneToOne; }
    }
    public interface  AtMostOneToAtMostOne
      extends
        FromAtMostOne,
        ToAtMostOne
    {
      default Arity arity() { return Arity.atMostOneToAtMostOne; }
    }
    public interface  AtMostOneToAtLeastOne
      extends
        FromAtMostOne,
        ToAtLeastOne
    {
      default Arity arity() { return Arity.atMostOneToAtLeastOne; }
    }
    public interface  AtMostOneToAny
      extends
        FromAtMostOne
    {
      default Arity arity() { return Arity.atMostOneToAny; }
    }

    // fromAtLeastOne
    public interface  AtLeastOneToOne
      extends
        FromAtLeastOne,
        ToOne
    {
      default Arity arity() { return Arity.atLeastOneToOne; }
    }
    public interface  AtLeastOneToAtMostOne
      extends
        FromAtLeastOne,
        ToAtMostOne
    {
      default Arity arity() { return Arity.atLeastOneToAtMostOne; }
    }
    public interface  AtLeastOneToAtLeastOne
      extends
        FromAtLeastOne,
        ToAtLeastOne
    {
      default Arity arity() { return Arity.atLeastOneToAtLeastOne; }
    }

    public interface  AtLeastOneToAny
      extends
        FromAtLeastOne
    {
      default Arity arity() { return Arity.atLeastOneToAny; }
    }

    // from any
    public interface  AnyToOne
      extends
        ToOne
    {
      default Arity arity() { return Arity.anyToOne; }
    }
    public interface  AnyToAtMostOne
      extends
        ToAtMostOne
    {
      default Arity arity() { return Arity.anyToAtMostOne; }
    }
    public interface  AnyToAtLeastOne
      extends
        ToAtLeastOne
    {
      default Arity arity() { return Arity.anyToAtLeastOne; }
    }
    public interface  AnyToAny
      extends
        HasArity
    {
      default Arity arity() { return Arity.anyToAny; }
    }
  }
}

```




[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/Property.java]: Property.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: TypedEdge.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: TypedElement.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: TypedVertex.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[test/java/com/bio4j/angulillos/TwitterGraph.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraph.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md