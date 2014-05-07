# Titan interfaces

- `Relationship` -> `TitanEdge`
- `RelationshipType` -> `TitanLabel` but it doesn't work, stupid java generics

- `Node` -> `TitanVertex`
- `NodeType` -> `TitanKey` but it doesn't work, stupid java generics

- `Property` -> `TitanProperty`?
- `PropertyType` -> `TitanKey`?

### general design

All the interfaces extend the corresponding type whenever possible, and wrap a value of the corresponding raw types.

#### questions

1. do we want nodes and edges to implement the model interfaces? **yes**
2. it is essential for them to implement the corresponding Titan entities? **no**
3. 


### properties

There's no way of implementing properties generically; what we can do is for each property have a Titan impl containing a `TitanProperty` field.

With respect to `PropertyType`, we have again the `Comparable<TitanElement>` covariance issue, so we need an interface (we cannot subclass `Enum`).


## Example

We want to implement the `Enzyme` node, backed by Titan:

``` java
public interface Enzyme extends Node<Enzyme, Enzyme.Type>,

  // properties
  Id<Enzyme, Enzyme.Type>,
  Cofactors<Enzyme, Enzyme.Type>,
  OfficialName<Enzyme, Enzyme.Type>,
  AlternateNames<Enzyme, Enzyme.Type>,
  CatalyticActivity<Enzyme, Enzyme.Type>,
  Comment<Enzyme, Enzyme.Type>, // WARNING: changed this from comments to comment
  PrositeCrossReferences<Enzyme, Enzyme.Type>

{
  
  // enzymaticActivity
  // incoming
  public List<EnzymaticActivity> enzymaticActivity_in();
  public List<Protein> enzymaticActivity_inNodes();

  // etc etc
}
```

For this we need to

1. implement the `type()` method coming from element
    **DONE** at the interface level, with a default method
2. implement the corresponding property methods: `id`, `comment`, etc
    **NEEDS WORK** we could have a helper method here. We already have a static method `Id.TYPE(Enzyme.Type)` returning the right `PropertyType`, which could be used for defining the corresponding `TitanKey` somewhere. Here, we could just use `getProperty(String key)` with the key coming from the type.
3. implement the in/out methods
    **NEEDS WORK**

I think that for nodes and relationships we can make them `TitanVertex` and `TitanEdge` respectively by wrapping and delegating. That should be enough.