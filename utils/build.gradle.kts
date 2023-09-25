plugins {
    id("convention.nane.module.base")
}

android {
    namespace = "org.techtown.nanez.utils"
}

dependencies {
    implementation(libs.bundles.module.utils)
    annotationProcessor(libs.glide)
}