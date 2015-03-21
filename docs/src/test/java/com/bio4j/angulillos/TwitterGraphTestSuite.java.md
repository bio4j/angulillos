
```java
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
```


This uses arity-specific methods to return **the** user that tweeted a tweet.


```java
  public TwitterGraph<I,V,VT,E,ET>.User tweeted(TwitterGraph<I,V,VT,E,ET>.Tweet tweet) {

    return tweet.inOneV(g.Posted());
  }
}

```


------

### Index

+ src
  + test
    + java
      + com
        + bio4j
          + angulillos
            + [TwitterGraph.java][test/java/com/bio4j/angulillos/TwitterGraph.java]
            + [TwitterGraphTestSuite.java][test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]
    + resources
  + main
    + java
      + com
        + bio4j
          + angulillos
            + [TypedGraph.java][main/java/com/bio4j/angulillos/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/bio4j/angulillos/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/bio4j/angulillos/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/bio4j/angulillos/TypedEdge.java]
            + [conversions.java][main/java/com/bio4j/angulillos/conversions.java]
            + [TypedElementIndex.java][main/java/com/bio4j/angulillos/TypedElementIndex.java]
            + [Property.java][main/java/com/bio4j/angulillos/Property.java]
            + [TypedVertexQuery.java][main/java/com/bio4j/angulillos/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/bio4j/angulillos/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/bio4j/angulillos/TypedEdgeIndex.java]
            + [TypedVertex.java][main/java/com/bio4j/angulillos/TypedVertex.java]

[test/java/com/bio4j/angulillos/TwitterGraph.java]: TwitterGraph.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: TwitterGraphTestSuite.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: ../../../../../main/java/com/bio4j/angulillos/TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: ../../../../../main/java/com/bio4j/angulillos/UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: ../../../../../main/java/com/bio4j/angulillos/TypedEdge.java.md
[main/java/com/bio4j/angulillos/conversions.java]: ../../../../../main/java/com/bio4j/angulillos/conversions.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: ../../../../../main/java/com/bio4j/angulillos/Property.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: ../../../../../main/java/com/bio4j/angulillos/TypedElement.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: ../../../../../main/java/com/bio4j/angulillos/TypedVertex.java.md