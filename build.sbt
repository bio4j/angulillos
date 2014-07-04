Nice.javaProject

name := "typed-graphs"

description := "A Java API for typed property graphs"

organization := "ohnosequences"

javaVersion := "1.8"

bucketSuffix := "era7.com"

libraryDependencies += "com.thinkaurelius.titan" % "titan-core" % "0.4.4"

libraryDependencies += "org.neo4j" % "neo4j" % "2.1.2"

dependencyOverrides += "net.sf.opencsv" % "opencsv" % "2.3"
