import com.build_logic.convention.configureKotlinAndroid
import com.build_logic.convention.configureKotlinMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinMultiplatformLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.multiplatform")
            apply("com.android.library")
        }

        configureKotlinMultiplatform()
        configureKotlinAndroid()
    }
}