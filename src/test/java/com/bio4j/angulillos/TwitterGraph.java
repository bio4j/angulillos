package com.bio4j.angulillos;

import com.bio4j.angulillos.TypedEdge.Type.*;

public abstract class TwitterGraph <
  RG extends UntypedGraph<RV,RE>,
  RV,RE
> extends
  SimpleGraph<TwitterGraph<RG,RV,RE>, RG,RV,RE>
{

  /* ### Vertices and their types */

  public final UserType UserType = new UserType();
  public final class UserType extends VertexType<User, UserType> {

    @Override public final User fromRaw(RV vertex) { return new User(vertex); }

    public final Property<UserType, String> name = new Property<>(UserType, String.class);
    public final Property<UserType, Integer> age = new Property<>(UserType, Integer.class);
  }

  public final class User extends Vertex<User, UserType> {

    public User(RV vertex) { super(vertex, UserType); }
    @Override public User self() { return this; }
  }


  public final FollowsType FollowsType = new FollowsType();
  public final class FollowsType extends EdgeType<
    User,    UserType,
    Follows, FollowsType,
    User,    UserType
  > implements AnyToAny {

    public FollowsType() { super(UserType, UserType); }
    @Override public final Follows fromRaw(RE edge) { return new Follows(edge); }
  }

  public final class Follows extends Edge<
    User,    UserType,
    Follows, FollowsType,
    User,    UserType
  > {

    public Follows(RE edge) { super(edge, FollowsType); }
    @Override public final Follows self() { return this; }
  }

  // /* #### Tweet
  // */
  // public final class TweetType
  // extends
  //   VertexType<
  //     TwitterGraph<I,RV,RE>.Tweet,
  //     TwitterGraph<I,RV,RE>.TweetType
  //   >
  // {
  //   public TweetType(RVT raw) { super(raw); }
  //
  //   @Override public final Tweet vertex(RV vertex) { return new Tweet(vertex, this); }
  //
  //   /* ##### Tweet properties
  //   */
  //   public final text text = new text();
  //   public final class text extends Property<Tweet,TweetType,text,String> {
  //     public text() { super(TweetType.this); }
  //     @Override public final Class<String> valueClass() { return String.class; }
  //   }
  //   public final url url = new url();
  //   public final class url extends Property<Tweet,TweetType,url,String> {
  //     public url() { super(TweetType.this); }
  //     @Override public final Class<String> valueClass() { return String.class; }
  //   }
  // }
  //
  // public final class Tweet
  // extends
  //   Vertex<
  //     TwitterGraph<I,RV,RE>.Tweet,
  //     TwitterGraph<I,RV,RE>.TweetType
  //   >
  // {
  //   public Tweet(RV vertex, TweetType type) { super(vertex, type); }
  //   @Override public final Tweet self() { return this; }
  // }
  //
  //
  // /* ### Edges
  // */
  // public final class PostedType
  // extends
  //   EdgeType<
  //     TwitterGraph<I,RV,RE>.User,TwitterGraph<I,RV,RE>.UserType,
  //     TwitterGraph<I,RV,RE>.Posted,TwitterGraph<I,RV,RE>.PostedType,
  //     TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType
  //   >
  // implements
  //   // u -[posted]-> t
  //   // u.outV(posted) = many maybe none
  //   // t.inV(posted) = one
  //   OneToAny
  // {
  //   public PostedType(RET edgeType) { super(TwitterGraph.this.User(), edgeType, TwitterGraph.this.Tweet()); }
  //
  //   @Override public final Posted edge(RE edge) { return new Posted(edge, this); }
  // }
  // public final class Posted
  // extends
  //   Edge<
  //     TwitterGraph<I,RV,RE>.User,TwitterGraph<I,RV,RE>.UserType,
  //     TwitterGraph<I,RV,RE>.Posted,TwitterGraph<I,RV,RE>.PostedType,
  //     TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType
  //   >
  // {
  //   public Posted(RE edge, PostedType type) { super(edge, type); }
  //   @Override public final Posted self() { return this; }
  // }
  //
  // public final class RepliesToType
  // extends
  //   EdgeType<
  //     TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType,
  //     TwitterGraph<I,RV,RE>.RepliesTo,TwitterGraph<I,RV,RE>.RepliesToType,
  //     TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType
  //   >
  // implements
  //   // a tweet can be a reply to at most one tweet
  //   AnyToAtMostOne
  // {
  //   public RepliesToType(RET edgeType) { super(TwitterGraph.this.Tweet(), edgeType, TwitterGraph.this.Tweet()); }
  //
  //   @Override public final RepliesTo edge(RE edge) { return new RepliesTo(edge, this); }
  // }
  // public final class RepliesTo
  // extends
  //   Edge<
  //     TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType,
  //     TwitterGraph<I,RV,RE>.RepliesTo,TwitterGraph<I,RV,RE>.RepliesToType,
  //     TwitterGraph<I,RV,RE>.Tweet,TwitterGraph<I,RV,RE>.TweetType
  //   >
  // {
  //   public RepliesTo(RE edge, RepliesToType type) { super(edge, type); }
  //   @Override public final RepliesTo self() { return this; }
  // }

}
