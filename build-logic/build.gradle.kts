plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "dongsani.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidlibrary") {
            id = "dongsani.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "dongsani.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("jvmHilt") {
            id = "dongsani.jvm.hilt"
            implementationClass = "JvmHiltConventionPlugin"
        }
        register("jvmLibrary") {
            id = "dongsani.jvm.library"
            implementationClass = "JvmLibraryPlugin"
        }
        register("androidFeature") {
            id = "dongsani.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}
