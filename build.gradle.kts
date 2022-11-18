buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependency.KOTLIN_GRADLE_PLUGIN)
        classpath(Dependency.GRADLE)
        classpath(Dependency.DETEKT)
        classpath(Dependency.KOVER)
        classpath(Dependency.KOTLIN_SERIALIZATION)
        classpath(Dependency.BUILD_KONFIG)
        classpath(Dependency.MOKO_RESOURCES_GENERATOR)
    }
}

allprojects {
    apply {
        plugin(Plugin.KOVER)
    }
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
