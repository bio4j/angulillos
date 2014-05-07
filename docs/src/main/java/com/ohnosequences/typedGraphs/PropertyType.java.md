
```java
package com.ohnosequences.typedGraphs;
```


*  A Property type.
*
*  @author <a href="mailto:eparejatobes@ohnosequences.com">Eduardo Pareja-Tobes</a>


```java
public abstract class PropertyType <
  // the element type
  N extends Element<N,NT>, NT extends Enum<NT> & ElementType<N,NT>,
  // the property (of that element)
  P extends Property<N,NT>, PT extends PropertyType<N,NT,P,PT,V>,
  // the value type of this property
  V
> 
{
  
  protected PropertyType(NT elementType, String name) {

    this.elementType = elementType;
    this.name = name;
  }

  private NT elementType;
  // this is a strong hint for you to implement this as a singleton
  // public abstract PT value(NT elementType);
  private String name;
```


the name is by default the name of the element together with that of the unique value here


```java
  public String fullName() { return elementType.name().concat(".").concat(name); }
```


the element type which has this property type


```java
  public NT elementType() { return this.elementType; }

  // just in case
  public abstract Class<V> valueClass();
  public String name() { return this.name(); }
}

```


------

### Index

+ src
  + test
    + java
      + com
        + ohnosequences
          + typedGraphs
  + main
    + java
      + com
        + ohnosequences
          + typedGraphs
            + [Relationship.java][main/java/com/ohnosequences/typedGraphs/Relationship.java]
            + [ElementType.java][main/java/com/ohnosequences/typedGraphs/ElementType.java]
            + [NodeType.java][main/java/com/ohnosequences/typedGraphs/NodeType.java]
            + [Node.java][main/java/com/ohnosequences/typedGraphs/Node.java]
            + [NodeIndex.java][main/java/com/ohnosequences/typedGraphs/NodeIndex.java]
            + [Retriever.java][main/java/com/ohnosequences/typedGraphs/Retriever.java]
            + [Property.java][main/java/com/ohnosequences/typedGraphs/Property.java]
            + [NodeUniqueIndex.java][main/java/com/ohnosequences/typedGraphs/NodeUniqueIndex.java]
            + [NodeListIndex.java][main/java/com/ohnosequences/typedGraphs/NodeListIndex.java]
            + [Module.java][main/java/com/ohnosequences/typedGraphs/Module.java]
            + [Element.java][main/java/com/ohnosequences/typedGraphs/Element.java]
            + [PropertyType.java][main/java/com/ohnosequences/typedGraphs/PropertyType.java]
            + [RelationshipType.java][main/java/com/ohnosequences/typedGraphs/RelationshipType.java]

[main/java/com/ohnosequences/typedGraphs/Relationship.java]: Relationship.java.md
[main/java/com/ohnosequences/typedGraphs/ElementType.java]: ElementType.java.md
[main/java/com/ohnosequences/typedGraphs/NodeType.java]: NodeType.java.md
[main/java/com/ohnosequences/typedGraphs/Node.java]: Node.java.md
[main/java/com/ohnosequences/typedGraphs/NodeIndex.java]: NodeIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Retriever.java]: Retriever.java.md
[main/java/com/ohnosequences/typedGraphs/Property.java]: Property.java.md
[main/java/com/ohnosequences/typedGraphs/NodeUniqueIndex.java]: NodeUniqueIndex.java.md
[main/java/com/ohnosequences/typedGraphs/NodeListIndex.java]: NodeListIndex.java.md
[main/java/com/ohnosequences/typedGraphs/Module.java]: Module.java.md
[main/java/com/ohnosequences/typedGraphs/Element.java]: Element.java.md
[main/java/com/ohnosequences/typedGraphs/PropertyType.java]: PropertyType.java.md
[main/java/com/ohnosequences/typedGraphs/RelationshipType.java]: RelationshipType.java.md