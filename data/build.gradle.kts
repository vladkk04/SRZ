plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.custom.hilt)
}

dependencies {
    api(projects.core.essentials)
    implementation(libs.jwt.decode)
    implementation(libs.datastore.preferences)
    implementation(projects.core.data)
}

