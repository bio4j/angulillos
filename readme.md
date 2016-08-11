# Angulillos[<?>](https://translate.google.com/#es/en/par%C3%A9ntesis%20angulares)

[![](https://travis-ci.org/bio4j/angulillos.svg?branch=master)](https://travis-ci.org/bio4j/angulillos)
[![](https://img.shields.io/codacy/0b73cc36435640c6ab2b96dbceb52494.svg)](https://www.codacy.com/app/bio4j/angulillos)
[![](http://github-release-version.herokuapp.com/github/bio4j/angulillos/release.svg)](https://github.com/bio4j/angulillos/releases/latest)
[![](https://img.shields.io/badge/license-AGPLv3-blue.svg)](https://tldrlegal.com/license/gnu-affero-general-public-license-v3-%28agpl-3.0%29)
[![](https://img.shields.io/badge/contact-gitter_chat-dd1054.svg)](https://gitter.im/bio4j/angulillos)

_Angulillos_ is a Java 8 library for _working with strongly typed graph data in a generic way_.

You write graph schemas using the Java type system, with graph traversals being statically checked over graph schemas. The same schemas and traversals can then be used with any graph technology implementing the _Angulillos_ API.

See also the [bio4j/angulillos-titan](https://github.com/bio4j/angulillos-titan) repo with the implementation of _Angulillos_ API for the [Titan](http://titandb.io) graph database.

This library was developed for the [Bio4j](https://github.com/bio4j/bio4j) bioinformatics graph data platform. But it can be used for working with any kind of graph structured data.


## Some notes about the code

If you are going to read the code and probably use it, you may find these notes useful.


### General notes about types

- Edge source and target vertices don't have to be from the same graph (note the wildcards in the API). This allows you to have a graph with edges that connect vertices of two other independent graphs.
- **But** all vertices and edges that interact through this API have to have _the same raw types_ (`RV,RE`).


### Type variables naming convention

Most frequently used things:

- `V` stands for **V**ertex
- `E` stands for **E**dge. Plus in the context of an edge
  + `S` stands for its **S**ource vertex
  + `T` stands for its **T**arget vertex
- `*T` suffix stands for **T**ype for any of the above (e.g. `VT` is **V**ertex **T**ype)
- `G` stands for **G**raph
- `R*` prefix means **R**aw. These are shared among all other types in the API:
  + `RV` stands for **R**aw **V**ertex type
  + `RE` stands for **R**aw **E**dge type

Now less frequently used things have not so nice, but unambiguous names:

- `F` for Element, because `E` is used for Edge and F is just the closes letter
- `X` for the property value type, because `V` is used for Vertex and X looks like an arbitrary type variable


### Type variables order convention

1. All graph element types (i.e. vertices and edges) come **in pairs**: typed element and its type. For example:

    - Vertex and its type:

        ```java
        <
          V  extends      TypedVertex<V,VT, G,RV,RE>,
          VT extends TypedVertex.Type<V,VT, G,RV,RE>
        >
        ```

    - Edge and its type:

        ```java
        <
          E  extends      TypedEdge<S,ST, E,ET, T,TT, G,RV,RE>,
          ET extends TypedEdge.Type<S,ST, E,ET, T,TT, G,RV,RE>
        >
        ```

2. The order of edge primary parameters is _source_, then _edge_ itself, then _target_.
And in general, when you mention edge and its source or target, you put source-related things _before_ the _edge_ and target-related things _after_ it.

3. Almost all types have _graph_ `G`, _raw vertex_ `RV` and _raw edge_ `RE` types and they are placed **in the end** of the list (see examples above).
To improve readability, it's recommended to put a space between the paired type variables and these three.
