plugins {
    id("convention.nane.module.base")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nane.detail"
}

dependencies {
    implementation(project(":base"))
    implementation(project(":utils"))
    implementation(project(":network"))
    implementation(project(":feature:login"))

    implementation(libs.bundles.module.home)
    kapt(libs.bundles.hilt.compile.kapt)
}

kapt {
    correctErrorTypes = true
}