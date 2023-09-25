plugins {
    id("convention.nane.module.base")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.nane.network"
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.bundles.module.network)
    kapt(libs.bundles.hilt.compile.kapt)
}


kapt {
    correctErrorTypes = true
}