plugins {
    kotlin("jvm") version "2.1.0"
    java
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "kotlin")

    group = "com.sarajuhosova.graffe"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(kotlin("test"))
        testImplementation("org.assertj:assertj-core:3.27.3")
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
