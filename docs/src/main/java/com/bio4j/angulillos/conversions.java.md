
```java
package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Iterator;
```

normally you'd do `import static com.bio4j.angulillos.conversions.*;`

```java
public class conversions {

  public static <O> Stream<O> stream(Iterable<O> iterable) {

    return stream(iterable.spliterator());
  }

  public static <O> Stream<O> stream(Spliterator<O> spliterator) {

    return StreamSupport.stream(spliterator, false);
  }

  public static <O> Stream<O> stream(Iterator<O> iterator) {

    return stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED));
  }

  public static <T> Stream<T> stream(Optional<T> opt) {

    return opt.map( Stream::of ).orElse( Stream.empty() );
  }

  public static <O> Stream<O> flatten(Stream<Stream<O>> streamstream) {

    return streamstream.flatMap(x -> x);
  }

  public static <O> Optional<O> flatten(Optional<Optional<O>> optopt) {

    return optopt.flatMap(x -> x);
  }
}

```




[test/java/com/bio4j/angulillos/Twitter.java]: ../../../../../test/java/com/bio4j/angulillos/Twitter.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md
[main/java/com/bio4j/angulillos/Arity.java]: Arity.java.md
[main/java/com/bio4j/angulillos/UntypedGraphSchema.java]: UntypedGraphSchema.java.md
[main/java/com/bio4j/angulillos/AnyElementType.java]: AnyElementType.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/Labeled.java]: Labeled.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/AnyEdgeType.java]: AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: AnyVertexType.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md