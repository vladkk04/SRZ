plugins {
    alias(libs.plugins.custom.android.feature.domain)
}

dependencies {
    api(projects.core.essentials)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

}