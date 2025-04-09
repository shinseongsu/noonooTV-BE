import com.google.protobuf.gradle.id

plugins {
    id("buildsrc.convention.kotlin-jvm")
    alias(libs.plugins.springBoot)
    alias(libs.plugins.kotlinPluginSpring)
    alias(libs.plugins.springDependencyManagement)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.graalvm)
}

dependencyManagement {
    imports {
        mavenBom(libs.springBootGrpcBom.get().toString())
    }
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.springBoot)
    implementation(libs.springBootWebflux)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.grpc)
    implementation(libs.bundles.jwt)
    implementation(libs.springBootRedisReactive)

    runtimeOnly(libs.bundles.jwtRuntime)
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:4.30.2"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.71.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.1:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {
                    option("jakarta_omit")
                    option("@generated=omit")
                }
                id("grpckt") {
                    option("jakarta_omit")
                    option("@generated=omit")
                }
            }
        }
    }
}
