plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "inspector_"
}

dependencies {
    implementation(projects.feature.admin.domain)
}