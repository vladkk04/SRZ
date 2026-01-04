plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "licenses_"
}

dependencies {
    implementation(projects.feature.licenses.domain)
}