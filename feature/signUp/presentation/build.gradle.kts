plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "signUp_"
}

dependencies {
    implementation(projects.feature.signUp.domain)
}