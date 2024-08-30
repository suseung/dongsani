package com.seungsu.convention

import org.gradle.api.JavaVersion

object DongsaniConfig {
    const val applicationId = "com.seungsu.donsani"

    const val minSdk = 28
    const val targetSdk = 34
    const val compileSdk = 34
    val javaCompileTarget = JavaVersion.VERSION_1_8
    val versionCode = 1
    val versionName = "1.0"
}
