package com.bio4j.angulillos;

// import com.bio4j.angulillos.TypedEdge.Type.*;
import java.net.URL;
import java.util.Date;

public class TwitterSchema<RV,RE>
extends
  TypedGraph<TwitterSchema<RV,RE>, RV,RE>
{

  public final TwitterSchema<RV,RE> self() { return this; }

  public TwitterSchema(UntypedGraph<RV,RE> raw) { super(raw); }


  /* ### Vertices and their types */

  public final User user = new User();
  public final class User extends VertexType<User> {
    @Override public User self() { return this; }

    public Property<User, String> name = property("name", String.class);
    public Property<User, Integer> age = property("age", Integer.class);
  }


  public final Tweet tweet = new Tweet();
  public final class Tweet extends VertexType<Tweet> {
    @Override public Tweet self() { return this; }

    public Property<Tweet, String> text = property("text", String.class);
    public Property<Tweet, URL> url     = property("url", URL.class);
  }


  /* ### Edges and their types */

  public final Follows follows = new Follows();
  public final class Follows extends EdgeType<User, Follows, User>
  implements AnyToAny {
    @Override public Follows self() { return this; }
    Follows() { super(user, user); }

    public Property<Follows, Date> since = property("since", Date.class);
  }

  // Any tweet is posted by exactly one user, but user may post any number of tweets (incl. 0)
  public final Posted posted = new Posted();
  public final class Posted extends EdgeType<User, Posted, Tweet>
  implements OneToAny {
    @Override public Posted self() { return this; }
    Posted() { super(user, tweet); }

    public Property<Posted, Date> date = property("date", Date.class);
  }

  // // A reply addresses exactly one tweet, but a tweet may not have any replies
  // public final Replies replies = new Replies();
  // public final class Replies extends EdgeType<Tweet, Replies, Tweet>
  // implements AnyToOne {
  //   @Override public Replies self() { return this; }
  //   Replies() { super(tweet, tweet); }
  //
  //   public Property<Replies, Date> date = property("date", Date.class);
  // }

}
