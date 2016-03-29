package com.bio4j.angulillos;

import java.util.stream.Stream;
import java.util.Optional;
import java.util.Collection;

public interface TypedIndex<
  FT extends ElementType<FT,G,RF>,
  F  extends ElementType<FT,G,RF>.Element,
  G  extends TypedGraph<G,?,?>,
  P  extends Property<FT,X>,
  X, RF
> {

  /* Index name */
  String _label();

  /* The graph */
  G graph();

  /* The indexed property. */
  P property();

  default FT elementType() { return property().elementType; }


  /* Query this index by comparing the property value with the given one */
  Stream<F> query(QueryPredicate.Compare predicate, X value);

  default Stream<F> query(X value) { return query(QueryPredicate.Compare.EQUAL, value); }


  /* Query this index by checking whether the property value is in/not in the given collection */
  Stream<F> query(QueryPredicate.Contain predicate, Collection<X> values);

  default Stream<F> query(Collection<X> values) { return query(QueryPredicate.Contain.IN, values); }

}

// NOTE: no much point in these two interfaces, because they don't introduce anything new and
//   in the TypedGraph there are classes doing the same types refinement

// interface VertexIndex<
//   VT extends VertexType<VT, G,RV,RE>,
//   G  extends TypedGraph<G,RV,RE>,
//   P  extends Property<VT,X>,
//   X, RV,RE
// > extends TypedIndex<VT, VertexType<VT, G,RV,RE>.Vertex, G,P,X,RV> {}
//
//
// interface EdgeIndex<
//   ST extends VertexType<ST, SG,RV,RE>,
//   SG extends TypedGraph<SG,RV,RE>,
//   ET extends EdgeType<ST,SG, ET,EG, TT,TG, RV,RE>,
//   EG extends TypedGraph<EG,RV,RE>,
//   TT extends VertexType<TT, TG,RV,RE>,
//   TG extends TypedGraph<TG,RV,RE>,
//   P  extends Property<ET,X>,
//   X, RV,RE
// > extends TypedIndex<ET, EdgeType<ST,SG, ET,EG, TT,TG, RV,RE>.Edge, EG,P,X,RE> {}


// TODO: make Unique/List into property's arity
