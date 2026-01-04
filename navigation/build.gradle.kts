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
    implementation(projects.feature.dataFilling.presentation)
    implementation(projects.feature.forgotPassword.presentation)

    implementation(projects.feature.fisherman.presentation)
    implementation(projects.feature.inspector.presentation)

    implementation(projects.feature.fishingSession.selectFishingSpot.presentation)
    implementation(projects.feature.fishingSession.addCaughtFish.presentation)
    implementation(projects.feature.fishingSession.session.presentation)

    implementation(projects.feature.profile.presentation)
    implementation(projects.feature.language.presentation)
    implementation(projects.feature.licenses.presentation)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.material3.navigation)
    implementation(projects.core.ui)

    api(libs.hilt.navigation.compose)
}