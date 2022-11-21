plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Android.COMPILE_SDK
    defaultConfig {
        applicationId = "co.nimblehq.blisskmmic.android"
        minSdk = Android.MIN_SDK
        targetSdk = Android.TARGET_SDK
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    packagingOptions {
        resources.excludes.add("**/attach_hotspot_windows.dll")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("META-INF/licenses/ASM")
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    // Koin
    implementation(Dependency.KOIN)
    implementation(Dependency.KOIN_ANDROID)
}
