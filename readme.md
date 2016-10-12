# Angulillos[<?>](https://translate.google.com/#es/en/par%C3%A9ntesis%20angulares)

[![](https://travis-ci.org/bio4j/angulillos.svg?branch=master)](https://travis-ci.org/bio4j/angulillos)
[![](https://img.shields.io/codacy/0b73cc36435640c6ab2b96dbceb52494.svg)](https://www.codacy.com/app/bio4j/angulillos)
[![](http://github-release-version.herokuapp.com/github/bio4j/angulillos/release.svg)](https://github.com/bio4j/angulillos/releases/latest)
[![](https://img.shields.io/badge/license-AGPLv3-blue.svg)](https://tldrlegal.com/license/gnu-affero-general-public-license-v3-%28agpl-3.0%29)
[![](https://img.shields.io/badge/contact-gitter_chat-dd1054.svg)](https://gitter.im/bio4j/angulillos)

Angulillos is a Java 8 library for strongly typed graph data.

You write graph schemas using the Java type system, and graph traversals are statically checked. The same schemas and traversals can then be used with any implementation of the _Angulillos_ API, such as [bio4j/angulillos-titan](https://github.com/bio4j/angulillos-titan).

### Why not TinkerPop?

Angulillos fixes the untypedness of TinkerPop at two different levels that we could call *structural* and *model*-specific:

- **Structural** You can write nonsensical traversals like "source of a vertex"
- **Model** No notion of graph schema. You can write "give me users followed by tweet"
