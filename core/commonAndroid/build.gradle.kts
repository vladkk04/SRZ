plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.hilt)
}

android {
    resourcePrefix = "commonAndroid_"
}

dependencies {
    implementation(projects.core.essentials)
    implementation(libs.coroutines.core)
    implementation(libs.androidx.browther)
    implementation(libs.androidx.lifecycle.runtime.compose)
}