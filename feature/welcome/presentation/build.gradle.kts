plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "welcome_"
}

dependencies {
    implementation(projects.feature.welcome.domain)
}