package com.bio4j.angulillos;

import java.net.URL;
import java.util.Date;
import java.util.stream.Stream;

public abstract class Twitter<RV,RE>
extends
  GraphSchema<Twitter<RV,RE>, RV,RE>
{

  public Twitter(UntypedGraph<RV,RE> raw) { super(raw); }

  public abstract class Vertex<
    V extends Vertex<V>
  > extends GraphSchema<Twitter<RV,RE>, RV,RE>.Vertex<V> {

    protected Vertex(RV raw, VertexType<V> type) { super(raw, type); }

    // experimenting with override:
    @Override public <
      E  extends      TypedEdge<V,VertexType<V>, E,ET, ?,?, ?,RV,RE>,
      ET extends TypedEdge.Type<V,VertexType<V>, E,ET, ?,?, ?,RV,RE>
    >
    Stream<E> outE(ET edgeType) { return graph().outE(self(), edgeType); }
  }


  /* ### Vertices and their types */

  public final class User extends Vertex<User> {
    @Override public final User self() { return this; }
    private User(RV raw) { super(raw, user); }

    public final Property<String> name = property("name", String.class);
    public final Property<Integer> age = property("age", Integer.class);
  }

  public final VertexType<User> user = new VertexType<>(User::new);


  public final class Tweet extends Vertex<Tweet> {
    @Override public final Tweet self() { return this; }
    private Tweet(RV raw) { super(raw, tweet); }

    public final Property<String> text = property("text", String.class);
    public final Property<URL>    url  = property("url",  URL.class);
  }

  public final VertexType<Tweet> tweet = new VertexType<>(Tweet::new);


  /* ### Edges and their types */

  public final class Follows extends Edge<User, Follows, User> {
    @Override public final Follows self() { return this; }
    private Follows(RE raw) { super(raw, follows); }

    public final Property<Date> since = property("since", Date.class);
  }

  public final AnyToAny<User, Follows, User> follows =
    new AnyToAny<>(user, Follows::new, user);


  public final class Posted extends Edge<User, Posted, Tweet> {
    @Override public final Posted self() { return this; }
    private Posted(RE raw) { super(raw, posted); }

    public final Property<Date> date = property("date", Date.class);
  }

  // Any tweet is posted by exactly one user, but user may post any number of tweets (incl. 0)
  public final AnyToAny<User, Posted, Tweet> posted =
    new AnyToAny<>(user, Posted::new, tweet);

}
