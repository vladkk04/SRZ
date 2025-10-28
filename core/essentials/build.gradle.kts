plugins {
    alias(libs.plugins.custom.jvm.library)
}
dependencies {
    implementation(libs.javax.inject)
    implementation(libs.coroutines.core)
}