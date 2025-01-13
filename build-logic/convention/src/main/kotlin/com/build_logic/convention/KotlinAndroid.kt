package com.build_logic.convention

import com.android.build.api.dsl.LibraryExtension
import com.build_logic.convention.utils.versionInt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureKotlinAndroid() {
    extensions.configure<LibraryExtension> {
        compileSdk = versionInt("android-compileSdk")

        defaultConfig {
            minSdk = versionInt("android-minSdk")
        }
    }
}