plugins {
    id("convention.nane.module.base")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nane.home"
}

dependencies {
    implementation(project(":base"))
    implementation(project(":utils"))
    implementation(project(":network"))

    implementation(libs.bundles.module.home)
    implementation(project(mapOf("path" to ":feature:search")))
    kapt(libs.bundles.hilt.compile.kapt)
}

kapt {
    correctErrorTypes = true
}