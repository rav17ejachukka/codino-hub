name := "codino-hub"

version := "1.0"

scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.slick" %% "slick-hikaricp" % "3.5.2",
      "org.mariadb.jdbc" % "mariadb-java-client" % "3.2.0",
            "org.playframework" %% "play-slick" % "7.0.0-M1",

          "ch.qos.logback" % "logback-classic" % "1.5.16"
    )
  )
