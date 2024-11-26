// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1"
    alias(libs.plugins.google.gms.google.services) apply false
}
buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}