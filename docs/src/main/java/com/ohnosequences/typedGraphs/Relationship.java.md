
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
  S extends Node<S,ST>, ST extends Node.Type<S,ST>,
  R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
  T extends Node<T,TT>, TT extends Node.Type<T,TT>
> 
  extends Element<R,RT> 
{
```


the source node of this relationship


```java
  public S source();
```


the target node of this relationship


```java
  public T target();

  public interface Type <
    S extends Node<S,ST>, ST extends Node.Type<S,ST>,
    R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type<S,ST,R,RT,T,TT>,
    T extends Node<T,TT>, TT extends Node.Type<T,TT>
  > 
    extends Element.Type<R,RT> 
  {
```


the arity for this relationship. This corresponds to the relationship between the two node types. Relationships are by default `manyToMany`


```java
    public default Arity arity() { return Arity.manyToMany; }

    public static enum Arity {

      // TODO: explain this
      oneToOne, 
      oneToMany, 
      manyToOne,
      manyToMany;
    }

    public ST sourceType();
    public TT targetType();


    //////////////////////////////////////////////////////////////

    // Bounds over targets
    interface ToMany <
      S extends Node<S,ST>, ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type.ToMany<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>, TT extends Node.Type<T,TT>
    > 
      extends Relationship.Type<S,ST, R,RT, T,TT> 
    {}

    interface ToOne <
      S extends Node<S,ST>, ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type.ToOne<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>, TT extends Node.Type<T,TT>
    >
      extends Relationship.Type<S,ST, R,RT, T,TT> 
    {}

    // Bounds over sources
    interface FromMany <
      S extends Node<S,ST>, ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type.FromMany<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>, TT extends Node.Type<T,TT>
    > 
      extends Relationship.Type<S,ST, R,RT, T,TT> 
    {}

    interface FromOne <
      S extends Node<S,ST>, ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, RT extends Relationship.Type.FromOne<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>, TT extends Node.Type<T,TT>
    > 
      extends Relationship.Type<S,ST, R,RT, T,TT> 
    {}


    // all the possible cases, just for convenience
    interface OneToMany <
      S extends Node<S,ST>,
      ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, 
      RT extends Relationship.Type.FromOne<S,ST,R,RT,T,TT> & Relationship.Type.ToMany<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>,
      TT extends Node.Type<T,TT>
    > 
      extends FromOne<S,ST, R,RT, T,TT>, ToMany<S,ST, R,RT, T,TT> 
    {

      public default Arity arity() { return Arity.oneToMany; }
    }

    interface OneToOne <
      S extends Node<S,ST>,
      ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, 
      RT extends Relationship.Type.FromOne<S,ST,R,RT,T,TT> & Relationship.Type.ToOne<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>,
      TT extends Node.Type<T,TT>
    > 
      extends FromOne<S,ST, R,RT, T,TT>, ToOne<S,ST, R,RT, T,TT> 
    {

      public default Arity arity() { return Arity.oneToOne; }
    }

    interface ManyToOne <
      S extends Node<S,ST>,
      ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, 
      RT extends Relationship.Type.FromMany<S,ST,R,RT,T,TT> & Relationship.Type.ToOne<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>,
      TT extends Node.Type<T,TT>
    > 
      extends FromMany<S,ST, R,RT, T,TT>, ToOne<S,ST, R,RT, T,TT> 
    {

      public default Arity arity() { return Arity.manyToOne; }
    }

    interface ManyToMany <
      S extends Node<S,ST>,
      ST extends Node.Type<S,ST>,
      R extends Relationship<S,ST,R,RT,T,TT>, 
      RT extends Relationship.Type.FromMany<S,ST,R,RT,T,TT> & Relationship.Type.ToMany<S,ST,R,RT,T,TT>,
      T extends Node<T,TT>,
      TT extends Node.Type<T,TT>
    > 
      extends FromMany<S,ST, R,RT, T,TT>, ToMany<S,ST, R,RT, T,TT> 
    {

      public default Arity arity() { return Arity.manyToMany; }
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
    + scala
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [TypedGraph.java][main/java/com/ohnosequences/typedGraphs/TypedGraph.java]
            + [Relationship.java][main/java/com/ohnosequences/typedGraphs/Relationship.java]
            + [Node.java][main/java/com/ohnosequences/typedGraphs/Node.java]
            + [NodeIndex.java][main/java/com/ohnosequences/typedGraphs/NodeIndex.java]
            + [RelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + titan
              + [TitanRelationship.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]
              + [TitanNodeIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]
              + [TitanTypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]
              + [TitanRelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]
              + [TitanProperty.java][main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]
              + [TitanNode.java][main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]
            + [Element.java][main/java/com/ohnosequences/typedGraphs/Element.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: ../../../../../test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/Relationship.java]: Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipIndex.java]: RelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationship.java]: titan/TitanRelationship.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNodeIndex.java]: titan/TitanNodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: titan/TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: titan/TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: titan/TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanNode.java]: titan/TitanNode.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: Element.java.md