package com.bio4j.angulillos.test;

import com.bio4j.angulillos.*;

import java.util.stream.Stream;


public abstract class TwitterGraphTestSuite<RV,RE> {

  protected Twitter<RV,RE> g;

  // This is how I see what's the real type of the RHS:
  // Integer i = g.user.fromRaw(null);

  // NOTE: The real type is:
  //   SimpleGraph<
  //     Twitter<RV,RE>, RV,RE
  //   >.VertexType<
  //     Twitter<RV,RE>.User
  //   >.Vertex
  // and the same as
  //   Twitter<RV,RE>.User.Vertex
  // as shown below:

  SimpleGraph<
    Twitter<RV,RE>, RV,RE
  >.VertexType<
    Twitter<RV,RE>.User
  >.Vertex u1 = g.user.fromRaw(null);

  Twitter<RV,RE>.User.Vertex u2 = u1;

  SimpleGraph<
    Twitter<RV,RE>, RV,RE
  >.VertexType<
    Twitter<RV,RE>.User
  >.Vertex u3 = u2;

  //////////////////////////////////////////

  // Trying to use some API and see that it returns correct type without any conversions:
  Twitter<RV,RE>.User.Vertex u =
    g.user.new Vertex(null) //.self()
    // g.user.fromRaw(null)
      .set(g.user.name, "Bob")
      .set(g.user.age, 42);

  Twitter<RV,RE>.Tweet.Vertex t =
    g.tweet.fromRaw(null)
      .set(g.tweet.text, "blah-bluh");

  //////////////////////////////////////////

  // Examples with edges:

  Twitter<RV,RE>.Posted.Edge p =
    g.posted.fromRaw(null)
      .set(g.posted.date, null);

  Twitter<RV,RE>.User.Vertex poster = p.source();

  // public Twitter<RV,RE>.User.Vertex addUser(String name, Integer age) {
  //
  //   Twitter<RV,RE>.User.Vertex u = g.user.addVertex();
  //
  //   return u.set(g.user.name, name)
  //           .set(g.user.age, age);
  // }
  // G.Tweet t = g.new Tweet();

  // public void doSomething(Twitter<RV,RE>.User.Vertex user) {
  //
  //   System.out.println(user.get(g.user.age));
  //
  //   // Twitter<RV,RE>.User.Vertex bob = addUser("Bob", 25);
  //   //
  //   // Twitter<RV,RE>.Follows.Edge edge =
  //   //   g.follows.addEdge( bob, addUser("Alice", 34) );
  //   //
  //   // Stream<Twitter<RV,RE>.Follows.Edge> followEdges = bob.outE(g.follows);
  //   // Stream<Twitter<RV,RE>.Tweet.Vertex> tweets = user.outV(g.posted);
  //   // Stream<Twitter<RV,RE>.User.Vertex> followees = bob.outV(g.follows);
  //   //
  //   // // Twitter<RV,RE>.User.Vertex source = g.source(edge);
  //   // Twitter<RV,RE>.User.Vertex source = edge.source();
  // }

  // public Stream<Twitter<RV,RE>.User> tweetedTheTweetsThatTweeted(Twitter<RV,RE>.User user) {
  //
  //   return user.outV(g.Posted()).flatMap(
  //     tw -> tw.inV(g.Posted())
  //   );
  // }
  //
  // /* This uses arity-specific methods to return **the** user that tweeted a tweet. */
  // public Twitter<RV,RE>.User tweeted(Twitter<RV,RE>.Tweet tweet) {
  //
  //   return tweet.inOneV(g.Posted());
  // }
  //
  // public Stream<Twitter<RV,RE>.User> repliedToSomeTweetFrom(Twitter<RV,RE>.User user) {
  //
  //   return user
  //     .outV( g.Posted() )
  //     .flatMap( tw -> tw.inV( g.RepliesTo() ) )
  //     .map( tw -> tw.inOneV( g.Posted() ) )
  //     .distinct();
  // }
}
