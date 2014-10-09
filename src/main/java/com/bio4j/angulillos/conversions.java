package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class conversions {
  
  public static <O> Stream<O> stream(Iterable<O> iterable) {

    return StreamSupport.stream(iterable.spliterator(), false);
  }
}
