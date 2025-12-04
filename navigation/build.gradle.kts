plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.custom.hilt)
}

dependencies {
    implementation(projects.feature.welcome.presentation)
    implementation(projects.feature.signIn.presentation)
    implementation(projects.feature.signUp.presentation)
    implementation(projects.feature.forgotPassword.presentation)
    implementation(projects.feature.profile.presentation)
    implementation(projects.feature.selectFishingSpot.presentation)
    implementation(projects.feature.guest.presentation)
   // implementation(projects.feature.supervisor.presentation)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.material3.navigation)
    api(libs.hilt.navigation.compose)
}