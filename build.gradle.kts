// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        val nav_version = "2.8.2"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("org.jetbrains.kotlin:kotlin-serialization")
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.5.12")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.androidx.navigation.safe.args) apply false
//    alias(libs.plugins.compose.compiler) apply false

//    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.6.0"
//    id("org.jetbrains.compose") version "1.5.12"
}