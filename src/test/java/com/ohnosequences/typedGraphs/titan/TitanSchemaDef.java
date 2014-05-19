package com.ohnosequences.typedGraphs.titan.test;

import com.ohnosequences.typedGraphs.*;
import com.ohnosequences.typedGraphs.titan.*;

import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;

public interface TitanSchemaDef {


  // define a term
  interface Term <
    N extends Term<N,NT>, NT extends Term.Type<N,NT>
  >
    extends Node<N,NT>
  {
    interface Type <
      N extends Term<N,NT>, NT extends Term.Type<N,NT>
    > 
      extends Node.Type<N,NT>
    {}

    // properties

    interface id <
      N extends Term<N,NT>,
      NT extends Term.Type<N,NT>,
      P extends id<N,NT,P>
    > 
      extends Property<N,NT,P,String> 
    {
      @Override public default String name() { return "id"; } 
      @Override public default Class<String> valueClass() { return String.class; }
    }
  } 

  // a term implementation
  abstract class TitanTerm extends TitanNode <
    TitanTerm, TitanTerm.Type
  > 
    implements Term<TitanTerm, TitanTerm.Type> 
  {

    public TitanTerm(TitanVertex vertex) { super(vertex); }

    abstract class Type implements TitanNode.Type<TitanTerm, TitanTerm.Type>, Term.Type<TitanTerm, TitanTerm.Type> {

      // implement this through the graph
      @Override public TitanKey titanKey() { return null; }
    }

    abstract class id implements 
      TitanProperty<TitanTerm,TitanTerm.Type, id, String>,
      Term.id<TitanTerm, TitanTerm.Type, id>
    {}

  }

  interface PartOf <
    S extends Term<S,ST>, ST extends Term.Type<S,ST>,
    R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOf.Type<S,ST,R,RT,T,TT>,
    T extends Term<T,TT>, TT extends Term.Type<T,TT>
  >
    extends Relationship<S,ST,R,RT,T,TT>
  {

    interface Type <
      S extends Term<S,ST>, ST extends Term.Type<S,ST>,
      R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOf.Type<S,ST,R,RT,T,TT>,
      T extends Term<T,TT>, TT extends Term.Type<T,TT>
    >
      extends Relationship.Type<S,ST,R,RT,T,TT>
    {}
  }

  abstract class TitanPartOf extends TitanRelationship <
    TitanTerm, TitanTerm.Type,
    TitanPartOf, TitanPartOf.Type,
    TitanTerm, TitanTerm.Type    
  > 
    implements PartOf <
      TitanTerm, TitanTerm.Type,
      TitanPartOf, TitanPartOf.Type,
      TitanTerm, TitanTerm.Type
    >
  {

    public TitanPartOf(TitanEdge edge) { super(edge); }

    abstract class Type implements TitanRelationship.Type < 
        TitanTerm, TitanTerm.Type,
        TitanPartOf, TitanPartOf.Type,
        TitanTerm, TitanTerm.Type    
      >,
      PartOf.Type <
        TitanTerm, TitanTerm.Type,
        TitanPartOf, TitanPartOf.Type,
        TitanTerm, TitanTerm.Type    
      >
      {

        // TODO implement through a graph where you define this
        @Override public TitanLabel label() { return null; } 
      }
  }
}