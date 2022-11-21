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
    id(Plugin.MOCKMP).version(Version.MOCKMP)
    id(Plugin.KSP).version(Version.KSP)
    id(Plugin.KOVER)
    id(Plugin.MOKO_RESOURCES)
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
            export(Dependency.MOKO_RESOURCES)
        }
        xcodeConfigurationToNativeBuildType["DebugStaging"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["DebugProduction"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["ReleaseStaging"] = NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["ReleaseProduction"] = NativeBuildType.RELEASE
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
                implementation(Dependency.KTOR_AUTH)
                implementation(Dependency.COROUTINES_TEST)
                implementation(Dependency.KOIN)
                implementation(Dependency.KOIN_TEST)
                implementation(project(Module.JSONAPI_CORE))
                implementation(Dependency.KOTLIN_TEST)
                implementation(Dependency.KOTEST_FRAMEWORK)
                implementation(Dependency.KOTEST_ASSERTIONS)
                implementation(Dependency.KOTEST_PROPERTY)
                implementation(Dependency.MULTIPLATFORM_SETTINGS)
                implementation(Dependency.MULTIPLATFORM_SETTINGS_SERIALIZATION)
                implementation(Dependency.MULTIPLATFORM_SETTINGS_TEST)
                implementation(Dependency.NAPIER)
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
                implementation(Dependency.KOIN_ANDROID)
                implementation(Dependency.SECURITY)
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

dependencies {
    commonMainApi(Dependency.MOKO_RESOURCES)
}

android {
    compileSdk = Android.COMPILE_SDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Android.MIN_SDK
        targetSdk = Android.TARGET_SDK
    }

    testOptions {
        unitTests.all {
            if (it.name == "testReleaseUnitTest") {
                it.extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
                    isDisabled.set(true)
                }
            }
            it.extensions.configure(kotlinx.kover.api.KoverTaskExtension::class) {
            }
        }
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
            BuildKonfig.PRODUCTION_BASE_URL
        )
    }

    defaultConfigs("staging") {
        buildConfigField(
            STRING,
            "CLIENT_ID",
            BuildKonfig.CLIENT_ID_STAGING
        )
        buildConfigField(
            STRING,
            "CLIENT_SECRET",
            BuildKonfig.CLIENT_SECRET_STAGING
        )
        buildConfigField(
            STRING,
            "BASE_URL",
            BuildKonfig.STAGING_BASE_URL
        )
    }
}

mockmp {
    usesHelper = true
}

kover {
    filters {
        classes {
            excludes += listOf("*Test*", "*Mock*", "co.nimblehq.blisskmmic.di*")
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "co.nimblehq.blisskmmic"
    disableStaticFrameworkWarning = true
}
