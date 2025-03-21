import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.assign

class KotlinMultiplatformMavenPublishConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.vanniktech.maven.publish")
        }

        extensions.configure<MavenPublishBaseExtension> {
            publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

            signAllPublications()

            coordinates(groupId = "io.github.mohamed-ie")

            pom {
                inceptionYear = "2025"
                url.set("https://github.com/mohamed-ie/libraries")

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
                        name = "Mohamed Ibrahim"
                        url = "https://github.com/mohamed-ie"
                    }
                }

                scm {
                    url = "https://github.com/mohamed-ie/libraries/"
                    connection = "scm:git:git://github.com/mohamed-ie/libraries.git"
                    developerConnection = "scm:git:ssh://git@github.com/mohamed-ie/libraries.git"
                }
            }
        }
    }
}