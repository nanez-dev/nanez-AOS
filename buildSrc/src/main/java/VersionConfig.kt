import org.gradle.api.JavaVersion

/**
 * Created by iseungjun on 2023/09/25
 */
object VersionConfig {
    private enum class JvmPair(val javaVersion: JavaVersion, val jvmTarget: String) {
        //java version, kotlinoption - jvmtarget은 버전이 일치해야함.
        VERSION_17(JavaVersion.VERSION_17, "17")
    }

    val javaVersion = JvmPair.VERSION_17.javaVersion
    val jvmTarget = JvmPair.VERSION_17.jvmTarget
}