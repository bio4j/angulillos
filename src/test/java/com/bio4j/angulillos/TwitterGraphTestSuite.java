package com.bio4j.angulillos;

import com.bio4j.angulillos.TwitterGraph.*;

import java.util.stream.Stream;
import java.util.Optional;

public abstract class TwitterGraphTestSuite<I extends UntypedGraph<V,VT,E,ET>,V,VT,E,ET> {

  protected TwitterGraph<I,V,VT,E,ET> o;

  public class Do<G extends TwitterGraph<I,V,VT,E,ET>> {

    protected G g;

    public void doSomething(TwitterGraph<I,V,VT,E,ET>.User user) {

    Optional<Stream<TwitterGraph<I,V,VT,E,ET>.Tweet>> tweets = user.outV(g.Posted());
  }

  }

  
  
}
