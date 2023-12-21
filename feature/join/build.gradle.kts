plugins {
    id("convention.nane.module.base")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.nane.join"
}

dependencies {
    implementation(project(":base"))
    implementation(project(":utils"))
    implementation(project(":network"))

    implementation(libs.bundles.module.home)
    kapt(libs.bundles.hilt.compile.kapt)
}

kapt {
    correctErrorTypes = true
}