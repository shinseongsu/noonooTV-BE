package buildsrc.convention

import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jmailen.gradle.kotlinter.tasks.InstallPreCommitHookTask
import org.jmailen.gradle.kotlinter.tasks.InstallPrePushHookTask

plugins {
    kotlin("jvm")
    id("org.jmailen.kotlinter")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
