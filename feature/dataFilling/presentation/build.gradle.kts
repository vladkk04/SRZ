plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.feature)
}

android {
    resourcePrefix = "dataFilling_"
}

dependencies {
    implementation(projects.core.commonAndroid)
    implementation(projects.feature.dataFilling.domain)
}