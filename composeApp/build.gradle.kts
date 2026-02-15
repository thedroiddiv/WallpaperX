import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
}

kotlin {

    android {
        namespace = "com.thedroiddiv.wallpaperx"
        compileSdk { version = release(36) }
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "WallpaperXApp"
            isStatic = true
        }
    }
    
    jvm()
    
    js {
        browser()
        binaries.executable()
    }
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.jetbrains.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(libs.jetbrains.compose.runtime)
            implementation(libs.jetbrains.compose.foundation)
            implementation(libs.jetbrains.compose.material3)
            implementation(libs.jetbrains.compose.ui)
            implementation(libs.jetbrains.compose.components.resources)
            implementation(libs.jetbrains.compose.uiToolingPreview)
            implementation(libs.jetbrains.androidx.lifecycle.viewmodelCompose)
            implementation(libs.jetbrains.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.jetbrains.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.jetbrains.kotlinx.coroutinesSwing)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.jetbrains.compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.thedroiddiv.wallpaperx.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.thedroiddiv.wallpaperx"
            packageVersion = "1.0.0"
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.thedroiddiv.wallpaperx"
    generateResClass = auto
}
