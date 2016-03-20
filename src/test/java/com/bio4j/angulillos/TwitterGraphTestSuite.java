package com.bio4j.angulillos;

import com.bio4j.angulillos.TwitterGraph.*;

import java.util.stream.Stream;


public abstract class TwitterGraphTestSuite<I extends UntypedGraph<RV,RE>,RV,RE> {

  protected TwitterGraph<I,RV,RE> g;

  public void doSomething(TwitterGraph<I,RV,RE>.User user) {

    Stream<TwitterGraph<I,RV,RE>.Tweet> tweets = user.outV(g.Posted());
  }

  public Stream<TwitterGraph<I,RV,RE>.User> tweetedTheTweetsThatTweeted(TwitterGraph<I,RV,RE>.User user) {

    return user.outV(g.Posted()).flatMap(
      tw -> tw.inV(g.Posted())
    );
  }

  /* This uses arity-specific methods to return **the** user that tweeted a tweet. */
  public TwitterGraph<I,RV,RE>.User tweeted(TwitterGraph<I,RV,RE>.Tweet tweet) {

    return tweet.inOneV(g.Posted());
  }

  public Stream<TwitterGraph<I,RV,RE>.User> repliedToSomeTweetFrom(TwitterGraph<I,RV,RE>.User user) {

    return user
      .outV( g.Posted() )
      .flatMap( tw -> tw.inV( g.RepliesTo() ) )
      .map( tw -> tw.inOneV( g.Posted() ) )
      .distinct();
  }
}
