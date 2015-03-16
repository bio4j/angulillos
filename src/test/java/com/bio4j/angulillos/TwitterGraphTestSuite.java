package com.bio4j.angulillos;

import com.bio4j.angulillos.TwitterGraph.*;

import java.util.stream.Stream;
import java.util.Optional;

public abstract class TwitterGraphTestSuite<I extends UntypedGraph<V,VT,E,ET>,V,VT,E,ET> {

  protected TwitterGraph<I,V,VT,E,ET> g;

  public void doSomething(TwitterGraph<I,V,VT,E,ET>.User user) {

    Stream<TwitterGraph<I,V,VT,E,ET>.Tweet> tweets = user.outV(g.Posted());
  }

  public Stream<TwitterGraph<I,V,VT,E,ET>.User> tweetedTheTweetsThatTweeted(TwitterGraph<I,V,VT,E,ET>.User user) {

    return user.outV(g.Posted()).flatMap(
      tw -> tw.inV(g.Posted())
    );
  }

  /*
    This uses arity-specific methods to return **the** user that tweeted a tweet.
  */
  public TwitterGraph<I,V,VT,E,ET>.User tweeted(TwitterGraph<I,V,VT,E,ET>.Tweet tweet) {

    return tweet.inOneV(g.Posted());
  }
}
