import org.jetbrains.kotlin.serialization.builtins.main

plugins {
    kotlin("jvm") version "1.9.22"
    application
    id("org.graalvm.buildtools.native") version "0.9.28"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("io.kotest:kotest-bom:5.8.0"))
    testImplementation("io.kotest:kotest-runner-junit5")
}

application {
    mainClass = "io.github.helpermethod.kotest.graalvm.ReproducerKt"
}

graalvmNative {
    agent {
        metadataCopy {
            mergeWithExisting = true
            inputTaskNames.add("test")
            outputDirectories.add("src/main/resources/META-INF/native-image/reproducer")
        }
    }
    binaries {
        named("test") {
            buildArgs("--trace-object-instantiation=kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInClassDescriptorFactory")
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
