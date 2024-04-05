import GradleBuildScript.makeAppVersionCode
import GradleBuildScript.makeVersionName
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Created by iseungjun on 2023/09/25
 */
object AppConfigScript {

    fun BaseAppModuleExtension.customSigningConfigs(project: Project) {
        signingConfigs {
            create("stg") {
                storeFile = project.file(project.findProperty("debugStoreFile") as String)
                storePassword = project.findProperty("debugStorePassword") as String
                keyAlias = project.findProperty("debugKeyAlias") as String
                keyPassword = project.findProperty("debugKeyPassword") as String
            }

            create("prod") {
                storeFile = project.file(project.findProperty("releaseStoreFile") as String)
                storePassword = project.findProperty("releaseStorePassword") as String
                keyAlias = project.findProperty("releaseKeyAlias") as String
                keyPassword = project.findProperty("releaseKeyPassword") as String
            }
        }
    }


    fun BaseAppModuleExtension.buildTypeConfigs(gitBranch: String) {
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

            buildResourceConfig(gitBranch)
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


    fun BaseAppModuleExtension.flavorsConfigs(gitBranch: String) {
        flavorDimensions += "environment"
        productFlavors {
            create("prod") {
                versionCode = makeAppVersionCode(550, gitBranch)
                signingConfig = signingConfigs.findByName("prod")
                dimension = "environment"
            }
        }
    }

    private fun BaseAppModuleExtension.buildResourceConfig(gitBranch: String) {
        applicationVariants.all {
            val buildTypeName = this.buildType.name
            val flavorsName = this.flavorName
            if (buildTypeName == "release") {
                outputs.configureEach {
                    (this as ApkVariantOutputImpl).outputFileName = "nane_${flavorsName}_${buildTypeName}_${makeVersionName(gitBranch)}.apk"
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