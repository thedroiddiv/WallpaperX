import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.dxn.wallpaperx.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 23
        targetSdk = 36
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    val localProperties = gradleLocalProperties(rootDir, providers)

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "UNSPLASH_API_KEY", "\"${localProperties.getProperty("UNSPLASH_API_KEY")}\"")
            buildConfigField("String", "PIXABAY_API_KEY", "\"${localProperties.getProperty("PIXABAY_API_KEY")}\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "UNSPLASH_API_KEY", "\"${localProperties.getProperty("UNSPLASH_API_KEY")}\"")
            buildConfigField("String", "PIXABAY_API_KEY", "\"${localProperties.getProperty("PIXABAY_API_KEY")}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp3.logging.interceptor)

    // Room
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.room.ktx)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
