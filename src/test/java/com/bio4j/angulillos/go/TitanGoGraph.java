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