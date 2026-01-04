plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "selectFishingSpot_"
}

dependencies {
    implementation(projects.feature.fishingSession.selectFishingSpot.domain)
}