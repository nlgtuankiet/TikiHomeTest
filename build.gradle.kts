import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        // for com.diffplug.gradle.spotless plugin
        maven("https://maven.fabric.io/public")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.3.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.org_jetbrains_kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-allopen:${Versions.org_jetbrains_kotlin}")
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version "0.3.2"
    id("com.diffplug.gradle.spotless") version "3.17.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://dl.bintray.com/mockito/maven")
    }
}

subprojects {
    apply(plugin = "com.diffplug.gradle.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            ktlint("0.29.0")
        }
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            incremental = true
            jvmTarget = "1.6"
            languageVersion = "1.3"
            apiVersion = "1.3"
        }
    }
}