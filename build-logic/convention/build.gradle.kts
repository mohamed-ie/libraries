plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.compose.gradle)
    compileOnly(libs.maven.publish.gradle)
}

gradlePlugin {
    plugins {
        register("kotlin-multiplatform-library") {
            id = "mohamedie.kotlin.multiplatform.library"
            implementationClass = "KotlinMultiplatformLibraryConventionPlugin"
        }

        register("kotlin-multiplatform-maven-publis") {
            id = "mohamedie.kotlin.multiplatform.maven.publish"
            implementationClass = "KotlinMultiplatformMavenPublishConventionPlugin"
        }
    }
}