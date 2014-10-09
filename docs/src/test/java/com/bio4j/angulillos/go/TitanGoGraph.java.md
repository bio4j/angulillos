
```java
// package com.bio4j.angulillos.test.go;

// import com.bio4j.angulillos.*;
// import com.bio4j.angulillos.titan.*;
// import com.thinkaurelius.titan.core.*;

// /*
//   Implementing the types with Titan
// */
// public final class TitanGoGraph
// extends 
//   GoGraph<TitanUntypedGraph,TitanVertex,TitanKey,TitanEdge,TitanLabel>
// implements 
//   TitanUntypedGraph
// {

//   private TitanGraph raw;
//   @Override
//   public TitanGraph titanGraph() { return this.raw; }

//   public final TitanUntypedGraph raw() { return this; }

//   private TitanKey titanTerm;
//   private TermType Term; 
//   private TitanGoGraph.Term_id Term_id = new Term_id();

//   private TitanLabel titanPartOf;
//   private PartOfType PartOf;

//   public TitanGoGraph(TitanGraph raw) {

//     // first init the raw graph
//     this.raw = raw;
//     // then call init types
//     initTypes();
//   }

//   private void initTypes() {

//     // init Term type
//     titanTerm = titanKeyForVertexType(Term_id);
//     Term = this.new TermType(titanTerm);

//     // init PartOf type
//     // the label needs the rel type needs the label...
//     // TODO is there a better alternative?
//     titanPartOf = raw().titanLabelForEdgeType(this.new PartOfType(null)); 
//     PartOf = this.new PartOfType(titanPartOf);

//   }

//   // types
//   @Override
//   public TitanGoGraph.Term_id Term_id() { return this.Term_id; }
//   @Override
//   public TitanGoGraph.TermType Term() { return this.Term; }

//   @Override
//   public TitanGoGraph.PartOfType PartOf() { return this.PartOf; }
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