import GradleBuildScript.gitBranch
import AppConfigScript.buildTypeConfigs
import AppConfigScript.etcOptionsConfigs
import AppConfigScript.bindingConfigs

plugins {
    kotlin("kapt")
    kotlin("android")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "org.techtown.nanez"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionName = AppConfig.versionName
        resValue("string", "gitBranch", gitBranch())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypeConfigs()
    etcOptionsConfigs(kotlinOptions)
    bindingConfigs()
}

dependencies {
    implementation(project(":base"))
    implementation(project(":utils"))
    implementation(project(mapOf("path" to ":network")))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:join"))
    implementation(project(":feature:theme"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:storage"))

    implementation(libs.bundles.module.app)
    kapt(libs.bundles.hilt.compile.kapt)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}


kapt {
    correctErrorTypes = true
}