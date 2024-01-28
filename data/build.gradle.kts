import com.android.build.api.dsl.LibraryBuildType
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("kotlin-kapt")
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
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
            gradleLocalProperties(rootDir).getProperty(key),
        )
    }
}

android {
    namespace = "com.dxn.wallpaperx.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 23
        targetSdk = 34
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
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // OkHttp
    val okhttpBom = platform(libs.okhttp3.bom)
    implementation(okhttpBom)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // FIXME: Is this the right way to use koin in data module?
    implementation(libs.koin.compose)
}
