plugins {
    alias(libs.plugins.dongsani.android.library)
}

android {
    namespace = "com.seungsu.resource"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}
