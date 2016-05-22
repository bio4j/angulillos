
```java
package com.bio4j.angulillos;

import java.util.Set;

public interface UntypedGraphSchema<SM> {
```

This method should take into account vertex label

```java
  SM createVertexType(SM schemaManager, AnyVertexType vertexType);
```

This method should take into account edge label, source/target types and to/from-arities

```java
  SM createEdgeType(SM schemaManager, AnyEdgeType edgeType);
```

This method should take into account property's element type and from-arity

```java
  SM createProperty(SM schemaManager, AnyProperty property);

  SM createUniqueVertexIndex(SM schemaManager, TypedVertexIndex.Unique<?,?,?,?,?,?> index);
  SM createNonUniqueVertexIndex(SM schemaManager, TypedVertexIndex.NonUnique<?,?,?,?,?,?> index);

  SM createUniqueEdgeIndex(SM schemaManager, TypedEdgeIndex.Unique<?,?,?,?,?,?> index);
  SM createNonUniqueEdgeIndex(SM schemaManager, TypedEdgeIndex.NonUnique<?,?,?,?,?,?> index);

  // This is like properties.foldLeft(schemaManager)(createProperty)
  default
  SM createProperties(SM schemaManager, Set<AnyProperty> properties) {
    // sm is a mutable accumulator passed around
    SM sm = schemaManager;

    for (AnyProperty p : properties) {
      sm = createProperty(sm, p);
    }

    return sm;
  }
```

Creates all graph's vertex/edge types with their properties

```java
  default
  SM createSchema(SM schemaManager, AnyTypedGraph g) {
    // sm is a mutable accumulator passed around
    SM sm = schemaManager;
```

Note that the type creation order is critical

```java
    for (AnyVertexType vt : g.vertexTypes()) {
      sm = createVertexType(sm, vt);
      sm = createProperties(sm, vt.properties());
    }

    for (AnyEdgeType et : g.edgeTypes()) {
      sm = createEdgeType(sm, et);
      sm = createProperties(sm, et.properties());
    }

    for (TypedVertexIndex.Unique<?,?,?,?,?,?> ui: g.uniqueVertexIndexes() ) {
      sm = createUniqueVertexIndex(sm, ui);
    }

    for (TypedVertexIndex.NonUnique<?,?,?,?,?,?> nui: g.nonUniqueVertexIndexes() ) {
      sm = createNonUniqueVertexIndex(sm, nui);
    }

    for (TypedEdgeIndex.Unique<?,?,?,?,?,?> ui: g.uniqueEdgeIndexes() ) {
      sm = createUniqueEdgeIndex(sm, ui);
    }

    for (TypedEdgeIndex.NonUnique<?,?,?,?,?,?> nui: g.nonUniqueEdgeIndexes() ) {
      sm = createNonUniqueEdgeIndex(sm, nui);
    }

    return sm;
  }

}

```




[test/java/com/bio4j/angulillos/Twitter.java]: ../../../../../test/java/com/bio4j/angulillos/Twitter.java.md
[test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java]: ../../../../../test/java/com/bio4j/angulillos/TwitterGraphTestSuite.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: TypedElement.java.md
[main/java/com/bio4j/angulillos/Arity.java]: Arity.java.md
[main/java/com/bio4j/angulillos/UntypedGraphSchema.java]: UntypedGraphSchema.java.md
[main/java/com/bio4j/angulillos/AnyElementType.java]: AnyElementType.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/Labeled.java]: Labeled.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: TypedVertex.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: TypedEdge.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/conversions.java]: conversions.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/QueryPredicate.java]: QueryPredicate.java.md
[main/java/com/bio4j/angulillos/AnyEdgeType.java]: AnyEdgeType.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: TypedGraph.java.md
[main/java/com/bio4j/angulillos/AnyProperty.java]: AnyProperty.java.md
[main/java/com/bio4j/angulillos/AnyVertexType.java]: AnyVertexType.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: Property.java.md