plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = 34
    defaultConfig {
        minSdk = 23
        targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    namespace = "com.dxn.wallpaperx.domain"
}

dependencies {

    implementation(project(":data"))

    // Coil
    implementation(libs.compose.coil)

    // Hilt
    implementation(libs.hilt.android)
    kapt("com.google.dagger:hilt-compiler:2.48")

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Paging 3
    implementation(libs.androidx.paging.compose)

    val koinVersion = "3.5.3"
    implementation("io.insert-koin:koin-androidx-compose:$koinVersion")
}
