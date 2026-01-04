plugins {
    alias(libs.plugins.custom.android.feature.domain)
}

dependencies {
    api(projects.core.essentials)
    api(projects.data)

    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}