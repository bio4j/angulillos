
```java
package com.ohnosequences.typedGraphs.test.go;

import com.ohnosequences.typedGraphs.*;
import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;
```


Implementing the types with Titan


```java
public final class TitanGoGraph
extends 
  GoGraph<TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
implements 
  TitanUntypedGraph
{

  private TitanGraph raw;
  @Override
  public TitanGraph titanGraph() { return this.raw; }

  public final TitanUntypedGraph raw() { return this; }

  private TitanKey titanTerm;
  private TermType Term; 
  private TitanGoGraph.Term_id Term_id = new Term_id();

  private TitanLabel titanPartOf;
  private PartOfType PartOf;

  public TitanGoGraph(TitanGraph raw) {

    // first init the raw graph
    this.raw = raw;
    // then call init types
    initTypes();
  }

  private void initTypes() {

    // init Term type
    titanTerm = titanKeyForVertexType(Term_id);
    Term = this.new TermType(titanTerm);

    // init PartOf type
    // the label needs the rel type needs the label...
    // TODO is there a better alternative?
    titanPartOf = raw().titanLabelForEdgeType(this.new PartOfType(null)); 
    PartOf = this.new PartOfType(titanPartOf);

  }

  // types
  @Override
  public TitanGoGraph.Term_id Term_id() { return this.Term_id; }
  @Override
  public TitanGoGraph.TermType Term() { return this.Term; }

  @Override
  public TitanGoGraph.PartOfType PartOf() { return this.PartOf; }
}
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
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [TypedElementIndex.java][main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/typedGraphs/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]
            + titan
              + [TitanTypedVertex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertex.java]
              + [TitanTypedEdge.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdge.java]
              + [TitanTypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]
              + [TitanTypedElement.java][main/java/com/ohnosequences/typedGraphs/titan/TitanTypedElement.java]
              + [TitanRelationshipIndex.java][main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]
              + [TitanProperty.java][main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]
            + [TypedVertex.java][main/java/com/ohnosequences/typedGraphs/TypedVertex.java]

[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraph.java]: TitanGoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/GoGraph.java]: GoGraph.java.md
[test/java/com/ohnosequences/typedGraphs/go/TitanGoGraphImpl.java]: TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/typedGraphs/go/TestTypeNames.java]: TestTypeNames.java.md
[main/java/com/ohnosequences/typedGraphs/TypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/UntypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/UntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdge.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedElementIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/Property.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertexQuery.java.md
[main/java/com/ohnosequences/typedGraphs/TypedElement.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedEdgeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdge.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedEdge.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanTypedElement.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanTypedElement.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanRelationshipIndex.java.md
[main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/titan/TitanProperty.java.md
[main/java/com/ohnosequences/typedGraphs/TypedVertex.java]: ../../../../../../main/java/com/ohnosequences/typedGraphs/TypedVertex.java.md