plugins {
    alias(libs.plugins.dongsani.android.library)
    alias(libs.plugins.dongsani.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.seungsu.data"
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.okhttp3)
    implementation(libs.bundles.androidx.room)
    implementation(libs.androidx.datastore)

    kapt(libs.androidx.room.compiler)
}
