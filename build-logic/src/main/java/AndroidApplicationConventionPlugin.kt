import com.android.build.api.dsl.ApplicationExtension
import com.seungsu.convention.DongsaniConfig
import com.seungsu.convention.configureAndroidCompose
import com.seungsu.convention.configureKotlinAndroid
import com.seungsu.convention.implementation
import com.seungsu.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)

                defaultConfig.apply {
                    applicationId = DongsaniConfig.applicationId
                    minSdk = DongsaniConfig.minSdk
                    targetSdk = DongsaniConfig.targetSdk
                    versionCode = DongsaniConfig.versionCode
                    versionName = DongsaniConfig.versionName

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }

                configureAndroidCompose(this)

                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                        excludes += "META-INF/gradle/incremental.annotation.processors"
                    }
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
            }

            dependencies {
                implementation(project(":presentation:common"))
                implementation(project(":presentation:exercise-grass"))
                implementation(project(":presentation:sparring"))
                implementation(project(":designsystem"))
                implementation(project(":data"))
                implementation(project(":domain"))

                implementation(libs.process.phoenix)
            }
        }
    }
}
