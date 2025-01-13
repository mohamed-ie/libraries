plugins {
    alias(libs.plugins.mohamedie.kotlin.multiplatform.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlinx.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutines.test)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = "com.compose.utils.app_event"
}

mavenPublishing {
    coordinates(
        artifactId = "compose-utils-app-event",
        version = "0.0.1"
    )

    pom {
        name = "App Event"
        description =
            "The App Event Invoker library provides a mechanism to emit and observe application-wide events in a thread-safe and flexible manner. Designed for Kotlin Multiplatform projects, especially Compose-based applications, it facilitates event-driven communication between different parts of an app"
        inceptionYear = "2025"
    }
}
