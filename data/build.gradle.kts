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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.11.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Room
    implementation("androidx.room:room-runtime:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.4.3")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")
}
