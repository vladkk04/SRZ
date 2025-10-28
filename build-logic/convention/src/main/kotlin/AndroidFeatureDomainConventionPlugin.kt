import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import com.electro.fish.convention.libs

class AndroidFeatureDomainConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("custom.jvm.library")
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt.core").get())
                add("ksp", libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}