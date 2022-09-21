pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Survey"
include(":androidApp")
include(":shared")
include(":nimble-jsonapi-kotlin:core")
