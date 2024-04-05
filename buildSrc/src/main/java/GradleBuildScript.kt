import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import java.util.regex.Pattern

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

    fun makeAppVersionCode(prefix: Int, gitBranch: String): Int {
        val releasePreFix = "release/"
        if (gitBranch.startsWith(releasePreFix)) {
            val versionName = gitBranch.substring(releasePreFix.length, gitBranch.length)
            return makeVersionCode(prefix, versionName)
        }

        return makeVersionCode(prefix, AppConfig.versionCode)
    }


    fun makeVersionName(gitBranch: String, default: String = AppConfig.versionName): String {
        val releasePreFix = "release/"
        if (gitBranch.startsWith(releasePreFix)) {
            val versionName = gitBranch.substring(releasePreFix.length, gitBranch.length)
            if (checkVersionCodePattern(versionName)) {
                return versionName
            }
        }
        return default
    }


    private fun makeVersionCode(prefix: Int, code: String): Int {
        if (!checkVersionCodePattern(code)) {
            log("makeAppVersionCode version=$999999999")
            return 999999999
        }

        return code.split(".").joinToString(separator = "", prefix = prefix.toString()) {
            String.format("%02d", it.toInt())
        }.toInt().also { result ->
            log("makeAppVersionCode version=$result")
        }
    }


    private fun checkVersionCodePattern(code: String): Boolean {
        return Pattern.matches("^\\d{1,2}.\\d{1,2}.\\d{1,2}\$", code)
    }

    fun log(message: String) {
        println("$TAG:$message")
    }

}