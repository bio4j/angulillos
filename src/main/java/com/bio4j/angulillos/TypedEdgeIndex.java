package com.bio4j.angulillos;

import java.util.Optional;
import java.util.stream.Stream;

interface TypedEdgeIndex <
  // src
  S extends TypedVertex<S,ST,SG,I,RV,RE>,
  ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
  SG extends TypedGraph<SG,I,RV,RE>,
  // rel
  R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RE,T,TT,TG>,
  RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RE,T,TT,TG>,
  // property
  P extends Property<R,RT,P,V,RG,I,RV,RE>,
  V,
  RG extends TypedGraph<RG,I,RV,RE>,
  I extends UntypedGraph<RV,RE>, RV,RE,
  // tgt
  T extends TypedVertex<T,TT,TG,I,RV,RE>,
  TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
  TG extends TypedGraph<TG,I,RV,RE>
>
extends
  TypedElementIndex<R,RT,P,V,RG,I,RV,RE>
{

  default RT edgeType() { return elementType(); }

  interface Unique <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RE,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RE,T,TT,TG>,
    // property
    P extends Property<R,RT,P,V,RG,I,RV,RE>,
    V,
    RG extends TypedGraph<RG,I,RV,RE>,
    I extends UntypedGraph<RV,RE>, RV,RE,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  extends
    TypedEdgeIndex<S,ST,SG, R,RT, P,V, RG,I,RV,RE, T,TT,TG>,
    TypedElementIndex.Unique<R,RT, P,V, RG,I,RV,RE>
  {

    /* get a node by providing a value of the indexed property. */
    default Optional<R> getEdge(V byValue) { return getElement(byValue); }
  }

  interface List <
    // src
    S extends TypedVertex<S,ST,SG,I,RV,RE>,
    ST extends TypedVertex.Type<S,ST,SG,I,RV,RE>,
    SG extends TypedGraph<SG,I,RV,RE>,
    // rel
    R extends TypedEdge<S,ST,SG,R,RT,RG,I,RV,RE,T,TT,TG>,
    RT extends TypedEdge.Type<S,ST,SG,R,RT,RG,I,RV,RE,T,TT,TG>,
    // property
    P extends Property<R,RT,P,V,RG,I,RV,RE>,
    V,
    RG extends TypedGraph<RG,I,RV,RE>,
    I extends UntypedGraph<RV,RE>, RV,RE,
    // tgt
    T extends TypedVertex<T,TT,TG,I,RV,RE>,
    TT extends TypedVertex.Type<T,TT,TG,I,RV,RE>,
    TG extends TypedGraph<TG,I,RV,RE>
  >
  extends
    TypedEdgeIndex<S,ST,SG, R,RT,P,V, RG,I,RV,RE, T,TT,TG>,
    TypedElementIndex.List<R,RT, P,V, RG,I,RV,RE>
  {

    /* get a list of nodes by providing a value of the indexed property. */
    default Stream<R> getEdges(V byValue) { return getElements(byValue); }
  }


}
