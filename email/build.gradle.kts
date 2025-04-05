plugins {
    id("buildsrc.convention.kotlin-jvm")
    alias(libs.plugins.ksp)
    alias(libs.plugins.springBoot)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springDependencyManagement)
}

dependencies {
    implementation(project(":user"))

    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.springBoot)
    implementation(libs.bundles.springBootMail)
    implementation(libs.bundles.springBootKafka)
    implementation(libs.bundles.konvert)
    implementation(libs.bundles.arrow)
    implementation(libs.bundles.mongodb)
    ksp(libs.bundles.konvertKsp)
    ksp(libs.arrowKsp)

    compileOnly(libs.bundles.db)
    compileOnly(libs.bundles.kotlinLogging)
}
