ThisBuild / scalaVersion     := "2.12.18"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.parthmundhwa"

lazy val root = (project in file("."))
  .settings(
    name := "customer-deduplication-scala",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-sql" % "3.5.1" % "provided",
      "org.apache.spark" %% "spark-core" % "3.5.1" % "provided",
      "org.apache.commons" % "commons-text" % "1.10.0",     // Jaro-Winkler
      "commons-codec" % "commons-codec" % "1.16.0"          // Soundex (optional)
    ),
    Compile / run / fork := true,
    Compile / run / javaOptions ++= Seq(
      "-Dspark.master=local[*]",
      "-Duser.timezone=UTC"
    )
  )
