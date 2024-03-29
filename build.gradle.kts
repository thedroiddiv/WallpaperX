// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.ksp)
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}

apply { from(rootProject.file("install-git-hooks.gradle")) }
tasks.getByPath(":app:preBuild").dependsOn(":installGitHook")
