import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin(Plugin.MULTIPLATFORM)
    kotlin(Plugin.COCOAPODS)
    id(Plugin.ANDROID_LIBRARY)
    id(Plugin.DETEKT)
    kotlin(Plugin.KOTLIN_SERIALIZATION)
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        name = "Shared"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.0"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "Shared"
        }
        xcodeConfigurationToNativeBuildType["Debug Staging"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Debug Production"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release Staging"] = NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Release Production"] = NativeBuildType.RELEASE
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependency.KOTLINX_SERIALIZATION)
                implementation(Dependency.KTOR_CORE)
                implementation(Dependency.KTOR_SERIALIZATION)
                implementation(Dependency.KTOR_LOGGING)
                implementation(Dependency.KTOR_JSON)
                implementation(Dependency.KTOR_CONTENT_NEGOTIATION)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependency.KTOR_ANDROID)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Dependency.KTOR_IOS)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = Android.COMPILE_SDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Android.MIN_SDK
        targetSdk = Android.TARGET_SDK
    }
}

 detekt {
    source = files(
        "./"
    )
    parallel = false
    config = files("../detekt-config.yml")
    buildUponDefaultConfig = false
    disableDefaultRuleSets = false
    ignoreFailures = false
 }

 tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        xml {
            outputLocation.set(file("build/reports/detekt/detekt-result.xml"))
            required.set(true) // reports can also be enabled and disabled at the task level as needed
        }
         html.required.set(true)
    }
 }

tasks.check {
    dependsOn(detekt)
}
