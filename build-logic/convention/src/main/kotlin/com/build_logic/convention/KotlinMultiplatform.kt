package com.build_logic.convention

import com.build_logic.convention.utils.versionInt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension


fun Project.configureKotlinMultiplatform(
    kotlinMultiplatformExtension: KotlinMultiplatformExtension = extensions.getByType<KotlinMultiplatformExtension>()
) {
    kotlinMultiplatformExtension.apply {
        jvm("desktop")

        androidTarget { publishLibraryVariants("release") }

        iosX64()
        iosArm64()
        iosSimulatorArm64()

        explicitApi = ExplicitApiMode.Strict
        jvmToolchain(versionInt("jdk"))
    }
}