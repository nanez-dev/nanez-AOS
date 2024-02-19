// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.gradle.plugin.android)
        classpath(libs.gradle.plugin.kotlin)
    }
}

plugins {
    alias(libs.plugins.hilt) apply false
}

allprojects {
    group = AppConfig.appPackage
}