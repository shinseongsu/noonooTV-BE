rootProject.name = "noonoo"

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    "user",
    "email"
)
