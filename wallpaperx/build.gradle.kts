import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.androidLint)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {

    android {
        namespace = "com.thedroiddiv.wallpaperx"
        compileSdk { version = release(36) }
    }

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "KaryaUiKit"
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                // Compose
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.jetbrains.compose.components.resources)
                 implementation(libs.jetbrains.compose.uiToolingPreview)

                // Material
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.material.icons.extended)

                // Lifecycle
                 implementation(libs.jetbrains.androidx.lifecycle.runtimeCompose)
            }
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.daiatech.karya.ui"
    generateResClass = auto
}

dependencies {
    androidRuntimeClasspath(libs.jetbrains.compose.uiTooling)
}
