plugins {
    id("com.android.application")
    kotlin("android")
}

val compose_compiler = "1.3.0"
val compose = "1.2.1"

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.example.kmmreduxsample.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compose_compiler
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    implementation("androidx.compose.ui:ui-tooling-preview:${compose}")

    // Compose
    debugImplementation("androidx.compose.ui:ui-tooling:${compose}")
    implementation("androidx.compose.ui:ui-tooling:${compose}")
    implementation("androidx.compose.ui:ui:${compose}")
    implementation("androidx.compose.material:material:${compose}")
    implementation("androidx.compose.ui:ui-tooling:${compose}")
    implementation("androidx.compose.ui:ui-util:${compose}")
    implementation( "androidx.navigation:navigation-compose:2.5.1")

    // DI
    implementation("io.insert-koin:koin-core:3.2.0")
    implementation("io.insert-koin:koin-android:3.2.0")
    implementation("io.insert-koin:koin-androidx-compose:3.2.0")
}