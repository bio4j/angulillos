package com.bio4j.angulillos;
// FIXME: this should be another package, this will reveal problems with the non-public classes

import com.bio4j.angulillos.*;
import java.net.URL;
import java.util.Date;

public class Twitter<RV,RE>
extends
  TypedGraph<Twitter<RV,RE>, RV,RE>
{

  public final Twitter<RV,RE> self() { return this; }

  public Twitter(UntypedGraph<RV,RE> raw) { super(raw); }


  /* ### Vertices and their types */

  public final User user = new User();
  public final class User extends VertexType<User> {
    @Override public User self() { return this; }
    // private User() {};

    public final Property<String> name = property("name", String.class);
    public final Property<Integer> age = property("age",  Integer.class);
  }


  public final Tweet tweet = new Tweet();
  public final class Tweet extends VertexType<Tweet> {
    @Override public Tweet self() { return this; }
    // private Tweet() {};

    public final Property<String> text = property("text", String.class);
    public final Property<URL>    url  = property("url",  URL.class);

    // NOTE: Try to uncomment it and instantiate Twitter
    // public final Property<Date> date = property("text", Date.class);
  }

  // NOTE: Try to uncomment it and instantiate Twitter
  // public final Tweet tweet2 = new Tweet();

  /* ### Edges and their types */

  // public final Follows follows = new Follows();
  // public final class Follows extends EdgeType<User, Follows, User>
  // implements AnyToAny {
  //   @Override public Follows self() { return this; }
  //   Follows() { super(user, user); }
  //
  //   public final Property<Date> since = property("since", Date.class);
  // }

  // // Any tweet is posted by exactly one user, but user may post any number of tweets (incl. 0)
  // public final Posted posted = new Posted();
  // public final class Posted extends EdgeType<User, Posted, Tweet>
  // implements OneToAny {
  //   @Override public Posted self() { return this; }
  //   Posted() { super(user, tweet); }
  //
  //   public final Property<Date> date = property("date", Date.class);
  // }

  // // A reply addresses exactly one tweet, but a tweet may not have any replies
  // public final Replies replies = new Replies();
  // public final class Replies extends EdgeType<Tweet, Replies, Tweet>
  // implements AnyToOne {
  //   @Override public Replies self() { return this; }
  //   Replies() { super(tweet, tweet); }
  //
  //   public final Property<Date> date = property("date", Date.class);
  // }

}
