plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "session_"
}

dependencies {
    implementation(projects.feature.fishingSession.session.domain)
    implementation(libs.google.mlkit.image.labeling)
}