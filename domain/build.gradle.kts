plugins {
    alias(libs.plugins.kotlinJvm)
    kotlin("kapt")
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)
}
