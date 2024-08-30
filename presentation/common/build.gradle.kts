plugins {
    alias(libs.plugins.dongsani.android.library)
    alias(libs.plugins.dongsani.android.hilt)
}

android {
    namespace = "com.seungsu.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation(libs.androidx.viewModel.lifecycle)
}
