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
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.browther)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
}