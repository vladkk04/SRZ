plugins {
    alias(libs.plugins.custom.jvm.library)
}
dependencies {
    api(libs.kotlinx.collections.immutable)
    implementation(libs.javax.inject)
    implementation(libs.coroutines.core)
}