pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

}
rootProject.name = "nanez"

rootProject.projectDir.listFiles()?.forEach {
    findSubProjects("", it)
}

fun findSubProjects(projectPath: String, file: File) {
    if (file.name.startsWith(".") ||
        file.name == "buildSrc" ||
        file.name == "build" ||
        file.name == "libs" ||
        file.name == "release-note" ||
        file.name == "src" ||
        file.name == "jni" ||
        file.name == "obj" ||
        file.name == "assets" ||
        file.name == "gradle" ||
        file.name == "doc" ||
        file.name == "res"
    ) {
        //프로젝트 검색과 상관없는파일 return
        return
    }

    if (file.name == "build.gradle.kts.kts" || file.name == "build.gradle.kts") {
        if (projectPath.isNotEmpty()) {
            println("include $projectPath")
            include(projectPath)
        }
        return
    }

    if (file.isDirectory) {
        file.listFiles()?.forEach {
            findSubProjects("$projectPath:${file.name}", it)
        }
    }
}
include(":feature:detail")
