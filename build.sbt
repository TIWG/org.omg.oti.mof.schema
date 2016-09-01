import java.io.File
import sbt.Keys._
import sbt._

import spray.json._, DefaultJsonProtocol._

import gov.nasa.jpl.imce.sbt._
import gov.nasa.jpl.imce.sbt.ProjectHelper._

updateOptions := updateOptions.value.withCachedResolution(true)

import scala.io.Source
import scala.util.control.Exception._

resolvers := {
  val previous = resolvers.value
  if (git.gitUncommittedChanges.value)
    Seq[Resolver](Resolver.mavenLocal) ++ previous
  else
    previous
}

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

val duplicatedFiles = Set(
  // scalahost also provides `scalac-plugin.xml`, but we are only interested in ours.
  "scalac-plugin.xml"
)

lazy val core = Project("org-omg-oti-mof-schema", file("."))
  .enablePlugins(IMCEGitPlugin)
  .enablePlugins(IMCEReleasePlugin)
  .settings(dynamicScriptsResourceSettings("org.omg.oti.mof.schema"))
  .settings(IMCEPlugin.strictScalacFatalWarningsSettings)
  .settings(IMCEReleasePlugin.packageReleaseProcessSettings)
  .settings(
    IMCEKeys.licenseYearOrRange := "2016",
    IMCEKeys.organizationInfo := IMCEPlugin.Organizations.oti,
    IMCEKeys.targetJDK := IMCEKeys.jdk18.value,

    logLevel in assembly := Level.Info,

    buildInfoPackage := "org.omg.oti.mof.schema",
    buildInfoKeys ++= Seq[BuildInfoKey](BuildInfoKey.action("buildDateUTC") { buildUTCDate.value }),

    projectID := {
      val previous = projectID.value
      previous.extra(
        "build.date.utc" -> buildUTCDate.value,
        "artifact.kind" -> "generic.library")
    },

    git.baseVersion := Versions.version,
    
    resourceDirectory in Compile :=
      baseDirectory.value / "resources",

    // disable publishing the jar produced by `test:package`
    publishArtifact in(Test, packageBin) := false,

    // disable publishing the test API jar
    publishArtifact in(Test, packageDoc) := false,

    // disable publishing the test sources jar
    publishArtifact in(Test, packageSrc) := false,

    resolvers += Resolver.bintrayRepo("jpl-imce", "gov.nasa.jpl.imce"),
    resolvers += Resolver.bintrayRepo("tiwg", "org.omg.tiwg"),

    unmanagedClasspath in Compile <++= unmanagedJars in Compile,

    libraryDependencies +=
      "gov.nasa.jpl.imce" %% "imce.third_party.other_scala_libraries" % Versions_other_scala_libraries.version
        % "provided"
        artifacts
        Artifact("imce.third_party.other_scala_libraries", "zip", "zip", Some("resource"), Seq(), None, Map()),

    libraryDependencies ~= {
      _ map { m =>
        m
          .exclude("commons-logging", "commons-logging")
          .exclude("com.typesafe", "config")
          .exclude("com.typesafe.play", "sbt-link")
          .exclude("org.slf4j", "slf4j-api")
          .exclude("org.slf4j", "slf4j-nop")
          .exclude("org.slf4j", "jcl-over-slf4j")
          .exclude("ch.qos.logback", "logback-classic")
      }
    },

    extractArchives := {},

    // do not include the scala library
    assembleArtifact in assemblyPackageScala := false,

    assemblyMergeStrategy in assembly := {
      case x if duplicatedFiles exists (x endsWith _) =>
        MergeStrategy.first
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    },

    assemblyShadeRules in assembly := Seq(
      ShadeRule
        .rename("com.typesafe.config.**" -> "oti_mof_schema_config.@1")
        .inLibrary("com.typesafe" % "config" % "1.3.0")
        .inProject
    ),

    // include the assembly as an artifact for publish or publishLocal

    artifact in (Compile, assembly) := {
      val art = (artifact in (Compile, assembly)).value
      art.copy(`classifier` = Some("assembly"))
    },

    addArtifact(artifact in (Compile, assembly), assembly)
  )

def dynamicScriptsResourceSettings(projectName: String): Seq[Setting[_]] = {

  import com.typesafe.sbt.packager.universal.UniversalPlugin.autoImport._

  def addIfExists(f: File, name: String): Seq[(File, String)] =
    if (!f.exists) Seq()
    else Seq((f, name))

  val QUALIFIED_NAME = "^[a-zA-Z][\\w_]*(\\.[a-zA-Z][\\w_]*)*$".r

  Seq(
    // the '*-resource.zip' archive will start from: 'dynamicScripts'
    com.typesafe.sbt.packager.Keys.topLevelDirectory in Universal := None,

    // name the '*-resource.zip' in the same way as other artifacts
    com.typesafe.sbt.packager.Keys.packageName in Universal :=
      normalizedName.value + "_" + scalaBinaryVersion.value + "-" + version.value + "-resource",

    // contents of the '*-resource.zip' to be produced by 'universal:packageBin'
    mappings in Universal <++= (
      baseDirectory,
      packageBin in Compile,
      packageSrc in Compile,
      packageDoc in Compile,
      packageBin in Test,
      packageSrc in Test,
      packageDoc in Test,
      streams) map {
      (base, bin, src, doc, binT, srcT, docT, s) =>
        val file2name =
          addIfExists(bin, projectName + "/lib/" + bin.name) ++
          addIfExists(binT, projectName + "/lib/" + binT.name) ++
          addIfExists(src, projectName + "/lib.sources/" + src.name) ++
          addIfExists(srcT, projectName + "/lib.sources/" + srcT.name) ++
          addIfExists(doc, projectName + "/lib.javadoc/" + doc.name) ++
          addIfExists(docT, projectName + "/lib.javadoc/" + docT.name)

        s.log.info(s"file2name entries: ${file2name.size}")
        s.log.info(file2name.mkString("\n"))

        file2name
    },

    artifacts <+= (name in Universal) { n => Artifact(n, "zip", "zip", Some("resource"), Seq(), None, Map()) },
    packagedArtifacts <+= (packageBin in Universal, name in Universal) map { (p, n) =>
      Artifact(n, "zip", "zip", Some("resource"), Seq(), None, Map()) -> p
    }
  )
}
