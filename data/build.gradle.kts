plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kotlinSerialization)
    kotlin("kapt")
}

android {
    namespace = "com.seungsu.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.okhttp3)
    implementation(libs.hilt.android)
    implementation(libs.hilt.compiler)
    implementation(libs.bundles.androidx.room)
    implementation(libs.androidx.datastore)

    kapt(libs.androidx.room.compiler)
    kapt(libs.hilt.compiler)
}
