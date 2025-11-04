plugins {
    alias(libs.plugins.custom.android.application.compose)
    alias(libs.plugins.custom.android.application)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.custom.hilt)
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.commonAndroid)
    implementation(projects.core.essentials)
    implementation(projects.core.data)

    implementation(projects.navigation)


    implementation(projects.data)

    implementation(libs.androidx.activity.compose)
}