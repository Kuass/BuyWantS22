import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "kr.kua"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
//    implementation("org.seleniumhq.selenium:selenium-support:${selenium_version}")

    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.6")
//    implementation("org.jsoup:jsoup:1.14.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
//    mainClassName = "kr.kua.Main"
    mainClass.set("MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}