package com.bio4j.angulillos;

import java.util.stream.Stream;


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
      .set(g.posted.date, null);

  Twitter<RV,RE>.User poster = p.source();

  Stream<Twitter<RV,RE>.Follows> fe = u.outE(g.follows);

  Stream<Twitter<RV,RE>.Tweet> ts = u.outV(g.posted);

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
