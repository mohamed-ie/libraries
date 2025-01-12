import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.vanniktech.mavenPublish)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    jvm("desktop")
    androidTarget {
        publishLibraryVariants("release")
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
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

        val desktopTest by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

android {
    namespace = "com.compose.utils.app_event"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(
        groupId = "io.github.mohamed-ie",
        artifactId = "compose-utils-app-event",
        version = "0.0.1"
    )

    pom {
        name = "App Event"
        description = "The App Event Invoker library provides a mechanism to emit and observe application-wide events in a thread-safe and flexible manner. Designed for Kotlin Multiplatform projects, especially Compose-based applications, it facilitates event-driven communication between different parts of an app"
        inceptionYear = "2025"
        url = "https://github.com/mohamed-ie/libraries"

        licenses {
            license {
                name = "Apache License, Version 2.0"
                url = "http://www.apache.org/licenses/LICENSE-2.0"
                distribution = "repo"
            }
        }

        developers {
            developer {
                id = "mohamed-ie"
                name = "Mohamed Ibrahim El Sayed Mostafa"
                url = "https://github.com/mohamed-ie"
            }
        }

        scm { url = "https://maven.pkg.github.com/mohamed-ie/libraries" }
    }
}
