Nice.javaProject

name         := "angulillos"
organization := "bio4j"
description  := "A Java API for typed property graphs with a lot of angulillos"

bucketSuffix := "era7.com"
javaVersion  := "1.8"

excludeFilter in unmanagedSources :=
  (excludeFilter in unmanagedSources).value ||
  "*Index.java" ||
  "*Query.java"

javacOptions ++= Seq(
  "-Xdiags:verbose"
)
