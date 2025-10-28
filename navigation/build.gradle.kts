plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.custom.hilt)
}

dependencies {
    implementation(projects.feature.signIn.presentation)
    implementation(projects.feature.signUp.presentation)
    implementation(projects.feature.welcome.presentation)

    implementation(libs.androidx.navigation.compose)
    api(libs.hilt.navigation.compose)
}