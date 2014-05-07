# Titan interfaces

- `Relationship` -> `TitanEdge`
- `RelationshipType` -> `TitanLabel` but it doesn't work, stupid java generics

- `Node` -> `TitanVertex`
- `NodeType` -> `TitanKey` but it doesn't work, stupid java generics

- `Property` -> `TitanProperty` not possible 
- `PropertyType` -> `TitanKey`?

### general design

All the interfaces extend the corresponding type whenever possible, and wrap a value of the corresponding raw types. We need a generic constructor from `TitanVertex`/`TitanEdge`. I think that for nodes and relationships we can make them `TitanVertex` and `TitanEdge` respectively by wrapping and delegating. That should be enough.

In general we need to

1. implement the `type()` method coming from element
    **DONE** at the interface level, with a default method
2. implement the corresponding property methods: `id`, `comment`, etc
    **NEEDS WORK** we could have a helper method here. We already have a static method `Id.TYPE(Enzyme.Type)` returning the right `PropertyType`, which could be used for defining the corresponding `TitanKey` somewhere. Here, we could just use `getProperty(String key)` with the key coming from the type; we can write a generic method for that, and then it would be just `id() { return getFrom(raw, Id.TYPE(Enzyme.Type)); }`
3. implement the in/out methods
    **NEEDS WORK** but it's easy. Again, we can have generic methods using the RelType and the `raw` TitanVertex thing.
4. For Rels, source and target
    **NEEDS WORK** but again easy, just call the right constructor



#### questions

1. do we want nodes and edges to implement the model interfaces? **yes**
2. it is essential for them to implement the corresponding Titan entities? **no**


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