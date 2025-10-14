// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.13.0" apply false
    id("org.jetbrains.kotlin.android") version "2.2.20" apply false
    id("com.google.dagger.hilt.android") version "2.57.2" apply false
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"
    id("com.google.devtools.ksp") version "2.2.20-2.0.3" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20"
    alias(libs.plugins.kotlinx.serialization) apply false
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

apply {
    from(rootProject.file("install-git-hooks.gradle"))
}
