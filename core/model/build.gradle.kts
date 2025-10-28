plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.custom.jvm.library)
}

dependencies {
    implementation(libs.kotlin.serialization.json)
}
