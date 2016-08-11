package com.bio4j.angulillos.test;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.Arity.*;

import java.net.URL;
import java.util.Date;

public class Twitter<RV,RE>
extends
  TypedGraph<Twitter<RV,RE>, RV,RE>
{
  @Override public final Twitter<RV,RE> self() { return this; }

  public Twitter(UntypedGraph<RV,RE> raw) { super(raw); }

  /* ### Vertices and their types */

  public final class User extends Vertex<User> {
    @Override public final User self() { return this; }
    private User(RV raw) { super(raw, user); }
  }

  public final UserType user = new UserType();
  public final class UserType extends VertexType<User> {
    public final User fromRaw(RV raw) { return new User(raw); }

    public final Name name = new Name();
    public final class Name extends Property<String> implements FromAny, ToOne {
      private Name() { super(String.class); }
    }

    public final Age age = new Age();
    public final class Age extends Property<Integer> implements FromAny, ToOne {
      private Age() { super(Integer.class); }
    }
  }

  public final class Tweet extends Vertex<Tweet> {
    @Override public final Tweet self() { return this; }
    private Tweet(RV raw) { super(raw, tweet); }
  }

  public final TweetType tweet = new TweetType();
  public final class TweetType extends VertexType<Tweet> {
    public final Tweet fromRaw(RV raw) { return new Tweet(raw); }

    public final Text text = new Text();
    public final class Text extends Property<String> implements FromAny, ToOne {
      private Text() { super(String.class); }
    }

    public final Url url = new Url();
    public final class Url extends Property<URL> implements FromAtMostOne, ToOne {
      private Url() { super(URL.class); }
    }

    public final ByUrl byUrl = new ByUrl();
    public final class ByUrl extends UniqueIndex<Url,URL> {
      private ByUrl() { super(url); }
    }
    // NOTE: Try to uncomment it and instantiate TwitterSchema
    // public final text text2 = new text();
  }

  // NOTE: Try to uncomment it and instantiate TwitterSchema
  // public final TweetType tweet2 = new TweetType();

  /* ### Edges and their types */

  public final class Follows extends Edge<User, Follows, User> {
    @Override public final Follows self() { return this; }
    private Follows(RE raw) { super(raw, follows); }
  }

  public final FollowsType follows = new FollowsType();
  public final class FollowsType extends EdgeType<User, Follows, User>
  implements FromAny, ToAny {

    private FollowsType() { super(user, user); }
    public final Follows fromRaw(RE raw) { return new Follows(raw); }

    public final Since since = new Since();
    public final class Since extends Property<Date> implements FromAny, ToOne {
      private Since() { super(Date.class); }
    }
  }

  public final class Posted extends Edge<User, Posted, Tweet> {
    @Override public final Posted self() { return this; }
    private Posted(RE raw) { super(raw, posted); }
  }

  public final PostedType posted = new PostedType();
  public final class PostedType extends EdgeType<User, Posted, Tweet>
  implements FromOne, ToAny {

    private PostedType() { super(user, tweet); }
    public final Posted fromRaw(RE raw) { return new Posted(raw); }

    public final When when = new When();
    public final class When extends Property<Date> implements FromAny, ToOne {
      private When() { super(Date.class); }
    }
  }

}
