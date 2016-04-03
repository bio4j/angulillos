package com.bio4j.angulillos;

import java.util.stream.Stream;


public abstract class TwitterGraphTestSuite<RV,RE> {

  protected Twitter<RV,RE> g;

  Twitter<RV,RE>.User u = g.addVertex(g.user);

  Twitter<RV,RE>.User u2 = u.set(g.user.name, "bob");

  String name = u2.get(g.user.name);

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
