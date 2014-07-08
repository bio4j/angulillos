
```java
package com.ohnosequences.typedGraphs;
```


A typed relationship with typed source and target. 

- `S` the source Node, `ST` the source Node type
- `R` the relationship, `RT` the relationship type
- `T` the target Node, `TT` the target Node type

@author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>


```java
public interface Relationship <
  // src
  S extends Node<S,ST,SG,I,RV,RVT,RE,RET>, 
  ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
  SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
  // rel
  R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
  RT extends Relationship.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
  RG extends TypedGraph<RG,I,RV,RVT,RE,RET>, 
  I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
  // tgt
  T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
  TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
  TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
> 
  extends Element<R,RT,RG,I,RV,RVT,RE,RET> 
{

  @Override
  RE raw();
```


the source node of this relationship


```java
  S source();
```


the target node of this relationship


```java
  T target();

  interface Type <
    // src
    S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
    ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
    SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
    // rel
    R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
    RT extends Relationship.Type<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
    RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
    I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
    // tgt
    T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
    TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
    TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
  > 
    extends Element.Type<R,RT,RG,I,RV,RVT,RE,RET> 
  {
```


the arity for this relationship. This corresponds to the relationship between the two node types. Relationships are by default `manyToMany`


```java
    default Arity arity() { 

      return Arity.manyToMany;
    }

    public enum Arity {

      // TODO: explain this
      oneToOne, 
      oneToMany, 
      manyToOne,
      manyToMany;
    }

    ST sourceType();
    TT targetType();

    R from(RE edge);

    RET raw();


    //////////////////////////////////////////////////////////////

    // Bounds over targets
    public interface ToMany <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends Relationship.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    > 
    extends
      Relationship.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    public interface ToOne <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends Relationship.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      Relationship.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    // Bounds over sources
    public interface FromMany <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends Relationship.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      Relationship.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}

    public interface FromOne <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends Relationship.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>, 
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends 
      Relationship.Type<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {}


    // all possible combinations

    public interface OneToMany <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        Relationship.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.OneToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      FromOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>,
      ToMany<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {

      default Arity arity() {

        return Arity.oneToMany;
      }
    }

    public interface OneToOne <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        Relationship.Type.FromOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.OneToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      FromOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>,
      ToOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {

      default Arity arity() {

        return Arity.oneToOne;
      }
    }
    
    public interface ManyToMany <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        Relationship.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ManyToMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      FromOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>,
      ToMany<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {

      default Arity arity() {

        return Arity.manyToMany;
      }
    }

    public interface ManyToOne <
      // src
      S extends Node<S,ST,SG,I,RV,RVT,RE,RET>,
      ST extends Node.Type<S,ST,SG,I,RV,RVT,RE,RET>, 
      SG extends TypedGraph<SG,I,RV,RVT,RE,RET>,
      // rel
      R extends Relationship<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RT extends
        Relationship.Type.FromMany<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG> &
        Relationship.Type.ManyToOne<S,ST,SG,R,RT,RG,I,RV,RVT,RE,RET,T,TT,TG>,
      RG extends TypedGraph<RG,I,RV,RVT,RE,RET>,
      I extends UntypedGraph<RV,RVT,RE,RET>, RV,RVT, RE,RET,
      // tgt
      T extends Node<T,TT,TG,I,RV,RVT,RE,RET>,
      TT extends Node.Type<T,TT,TG,I,RV,RVT,RE,RET>,
      TG extends TypedGraph<TG,I,RV,RVT,RE,RET>
    >
    extends
      FromOne<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>,
      ToMany<S,ST,SG, R,RT,RG, I,RV,RVT,RE,RET, T,TT,TG>
    {

      default Arity arity() {

        return Arity.manyToOne;
      }
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
            + [Relationship.java][main/java/com/ohnosequences/typedGraphs/Relationship.java]
            + [UntypedGraph.java][main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]
            + [ElementIndex.java][main/java/com/ohnosequences/typedGraphs/ElementIndex.java]
            + [Node.java][main/java/com/ohnosequences/typedGraphs/Node.java]
            + [NodeIndex.java][main/java/com/ohnosequences/typedGraphs/NodeIndex.java]
            + [RelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [NodeQuery.java][main/java/com/ohnosequences/typedGraphs/NodeQuery.java]
            + titan
              + [TitanElement.java][main/java/com/ohnosequences/typedGraphs/titan/TitanElement.java]
              + [TitanRelationship.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]
              + [TitanNodeIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]
              + [TitanTypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]
              + [TitanRelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]
              + [TitanProperty.java][main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]
              + [TitanNode.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]
            + [Element.java][main/java/com/ohnosequences/typedGraphs/Element.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/ElementIndex.java]: ElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeQuery.java]: NodeQuery.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanElement.java]: titan/TitanElement.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: titan/TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: titan/TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: titan/TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: titan/TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: titan/TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: titan/TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: Element.java.md