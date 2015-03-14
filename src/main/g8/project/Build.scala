import sbt._
import Keys._

import com.typesafe.sbt.SbtScalariform._
import scalariform.formatter.preferences._
import sbtunidoc.Plugin._

object $name;format="Camel"$Build extends Build {

addCommandAlias("rebuild", ";clean; compile; package")

//////////////////////////////////////////////////////////////////////////////
// PROJECTS
//////////////////////////////////////////////////////////////////////////////
	lazy val $name;format="snake"$ = Project(id = "$name;format="Camel"$",
                              base = file("."),
                              settings = commonSettings) aggregate(
                                persistence,
                              	core,
                                domain,
                              	protocol,
                              	transport)

  lazy val persistence = Project(id = "$name;format="normalize"$-persistence",
                                 settings = commonSettings ++ Seq(
                                  libraryDependencies ++= Seq(
                                  
                                  )
                                 ),
                                 base = file("persistence")) dependsOn (protocol)

	lazy val core = Project(id = "$name;format="normalize"$-core",
                        	settings = commonSettings ++ Seq(
                            libraryDependencies ++= Seq(

                            )
                          ),
                          base = file("core")) dependsOn (
                            persistence,
                            domain,
                            protocol,
                            transport
                          )

	lazy val domain = Project(id = "$name;format="normalize"$-domain",
                           	settings = commonSettings ++ Seq(
                              libraryDependencies ++= Seq(
                              "org.scalacheck" %% "scalacheck" % "1.11.6" % "test"
                              )
                            ),
                            base = file("domain")) dependsOn (protocol)
  
	lazy val protocol = Project(id = "$name;format="normalize"$-protocol",
                              settings = commonSettings,
                              base = file("protocol"))
  
	lazy val transport = Project(id = "$name;format="normalize"$-transport",
                               settings = commonSettings ++ Seq(
                                 libraryDependencies ++= Seq(
                                 )
                               ),
                               base = file("transport")) dependsOn(protocol)
  
//////////////////////////////////////////////////////////////////////////////
// PROJECT INFO
//////////////////////////////////////////////////////////////////////////////

  val ORGANIZATION    = "$organization$"
  val PROJECT_NAME    = "$name;format="normalize"$"
  val PROJECT_VERSION = "0.1-SNAPSHOT"
  val SCALA_VERSION   = "2.11.4"


//////////////////////////////////////////////////////////////////////////////
// DEPENDENCY VERSIONS
//////////////////////////////////////////////////////////////////////////////

  val TYPESAFE_CONFIG_VERSION = "1.2.1"
  val SCALATEST_VERSION       = "2.2.2"
  val SLF4J_VERSION           = "1.7.9"
  val LOGBACK_VERSION         = "1.1.2"


//////////////////////////////////////////////////////////////////////////////
// SHARED SETTINGS
//////////////////////////////////////////////////////////////////////////////

  lazy val commonSettings = Project.defaultSettings ++
                            basicSettings ++
                            formatSettings ++
                            net.virtualvoid.sbt.graph.Plugin.graphSettings

  lazy val basicSettings = Seq(
    version := PROJECT_VERSION,
    organization := ORGANIZATION,
    scalaVersion := SCALA_VERSION,

    libraryDependencies ++= Seq(
      "com.typesafe"     % "config"          % TYPESAFE_CONFIG_VERSION,
      "org.slf4j"        % "slf4j-api"       % SLF4J_VERSION,
      "com.github.nscala-time"       %% "nscala-time" % "1.6.0",
      "ch.qos.logback"   % "logback-classic" % LOGBACK_VERSION % "runtime",
      "org.scalatest"   %% "scalatest"       % SCALATEST_VERSION % "test"
    ),

    scalacOptions in Compile ++= Seq(
      "-unchecked",
      "-deprecation",
      "-feature"
    ),

    javaOptions += "-Djava.library.path=%s:%s".format(
      sys.props("java.library.path")
    ),

    fork in run := true,

    fork in Test := true,

    parallelExecution in Test := false
  )

  lazy val formatSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences := FormattingPreferences()
      .setPreference(IndentWithTabs, false)
      .setPreference(IndentSpaces, 2)
      .setPreference(AlignParameters, false)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
      .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true)
      .setPreference(PreserveDanglingCloseParenthesis, true)
      .setPreference(CompactControlReadability, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(PreserveSpaceBeforeArguments, true)
      .setPreference(SpaceBeforeColon, false)
      .setPreference(SpaceInsideBrackets, false)
      .setPreference(SpaceInsideParentheses, false)
      .setPreference(SpacesWithinPatternBinders, true)
      .setPreference(FormatXml, true)
  )
  
}
