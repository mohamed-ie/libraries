plugins {
    `kotlin-dsl`
    alias(libs.plugins.mohamedie.kotlin.multiplatform.maven.publish)
}

dependencies {
    implementation(libs.ksp)
}

mavenPublishing {
    coordinates(
        artifactId = "uistate-compiler",
        version = "0.0.1"
    )

    pom {
        name = "UI State Compiler"
        description = "The UI State Compiler is a KSP (Kotlin Symbol Processing) based compiler plugin that generates helper functions and properties for UI state management, working in conjunction with the UI State library."
        inceptionYear = "2025"
    }
}