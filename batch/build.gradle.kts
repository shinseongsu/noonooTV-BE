plugins {
    id("buildsrc.convention.kotlin-jvm")
    alias(libs.plugins.springBoot)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springDependencyManagement)
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.exposed)
    implementation(libs.bundles.mongodb)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.springBatch)
    implementation(libs.bundles.springBootKafka)
}
