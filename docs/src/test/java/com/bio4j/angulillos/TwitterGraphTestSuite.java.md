
```java
package com.bio4j.angulillos.test;

import java.util.stream.Stream;
import java.util.Optional;
import java.util.Date;
import java.net.URL;

import com.bio4j.angulillos.*;

public abstract class TwitterGraphTestSuite<RV,RE> {

  protected Twitter<RV,RE> g;

  //////////////////////////////////////////

  // Trying to use some API and see that it returns correct type without any conversions:
  public final Twitter<RV,RE>.User u =
    g.user.fromRaw(null)
      .set(g.user.name, "Bob")
      .set(g.user.age, 42);

  public final String name = u.get(g.user.name);

  public final Twitter<RV,RE>.Tweet t =
    g.tweet.fromRaw(null)
      .set(g.tweet.text, "blah-bluh");

  //////////////////////////////////////////

  // Examples with edges:

  public final Twitter<RV,RE>.Posted p =
    g.posted.fromRaw(null)
      .set(g.posted.when, null);

  public final Twitter<RV,RE>.User poster = p.source();


  public final Stream<Twitter<RV,RE>.Follows> fe = u.outE(g.follows);

  public final Stream<Date> dates = fe.map(edge -> edge.get(g.follows.since));


  public final Stream<Twitter<RV,RE>.Tweet> ts = u.outV(g.posted);

  public final Stream<String> texts = ts.map(tweet -> tweet.get(g.tweet.text));

  public final Optional<Twitter<RV,RE>.Tweet> fromURL(URL url) { return g.tweet.byUrl.find(url); }

  // to print the schema:

  // g.vertexTypes.foreach { vt =>
  //   println(s"""${vt._label}:
  //     |  properties: ${vt.properties().map(_._label())}
  //     |  inEdges: ${vt.inEdges().map{ _._label() }}
  //     |  outEdges: ${vt.outEdges().map{ _._label() }}""".stripMargin
  //     )
  // }
  //
  // g.edgeTypes.foreach { et =>
  //   println(s"""${et._label}:
  //     |  properties: ${et.properties().map(_._label())}
  //     |  source: ${et.source()._label()}
  //     |  target: ${et.target()._label()}""".stripMargin
  //     )
  // }

  // public void doSomething(Twitter<RV,RE>.User user) {
  //
  //   Stream<Twitter<RV,RE>.Tweet> tweets = user.outV(g.posted);
  // }
  //
  // public Stream<Twitter<RV,RE>.User> tweetedTheTweetsThatTweeted(Twitter<RV,RE>.User user) {
  //
  //   return user.outV(g.posted).flatMap(
  //     tw -> tw.inV(g.posted)
  //   );
  // }
  //

  // see #78

```

This uses arity-specific methods to return **the** user that tweeted a tweet.

```java
  public Twitter<RV,RE>.User tweeted(Twitter<RV,RE>.Tweet tweet) {

    return tweet.inOneV(g.posted);
  }
  //
  // public Stream<Twitter<RV,RE>.User> repliedToSomeTweetFrom(Twitter<RV,RE>.User user) {
  //
  //   return user
  //     .outV( g.posted )
  //     .flatMap( tw -> tw.inV( g.RepliesTo() ) )
  //     .map( tw -> tw.inOneV( g.posted ) )
  //     .distinct();
  // }
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