
```java
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

  // public abstract class Vertex<
  //   V extends Vertex<V>
  // > extends TypedGraph<Twitter<RV,RE>, RV,RE>.Vertex<V> {
  //
  //   protected Vertex(RV raw, VertexType<V> type) { super(raw, type); }
  //
  //   // experimenting with override:
  //   @Override public <
  //     E  extends      TypedEdge<V,VertexType<V>, E,ET, ?,?, ?,RV,RE>,
  //     ET extends TypedEdge.Type<V,VertexType<V>, E,ET, ?,?, ?,RV,RE>
  //   >
  //   Stream<E> outE(ET edgeType) {
  //     System.out.println("This is overriden Twitter-graph specific outE");
  //     return outE(edgeType);
  //   }
  // }

```

### Vertices and their types

```java
  public final class User extends Vertex<User> {
    @Override public final User self() { return this; }
    private User(RV raw) { super(raw, user); }
  }

  public final UserType user = new UserType();
  public final class UserType extends VertexType<User> {
    public final User fromRaw(RV raw) { return new User(raw); }

    public final name name = new name();
    public final class name extends Property<String> implements FromAny, ToOne {
      private name() { super(String.class); }
    }

    public final age age = new age();
    public final class age extends Property<Integer> implements FromAny, ToOne {
      private age() { super(Integer.class); }
    }
  }


  public final class Tweet extends Vertex<Tweet> {
    @Override public final Tweet self() { return this; }
    private Tweet(RV raw) { super(raw, tweet); }
  }

  public final TweetType tweet = new TweetType();
  public final class TweetType extends VertexType<Tweet> {
    public final Tweet fromRaw(RV raw) { return new Tweet(raw); }

    public final text text = new text();
    public final class text extends Property<String> implements FromAny, ToOne {
      private text() { super(String.class); }
    }

    public final url url = new url();
    public final class url extends Property<URL> implements FromAtMostOne, ToOne {
      private url() { super(URL.class); }
    }

    public final byUrl byUrl = new byUrl();
    public final class byUrl extends UniqueIndex<url,URL> {
      private byUrl() { super(url); }
    }
    // NOTE: Try to uncomment it and instantiate TwitterSchema
    // public final text text2 = new text();
  }

  // NOTE: Try to uncomment it and instantiate TwitterSchema
  // public final TweetType tweet2 = new TweetType();

```

### Edges and their types

```java
  public final class Follows extends Edge<User, Follows, User> {
    @Override public final Follows self() { return this; }
    private Follows(RE raw) { super(raw, follows); }
  }

  public final FollowsType follows = new FollowsType();
  public final class FollowsType extends EdgeType<User, Follows, User>
  implements FromAny, ToAny {

    private FollowsType() { super(user, user); }
    public final Follows fromRaw(RE raw) { return new Follows(raw); }

    public final since since = new since();
    public final class since extends Property<Date> implements FromAny, ToOne {
      private since() { super(Date.class); }
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

    public final when when = new when();
    public final class when extends Property<Date> implements FromAny, ToOne {
      private when() { super(Date.class); }
    }
  }

}

```




[test/java/com/bio4j/angulillos/Twitter.java]: Twitter.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: TwitterGraphTestSuite.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: ../../../../../main/java/com/bio4j/angulillos/TypedElement.java.md
[main/java/com/bio4j/angulillos/Arity.java]: ../../../../../main/java/com/bio4j/angulillos/Arity.java.md
[main/java/com/bio4j/angulillos/UntypedGraphSchema.java]: ../../../../../main/java/com/bio4j/angulillos/UntypedGraphSchema.java.md
[main/java/com/bio4j/angulillos/AnyElementType.java]: ../../../../../main/java/com/bio4j/angulillos/AnyElementType.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: ../../../../../main/java/com/bio4j/angulillos/UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/Labeled.java]: ../../../../../main/java/com/bio4j/angulillos/Labeled.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertex.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: ../../../../../main/java/com/bio4j/angulillos/TypedEdge.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/conversions.java]: ../../../../../main/java/com/bio4j/angulillos/conversions.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: ../../../../../main/java/com/bio4j/angulillos/QueryPredicate.java.md
[main/java/com/bio4j/angulillos/AnyEdgeType.java]: ../../../../../main/java/com/bio4j/angulillos/AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: ../../../../../main/java/com/bio4j/angulillos/TypedGraph.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: ../../../../../main/java/com/bio4j/angulillos/AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: ../../../../../main/java/com/bio4j/angulillos/AnyVertexType.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: ../../../../../main/java/com/bio4j/angulillos/Property.java.md