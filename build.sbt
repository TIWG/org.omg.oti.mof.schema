import java.io.File
import sbt.Keys._
import sbt._

import spray.json._, DefaultJsonProtocol._

import gov.nasa.jpl.imce.sbt._
import gov.nasa.jpl.imce.sbt.ProjectHelper._

useGpg := true

developers := List(
  Developer(
    id="rouquett",
    name="Nicolas F. Rouquette",
    email="nicolas.f.rouquette@jpl.nasa.gov",
    url=url("https://gateway.jpl.nasa.gov/personal/rouquett/default.aspx")),
  Developer(
    id="sherzig",
    name="Sebastian J. Herzig",
    email="sebastian.j.herzig@jpl.nasa.gov",
    url=url("https://gateway.jpl.nasa.gov/personal/sherzig/default.aspx")))

import scala.io.Source
import scala.util.control.Exception._

def docSettings(diagrams:Boolean): Seq[Setting[_]] =
  Seq(
    sources in (Compile,doc) <<= (git.gitUncommittedChanges, sources in (Compile,compile)) map {
      (uncommitted, compileSources) =>
        if (uncommitted)
          Seq.empty
        else
          compileSources
    },

    sources in (Test,doc) <<= (git.gitUncommittedChanges, sources in (Test,compile)) map {
      (uncommitted, testSources) =>
        if (uncommitted)
          Seq.empty
        else
          testSources
    },

    scalacOptions in (Compile,doc) ++=
      (if (diagrams)
        Seq("-diagrams", "-diagrams-dot-path", "/usr/bin/dot", "-diagrams-debug")
      else
        Seq()
        ) ++
        Seq(
          "-doc-title", name.value,
          "-doc-root-content", baseDirectory.value + "/rootdoc.txt",
          "-groups"
        ),
    autoAPIMappings := ! git.gitUncommittedChanges.value
//    apiMappings <++=
//      ( git.gitUncommittedChanges,
//        dependencyClasspath in Compile in doc,
//        IMCEKeys.nexusJavadocRepositoryRestAPIURL2RepositoryName,
//        IMCEKeys.pomRepositoryPathRegex,
//        streams ) map { (uncommitted, deps, repoURL2Name, repoPathRegex, s) =>
//        if (uncommitted)
//          Map[File, URL]()
//        else
//          (for {
//            jar <- deps
//            url <- jar.metadata.get(AttributeKey[ModuleID]("moduleId")).flatMap { moduleID =>
//              val urls = for {
//                (repoURL, repoName) <- repoURL2Name
//                (query, match2publishF) = IMCEPlugin.nexusJavadocPOMResolveQueryURLAndPublishURL(
//                  repoURL, repoName, moduleID)
//                url <- nonFatalCatch[Option[URL]]
//                  .withApply { (_: java.lang.Throwable) => None }
//                  .apply({
//                    val conn = query.openConnection.asInstanceOf[java.net.HttpURLConnection]
//                    conn.setRequestMethod("GET")
//                    conn.setDoOutput(true)
//                    repoPathRegex
//                      .findFirstMatchIn(Source.fromInputStream(conn.getInputStream).getLines.mkString)
//                      .map { m =>
//                        val javadocURL = match2publishF(m)
//                        s.log.info(s"Javadoc for: $moduleID")
//                        s.log.info(s"= mapped to: $javadocURL")
//                        javadocURL
//                      }
//                  })
//              } yield url
//              urls.headOption
//            }
//          } yield jar.data -> url).toMap
//      }
  )

resolvers := {
  val previous = resolvers.value
  if (git.gitUncommittedChanges.value)
    Seq[Resolver](Resolver.mavenLocal) ++ previous
  else
    previous
}

shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

lazy val mdRoot = SettingKey[File]("md-root", "MagicDraw Installation Directory")

lazy val specsRoot = SettingKey[File]("specs-root", "MagicDraw DynamicScripts Test Specification Directory")

lazy val runMDTests = taskKey[Unit]("Run MagicDraw DynamicScripts Unit Tests")

val duplicatedFiles = Set(
  // scalahost also provides `scalac-plugin.xml`, but we are only interested in ours.
  "scalac-plugin.xml"
)

/*
 * For now, we can't compile in strict mode because the Scala macros used for generating the JSon adapters
 * results in a compilation warning:
 *
 * Warning:(1, 0) Unused import
 * / *
 * ^
 *
 */
lazy val core = Project("org-omg-oti-mof-schema", file("."))
  .enablePlugins(IMCEGitPlugin)
  .enablePlugins(IMCEReleasePlugin)
  .settings(dynamicScriptsResourceSettings("org.omg.oti.mof.schema"))
  .settings(IMCEPlugin.strictScalacFatalWarningsSettings)
  .settings(docSettings(diagrams=true))
  .settings(IMCEReleasePlugin.packageReleaseProcessSettings)
  .settings(
    IMCEKeys.licenseYearOrRange := "2014-2016",
    IMCEKeys.organizationInfo := IMCEPlugin.Organizations.oti,
    IMCEKeys.targetJDK := IMCEKeys.jdk18.value,

    logLevel in assembly := Level.Info,

    organization := "org.omg.tiwg",
    organizationHomepage :=
      Some(url("http://www.omg.org/members/sysml-rtf-wiki/doku.php?id=rtf5:groups:tools_infrastructure:index")),

    buildInfoPackage := "org.omg.oti.mof.schema",
    buildInfoKeys ++= Seq[BuildInfoKey](BuildInfoKey.action("buildDateUTC") { buildUTCDate.value }),

    projectID := {
      val previous = projectID.value
      previous.extra(
        "build.date.utc" -> buildUTCDate.value,
        "artifact.kind" -> "generic.library")
    },

    git.baseVersion := Versions.version,

    scalaSource in Compile :=
      baseDirectory.value / "src",
      
    unmanagedSourceDirectories in Compile +=
      baseDirectory.value / "src-gen",
    
    resourceDirectory in Compile :=
      baseDirectory.value / "resources",

    scalaSource in Test :=
      baseDirectory.value / "test",

    resourceDirectory in Test :=
      baseDirectory.value / "resources",

    // disable publishing the jar produced by `test:package`
    publishArtifact in(Test, packageBin) := false,

    // disable publishing the test API jar
    publishArtifact in(Test, packageDoc) := false,

    // disable publishing the test sources jar
    publishArtifact in(Test, packageSrc) := false,

    unmanagedClasspath in Compile <++= unmanagedJars in Compile,

    libraryDependencies +=
      "org.scala-lang" % "scala-library" % scalaVersion.value % "provided",

    libraryDependencies +=
      "org.scala-lang" % "scalap" % scalaVersion.value % "provided",

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

    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play" % Versions.play % "compile" withSources(),
      "com.typesafe.play" %% "play-iteratees" % Versions.play % "compile" withSources(),
      "com.typesafe.play" %% "play-json" % Versions.play % "compile" withSources(),

      "io.megl" %% "play-json-extra" % Versions.play_json_extra % "compile" withSources()
    ),

    extractArchives := {},

    IMCEKeys.nexusJavadocRepositoryRestAPIURL2RepositoryName := Map(
      "https://oss.sonatype.org/service/local" -> "releases",
      "https://cae-nexuspro.jpl.nasa.gov/nexus/service/local" -> "JPL",
      "https://cae-nexuspro.jpl.nasa.gov/nexus/content/groups/jpl.beta.group" -> "JPL Beta Group",
      "https://cae-nexuspro.jpl.nasa.gov/nexus/content/groups/jpl.public.group" -> "JPL Public Group"),
    IMCEKeys.pomRepositoryPathRegex := """\<repositoryPath\>\s*([^\"]*)\s*\<\/repositoryPath\>""".r,

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
