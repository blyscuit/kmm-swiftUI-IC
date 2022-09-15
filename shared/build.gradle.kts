plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
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
        xcodeConfigurationToNativeBuildType["Debug Staging"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Debug Production"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Release Staging"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Release Production"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}

val detekt by configurations.creating

val detektTask = tasks.register<JavaExec>("detekt") {
    main = "io.gitlab.arturbosch.detekt.cli.Main"
    classpath = detekt

    val input = projectDir
    val config = "$projectDir/detekt-config.yml"
    val exclude = ".*/build/.*,.*/resources/.*"
    val report = "xml:./build/reports/detekt/detekt-report.xml"
    val reportHTML = "html:./build/reports/detekt/detekt-report.html"
    val params = listOf("-i", input, "-c", config, "-ex", exclude, "-r", report, "-r", reportHTML)

    args(params)
}

dependencies {
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:1.21.0")
}

tasks.check {
    dependsOn(detektTask)
}