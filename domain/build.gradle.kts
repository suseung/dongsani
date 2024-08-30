plugins {
    alias(libs.plugins.dongsani.jvm.library)
    alias(libs.plugins.dongsani.jvm.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}
