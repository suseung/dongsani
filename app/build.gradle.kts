plugins {
    alias(libs.plugins.dongsani.android.application)
    alias(libs.plugins.dongsani.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.seungsu.donsani"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.bundles.androidx.ui.compose)
    debugImplementation(libs.bundles.androidx.ui.compose.debug)

}
