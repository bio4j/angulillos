package com.ohnosequences.typedGraphs.titan;

import com.ohnosequences.typedGraphs.*;

import com.thinkaurelius.titan.core.attribute.Cmp;
import com.thinkaurelius.titan.core.*;

import java.util.Optional;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import com.tinkerpop.blueprints.Edge;

public interface TitanRelationshipIndex <
  S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
> 
extends 
  RelationshipIndex<S,ST,R,RT,T,TT,P,V>
{

  public static interface Unique <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  > 
  extends 
    RelationshipIndex.Unique<S,ST,R,RT,T,TT,P,V> 
  {

    /*
      get a relationship by providing a value of the indexed property.
    */
    public Optional<R> getRelationship(V byValue);
  }

  public static interface List <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  > 
  extends 
    RelationshipIndex.List<S,ST,R,RT,T,TT,P,V>
  {

    /*
      get a list of relationships by providing a value of the indexed property.
    */
    public Optional<java.util.List<R>> getRelationships(V byValue);
  }





  public abstract class Default <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  >
  implements
    TitanRelationshipIndex<S,ST,R,RT,T,TT,P,V>
  {

    public Default(TitanTypedGraph graph, P property) {

      this.graph = graph;
      this.property = property;
    }

    protected TitanTypedGraph graph;
    protected P property;

    @Override 
    public Optional<java.util.List<R>> query(com.tinkerpop.blueprints.Compare predicate, V value) {

      java.util.List<R> list = new LinkedList<>();

      Iterator<Edge> iterator = graph.rawGraph()
        .query().has(
          property.fullName(),
          predicate,
          value
        )
        .edges().iterator();
      
      Boolean someResult = iterator.hasNext();

      while ( iterator.hasNext() ) {

        list.add(property.elementType().fromTitanEdge( (TitanEdge) iterator.next() ));
      }

      if (someResult ) {

        return Optional.of(list);
      } else {

        return Optional.empty();
      }
    }
  }


  /* Default implementation of a relationship unique index */
  public final class DefaultUnique <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  > 
  extends
    Default<S,ST,R,RT,T,TT,P,V>
  implements 
    Unique<S,ST,R,RT,T,TT,P,V> 
  {

    public DefaultUnique(TitanTypedGraph graph, P property) {

      super(graph,property);
    }

    @Override
    public Optional<R> getRelationship(V byValue) {

      // crappy Java generics force the cast here
      Iterator<Edge> rawResult = graph.rawGraph()
        .query().has(
          property.fullName(),
          Cmp.EQUAL, 
          byValue
        )
        .edges().iterator();

      if (rawResult.hasNext() ) {

        return Optional.of( property.elementType().fromTitanEdge( (TitanEdge) rawResult.next() ) );

      } else {

        return Optional.empty();
      }
    }

  }

  final class DefaultList <
    S extends TitanNode<S,ST>,
    ST extends TitanNode.Type<S,ST>,
    R extends TitanRelationship<S,ST,R,RT,T,TT>, 
    RT extends TitanRelationship.Type<S,ST,R,RT,T,TT>,
    T extends TitanNode<T,TT>,
    TT extends TitanNode.Type<T,TT>,
    P extends TitanProperty<R,RT,P,V>, V
  >
  extends
    Default<S,ST,R,RT,T,TT,P,V> 
  implements 
    List<S,ST,R,RT,T,TT,P,V> {

    public DefaultList(TitanTypedGraph graph, P property) {

      super(graph,property);
    }

    @Override
    public Optional<java.util.List<R>> getRelationships(V byValue) {

      java.util.List<R> list = new LinkedList<>();

      Iterator<Edge> iterator = graph.rawGraph()
        .query().has(
          property.fullName(),
          Cmp.EQUAL,
          byValue
        )
        .edges().iterator();
      
      Boolean someResult = iterator.hasNext();

      while ( iterator.hasNext() ) {

        list.add(property.elementType().fromTitanEdge( (TitanEdge) iterator.next() ));
      }

      if ( someResult ) {

        return Optional.of( list );
      
      } else {

        return Optional.empty();
      }
      
    }
  }

}