import com.seungsu.convention.debugImplementation
import com.seungsu.convention.implementation
import com.seungsu.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

internal class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dongsani.android.library")
            }

            dependencies {
                implementation(project(":designsystem"))
                implementation(project(":resource"))
                implementation(project(":domain"))
                implementation(project(":core"))
                implementation(project(":presentation:common"))

                implementation(libs.androidx.core.ktx)
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.material3)
                implementation(libs.coil.compose)

                implementation(libs.androidx.hilt.navigation.compose)
                implementation(libs.bundles.androidx.ui.compose)
                debugImplementation(libs.bundles.androidx.ui.compose.debug)
                implementation(platform(libs.androidx.compose.bom))
                implementation(libs.androidx.preference.ktx)
            }
        }
    }
}
