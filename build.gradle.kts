plugins {
    kotlin("jvm") version "2.1.0"
    java
}

allprojects {
    val libraries = rootProject.libs

    apply(plugin = "kotlin")
    apply(plugin = "java")

    group = "com.sarajuhosova.graffe"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(kotlin("test"))
        testImplementation(libraries.assertj)
    }

    tasks.test {
        useJUnitPlatform()
    }
    kotlin {
        jvmToolchain(21)
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}
