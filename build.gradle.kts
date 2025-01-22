plugins {
    kotlin("jvm") version "2.1.0"
    antlr
    idea
}

group = "com.sarajuhosova.graffe"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.2")
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

tasks.generateGrammarSource {
    outputDirectory = file("$rootDir/build/generated/sources/main/java/antlr")

    arguments = arguments +
            "-package" + "com.sarajuhosova.graffe" +
            "-visitor"
}

sourceSets {
    main {
        java {
            srcDir(tasks.generateGrammarSource)
        }
    }
    test {
        java {
            srcDir(tasks.generateGrammarSource)
        }
    }
}
idea {
    module {
        sourceDirs.add(file("$rootDir/src/main/antlr"))
    }
}
