# Titan typed graphs

Let's recall what we need for each type, node, etc.

## define node types

In principle the easiest way to do this is by a property which identifies them. `PropertyType`s have a scoped name `fullName` which is different for each combination of node type and property type. For example, for a node type called `user` and a property called `id` its name will be `user.id`. For complete disambiguation I will use the graph `pkg` as a prefix to that. Then, for GO we will have `com.bio4j.model.go.nodes.Term.id` or something similar. So summarizing

- graph `pkg` +`.`+ node type name +`.` + property name

From the Titan point of view we need to define a `TitanKey` with that name, and configure it to be indexed etc.

## define label types

The mapping here is simpler because we have labels for edges in Titan. We just need to create the corresponding `TitanLabel` with matching arity. Note that in practice you don't know about the Titan signature at this point; we can return a `LabelMaker` with the minimum configuration and provide another method which would add the signature based on a list of properties for that Relationship.

``` java
// let's assume this node has only an id, stored in the type
TitanKey enzymeTypeKey = titanKeyForNodeType(Enzyme.Type.ID_TYPE);
// now a label
LabelMaker enzymaticActivityLabel = signatureFor(
  titanLabelForRelationshipType(EnzymaticActivity.Type.ID_TYPE),
  EnzymaticActivity.Type.ID_TYPE
);
// now create it if that's all
enzimaticActivityLabel.make();
// you can use it now for defining the corresponding titan rel type
```

## declare indexes

I think that in terms of usage indexes should be exposed from the corresponding `TitanTypedGraph`. For creating them we need (private? protected?) methods on `TitanTypedGraph` which would act on the wrapped raw `TitanGraph`. It would be up to the implementer to create them etc. Note that in general you always have access to the underlying type so that you can use any Titan-specific stuff you might need.

## node and relationship retrievers

First, for retrieving a node or a relationship there should be an index for that. This means that a graph needs to expose

1. all the indexes defined over a particular node/rel type and property type thereof
2. have a method for returning a retriever over that index

In the Titan case, retrievers would hold a reference to the corresponding graph.