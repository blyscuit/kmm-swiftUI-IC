import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin(Plugin.MULTIPLATFORM)
    kotlin(Plugin.COCOAPODS)
    id(Plugin.ANDROID_LIBRARY)
    id(Plugin.DETEKT)
    kotlin(Plugin.KOTLIN_SERIALIZATION)
    id(Plugin.NATIVE_COROUTINES).version(Version.NATIVE_COROUTINES_KOTLIN)
    id(Plugin.BUILD_KONFIG)
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
                implementation(Dependency.KTOR_MOCK)
                implementation(Dependency.COROUTINES_TEST)
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
    compileSdk = Android.CompileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Android.MinSdk
        targetSdk = Android.CompileSdk
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
         html.required.set(true)
         xml.required.set(true)
     }
 }

tasks.check {
    dependsOn(detekt)
}

buildkonfig {
    packageName = "co.nimblehq.blisskmmic"

    defaultConfigs {
        buildConfigField(
            STRING,
            "CLIENT_ID",
            BuildKonfig.CLIENT_ID
        )
        buildConfigField(
            STRING,
            "CLIENT_SECRET",
            BuildKonfig.CLIENT_SECRET
        )
        buildConfigField(
            STRING,
            "BASE_URL",
            BuildKonfig.STAGING_BASE_URL
        )
    }

    defaultConfigs("production") {
        buildConfigField(
            STRING,
            "BASE_URL",
            BuildKonfig.PRODUCTION_BASE_URL
        )
    }

    defaultConfigs("staging") {
        buildConfigField(
            STRING,
            "BASE_URL",
            BuildKonfig.STAGING_BASE_URL
        )
    }
}