
```java
package com.bio4j.angulillos.test.go;

import com.bio4j.angulillos.*;
import com.bio4j.angulillos.titan.*;
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
          + angulillos
            + go
              + [TitanGoGraph.java][test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java]
              + [GoGraph.java][test/java/com/ohnosequences/angulillos/go/GoGraph.java]
              + [TitanGoGraphImpl.java][test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java]
              + [TestTypeNames.java][test/java/com/ohnosequences/angulillos/go/TestTypeNames.java]
    + scala
  + main
    + java
      + com
        + ohnosequences
          + angulillos
            + [TypedGraph.java][main/java/com/ohnosequences/angulillos/TypedGraph.java]
            + [TypedVertexIndex.java][main/java/com/ohnosequences/angulillos/TypedVertexIndex.java]
            + [UntypedGraph.java][main/java/com/ohnosequences/angulillos/UntypedGraph.java]
            + [TypedEdge.java][main/java/com/ohnosequences/angulillos/TypedEdge.java]
            + [TypedElementIndex.java][main/java/com/ohnosequences/angulillos/TypedElementIndex.java]
            + [Property.java][main/java/com/ohnosequences/angulillos/Property.java]
            + [TypedVertexQuery.java][main/java/com/ohnosequences/angulillos/TypedVertexQuery.java]
            + [TypedElement.java][main/java/com/ohnosequences/angulillos/TypedElement.java]
            + [TypedEdgeIndex.java][main/java/com/ohnosequences/angulillos/TypedEdgeIndex.java]
            + titan
              + [TitanTypedEdgeIndex.java][main/java/com/ohnosequences/angulillos/titan/TitanTypedEdgeIndex.java]
              + [TitanTypedVertexIndex.java][main/java/com/ohnosequences/angulillos/titan/TitanTypedVertexIndex.java]
              + [TitanUntypedGraph.java][main/java/com/ohnosequences/angulillos/titan/TitanUntypedGraph.java]
            + [TypedVertex.java][main/java/com/ohnosequences/angulillos/TypedVertex.java]

[test/java/com/ohnosequences/angulillos/go/TitanGoGraph.java]: TitanGoGraph.java.md
[test/java/com/ohnosequences/angulillos/go/GoGraph.java]: GoGraph.java.md
[test/java/com/ohnosequences/angulillos/go/TitanGoGraphImpl.java]: TitanGoGraphImpl.java.md
[test/java/com/ohnosequences/angulillos/go/TestTypeNames.java]: TestTypeNames.java.md
[main/java/com/ohnosequences/angulillos/TypedGraph.java]: ../../../../../../main/java/com/ohnosequences/angulillos/TypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedVertexIndex.java]: ../../../../../../main/java/com/ohnosequences/angulillos/TypedVertexIndex.java.md
[main/java/com/ohnosequences/angulillos/UntypedGraph.java]: ../../../../../../main/java/com/ohnosequences/angulillos/UntypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedEdge.java]: ../../../../../../main/java/com/ohnosequences/angulillos/TypedEdge.java.md
[main/java/com/ohnosequences/angulillos/TypedElementIndex.java]: ../../../../../../main/java/com/ohnosequences/angulillos/TypedElementIndex.java.md
[main/java/com/ohnosequences/angulillos/Property.java]: ../../../../../../main/java/com/ohnosequences/angulillos/Property.java.md
[main/java/com/ohnosequences/angulillos/TypedVertexQuery.java]: ../../../../../../main/java/com/ohnosequences/angulillos/TypedVertexQuery.java.md
[main/java/com/ohnosequences/angulillos/TypedElement.java]: ../../../../../../main/java/com/ohnosequences/angulillos/TypedElement.java.md
[main/java/com/ohnosequences/angulillos/TypedEdgeIndex.java]: ../../../../../../main/java/com/ohnosequences/angulillos/TypedEdgeIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanTypedEdgeIndex.java]: ../../../../../../main/java/com/ohnosequences/angulillos/titan/TitanTypedEdgeIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanTypedVertexIndex.java]: ../../../../../../main/java/com/ohnosequences/angulillos/titan/TitanTypedVertexIndex.java.md
[main/java/com/ohnosequences/angulillos/titan/TitanUntypedGraph.java]: ../../../../../../main/java/com/ohnosequences/angulillos/titan/TitanUntypedGraph.java.md
[main/java/com/ohnosequences/angulillos/TypedVertex.java]: ../../../../../../main/java/com/ohnosequences/angulillos/TypedVertex.java.md