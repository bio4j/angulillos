package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.net.URL;
import java.util.Date;

public abstract class TwitterGraphTestSuite<RV,RE> {

  protected Twitter<RV,RE> g;

  //////////////////////////////////////////

  // Trying to use some API and see that it returns correct type without any conversions:
  Twitter<RV,RE>.User u =
    g.user.fromRaw(null)
      .name.set("Bob")
      .age.set(42);

  String name = u.name.get();

  Twitter<RV,RE>.Tweet t =
    g.tweet.fromRaw(null)
      .text.set("blah-bluh");

  //////////////////////////////////////////

  // Examples with edges:

  Twitter<RV,RE>.Posted p =
    g.posted.fromRaw(null)
      .date.set(null);

  Twitter<RV,RE>.User poster = p.source();


  Stream<Twitter<RV,RE>.Follows> fe = u.outE(g.follows);

  Stream<Date> dates = fe.map(edge -> edge.since.get());


  Stream<Twitter<RV,RE>.Tweet> ts = u.outV(g.posted);

  Stream<String> texts = ts.map(tweet -> tweet.text.get());

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
