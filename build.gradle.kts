import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin ("jvm") version "1.7.21"
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "vn.thuanpm"
version = "1.0.0-SNAPSHOT"

repositories {
  maven {
    url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
    mavenContent {
      snapshotsOnly()
    }
  }
  mavenCentral()
}

val vertxVersion = "4.5.4-SNAPSHOT"
val junitJupiterVersion = "5.9.1"

val mainVerticleName = "vn.thuanpm.kotlinzen.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-web-validation")
  implementation("io.vertx:vertx-web-client")
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-pg-client")
  implementation("io.vertx:vertx-web-openapi")
  implementation("io.vertx:vertx-lang-kotlin-coroutines")
  implementation("io.vertx:vertx-web-openapi-router")
  implementation("io.vertx:vertx-lang-kotlin")
  implementation("io.vertx:vertx-reactive-streams")
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.ongres.scram:client:2.1")
  implementation("io.vertx:vertx-config:4.5.3")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
  implementation("org.hibernate.reactive:hibernate-reactive-core:2.2.2.Final")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}
