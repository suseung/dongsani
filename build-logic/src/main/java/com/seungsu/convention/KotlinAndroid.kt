package com.seungsu.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        defaultConfig {
            compileSdk = DongsaniConfig.compileSdk
            minSdk = DongsaniConfig.minSdk
        }

        compileOptions {
            sourceCompatibility = DongsaniConfig.javaCompileTarget
            targetCompatibility = DongsaniConfig.javaCompileTarget
        }

        buildFeatures {
            viewBinding = true
            compose = true
        }
    }

    configureKotlin()
}

internal fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = DongsaniConfig.javaCompileTarget.toString()
        }
    }
}
