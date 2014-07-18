package com.ohnosequences.typedGraphs.test.go;

import com.ohnosequences.typedGraphs.*;

/*
  This interface contains an example graph types declaration.
*/
public abstract class GoGraph <
  // untyped graph
  I extends UntypedGraph<RV,RVT,RE,RET>, 
  // vertices
  RV,RVT,
  // edges
  RE,RET
>
implements
  TypedGraph<GoGraph<I,RV,RVT,RE,RET>,I,RV,RVT,RE,RET> 
{

  /* types */
  public abstract TermType Term();
  public abstract TermType_id Term_id();

  /* gene ontology Term type */
  public final class TermType
  implements
    TypedVertex.Type <
      Term<I,RV,RVT,RE,RET>,
      GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    public TermType(RVT raw) { this.raw = raw; }

    private RVT raw;

    public final RVT raw() { return raw; }

    public TermType value() { return graph().Term(); }

    public GoGraph<I,RV,RVT,RE,RET> graph() { return GoGraph.this; }

    public Term<I,RV,RVT,RE,RET> from(RV vertex) { return new Term<I,RV,RVT,RE,RET>(vertex, this); }

    /* #### term properties */

    // static final id id = new id();
    public final TermType_id id = GoGraph.this.Term_id();
  }
  // properties
  public final class TermType_id
  implements 
    Property <
      Term<I,RV,RVT,RE,RET>, 
      GoGraph<I,RV,RVT,RE,RET>.TermType, 
      TermType_id, String, 
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    public TermType_id() {}

    @Override
    public String name() { return "id"; }

    @Override
    public GoGraph<I,RV,RVT,RE,RET>.TermType elementType() { return GoGraph.this.Term(); }

    @Override
    public Class<String> valueClass() { return String.class; }
  }








  /* gene ontology term */
  public static final class Term <I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET>
  implements
    TypedVertex <
      Term<I,RV,RVT,RE,RET>,
      GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    public Term(RV vertex, GoGraph<I,RV,RVT,RE,RET>.TermType type) {

      this.vertex = vertex;
      this.type = type;
    }

    private RV vertex;
    private GoGraph<I,RV,RVT,RE,RET>.TermType type;

    public GoGraph<I,RV,RVT,RE,RET> graph() { return type().graph(); }
    public RV raw() { return this.vertex; }

    public GoGraph<I,RV,RVT,RE,RET>.TermType type() { return type; } 

    public Term<I,RV,RVT,RE,RET> self() { return this; }


    /*
      These two methods are completely optional, they just give you a way of retrieving a particular property at the level of this interface.
    */
    // public final String name() { return this.get(type().Name()); }
    public final String id() { return get( type().id ); }
  }

  
  
  /*
  The partOf relationship; it goes from terms to terms.
  */
  // interface PartOf <
  //   S extends Term<S,ST>, ST extends TermType<S,ST>,
  //   R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOfType<S,ST,R,RT,T,TT>,
  //   T extends Term<T,TT>, TT extends TermType<T,TT>
  // >
  //   extends Relationship<S,ST,R,RT,T,TT>
  // {}

  // interface PartOfType <
  //   S extends Term<S,ST>, ST extends TermType<S,ST>,
  //   R extends PartOf<S,ST,R,RT,T,TT>, RT extends PartOfType<S,ST,R,RT,T,TT>,
  //   T extends Term<T,TT>, TT extends TermType<T,TT>
  // >
  //   extends Relationship.Type.ManyToMany<S,ST,R,RT,T,TT>
  // {} 
}