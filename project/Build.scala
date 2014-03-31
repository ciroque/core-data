import sbt._
import sbt.Keys._

object ApplicationBuild extends Build {

  val appName               = "core-data"
  val appVersion              = "1.0-SNAPSHOT"

  override lazy val settings = super.settings ++ Seq(
    organization := "org.ciroque",
    version := appVersion,

    publishMavenStyle := true,
    publishArtifact in Test := false,

    publishTo <<= version {
      (v: String) =>
        val nexus = "http://amala.wagner-x.net:8081/nexus/"
        if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
        else Some("releases" at nexus + "content/repositories/releases")
    },
    pomIncludeRepository := {
      x => true
    },
    licenses += ("MIT" -> url("http://opensource.org/licenses/MIT")),
    homepage := Some(url("http://ciroque-x.net/")),
    scmInfo := Some(ScmInfo(url("http://ciroque-x.net"), "https://github.com/ciroque/core-data.git")),

    // Maven central wants some extra metadata to keep things 'clean'.
    pomExtra := (
      <developers>
        <developer>
          <id>ciroque</id>
          <name>Steve Wagner</name>
        </developer>
      </developers>)
  )
}
