plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "supervisor_"
}

dependencies {
    implementation(projects.feature.admin.domain)
}