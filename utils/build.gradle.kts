plugins {
    id("convention.nane.module.base")
}

android {
    namespace = "org.techtown.nanez.utils"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.bundles.module.utils)
    annotationProcessor(libs.glide)
}