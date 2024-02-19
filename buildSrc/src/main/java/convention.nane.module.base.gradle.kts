plugins {
    kotlin("kapt")
    kotlin("android")
    id("convention.android.library.base")
}

android {
    dataBinding.enable = true
    viewBinding.enable = true
}

dependencies {
    val libs =  project.extensions.getByType<VersionCatalogsExtension>().named("libs")
    implementation(libs.findBundle("module-base").get())
}