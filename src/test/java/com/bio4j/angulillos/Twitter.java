package com.bio4j.angulillos;

import java.net.URL;
import java.util.Date;

public abstract class Twitter<RV,RE>
extends
  GraphSchema<Twitter<RV,RE>, RV,RE>
{

  public Twitter(UntypedGraph<RV,RE> raw) { super(raw); }


  /* ### Vertices and their types */

  public final class User extends Vertex<User> {
    @Override public final User self() { return this; }
    private User(RV raw) { super(raw); }
    @Override public final User fromRaw(RV raw) { return new User(raw); }

    public final Property<String> name = property("name", String.class);
    public final Property<Integer> age = property("age", Integer.class);
  }

  public final VertexType<User> user = new User(null);
  // public final User user(RV raw) { return new User(raw); }

  // NOTE: although we initialized raw with null, it's not accessible:
  // RV nope = user.raw;
  // RV neither = user.raw();


  // public final class Tweet extends Vertex<Tweet> {
  //   @Override public final Tweet self() { return this; }
  //   private Tweet(RV raw) { super(raw, tweet); }
  // }
  //
  // public final TweetType tweet = new TweetType();
  // public final class TweetType extends VertexType<Tweet> {
  //   @Override public final Tweet fromRaw(RV raw) { return new Tweet(raw); }
  //
  //   public final Property<String> text = property("text", String.class);
  //   public final Property<URL>    url  = property("url",  URL.class);
  // }
  //
  //
  // /* ### Edges and their types */
  //
  // public final class Follows extends Edge<User, Follows, User> {
  //   @Override public final Follows self() { return this; }
  //   private Follows(RE raw) { super(raw, follows); }
  // }
  //
  // public final FollowsType follows = new FollowsType();
  // public final class FollowsType extends EdgeType<User, Follows, User>
  // implements AnyToAny {
  //   @Override public final Follows fromRaw(RE raw) { return new Follows(raw); }
  //   private FollowsType() { super(user, user); }
  //
  //   public final Property<Date> since = property("since", Date.class);
  // }
  //
  //
  // public final class Posted extends Edge<User, Posted, Tweet> {
  //   @Override public final Posted self() { return this; }
  //   private Posted(RE raw) { super(raw, posted); }
  // }
  //
  // // Any tweet is posted by exactly one user, but user may post any number of tweets (incl. 0)
  // public final PostedType posted = new PostedType();
  // public final class PostedType extends EdgeType<User, Posted, Tweet>
  // implements OneToAny {
  //   @Override public final Posted fromRaw(RE raw) { return new Posted(raw); }
  //   private PostedType() { super(user, tweet); }
  //
  //   public final Property<Date> date = property("date", Date.class);
  // }

}
