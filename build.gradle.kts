plugins {
    id("org.jetbrains.dokka") version "1.9.20"
}

allprojects {
    group = "io.eio.sdk"
    version = "0.1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "com.android.library")
    apply(plugin = "org.jetbrains.kotlin.android")

    android {
        compileSdk = 28
        defaultConfig {
            minSdk = 21
            targetSdk = 28
        }
        buildFeatures { buildConfig = false }
    }

    dependencies {
        implementation("androidx.core:core-ktx:1.12.0")
    }
}
