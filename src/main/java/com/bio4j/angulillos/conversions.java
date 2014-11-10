package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Iterator;

/*
normally you'd do `import static com.bio4j.angulillos.conversions.*;`
*/
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

  /*
  This method takes a stream of options and returns an option which is none if **all** options where none, some of a stream with the somes values otherwise.
  */
  // TODO any is not a great name; other options? sequence? thosePresent? somes?
  public static <O> Optional<Stream<O>> any(Stream<Optional<O>> stream) {

    Stream<O> filtered = stream.filter(Optional::isPresent).map(Optional::get);

    Iterator<O> it_filtered = filtered.iterator();

    if ( it_filtered.hasNext() ) {

      return Optional.of(stream(it_filtered));

    } else {

      return Optional.empty();
    }
  }
}
