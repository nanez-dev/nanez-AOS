plugins {
    id("convention.nane.module.base")
}

android {
    namespace = "com.nane.base"
}

dependencies {
    implementation(project(":utils"))
}