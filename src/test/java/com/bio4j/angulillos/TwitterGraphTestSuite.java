package com.bio4j.angulillos;

// import com.bio4j.angulillos.TwitterSchema.*;

import java.util.stream.Stream;


public abstract class TwitterGraphTestSuite<
  G extends TwitterSchema<RV,RE>,
  RV,RE
> {

  protected G g;

  public TwitterSchema<RV,RE>.User.Vertex addUser(String name, Integer age) {

    return g.addVertex(g.user)
            .set(g.user.name, name)
            .set(g.user.age, age);
  }

  public void doSomething(TwitterSchema<RV,RE>.User.Vertex user) {

    System.out.println(user.get(g.user.age));

    TwitterSchema<RV,RE>.User.Vertex bob = addUser("Bob", 25);

    TwitterSchema<RV,RE>.Follows.Edge edge =
      g.addEdge(
        bob,
        g.follows,
        addUser("Alice", 34)
      );

    Stream<TwitterSchema<RV,RE>.Tweet.Vertex> tweets = user.outV(g.posted);
    Stream<TwitterSchema<RV,RE>.User.Vertex> followees = bob.outV(g.follows);
  }

  // public Stream<TwitterSchema<RV,RE>.User> tweetedTheTweetsThatTweeted(TwitterSchema<RV,RE>.User user) {
  //
  //   return user.outV(g.Posted()).flatMap(
  //     tw -> tw.inV(g.Posted())
  //   );
  // }
  //
  // /* This uses arity-specific methods to return **the** user that tweeted a tweet. */
  // public TwitterSchema<RV,RE>.User tweeted(TwitterSchema<RV,RE>.Tweet tweet) {
  //
  //   return tweet.inOneV(g.Posted());
  // }
  //
  // public Stream<TwitterSchema<RV,RE>.User> repliedToSomeTweetFrom(TwitterSchema<RV,RE>.User user) {
  //
  //   return user
  //     .outV( g.Posted() )
  //     .flatMap( tw -> tw.inV( g.RepliesTo() ) )
  //     .map( tw -> tw.inOneV( g.Posted() ) )
  //     .distinct();
  // }
}
