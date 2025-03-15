plugins {
    alias(libs.plugins.mohamedie.kotlin.multiplatform.library)
    alias(libs.plugins.mohamedie.kotlin.multiplatform.maven.publish)
}

android {
    namespace = "io.github.mohamed_ie.uistate"
}

mavenPublishing {
    coordinates(
        artifactId = "uistate",
        version = "0.0.1"
    )

    pom {
        name = "UI State"
        description = "The UI State library provides annotations and base classes for managing UI state in Kotlin Multiplatform projects, particularly suited for Jetpack Compose applications."
        inceptionYear = "2025"
    }
}