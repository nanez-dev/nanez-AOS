plugins {
    id("convention.nane.module.base")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nane.profile"
}

dependencies {
    implementation(project(":base"))
    implementation(project(":utils"))
    implementation(project(":network"))
    implementation(project(":feature:login"))
    implementation(project(":feature:password"))
    implementation(project(":feature:setting"))

    implementation(libs.bundles.module.home)
    kapt(libs.bundles.hilt.compile.kapt)
}

kapt {
    correctErrorTypes = true
}