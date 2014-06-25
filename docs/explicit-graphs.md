# Adding a graph type

The ideas are

1. add a raw graph type to nodes and rels
2. that raw graph has methods returning `Object` for the basic graph ops (get out edges, by label, whatever)
3. use that together with the type constructors to implement generic in/out methods at the node level

Using inheritance for graphs doesn't work; at some point you need to say that a type param is bounded by an intersection of two type parameters, and this is not allowed.
