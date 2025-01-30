import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    antlr
    idea
    application
}

dependencies {
    antlr(libs.antlr4)
}

tasks.generateGrammarSource {
    outputDirectory = file("$rootDir/build/generated/sources/main/java/antlr")

    arguments = arguments +
            "-package" + "com.sarajuhosova.graffe" +
            "-visitor"
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.withType<AntlrTask>())
}
tasks.withType<Jar>().configureEach {
    dependsOn(tasks.withType<AntlrTask>())
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

application {
    mainClass = "com.sarajuhosova.graffe.MainKt"
}
