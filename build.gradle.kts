// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.application") version "8.0.2" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
}

apply {
    from(rootProject.file("install-git-hooks.gradle"))
}

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        disabledRules.set(listOf("no-wildcard-imports", "filename", "enum-entry-name-case"))
    }
}

tasks.getByPath(":ui:preBuild").dependsOn(":installGitHook")
