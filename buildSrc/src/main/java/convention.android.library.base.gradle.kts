plugins {
    kotlin("kapt")
    kotlin("android")
    id("com.android.library")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = VersionConfig.javaVersion
        targetCompatibility = VersionConfig.javaVersion
    }

    kotlinOptions {
        jvmTarget = VersionConfig.jvmTarget
    }
}

dependencies {
    val libs =  project.extensions.getByType<VersionCatalogsExtension>().named("libs")

    testImplementation(libs.findLibrary("junit").get())
    androidTestImplementation(libs.findBundle("androidx-test").get())
}