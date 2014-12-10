package com.bio4j.angulillos.test;

import com.bio4j.angulillos.*;
// import com.bio4j.angulillos.test.TwitterGraph.*;

import java.util.stream.Stream;
import java.util.Optional;

public abstract class TwitterGraphTestSuite<
  I extends UntypedGraph<RV,RVT, RE,RET>, 
  RV, RVT,
  RE, RET
> 
{

  public abstract TwitterGraph<I,RV,RVT,RE,RET> g();

  public void doSomething(TwitterGraph<I,RV,RVT,RE,RET>.User user) {

    Optional<Stream<TwitterGraph<I,RV,RVT,RE,RET>.Tweet>> tweets = user.outV(g().Posted());
  }
  
}