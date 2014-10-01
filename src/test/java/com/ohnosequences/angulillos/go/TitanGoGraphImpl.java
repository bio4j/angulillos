// package com.ohnosequences.angulillos.test.go;

// import com.ohnosequences.angulillos.*;
// import com.ohnosequences.angulillos.titan.*;
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