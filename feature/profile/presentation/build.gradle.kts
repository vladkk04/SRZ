plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "profile_"
}

dependencies {
    implementation(projects.feature.profile.domain)
    implementation(libs.androidx.appcompat)
}