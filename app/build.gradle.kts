import AppConfigScript.bindingConfigs
import AppConfigScript.buildTypeConfigs
import AppConfigScript.customSigningConfigs
import AppConfigScript.etcOptionsConfigs
import AppConfigScript.flavorsConfigs
import GradleBuildScript.gitBranch

plugins {
    kotlin("kapt")
    kotlin("android")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
}

android {
    val gitBranch = gitBranch()

    namespace = "org.techtown.nanez"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionName = AppConfig.versionName
        resValue("string", "gitBranch", gitBranch)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    customSigningConfigs(project)
    buildTypeConfigs(gitBranch)
    flavorsConfigs(gitBranch)
    etcOptionsConfigs(kotlinOptions)
    bindingConfigs()
}

dependencies {
    implementation(project(":base"))
    implementation(project(":utils"))
    implementation(project(":network"))
    implementation(project(":feature:home"))
    implementation(project(":feature:login"))
    implementation(project(":feature:join"))
    implementation(project(":feature:theme"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:storage"))
    implementation(project(":feature:detail"))
    implementation(project(":feature:search"))
    implementation(project(":feature:password"))

    implementation(libs.bundles.module.app)
    kapt(libs.bundles.hilt.compile.kapt)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}


kapt {
    correctErrorTypes = true
}