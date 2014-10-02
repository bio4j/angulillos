
```java
// package com.ohnosequences.typedGraphs.test.go;

// import com.ohnosequences.typedGraphs.*;
// import com.ohnosequences.typedGraphs.titan.*;
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
        + ohnosequences
          + typedGraphs
            + go
              + [TitanGoGraph.java][test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]
    + scala
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [TypedGraph.java][main/java/com/ohnosequences/typedGraphs/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/ohnosequences/typedGraphs/TypedEdge.java]
            + [TypedElementIndex.java][main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/typedGraphs/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]
            + titan
              + [TitanTypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]
            + [TypedVertex.java][main/java/com/ohnosequences/typedGraphs/TypedVertex.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdge.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Property.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElement.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertex.java.md