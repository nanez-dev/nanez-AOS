plugins {
    id("convention.nane.module.base")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nane.search"
}

dependencies {
    implementation(project(":base"))
    implementation(project(":utils"))
    implementation(project(":network"))
    implementation(project(mapOf("path" to ":feature:theme")))

    implementation(libs.bundles.module.search)
    kapt(libs.bundles.hilt.compile.kapt)
}

kapt {
    correctErrorTypes = true
}