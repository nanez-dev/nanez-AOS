import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Created by iseungjun on 2023/09/25
 */
object AppConfigScript {

    fun BaseAppModuleExtension.buildTypeConfigs() {
        GradleBuildScript.log("buildTypeConfigs")
        buildTypes {
            getByName("debug") {
                isDebuggable = true
                applicationIdSuffix = ".debug"
                isShrinkResources = false
                isMinifyEnabled = false
                isCrunchPngs = false
                aaptOptions.cruncherEnabled = false
            }

            getByName("release") {
                isShrinkResources = true
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                isCrunchPngs = false
                aaptOptions.cruncherEnabled = false
            }

            buildResourceConfig()
        }
    }

    fun BaseAppModuleExtension.etcOptionsConfigs(kotlinJvmOptions: KotlinJvmOptions) {
        packaging {
            resources {
                excludes += "META-INF/LICENSE"
                excludes += "META-INF/NOTICE"
                excludes += "META-INF/DEPENDENCIES"
                excludes += "META-INF/LICENSE.txt"
                excludes += "META-INF/NOTICE.txt"
                excludes += ".readme"
            }
        }

        compileOptions {
            sourceCompatibility = VersionConfig.javaVersion
            targetCompatibility = VersionConfig.javaVersion
        }

        kotlinJvmOptions.jvmTarget = VersionConfig.jvmTarget

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }

        lint {
            disable.add("MissingTranslation")
            disable.add("NullSafeMutableLiveData")
            checkReleaseBuilds = false
        }
    }

    fun BaseAppModuleExtension.bindingConfigs() {
        dataBinding {
            enable = true
        }

        viewBinding {
            enable = true
        }
    }

    private fun BaseAppModuleExtension.buildResourceConfig() {
        applicationVariants.all {
            val buildTypeName = this.buildType.name
            val flavorsName = this.flavorName
            if (buildTypeName == "release") {
                outputs.configureEach {
                    (this as ApkVariantOutputImpl).versionCodeOverride = GradleBuildScript.makeAppVersionCode(550)
                }
            }

            when {
                buildTypeName == "release" -> {
                    resValue("string", "app_name", "Nanez")
                }

                buildTypeName == "debug" -> {
                    resValue("string", "app_name", "Nanez DEV")
                }

            }
        }
    }
}