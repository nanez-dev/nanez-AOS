import org.gradle.api.Project
import java.io.ByteArrayOutputStream

/**
 * Created by iseungjun on 2023/09/25
 */
object GradleBuildScript {

    private const val TAG = "GradleBuildScript"

    fun Project.gitBranch(): String {
        val stdout = ByteArrayOutputStream()
        exec {
            commandLine("git", "rev-parse", "--abbrev-ref", "HEAD")
            standardOutput = stdout
        }
        log("gitBranch ${stdout.toString().trim()}")
        return stdout.toString().trim()
    }

    fun makeAppVersionCode(prifix: Int): Int {
        return AppConfig.versionCode.split(".").map {
            String.format("%02d", it.toInt())
        }.joinToString(separator = "", prefix = prifix.toString()).toInt().also {
            log("makeAppVersionCode version = ${it}")
        }
    }

    fun log(message: String) {
        println("$TAG:$message")
    }

}