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

  public final User user = new User();
  public final class User extends VertexType<User> {
    User() { super(user); }

    public Property<User, String> name = new Property<>(this, String.class);
    public Property<User, Integer> age = new Property<>(this, Integer.class);
  }


  public final Tweet tweet = new Tweet();
  public final class Tweet extends VertexType<Tweet> {
    Tweet() { super(tweet); }

    public Property<Tweet, String> text = new Property<>(this, String.class);
    public Property<Tweet, URL> url     = new Property<>(this, URL.class);
  }


  /* ### Edges and their types */

  public final Follows follows = new Follows();
  public final class Follows extends EdgeType<User, Follows, User>
  implements AnyToAny {
    Follows() { super(user, follows, user); }

    public Property<Follows, Date> since = new Property<>(this, Date.class);
  }

  // Any tweet is posted by exactly one user, but user may post any number of tweets (incl. 0)
  public final Posted posted = new Posted();
  public final class Posted extends EdgeType<User, Posted, Tweet>
  implements OneToAny {
    Posted() { super(user, posted, tweet); }

    public Property<Posted, Date> date = new Property<>(this, Date.class);
  }

  // A reply addresses exactly one tweet, but a tweet may not have any replies
  public final Replies replies = new Replies();
  public final class Replies extends EdgeType<Tweet, Replies, Tweet>
  implements AnyToOne {
    Replies() { super(tweet, replies, tweet); }

    public Property<Replies, Date> date = new Property<>(this, Date.class);
  }

}
