package com.bio4j.angulillos;

import com.bio4j.angulillos.TypedEdge.Type.*;

public abstract class TwitterGraph <
  I extends UntypedGraph<RV,RE>,
  RV,RE
>
implements
  TypedGraph<
    TwitterGraph<I,RV,RE>,
    I, RV,RE
  >
{

  protected I rawGraph = null;
  public TwitterGraph(I graph) { rawGraph = graph; }
  @Override public I raw() { return rawGraph; }

  // vertices
  public abstract TwitterGraph<I,RV,RE>.UserType        User();
  public abstract TwitterGraph<I,RV,RE>.TweetType       Tweet();
  // edges
  public abstract TwitterGraph<I,RV,RE>.PostedType      Posted();
  public abstract TwitterGraph<I,RV,RE>.RepliesToType   RepliesTo();
  public abstract TwitterGraph<I,RV,RE>.FollowsType     Follows();

  /* ### Vertices and their types
  */
  /* #### User
  */
  public final class UserType
  extends
    VertexType<
      TwitterGraph<I,RV,RE>.User,
      TwitterGraph<I,RV,RE>.UserType
    >
  {
    public UserType(RVT raw) { super(raw); }

    @Override public final User vertex(RV vertex) { return new User(vertex, this); }

    /* ##### User properties
    */
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
      TwitterGraph<I,RV,RE>.User,
      TwitterGraph<I,RV,RE>.UserType
    >
  {
    public User(RV vertex, UserType type) { super(vertex, type); }
    @Override public final User self() { return this; }
  }

  /* #### Tweet
  */
  public final class TweetType
  extends
    VertexType<
      TwitterGraph<I,RV,RE>.Tweet,
      TwitterGraph<I,RV,RE>.TweetType
    >
  {
    public TweetType(RVT raw) { super(raw); }

    @Override public final Tweet vertex(RV vertex) { return new Tweet(vertex, this); }

    /* ##### Tweet properties
    */
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
      TwitterGraph<I,RV,RE>.Tweet,
      TwitterGraph<I,RV,RE>.TweetType
    >
  {
    public Tweet(RV vertex, TweetType type) { super(vertex, type); }
    @Override public final Tweet self() { return this; }
  }


  /* ### Edges
  */
  public final class PostedType
  extends
    EdgeType<
      TwitterGraph<I,RV,RE>.User,TwitterGraph<I,RV,RE>.UserType,
      TwitterGraph<I,RV,RE>.Posted,TwitterGraph<I,RV,RE>.PostedType,
      TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType
    >
  implements
    // u -[posted]-> t
    // u.outV(posted) = many maybe none
    // t.inV(posted) = one
    OneToAny
  {
    public PostedType(RET edgeType) { super(TwitterGraph.this.User(), edgeType, TwitterGraph.this.Tweet()); }

    @Override public final Posted edge(RE edge) { return new Posted(edge, this); }
  }
  public final class Posted
  extends
    Edge<
      TwitterGraph<I,RV,RE>.User,TwitterGraph<I,RV,RE>.UserType,
      TwitterGraph<I,RV,RE>.Posted,TwitterGraph<I,RV,RE>.PostedType,
      TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType
    >
  {
    public Posted(RE edge, PostedType type) { super(edge, type); }
    @Override public final Posted self() { return this; }
  }

  public final class FollowsType
  extends
    EdgeType<
      TwitterGraph<I,RV,RE>.User,TwitterGraph<I,RV,RE>.UserType,
      TwitterGraph<I,RV,RE>.Follows,TwitterGraph<I,RV,RE>.FollowsType,
      TwitterGraph<I,RV,RE>.User,TwitterGraph<I,RV,RE>.UserType
    >
  implements
    AnyToAny
  {
    public FollowsType(RET edgeType) { super(TwitterGraph.this.User(), edgeType, TwitterGraph.this.User()); }

    @Override public final Follows edge(RE edge) { return new Follows(edge, this); }
  }
  public final class Follows
  extends
    Edge<
      TwitterGraph<I,RV,RE>.User,TwitterGraph<I,RV,RE>.UserType,
      TwitterGraph<I,RV,RE>.Follows,TwitterGraph<I,RV,RE>.FollowsType,
      TwitterGraph<I,RV,RE>.User,TwitterGraph<I,RV,RE>.UserType
    >
  {
    public Follows(RE edge, FollowsType type) { super(edge, type); }
    @Override public final Follows self() { return this; }
  }

  public final class RepliesToType
  extends
    EdgeType<
      TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType,
      TwitterGraph<I,RV,RE>.RepliesTo,TwitterGraph<I,RV,RE>.RepliesToType,
      TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType
    >
  implements
    // a tweet can be a reply to at most one tweet
    AnyToAtMostOne
  {
    public RepliesToType(RET edgeType) { super(TwitterGraph.this.Tweet(), edgeType, TwitterGraph.this.Tweet()); }

    @Override public final RepliesTo edge(RE edge) { return new RepliesTo(edge, this); }
  }
  public final class RepliesTo
  extends
    Edge<
      TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType,
      TwitterGraph<I,RV,RE>.RepliesTo,TwitterGraph<I,RV,RE>.RepliesToType,
      TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType
    >
  {
    public RepliesTo(RE edge, RepliesToType type) { super(edge, type); }
    @Override public final RepliesTo self() { return this; }
  }















  ///////////////////////////////////////////////////////////////////////////////////////////////////
  /*
  ### Abstract helper classes

  These classes bound the types to be from this graph
  */

  public abstract class ElementType<
    E extends TwitterGraph<I, RV,RE>.Element<E, ET>,
    ET extends TwitterGraph<I, RV,RE>.ElementType<E, ET>
  >
  implements
    TypedElement.Type<E,ET,TwitterGraph<I,RV,RE>, I, RV,RE>
  {
    @Override public final TwitterGraph<I,RV,RE> graph() { return TwitterGraph.this; }
  }

  public abstract class Element<
    E extends TwitterGraph<I, RV,RE>.Element<E,ET>,
    ET extends TwitterGraph<I, RV,RE>.ElementType<E,ET>
  >
  implements
    TypedElement<E,ET,TwitterGraph<I, RV,RE>, I, RV,RE>
  {
    @Override public final TwitterGraph<I, RV,RE> graph() { return TwitterGraph.this; }
  }

  public abstract class VertexType<
    V extends TwitterGraph<I, RV,RE>.Vertex<V,VT>,
    VT extends TwitterGraph<I, RV,RE>.VertexType<V,VT>
  >
  extends
    TwitterGraph<I,RV,RE>.ElementType<V,VT>
  implements
    com.bio4j.angulillos.TypedVertex.Type<V,VT,TwitterGraph<I, RV,RE>, I,RV,RE>
  {
    private final RVT raw;
    protected VertexType(RVT type) { this.raw = type; }
    @Override public final RVT raw() { return this.raw; }
  }

  public abstract class Vertex<
    V extends TwitterGraph<I, RV,RE>.Vertex<V,VT>,
    VT extends TwitterGraph<I, RV,RE>.VertexType<V,VT>
  >
  extends
    TwitterGraph<I,RV,RE>.Element<V,VT>
  implements
    com.bio4j.angulillos.TypedVertex<V,VT,TwitterGraph<I, RV,RE>, I,RV,RE>
  {
    private final RV raw;
    private final VT type;
    protected Vertex(RV vertex, VT type) {
      this.raw = vertex;
      this.type = type;
    }
    @Override public final RV raw() { return this.raw; }
    @Override public final VT type() { return type; }
  }

  public abstract class EdgeType<
    S extends TwitterGraph<I, RV,RE>.Vertex<S,ST>,
    ST extends TwitterGraph<I, RV,RE>.VertexType<S,ST>,
    E extends TwitterGraph<I, RV,RE>.Edge<S,ST,E,ET,T,TT>,
    ET extends TwitterGraph<I, RV,RE>.EdgeType<S,ST,E,ET,T,TT>,
    T extends TwitterGraph<I, RV,RE>.Vertex<T,TT>,
    TT extends TwitterGraph<I, RV,RE>.VertexType<T,TT>
  >
  extends
    TwitterGraph<I,RV,RE>.ElementType<E,ET>
  implements
    com.bio4j.angulillos.TypedEdge.Type<
      S, ST, TwitterGraph<I, RV,RE>,
      E, ET, TwitterGraph<I, RV,RE>, I, RV,RE,
      T, TT, TwitterGraph<I, RV,RE>
    >
  {
    private final RET raw;
    private final ST srcT;
    private final TT tgtT;
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
    S extends TwitterGraph<I, RV,RE>.Vertex<S,ST>,
    ST extends TwitterGraph<I, RV,RE>.VertexType<S,ST>,
    E extends TwitterGraph<I, RV,RE>.Edge<S,ST,E,ET,T,TT>,
    ET extends TwitterGraph<I, RV,RE>.EdgeType<S,ST,E,ET,T,TT>,
    T extends TwitterGraph<I, RV,RE>.Vertex<T,TT>,
    TT extends TwitterGraph<I, RV,RE>.VertexType<T,TT>
  >
  extends
    TwitterGraph<I,RV,RE>.Element<E,ET>
  implements
    com.bio4j.angulillos.TypedEdge<
      S, ST, TwitterGraph<I, RV,RE>,
      E, ET, TwitterGraph<I, RV,RE>, I, RV,RE,
      T, TT, TwitterGraph<I, RV,RE>
    >
  {
    private final RE edge;
    private final ET type;
    protected Edge(RE edge, ET type) {
      this.edge = edge;
      this.type = type;
    }
    @Override public final RE raw() { return this.edge; }
    @Override public final ET type() { return type; }
  }

  public abstract class Property<
    V extends TwitterGraph<I,RV,RE>.Element<V,VT>,
    VT extends TwitterGraph<I,RV,RE>.ElementType<V, VT>,
    P extends TwitterGraph<I,RV,RE>.Property<V,VT,P,X>,
    X
  >
  implements
    com.bio4j.angulillos.Property<V,VT,P,X,TwitterGraph<I, RV,RE>, I, RV,RE>
  {
    private final VT type;
    protected Property(VT type) { this.type = type; }
    @Override public final VT elementType() { return type; }
  }
}
