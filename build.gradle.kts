// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        disabledRules.set(listOf("no-wildcard-imports"))
    }
}

apply { from(rootProject.file("install-git-hooks.gradle")) }
tasks.getByPath(":ui:preBuild").dependsOn(":installGitHook")
