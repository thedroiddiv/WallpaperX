import com.android.build.api.dsl.LibraryBuildType
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

/**
 * Provides values in the BuildConfig file for the [keys] passed
 * All the [keys] should have a corresponding `key=value` entry in local.properties.file
 * @param keys List of all the keys which need to be present in BuildConfig
 */
fun LibraryBuildType.provideStringFromLocalProperties(vararg keys: String) {
    keys.forEach { key ->
        buildConfigField(
            "String",
            key,
            gradleLocalProperties(rootDir).getProperty(key)
        )
    }
}

android {
    namespace = "com.dxn.wallpaperx.data"
    compileSdk = 32
    defaultConfig {
        minSdk = 23
        targetSdk = 32
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug { provideStringFromLocalProperties("PIXABAY_API_KEY", "UNSPLASH_API_KEY") }
        release {
            isMinifyEnabled = false
            provideStringFromLocalProperties("PIXABAY_API_KEY", "UNSPLASH_API_KEY")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // Hilt
    implementation(libs.hilt.android)
    kapt("com.google.dagger:hilt-compiler:2.48")
}
