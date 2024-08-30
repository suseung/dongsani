plugins {
    alias(libs.plugins.dongsani.android.library)
}

android {
    namespace = "com.seungsu.design"
}

dependencies {
    implementation(project(":resource"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.bundles.androidx.ui.compose)
    debugImplementation(libs.bundles.androidx.ui.compose.debug)
}
