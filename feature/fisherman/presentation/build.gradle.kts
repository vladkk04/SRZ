plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "fisherman_"
}

dependencies {
    implementation(projects.feature.fisherman.domain)
}