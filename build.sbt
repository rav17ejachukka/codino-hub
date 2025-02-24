name := "codino-hub"
version := "1.0-SNAPSHOT"
scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      // play-slick
      "org.playframework" %% "play-slick" % "6.1.0",
      "org.playframework" %% "play-slick-evolutions" % "6.1.0",
      "org.mariadb.jdbc" % "mariadb-java-client" % "3.3.2",
      "org.playframework" %% "play-guice" % "3.0.1",
      "com.typesafe.slick" %% "slick" % "3.5.0",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.5.0",

      //Zio
      "dev.zio" %% "zio" % "2.0.18",
      "dev.zio" %% "zio-streams" % "2.0.19",
      "dev.zio" %% "zio-json" % "0.6.2",
      "com.sun.mail" % "jakarta.mail" % "2.0.1",
      "io.getquill" %% "quill-jdbc-zio" % "4.8.0",
      "dev.zio" %% "zio-logging" % "2.1.14",
      "dev.zio" %% "zio-logging-slf4j" % "2.1.14",
      "dev.zio" %% "zio-interop-cats" % "23.1.0.0",

      // Bcrypt for password
      "org.mindrot" % "jbcrypt" % "0.4",
      // to generate password
      "org.apache.commons" % "commons-text" % "1.10.0"



    )
  )

// Required for Play Framework 3.x
ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
