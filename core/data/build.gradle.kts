plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.custom.hilt)
}

dependencies {
    implementation(projects.core.essentials)
    api(libs.bundles.ktor)
    api(libs.kotlin.serialization.json)
}
