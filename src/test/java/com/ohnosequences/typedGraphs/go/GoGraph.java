package com.ohnosequences.typedGraphs.test.go;

import com.ohnosequences.typedGraphs.*;

/*
  # Example Graph model

  This interface contains an example graph types declaration. The particular graph schema is a simplified version of the Gene Ontology data types.
*/
public abstract class GoGraph<
  // untyped graph
  I extends UntypedGraph<RV,RVT,RE,RET>, 
  // vertices
  RV,RVT,
  // edges
  RE,RET
>
implements
  TypedGraph<
    GoGraph<I,RV,RVT,RE,RET>,
    I,RV,RVT,RE,RET
  > 
{

  /* 
    ### abstract type witnesses 
    

  */
  public abstract TermType Term();
  public abstract Term_id Term_id();
  public abstract PartOfType PartOf();

  /* 
    ### the `Term` type
    
    This inner class represents ...
  */
  public final class TermType
  implements
    TypedVertex.Type <
      // vertex
      Term<I,RV,RVT,RE,RET>,
      // vertex type
      GoGraph<I,RV,RVT,RE,RET>.TermType,
      // graph
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    public TermType(RVT raw) { this.raw = raw; }

    private RVT raw;

    @Override
    public final RVT raw() { return raw; }

    @Override
    public final TermType value() { return graph().Term(); }

    @Override
    public final GoGraph<I,RV,RVT,RE,RET> graph() { return GoGraph.this; }

    @Override
    public final Term<I,RV,RVT,RE,RET> from(RV vertex) { return new Term<I,RV,RVT,RE,RET>(vertex, this); }

    public final Term_id id = GoGraph.this.Term_id();
  }
  
  /* 
    #### `Term` properties

    ...   
  */
  public final class Term_id
  implements 
    Property <
      Term<I,RV,RVT,RE,RET>, 
      GoGraph<I,RV,RVT,RE,RET>.TermType, 
      Term_id, String, 
      GoGraph<I,RV,RVT,RE,RET>,
      I,RV,RVT,RE,RET
    >
  {

    public Term_id() {}

    @Override
    public final String name() { return "id"; }

    @Override
    public final GoGraph<I,RV,RVT,RE,RET>.TermType elementType() { return GoGraph.this.Term(); }

    @Override
    public final Class<String> valueClass() { return String.class; }
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

    private RV vertex;
    private GoGraph<I,RV,RVT,RE,RET>.TermType type;

    public Term(RV vertex, GoGraph<I,RV,RVT,RE,RET>.TermType type) {

      this.vertex = vertex;
      this.type = type;
    }

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
  public static final class PartOf<I extends UntypedGraph<RV,RVT,RE,RET>,RV,RVT,RE,RET>
  implements
    TypedEdge<
      Term<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>,

      PartOf<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.PartOfType,
      GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET, 

      Term<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>
    >
  {


    private RE edge;
    private GoGraph<I,RV,RVT,RE,RET>.PartOfType type;
    
    public PartOf(RE edge, GoGraph<I,RV,RVT,RE,RET>.PartOfType type) {

      this.edge = edge;
      this.type = type;
    }

    public GoGraph<I,RV,RVT,RE,RET> graph() { return type().graph(); }
    public RE raw() { return this.edge; }

    public GoGraph<I,RV,RVT,RE,RET>.PartOfType type() { return type; } 

    public PartOf<I,RV,RVT,RE,RET> self() { return this; }
  }

  public final class PartOfType
  implements
    TypedEdge.Type<
      Term<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>,

      PartOf<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.PartOfType,
      GoGraph<I,RV,RVT,RE,RET>, I,RV,RVT,RE,RET, 

      Term<I,RV,RVT,RE,RET>, GoGraph<I,RV,RVT,RE,RET>.TermType,
      GoGraph<I,RV,RVT,RE,RET>
    >
  {

    private RET raw;

    public PartOfType(RET raw) { this.raw = raw; }


    @Override
    public final GoGraph<I,RV,RVT,RE,RET>.TermType targetType() { return graph().Term(); }

    @Override
    public final GoGraph<I,RV,RVT,RE,RET>.TermType sourceType() { return graph().Term(); }


    @Override
    public final RET raw() { return raw; }

    @Override
    public final PartOfType value() { return graph().PartOf(); }

    @Override
    public final GoGraph<I,RV,RVT,RE,RET> graph() { return GoGraph.this; }

    @Override
    public final PartOf<I,RV,RVT,RE,RET> from(RE edge) { return new PartOf<I,RV,RVT,RE,RET>(edge, this); }
  }
}