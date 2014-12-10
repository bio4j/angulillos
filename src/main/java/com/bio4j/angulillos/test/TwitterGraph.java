package com.bio4j.angulillos.test;

import com.bio4j.angulillos.*;

public abstract class TwitterGraph <
  I extends UntypedGraph<RV,RVT, RE,RET>,
  RV, RVT,
  RE, RET
>
implements
  TypedGraph<
    TwitterGraph<I, RV,RVT, RE,RET>,
    I, RV,RVT, RE,RET
  >
{

  protected I rawGraph = null;
  public TwitterGraph(I graph) { rawGraph = graph; }
  @Override public I raw() { return rawGraph; }

  // types
  public abstract UserType User();
  
  /*
  ### Vertices and their types
  */

  /*
  #### User
  */
  public final class UserType
  extends
    VertexType<
      TwitterGraph<I, RV, RVT, RE, RET>.User,
      TwitterGraph<I, RV, RVT, RE, RET>.UserType
    > 
  {
    public UserType(RVT raw) { super(raw); }
    @Override public final UserType value() { return graph().User(); }
    @Override public final User from(RV vertex) { return new User(vertex, this); }

    /* ##### User properties */
    public final name name = new name();
    public final class name extends Property<User,UserType,name,String> {
      public name() { super(UserType.this); }
      @Override public final Class<String> valueClass() { return String.class; }
    }
    public final text text = new text();
    public final class text extends Property<User,UserType,text,String> {
      public text() { super(UserType.this); }
      @Override public final Class<String> valueClass() { return String.class; }
    }
    public final url url = new url();
    public final class url extends Property<User,UserType,url,String> {
      public url() { super(UserType.this); }
      @Override public final Class<String> valueClass() { return String.class; }
    }
    public final age age = new age();
    public final class age extends Property<User,UserType,age,Integer> {
      public age() { super(UserType.this); }
      @Override public final Class<Integer> valueClass() { return Integer.class; }
    }
  }

  public final class User
  extends 
    Vertex<
      TwitterGraph<I, RV, RVT, RE, RET>.User,
      TwitterGraph<I, RV, RVT, RE, RET>.UserType
    > 
  {  
    public User(RV vertex, UserType type) { super(vertex, type); }
    @Override public final User self() { return this; }
  }


















  /*
  ### Abstract helper classes

  These classes bound the types to be from this graph
  */

  public abstract class ElementType<
    E extends TwitterGraph<I, RV, RVT, RE, RET>.Element<E, ET>,
    ET extends TwitterGraph<I, RV, RVT, RE, RET>.ElementType<E, ET>
  >
  implements
    TypedElement.Type<E,ET,TwitterGraph<I,RV,RVT,RE,RET>, I, RV, RVT, RE, RET> 
  {
    @Override public final TwitterGraph<I,RV,RVT,RE,RET> graph() { return TwitterGraph.this; }
  }

  public abstract class Element<
    E extends TwitterGraph<I, RV, RVT, RE, RET>.Element<E,ET>,
    ET extends TwitterGraph<I, RV, RVT, RE, RET>.ElementType<E,ET>
  >
  implements
    TypedElement<E,ET,TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> 
  {
    @Override public final TwitterGraph<I, RV, RVT, RE, RET> graph() { return TwitterGraph.this; }
  }

  public abstract class VertexType<
    V extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<V,VT>,
    VT extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<V,VT>
  >
  extends
    ElementType<V,VT>
  implements
    com.bio4j.angulillos.TypedVertex.Type<V,VT,TwitterGraph<I, RV, RVT, RE, RET>, I,RV,RVT,RE,RET>
  {
    private RVT raw;
    protected VertexType(RVT type) { this.raw = type; }
    @Override public final RVT raw() { return this.raw; }
  }

  public abstract class Vertex<
    V extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<V,VT>,
    VT extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<V,VT>
  >
  extends
    Element<V,VT>
  implements
    com.bio4j.angulillos.TypedVertex<V,VT,TwitterGraph<I, RV, RVT, RE, RET>, I,RV,RVT,RE,RET>
  {
    private RV raw;
    private VT type;
    protected Vertex(RV vertex, VT type) {
      this.raw = vertex;
      this.type = type;
    }
    @Override public final RV raw() { return this.raw; }
    @Override public final VT type() { return type; }
  }

  public abstract class EdgeType<
    S extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<S,ST>,
    ST extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<S,ST>,
    E extends TwitterGraph<I, RV, RVT, RE, RET>.Edge<S,ST,E,ET,T,TT>,
    ET extends TwitterGraph<I, RV, RVT, RE, RET>.EdgeType<S,ST,E,ET,T,TT>,
    T extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<T,TT>,
    TT extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<T,TT>
  >
  extends
    ElementType<E,ET>
  implements
    com.bio4j.angulillos.TypedEdge.Type<
      S, ST, TwitterGraph<I, RV, RVT, RE, RET>,
      E, ET, TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TwitterGraph<I, RV, RVT, RE, RET>
    >
  {
    private RET raw;
    private ST srcT;
    private TT tgtT;
    protected EdgeType(ST srcT, RET raw, TT tgtT) {
      this.raw = raw;
      this.srcT = srcT;
      this.tgtT = tgtT;
    }
    @Override public final ST sourceType() { return srcT; }
    @Override public final TT targetType() { return tgtT; }
    @Override public final RET raw() { return raw; }
  }

  public abstract class Edge<
    S extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<S,ST>,
    ST extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<S,ST>,
    E extends TwitterGraph<I, RV, RVT, RE, RET>.Edge<S,ST,E,ET,T,TT>,
    ET extends TwitterGraph<I, RV, RVT, RE, RET>.EdgeType<S,ST,E,ET,T,TT>,
    T extends TwitterGraph<I, RV, RVT, RE, RET>.Vertex<T,TT>,
    TT extends TwitterGraph<I, RV, RVT, RE, RET>.VertexType<T,TT>
  >
  extends
    Element<E,ET>
  implements
    com.bio4j.angulillos.TypedEdge<
      S, ST, TwitterGraph<I, RV, RVT, RE, RET>,
      E, ET, TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
      T, TT, TwitterGraph<I, RV, RVT, RE, RET>
    >
  {
    private RE edge;
    private ET type;
    protected Edge(RE edge, ET type) {
      this.edge = edge;
      this.type = type;
    }
    @Override public final RE raw() { return this.edge; }
    @Override public final ET type() { return type; } 
  }




  public abstract class Property<
    V extends TwitterGraph<I,RV, RVT, RE, RET>.Element<V,VT>,
    VT extends TwitterGraph<I,RV,RVT, RE, RET>.ElementType<V, VT>,
    P extends TwitterGraph<I,RV,RVT, RE, RET>.Property<V,VT,P,PV>,
    PV
  >
  implements
    com.bio4j.angulillos.Property<V,VT,P,PV,TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET> 
  {
    private VT type;
    protected Property(VT type) { this.type = type; }
    @Override public final VT elementType() { return type; }
  }



  // public abstract static class GoEdge<
  //     S extends TwitterVertex<S, ST, I, RV, RVT, RE, RET>,
  //     ST extends TwitterGraph<I, RV, RVT, RE, RET>.TwitterVertexType<S, ST>,
  //     E extends GoEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
  //     ET extends TwitterGraph<I, RV, RVT, RE, RET>.GoEdgeType<S, ST, E, ET, T, TT>,
  //     T extends TwitterVertex<T, TT, I, RV, RVT, RE, RET>,
  //     TT extends TwitterGraph<I, RV, RVT, RE, RET>.TwitterVertexType<T, TT>,
  //     I extends UntypedGraph<RV, RVT, RE, RET>, RV, RVT, RE, RET
  //     >
  //     implements
  //     TypedEdge<
  //         S, ST, TwitterGraph<I, RV, RVT, RE, RET>,
  //         E, ET, TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
  //         T, TT, TwitterGraph<I, RV, RVT, RE, RET>
  //         > {

  //   private RE edge;
  //   private ET type;

  //   protected GoEdge(RE edge, ET type) {

  //     this.edge = edge;
  //     this.type = type;
  //   }

  //   @Override
  //   public TwitterGraph<I, RV, RVT, RE, RET> graph() {
  //     return type().graph();
  //   }

  //   @Override
  //   public RE raw() {
  //     return this.edge;
  //   }

  //   @Override
  //   public ET type() {
  //     return type;
  //   }
  // }

  // abstract class GoEdgeType<
  //     S extends TwitterVertex<S, ST, I, RV, RVT, RE, RET>,
  //     ST extends TwitterGraph<I, RV, RVT, RE, RET>.TwitterVertexType<S, ST>,
  //     E extends GoEdge<S, ST, E, ET, T, TT, I, RV, RVT, RE, RET>,
  //     ET extends TwitterGraph<I, RV, RVT, RE, RET>.GoEdgeType<S, ST, E, ET, T, TT>,
  //     T extends TwitterVertex<T, TT, I, RV, RVT, RE, RET>,
  //     TT extends TwitterGraph<I, RV, RVT, RE, RET>.TwitterVertexType<T, TT>
  //     >
  //     implements
  //     TypedEdge.Type<
  //         S, ST, TwitterGraph<I, RV, RVT, RE, RET>,
  //         E, ET, TwitterGraph<I, RV, RVT, RE, RET>, I, RV, RVT, RE, RET,
  //         T, TT, TwitterGraph<I, RV, RVT, RE, RET>
  //         > {

  //   private RET raw;
  //   private ST srcT;
  //   private TT tgtT;

  //   protected GoEdgeType(ST srcT, RET raw, TT tgtT) {

  //     this.raw = raw;
  //     this.srcT = srcT;
  //     this.tgtT = tgtT;
  //   }

  //   @Override
  //   public final ST sourceType() {
  //     return srcT;
  //   }

  //   @Override
  //   public final TT targetType() {
  //     return tgtT;
  //   }

  //   @Override
  //   public final RET raw() {
  //     return raw;
  //   }

  //   @Override
  //   public final TwitterGraph<I, RV, RVT, RE, RET> graph() {
  //     return TwitterGraph.this;
  //   }
  // }

}




//   // case class Date(day: Integer, month: Integer, year: Integer)
//   case object time extends Property[String]

//   case object User  extends VertexType("user", name :~: age :~: ∅)
//   case object Tweet extends VertexType("tweet", text :~: ∅)

//   case object Posted  extends EdgeType(User, "posted", Tweet, time :~: url :~: ∅) with OneIn with ManyOut
//   case object Follows extends EdgeType(User, "follows", User, ∅) with ManyIn with ManyOut

//   case object UserNameIx extends CompositeIndex(User, name)
//   case object TweetTextIx extends CompositeIndex(Tweet, text)
//   case object PostedTimeIx extends CompositeIndex(Posted, time)

//   val schema = GraphSchema("twitter",
//     vertexTypes = U
// ser :~: Tweet :~: ∅,
//     edgeTypes = Posted :~: Follows :~: ∅,
//     indexes = UserNameIx :~: TweetTextIx :~: PostedTimeIx :~: ∅
//   )

