
```java
package com.bio4j.angulillos;

import com.bio4j.angulillos.TypedEdge.Type.*;

public abstract class TwitterGraph <
  I extends UntypedGraph<RV,RVT, RE,RET>,
  RV, RVT,
  RE, RET
>
implements
  TypedGraph<
    TwitterGraph<I,RV,RVT,RE,RET>,
    I, RV,RVT, RE,RET
  >
{

  protected I rawGraph = null;
  public TwitterGraph(I graph) { rawGraph = graph; }
  @Override public I raw() { return rawGraph; }

  // types
  public abstract TwitterGraph<I,RV,RVT,RE,RET>.UserType    User();
  public abstract TwitterGraph<I,RV,RVT,RE,RET>.TweetType   Tweet();
  public abstract TwitterGraph<I,RV,RVT,RE,RET>.PostedType  Posted();
```


  ### Vertices and their types


  #### User


```java
  public final class UserType
  extends
    VertexType<
      TwitterGraph<I,RV,RVT,RE,RET>.User,
      TwitterGraph<I,RV,RVT,RE,RET>.UserType
    > 
  {
    public UserType(RVT raw) { super(raw); }
    @Override public final UserType value() { return graph().User(); }
    @Override public final User from(RV vertex) { return new User(vertex, this); }
```

##### User properties

```java
    public final name name = new name();
    public final class name extends Property<User,UserType,name,String> {
      public name() { super(UserType.this); }
      @Override public final Class<String> valueClass() { return String.class; }
    }
    public final age age = new age();
    public final class age extends Property<User,UserType,age,Integer> {
      public age() { super(UserType.this); }
      @Override public final Class<Integer> valueClass() { return Integer.class; }
    }
  }

  public final class User
  extends 
    Vertex<
      TwitterGraph<I,RV,RVT,RE,RET>.User,
      TwitterGraph<I,RV,RVT,RE,RET>.UserType
    > 
  {  
    public User(RV vertex, UserType type) { super(vertex, type); }
    @Override public final User self() { return this; }
  }
```


  #### Tweet


```java
  public final class TweetType
  extends
    VertexType<
      TwitterGraph<I,RV,RVT,RE,RET>.Tweet,
      TwitterGraph<I,RV,RVT,RE,RET>.TweetType
    > 
  {
    public TweetType(RVT raw) { super(raw); }
    @Override public final TweetType value() { return graph().Tweet(); }
    @Override public final Tweet from(RV vertex) { return new Tweet(vertex, this); }
```

##### Tweet properties

```java
    public final text text = new text();
    public final class text extends Property<Tweet,TweetType,text,String> {
      public text() { super(TweetType.this); }
      @Override public final Class<String> valueClass() { return String.class; }
    }
    public final url url = new url();
    public final class url extends Property<Tweet,TweetType,url,String> {
      public url() { super(TweetType.this); }
      @Override public final Class<String> valueClass() { return String.class; }
    }
  }

  public final class Tweet
  extends 
    Vertex<
      TwitterGraph<I,RV,RVT,RE,RET>.Tweet,
      TwitterGraph<I,RV,RVT,RE,RET>.TweetType
    > 
  {  
    public Tweet(RV vertex, TweetType type) { super(vertex, type); }
    @Override public final Tweet self() { return this; }
  }
```


  ### Edges


```java
  public final class PostedType 
  extends 
    EdgeType<
      TwitterGraph<I,RV,RVT,RE,RET>.User,TwitterGraph<I,RV,RVT,RE,RET>.UserType,
      TwitterGraph<I,RV,RVT,RE,RET>.Posted,TwitterGraph<I,RV,RVT,RE,RET>.PostedType,
      TwitterGraph<I,RV,RVT,RE,RET>.Tweet,TwitterGraph<I,RV,RVT,RE,RET>.TweetType
    >
  implements
    // u -[posted]-> t
    // u.outV(posted) = many maybe none
    // t.inV(posted) = one
    FromOneToManyOptional
  {
    public PostedType(RET edgeType) { super(TwitterGraph.this.User(), edgeType, TwitterGraph.this.Tweet()); }
    @Override public final PostedType value() { return graph().Posted(); }
    @Override public final Posted from(RE edge) { return new Posted(edge, this); }
  }
  public final class Posted
  extends 
    Edge<
      TwitterGraph<I,RV,RVT,RE,RET>.User,TwitterGraph<I,RV,RVT,RE,RET>.UserType,
      TwitterGraph<I,RV,RVT,RE,RET>.Posted,TwitterGraph<I,RV,RVT,RE,RET>.PostedType,
      TwitterGraph<I,RV,RVT,RE,RET>.Tweet,TwitterGraph<I,RV,RVT,RE,RET>.TweetType
    >
  {
    public Posted(RE edge, PostedType type) { super(edge, type); }
    @Override public final Posted self() { return this; }
  }















  ///////////////////////////////////////////////////////////////////////////////////////////////////

```


  ### Abstract helper classes

  These classes bound the types to be from this graph


```java
  public abstract class ElementType<
    E extends TwitterGraph<I, RV, RVT, RE, RET>.Element<E, ET>,
    ET extends TwitterGraph<I, RV, RVT, RE, RET>.ElementType<E, ET>
  >
  implements
    TypedElement.Type<E,ET,TwitterGraph<I,RV,RVT,RE,RET>, I, RV, RVT, RE, RET> 
  {
    @Override public final TwitterGraph<I,RV,RVT,RE,RET> graph() { return TwitterGraph.this; }
  }

  public abstract class Element<
    E extends TwitterGraph<I, RV, RVT, RE, RET>.Element<E,ET>,
    ET extends TwitterGraph<I, RV, RVT, RE, RET>.ElementType<E,ET>
  >
  implements
    TypedElement<E,ET,TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> 
  {
    @Override public final TwitterGraph<I, RV, RVT, RE, RET> graph() { return TwitterGraph.this; }
  }

  public abstract class VertexType<
    V extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<V,VT>,
    VT extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<V,VT>
  >
  extends
    TwitterGraph<I,RV,RVT,RE,RET>.ElementType<V,VT>
  implements
    com.bio4j.angulillos.TypedVertex.Type<V,VT,TwitterGraph<I, RV, RVT, RE, RET>, I,RV,RVT,RE,RET>
  {
    private RVT raw;
    protected VertexType(RVT type) { this.raw = type; }
    @Override public final RVT raw() { return this.raw; }
  }

  public abstract class Vertex<
    V extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<V,VT>,
    VT extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<V,VT>
  >
  extends
    TwitterGraph<I,RV,RVT,RE,RET>.Element<V,VT>
  implements
    com.bio4j.angulillos.TypedVertex<V,VT,TwitterGraph<I, RV, RVT, RE, RET>, I,RV,RVT,RE,RET>
  {
    private RV raw;
    private VT type;
    protected Vertex(RV vertex, VT type) {
      this.raw = vertex;
      this.type = type;
    }
    @Override public final RV raw() { return this.raw; }
    @Override public final VT type() { return type; }
  }

  public abstract class EdgeType<
    S extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<S,ST>,
    ST extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<S,ST>,
    E extends TwitterGraph<I, RV, RVT, RE, RET>.Edge<S,ST,E,ET,T,TT>,
    ET extends TwitterGraph<I, RV, RVT, RE, RET>.EdgeType<S,ST,E,ET,T,TT>,
    T extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<T,TT>,
    TT extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<T,TT>
  >
  extends
    TwitterGraph<I,RV,RVT,RE,RET>.ElementType<E,ET>
  implements
    com.bio4j.angulillos.TypedEdge.Type<
      S, ST, TwitterGraph<I, RV, RVT, RE, RET>,
      E, ET, TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TwitterGraph<I, RV, RVT, RE, RET>
    >
  {
    private RET raw;
    private ST srcT;
    private TT tgtT;
    protected EdgeType(ST srcT, RET raw, TT tgtT) {
      this.raw = raw;
      this.srcT = srcT;
      this.tgtT = tgtT;
    }
    @Override public final ST sourceType() { return srcT; }
    @Override public final TT targetType() { return tgtT; }
    @Override public final RET raw() { return raw; }
  }

  public abstract class Edge<
    S extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<S,ST>,
    ST extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<S,ST>,
    E extends TwitterGraph<I, RV, RVT, RE, RET>.Edge<S,ST,E,ET,T,TT>,
    ET extends TwitterGraph<I, RV, RVT, RE, RET>.EdgeType<S,ST,E,ET,T,TT>,
    T extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<T,TT>,
    TT extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<T,TT>
  >
  extends
    TwitterGraph<I,RV,RVT,RE,RET>.Element<E,ET>
  implements
    com.bio4j.angulillos.TypedEdge<
      S, ST, TwitterGraph<I, RV, RVT, RE, RET>,
      E, ET, TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TwitterGraph<I, RV, RVT, RE, RET>
    >
  {
    private RE edge;
    private ET type;
    protected Edge(RE edge, ET type) {
      this.edge = edge;
      this.type = type;
    }
    @Override public final RE raw() { return this.edge; }
    @Override public final ET type() { return type; } 
  }

  public abstract class Property<
    V extends TwitterGraph<I,RV, RVT, RE, RET>.Element<V,VT>,
    VT extends TwitterGraph<I,RV,RVT, RE, RET>.ElementType<V, VT>,
    P extends TwitterGraph<I,RV,RVT, RE, RET>.Property<V,VT,P,PV>,
    PV
  >
  implements
    com.bio4j.angulillos.Property<V,VT,P,PV,TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> 
  {
    private VT type;
    protected Property(VT type) { this.type = type; }
    @Override public final VT elementType() { return type; }
  }
}




//   // case class Date(day: Integer, month: Integer, year: Integer)
//   case object time extends Property[String]

//   case object User  extends VertexType("user", name :~: age :~: ∅)
//   case object Tweet extends VertexType("tweet", text :~: ∅)

//   case object Posted  extends EdgeType(User, "posted", Tweet, time :~: url :~: ∅) with OneIn with ManyOut
//   case object Follows extends EdgeType(User, "follows", User, ∅) with ManyIn with ManyOut

//   case object UserNameIx extends CompositeIndex(User, name)
//   case object TweetTextIx extends CompositeIndex(Tweet, text)
//   case object PostedTimeIx extends CompositeIndex(Posted, time)

//   val schema = GraphSchema("twitter",
//     vertexTypes = U
// ser :~: Tweet :~: ∅,
//     edgeTypes = Posted :~: Follows :~: ∅,
//     indexes = UserNameIx :~: TweetTextIx :~: PostedTimeIx :~: ∅
//   )


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

[test/java/com/bio4j/angulillos/TwitterGraph.java]: TwitterGraph.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: TwitterGraphTestSuite.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: ../../../../../main/java/com/bio4j/angulillos/TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: ../../../../../main/java/com/bio4j/angulillos/UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: ../../../../../main/java/com/bio4j/angulillos/TypedEdge.java.md
[main/java/com/bio4j/angulillos/conversions.java]: ../../../../../main/java/com/bio4j/angulillos/conversions.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: ../../../../../main/java/com/bio4j/angulillos/Property.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: ../../../../../main/java/com/bio4j/angulillos/TypedElement.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertex.java.md