package com.bio4j.angulillos;

interface QueryPredicate {

  /* This is the same as
     - http://thinkaurelius.github.io/titan/javadoc/current/com/thinkaurelius/titan/core/attribute/Cmp.html
     - http://tinkerpop.apache.org/javadocs/3.1.1-incubating/core/org/apache/tinkerpop/gremlin/process/traversal/Compare.html
  */
  public enum Compare implements QueryPredicate {
    EQUAL,
    GREATER_THAN,
    GREATER_THAN_EQUAL,
    LESS_THAN,
    LESS_THAN_EQUAL,
    NOT_EQUAL;
  }

  /* This is the same as
     - http://thinkaurelius.github.io/titan/javadoc/current/com/thinkaurelius/titan/core/attribute/Contain.html
     - http://tinkerpop.apache.org/javadocs/3.1.1-incubating/core/org/apache/tinkerpop/gremlin/process/traversal/Contains.html
  */
  public enum Contain implements QueryPredicate {
    IN,
    NOT_IN;
  }

}
