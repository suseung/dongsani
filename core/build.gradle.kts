plugins {
    alias(libs.plugins.dongsani.android.library)
}

android {
    namespace = "com.seungsu.core"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}
