plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "language_"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(projects.feature.language.domain)
}