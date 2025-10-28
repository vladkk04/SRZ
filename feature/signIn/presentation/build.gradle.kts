plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "signIn_"
}

dependencies {
    implementation(projects.feature.signIn.domain)
}