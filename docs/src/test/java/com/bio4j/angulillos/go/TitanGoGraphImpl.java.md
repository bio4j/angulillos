
```java
// package com.bio4j.angulillos.test.go;

// import com.bio4j.angulillos.*;
// import com.bio4j.angulillos.titan.*;
// import com.thinkaurelius.titan.core.*;

// public final class TitanGoGraphImpl extends TitanGoGraph {

//   TitanGoGraphImpl(TitanGraph graph) { super(graph); initTypes(); }

//   private void initTypes() {


//     // Term keys
//     termTkey = titanKeyForNodeType(termT.id);
//     termIdKey = termTkey;
//     termNameKey = titanKeyForNodeProperty(termT.name).make();
//     // if you want to index the name just add the corresponding titan stuff before make, like
//     // termNameKey = titanKeyForNodeProperty(termT.name).indexed(com.tinkerpop.blueprints.Edge.class).make();

//     // partOf stuff
//     partOfLabel = titanLabelForRelationshipType(partOfT).make();
//   }
// }
```


------

### Index

+ src
  + test
    + java
      + com
        + bio4j
          + angulillos
            + go
              + [TitanGoGraph.java][test/java/com/bio4j/angulillos/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/bio4j/angulillos/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/bio4j/angulillos/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/bio4j/angulillos/go/TestTypeNames.java]
    + scala
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

[test/java/com/bio4j/angulillos/go/TitanGoGraph.java]: TitanGoGraph.java.md
[test/java/com/bio4j/angulillos/go/GoGraph.java]: GoGraph.java.md
[test/java/com/bio4j/angulillos/go/TitanGoGraphImpl.java]: TitanGoGraphImpl.java.md
[test/java/com/bio4j/angulillos/go/TestTypeNames.java]: TestTypeNames.java.md
[main/java/com/bio4j/angulillos/TypedGraph.java]: ../../../../../../main/java/com/bio4j/angulillos/TypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedVertexIndex.java]: ../../../../../../main/java/com/bio4j/angulillos/TypedVertexIndex.java.md
[main/java/com/bio4j/angulillos/UntypedGraph.java]: ../../../../../../main/java/com/bio4j/angulillos/UntypedGraph.java.md
[main/java/com/bio4j/angulillos/TypedEdge.java]: ../../../../../../main/java/com/bio4j/angulillos/TypedEdge.java.md
[main/java/com/bio4j/angulillos/conversions.java]: ../../../../../../main/java/com/bio4j/angulillos/conversions.java.md
[main/java/com/bio4j/angulillos/TypedElementIndex.java]: ../../../../../../main/java/com/bio4j/angulillos/TypedElementIndex.java.md
[main/java/com/bio4j/angulillos/Property.java]: ../../../../../../main/java/com/bio4j/angulillos/Property.java.md
[main/java/com/bio4j/angulillos/TypedVertexQuery.java]: ../../../../../../main/java/com/bio4j/angulillos/TypedVertexQuery.java.md
[main/java/com/bio4j/angulillos/TypedElement.java]: ../../../../../../main/java/com/bio4j/angulillos/TypedElement.java.md
[main/java/com/bio4j/angulillos/TypedEdgeIndex.java]: ../../../../../../main/java/com/bio4j/angulillos/TypedEdgeIndex.java.md
[main/java/com/bio4j/angulillos/TypedVertex.java]: ../../../../../../main/java/com/bio4j/angulillos/TypedVertex.java.md