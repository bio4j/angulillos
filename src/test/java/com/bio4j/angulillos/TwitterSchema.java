package com.bio4j.angulillos;

import com.bio4j.angulillos.TypedEdge.Type.*;
import java.net.URL;
import java.util.Date;

public abstract class TwitterSchema<RV,RE>
extends
  GraphSchema<TwitterSchema<RV,RE>, RV,RE>
{

  public TwitterSchema(UntypedGraph<RV,RE> raw) { super(raw); }

  /* ### Vertices and their types */

  public final UserType UserType = new UserType();
  public final class UserType extends VertexType<User, UserType> {

    @Override public User fromRaw(RV vertex) { return new User(vertex); }

    public Property<UserType, String> name = new Property<>(UserType, String.class);
    public Property<UserType, Integer> age = new Property<>(UserType, Integer.class);
  }

  public final class User extends Vertex<User, UserType> {

    public User(RV vertex) { super(vertex, UserType); }
    @Override public User self() { return this; }
  }


  public final TweetType TweetType = new TweetType();
  public final class TweetType extends VertexType<Tweet, TweetType> {

    @Override public Tweet fromRaw(RV vertex) { return new Tweet(vertex); }

    public Property<TweetType, String> text = new Property<>(TweetType, String.class);
    public Property<TweetType, URL> url = new Property<>(TweetType, URL.class);
  }

  public final class Tweet extends Vertex<Tweet, TweetType> {

    public Tweet(RV vertex) { super(vertex, TweetType); }
    @Override public Tweet self() { return this; }
  }


  /* ### Edges and their types */

  public final FollowsType FollowsType = new FollowsType();
  public final class FollowsType extends EdgeType<
    User,    UserType,
    Follows, FollowsType,
    User,    UserType
  > implements AnyToAny {

    public FollowsType() { super(UserType, UserType); }
    @Override public Follows fromRaw(RE edge) { return new Follows(edge); }
  }

  public final class Follows extends Edge<
    User,    UserType,
    Follows, FollowsType,
    User,    UserType
  > {

    public Follows(RE edge) { super(edge, FollowsType); }
    @Override public Follows self() { return this; }
  }


  public final PostedType PostedType = new PostedType();
  public final class PostedType extends EdgeType<
    User,   UserType,
    Posted, PostedType,
    Tweet,  TweetType
  > implements
    // Any tweet is posted by exactly one user, but user may post any number of tweets (incl. 0)
    OneToAny {

    public PostedType() { super(UserType, TweetType); }
    @Override public Posted fromRaw(RE edge) { return new Posted(edge); }

    public Property<PostedType, Date> date = new Property<>(PostedType, Date.class);
  }

  public final class Posted extends Edge<
    User,   UserType,
    Posted, PostedType,
    Tweet,  TweetType
  > {

    public Posted(RE edge) { super(edge, PostedType); }
    @Override public Posted self() { return this; }
  }


  public final RepliesType RepliesType = new RepliesType();
  public final class RepliesType extends EdgeType<
    Tweet,   TweetType,
    Replies, RepliesType,
    Tweet,   TweetType
  > implements
    // A reply addresses exactly one tweet, but a tweet may not have any replies
    AnyToOne {

    public RepliesType() { super(TweetType, TweetType); }
    @Override public Replies fromRaw(RE edge) { return new Replies(edge); }

    public Property<RepliesType, Date> date = new Property<>(RepliesType, Date.class);
  }

  public final class Replies extends Edge<
    Tweet,   TweetType,
    Replies, RepliesType,
    Tweet,   TweetType
  > {

    public Replies(RE edge) { super(edge, RepliesType); }
    @Override public Replies self() { return this; }
  }

}
