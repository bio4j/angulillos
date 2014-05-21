package com.ohnosequences.typedGraphs.test.go;

import com.ohnosequences.typedGraphs.*;

/*
  This interface contains an example graph types declaration.
*/
public interface GoGraph {

  /*
    A gene ontology term
  */
  interface Term <
    N extends Term<N,NT>, NT extends TermType<N,NT>
  >
    extends Node<N,NT>
  {
    public default String name() { return this.get(type().Name()); }
    public default String id() { return this.get(type().Id()); }
  }
  /*
    The type of a Term. Nested here we can find the properties of this type.
  */
  interface TermType <
      N extends Term<N,NT>, NT extends TermType<N,NT>
    > 
      extends Node.Type<N,NT>
    {

      public <P extends id<N,NT,P>> P Id();
      // properties
      interface id <
        N extends Term<N,NT>,
        NT extends TermType<N,NT>,
        P extends id<N,NT,P>
      > 
        extends Property<N,NT,P,String> 
      {
        @Override public default String name() { return "id"; } 
        @Override public default Class<String> valueClass() { return String.class; }
      }
      public <P extends name<N,NT,P>> P Name();
      interface name <
        N extends Term<N,NT>,
        NT extends TermType<N,NT>,
        P extends name<N,NT,P>
      > 
        extends Property<N,NT,P,String> 
      {
        @Override public default String name() { return "name"; } 
        @Override public default Class<String> valueClass() { return String.class; }
      }
    }

  
  /*
  The partOf relationship
  */
  interface PartOf <
    S extends Term<S,ST>, ST extends TermType<S,ST>,
    R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOfType<S,ST,R,RT,T,TT>,
    T extends Term<T,TT>, TT extends TermType<T,TT>
  >
    extends Relationship<S,ST,R,RT,T,TT>
  {}

  interface PartOfType <
      S extends Term<S,ST>, ST extends TermType<S,ST>,
      R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOfType<S,ST,R,RT,T,TT>,
      T extends Term<T,TT>, TT extends TermType<T,TT>
    >
      extends Relationship.Type<S,ST,R,RT,T,TT>
    {} 
}