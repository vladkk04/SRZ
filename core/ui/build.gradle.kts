plugins {
    alias(libs.plugins.custom.android.library.compose)
    alias(libs.plugins.custom.android.library)
}

dependencies {
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    api(projects.core.essentials)
    api(libs.androidx.material3.icons.extended)
    api(libs.androidx.material3)
    api(libs.androidx.core.ktx)
    api(libs.coil.compose)

}