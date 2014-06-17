package com.ohnosequences.typedGraphs.test.go;

import com.ohnosequences.typedGraphs.*;
import com.ohnosequences.typedGraphs.titan.*;
import com.thinkaurelius.titan.core.*;

/*
  Implementing the types with Titan
*/
public abstract class TitanGoGraph 
implements 
  TitanTypedGraph,
  GoGraph 
{

  protected TitanGraph rawGraph;
  TitanGoGraph(TitanGraph rawGraph) { this.rawGraph = rawGraph; }
  @Override public TitanGraph rawGraph() { return rawGraph; }

  /*
  A gene ontology term implementation. It is a Titan node and implements the `Term` interface. It is defined as an inner class here just for convenience in this small example; it could be anywhere else. it'd just need a reference to this graph for retrieving its type. 
  */
  public final class TitanTerm
  extends
    TitanNode<TitanTerm, TitanTermType>
  implements
    Term<TitanTerm, TitanTermType>
  {
    TitanTerm(TitanVertex vertex) { super(vertex); }
    /*
      Note here how we need a reference to the enclosing graph, which contains the term type value.
    */
    @Override public TitanTermType type() { return TitanGoGraph.this.termT; }
  }

  /*
    The type of a TitanTerm. This an inner class of the graph. The first key here represents the type of the node, while the rest are for properties of this term: `id` and `name` in this case.
  */
  TitanKey termTkey;
  TitanKey termIdKey;
  TitanKey termNameKey;
  TitanTermType termT = new TitanTermType();
  public final class TitanTermType
  implements
    TitanNode.Type<TitanTerm,TitanTermType>,
    TermType<TitanTerm,TitanTermType>
  {
    @Override public TitanKey titanKey() { return TitanGoGraph.this.termTkey; }
    @Override public TitanTermType value() { return TitanGoGraph.this.termT; }
    @Override public TitanTerm fromTitanVertex(TitanVertex vertex) { return new TitanTerm(vertex); }
    // properties
    public id id = new id();
    // no need to worry about the unchecked warning
    @Override public <P extends GoGraph.id<TitanTerm,TitanTermType,P>> P Id() { return (P) id; }
    public final class id 
    implements
      com.ohnosequences.typedGraphs.titan.TitanProperty<TitanTerm,TitanTermType,id,String>,
      GoGraph.id<TitanTerm,TitanTermType,id>
    {
      @Override public TitanTermType elementType() { return TitanTermType.this; }
      @Override public TitanKey titanKey() { return TitanGoGraph.this.termIdKey; }
    }
    name name = new name();
    @Override public name Name() { return name; }
    public final class name 
    implements 
      com.ohnosequences.typedGraphs.titan.TitanProperty<TitanTerm,TitanTermType,name,String>,
      TermType.name<TitanTerm,TitanTermType,name>
    {
      @Override public TitanTermType elementType() { return TitanTermType.this; }
      @Override public TitanKey titanKey() { return TitanGoGraph.this.termNameKey; }
    }
  }

  // rels

  // the partOf rel
  public final class TitanPartOf
  extends
    TitanRelationship<TitanTerm,TitanTermType, TitanPartOf,TitanPartOfType, TitanTerm,TitanTermType>
  implements
    PartOf<TitanTerm,TitanTermType, TitanPartOf,TitanPartOfType, TitanTerm,TitanTermType>
  {
    TitanPartOf(TitanEdge edge) { super(edge); }
    /*
      Note here how we need a reference to the enclosing graph, which contains the term type value.
    */
    @Override public TitanPartOfType type() { return TitanGoGraph.this.partOfT; }
  }

  TitanLabel partOfLabel;
  TitanPartOfType partOfT = new TitanPartOfType();
  public final class TitanPartOfType
  implements
    TitanRelationship.Type<TitanTerm,TitanTermType, TitanPartOf,TitanPartOfType, TitanTerm,TitanTermType>,
    PartOfType<TitanTerm,TitanTermType, TitanPartOf,TitanPartOfType, TitanTerm,TitanTermType>
  {
    @Override public TitanLabel label() { return TitanGoGraph.this.partOfLabel; }
    @Override public TitanPartOfType value() { return TitanGoGraph.this.partOfT; }
    @Override public TitanTermType sourceType() { return TitanGoGraph.this.termT; }
    @Override public TitanTermType targetType() { return TitanGoGraph.this.termT; }
    @Override public TitanPartOf fromTitanEdge(TitanEdge edge) { return new TitanPartOf(edge); }
  }


}