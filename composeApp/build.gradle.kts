import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinSerialization)
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

        // ┌─ commonMain ──────────────────────────────────────────────────────────┐
        // │   Abstractions only — no Room dependency here.                        │
        // │   JS and WasmJS source sets provide their own DB implementations.     │
        // │                                                                       │
        // │   ┌─ roomMain ───────────────────────────────────────────────────┐    │
        // │   │   Room-backed implementation of the common DB abstraction.   │    │
        // │   │   androidMain / iosMain / jvmMain all depend on this.        │    │
        // │   └──────────────────────────────────────────────────────────────┘    │
        // └───────────────────────────────────────────────────────────────────────┘
        val roomMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.room.runtime)
            }
        }
        // Wire Room-supported platforms to roomMain
        androidMain.get().dependsOn(roomMain)
        iosMain.get().dependsOn(roomMain)
        jvmMain.get().dependsOn(roomMain)

        // ── commonMain ────────────────────────────────────────────────────────────
        commonMain.dependencies {
            implementation(libs.jetbrains.compose.runtime)
            implementation(libs.jetbrains.compose.foundation)
            implementation(libs.jetbrains.compose.material3)
            implementation(libs.jetbrains.compose.ui)
            implementation(libs.jetbrains.compose.components.resources)
            implementation(libs.jetbrains.compose.uiToolingPreview)
            implementation(libs.jetbrains.androidx.lifecycle.viewmodelCompose)
            implementation(libs.jetbrains.androidx.lifecycle.runtimeCompose)
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
            // Kotlinx Serialization
            implementation(libs.kotlinx.serialization.json)
            // Napier
            implementation(libs.napier)
            // Coil3
            implementation(libs.coil3.compose)
            implementation(libs.coil3.network.ktor3)
        }
        commonTest.dependencies {
            implementation(libs.jetbrains.kotlin.test)
        }

        // ── androidMain ───────────────────────────────────────────────────────────
        androidMain.dependencies {
            implementation(libs.jetbrains.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            // Ktor: OkHttp engine for Android
            implementation(libs.ktor.client.okhttp)
            // Android uses the system SQLite — no sqlite-bundled needed
        }

        // ── iosMain ───────────────────────────────────────────────────────────────
        iosMain.dependencies {
            // Ktor: Darwin (NSURLSession) engine for iOS
            implementation(libs.ktor.client.darwin)
            // SQLite driver for Room on iOS (not provided by the OS SDK)
            implementation(libs.sqlite.bundled)
        }

        // ── jvmMain ───────────────────────────────────────────────────────────────
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.jetbrains.kotlinx.coroutinesSwing)
            // Ktor: CIO engine for Desktop JVM
            implementation(libs.ktor.client.cio)
            // SQLite driver for Room on Desktop
            implementation(libs.sqlite.bundled)
        }

        // ── jsMain / wasmJsMain ───────────────────────────────────────────────────
        // Room is not supported on JS/WasmJS. These source sets will contain a
        // custom in-memory (or IndexedDB-backed) implementation of the common
        // DB abstraction defined in commonMain.
        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
        wasmJsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.jetbrains.compose.uiTooling)
    // Room KSP annotation processors — one entry per Room-supported platform
    add("kspAndroid", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspJvm", libs.room.compiler)
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
