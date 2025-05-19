import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.styleloop"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.styleloop"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Navigation Components
    implementation("androidx.navigation:navigation-runtime-ktx:2.8.9")
    implementation("androidx.navigation:navigation-compose:2.8.9") // Latest stable version

    // Compose Libraries
    implementation("androidx.compose.material3:material3:1.1.0") // Material 3 for Compose
    implementation("androidx.compose.ui:ui:1.5.0") // Latest Compose UI version
    implementation("androidx.activity:activity-compose:1.7.0") // Compose support for activities

    // Image Loading (Coil)
    implementation("io.coil-kt:coil-compose:2.1.0") // For image loading in Compose

    // Lifecycle Libraries
    implementation ("com.google.firebase:firebase-auth:21.0.1") // Firebase authentication
    implementation ("com.google.firebase:firebase-database:20.0.3")// Firebase Realtime Database

    implementation("androidx.compose.ui:ui:1.4.0")// for Compose UI components
    implementation("androidx.compose.runtime:runtime-livedata:1.4.0")// for LiveData support in Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0") // for viewModel() composable


        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0") // Ensure you have this for coroutines support
        implementation("androidx.compose.runtime:runtime-livedata:1.0.0") // For Compose runtime if not already included
    }













