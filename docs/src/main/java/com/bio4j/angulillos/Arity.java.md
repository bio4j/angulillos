
```java
package com.bio4j.angulillos;

interface HasFromArity { Arity fromArity(); }
interface HasToArity   { Arity   toArity(); }
```

### Arities

```java
public enum Arity {

  One,        // Exactly one: X
  AtMostOne,  // One or none: Optional[X]
  AtLeastOne, // Non-empty list: NEList[X]
  Any;        // Usual list: List[X]

```

#### In-arities

```java
  public interface FromAtLeastOne extends HasFromArity { default Arity fromArity() { return Arity.One; } }
  public interface FromOne        extends HasFromArity { default Arity fromArity() { return Arity.AtMostOne; } }
  public interface FromAtMostOne  extends HasFromArity { default Arity fromArity() { return Arity.AtLeastOne; } }
  public interface FromAny        extends HasFromArity { default Arity fromArity() { return Arity.Any; } }
```

#### Out-arities

```java
  public interface ToAtLeastOne extends HasToArity { default Arity toArity() { return Arity.One; } }
  public interface ToOne        extends HasToArity { default Arity toArity() { return Arity.AtMostOne; } }
  public interface ToAtMostOne  extends HasToArity { default Arity toArity() { return Arity.AtLeastOne; } }
  public interface ToAny        extends HasToArity { default Arity toArity() { return Arity.Any; } }
}

```




[main/java/com/bio4j/angulillos/AnyEdgeType.java]: AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/AnyElementType.java]: AnyElementType.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: AnyVertexType.java.md
[main/java/com/bio4j/angulillos/Arity.java]: Arity.java.md
[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/Labeled.java]: Labeled.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/bio4j/angulillos/UntypedGraphSchema.java]: UntypedGraphSchema.java.md
[test/java/com/bio4j/angulillos/Twitter.java]: ../../../../../test/java/com/bio4j/angulillos/Twitter.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md