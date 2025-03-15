pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
includeBuild("build-logic")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "libraries"

//sample
include(":sample:composeApp")

include(":compose:compose_utils_app-event")

include(":uistate:uistate-compiler")
include(":uistate:uistate")

include(":eventbus:eventbus")
include(":eventbus:eventbus-compose")