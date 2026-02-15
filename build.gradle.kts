// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.hiltAndroid) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.composeCompiler) apply false

    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent
}

apply {
    from(rootProject.file("install-git-hooks.gradle"))
}
