plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "GRaffe"

include(":graffe")
include(":intellij")
include(":language-server")
