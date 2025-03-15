plugins {
    alias(libs.plugins.mohamedie.kotlin.multiplatform.library)
    alias(libs.plugins.mohamedie.kotlin.multiplatform.maven.publish)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlinx.coroutines.core)
                api(projects.eventbus.eventbus)
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
    namespace = "io.github.mohamed_ie.eventbus_compose"
}

mavenPublishing {
    coordinates(
        artifactId = "eventbus-compose",
        version = "0.0.1"
    )

    pom {
        name = "EventBus Composse"
        description =
            "The EventBus library provides a mechanism to emit and observe application-wide events in a thread-safe and flexible manner."
        inceptionYear = "2025"
    }
}
