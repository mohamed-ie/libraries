plugins {
    alias(libs.plugins.mohamedie.kotlin.multiplatform.library)
    alias(libs.plugins.mohamedie.kotlin.multiplatform.maven.publish)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutines.test)
            }
        }
    }
}

android {
    namespace = "io.github.mohamed_ie.eventbus"
}

mavenPublishing {
    coordinates(
        artifactId = "eventbus",
        version = "0.0.1"
    )

    pom {
        name = "EventBus"
        description = "The EventBus library provides a mechanism to emit and observe application-wide events in a thread-safe and flexible manner."
        inceptionYear = "2025"
    }
}
