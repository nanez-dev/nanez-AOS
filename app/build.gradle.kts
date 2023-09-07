plugins {
    kotlin("kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "org.techtown.nanez"
    compileSdk = 33

    defaultConfig {
        applicationId = "org.techtown.nanez"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding.enable = true
    viewBinding.enable = true
}

dependencies {
    implementation(project(":base"))
    implementation(project(":home"))
    implementation(project(":utils"))
    implementation(project(mapOf("path" to ":network")))

    implementation(libs.bundles.module.app)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}


kapt {
    correctErrorTypes = true
}