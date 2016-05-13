
```java
package com.bio4j.angulillos.test;

import java.util.stream.Stream;
import java.util.Date;

public abstract class TwitterGraphTestSuite<RV,RE> {

  protected Twitter<RV,RE> g;

  //////////////////////////////////////////

  // Trying to use some API and see that it returns correct type without any conversions:
  Twitter<RV,RE>.User u =
    g.user.fromRaw(null)
      .set(g.user.name, "Bob")
      .set(g.user.age, 42);

  String name = u.get(g.user.name);

  Twitter<RV,RE>.Tweet t =
    g.tweet.fromRaw(null)
      .set(g.tweet.text, "blah-bluh");

  //////////////////////////////////////////

  // Examples with edges:

  Twitter<RV,RE>.Posted p =
    g.posted.fromRaw(null)
      .set(g.posted.when, null);

  Twitter<RV,RE>.User poster = p.source();


  Stream<Twitter<RV,RE>.Follows> fe = u.outE(g.follows);

  Stream<Date> dates = fe.map(edge -> edge.get(g.follows.since));


  Stream<Twitter<RV,RE>.Tweet> ts = u.outV(g.posted);

  Stream<String> texts = ts.map(tweet -> tweet.get(g.tweet.text));

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
  // /* This uses arity-specific methods to return **the** user that tweeted a tweet. */
  // public Twitter<RV,RE>.User tweeted(Twitter<RV,RE>.Tweet tweet) {
  //
  //   return tweet.inOneV(g.posted);
  // }
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
[main/java/com/bio4j/angulillos/Arity.java]: ../../../../../main/java/com/bio4j/angulillos/Arity.java.md
[main/java/com/bio4j/angulillos/UntypedGraphSchema.java]: ../../../../../main/java/com/bio4j/angulillos/UntypedGraphSchema.java.md
[main/java/com/bio4j/angulillos/AnyElementType.java]: ../../../../../main/java/com/bio4j/angulillos/AnyElementType.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: ../../../../../main/java/com/bio4j/angulillos/UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/Labeled.java]: ../../../../../main/java/com/bio4j/angulillos/Labeled.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/conversions.java]: ../../../../../main/java/com/bio4j/angulillos/conversions.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: ../../../../../main/java/com/bio4j/angulillos/QueryPredicate.java.md
[main/java/com/bio4j/angulillos/AnyEdgeType.java]: ../../../../../main/java/com/bio4j/angulillos/AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: ../../../../../main/java/com/bio4j/angulillos/TypedGraph.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: ../../../../../main/java/com/bio4j/angulillos/AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: ../../../../../main/java/com/bio4j/angulillos/AnyVertexType.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedElementIndex.java.md