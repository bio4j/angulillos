package com.ohnosequences.typedGraphs.test;

import com.ohnosequences.typedGraphs.*;

public interface SchemaDef {

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
}