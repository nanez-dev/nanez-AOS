plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    alias(libs.plugins.hilt) apply false
}

dependencies {
    implementation(libs.gradle.plugin.android)
    implementation(libs.gradle.plugin.kotlin)
    implementation(libs.hilt.poet)
}