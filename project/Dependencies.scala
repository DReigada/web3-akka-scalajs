import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt._

object Dependencies {
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.0.10"
  lazy val scalajsLinkScripts = "com.vmunier" %% "scalajs-scripts" % "1.1.0"


  // Scala js dependencies
  //lazy val scalajsDom = "org.scala-js" %%% "scalajs-dom" % "0.9.3" withSources()

}
