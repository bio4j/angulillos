# Titan interfaces

- `Relationship` -> `TitanEdge`
- `RelationshipType` -> `TitanLabel`

- `Node` -> `TitanVertex`
- `NodeType` -> `TitanKey` but it doesn't work, stupid java generics

- `Property` -> `TitanProperty`?
- `PropertyType` -> `TitanKey`?

### general design

All the interfaces extend the corresponding type whenever possible, and wrap a value of the corresponding raw types.
