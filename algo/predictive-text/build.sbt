ThisBuild / scalaVersion := "2.12.11"
ThisBuild / organization := "com.example"

lazy val predictiveText = (project in file("."))
  .settings(
    name := "Predictive Text"
  )