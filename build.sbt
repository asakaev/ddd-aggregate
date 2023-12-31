ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "io.github.asakaev"

lazy val root = (project in file("."))
  .settings(
    name := "ddd-aggregate",
    libraryDependencies += "dev.zio" %% "zio" % "2.0.18"
  )
