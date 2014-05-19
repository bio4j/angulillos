package com.ohnosequences.typedGraphs.titan.test;

import com.ohnosequences.typedGraphs.*;
import com.ohnosequences.typedGraphs.titan.*;

import com.thinkaurelius.titan.core.TitanLabel;
import com.thinkaurelius.titan.core.TitanKey;
import com.thinkaurelius.titan.core.TitanVertex;
import com.thinkaurelius.titan.core.TitanEdge;

import com.ohnosequences.typedGraphs.test.SchemaDef.*;

public interface TitanSchemaDef {

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