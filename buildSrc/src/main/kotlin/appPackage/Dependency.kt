object Dependency {

    // Kotlin
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val GRADLE = "com.android.tools.build:gradle:${Version.GRADLE}"
    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlin:kotlin-serialization:${Version.KOTLIN}"
    const val KOTLIN_TEST = "org.jetbrains.kotlin:kotlin-test:${Version.KOTLIN}"

    // Kotlinx
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES_CORE}"
    const val KOVER = "org.jetbrains.kotlinx:kover:${Version.KOVER}"
    const val KOTLINX_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Version.KOTLINX_SERIALIZATION}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES_CORE}"

    // DETEKT
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Version.DETEKT}"

    // Ktor
    const val KTOR_CORE = "io.ktor:ktor-client-core:${Version.KTOR}"
    const val KTOR_SERIALIZATION = "io.ktor:ktor-client-serialization:${Version.KTOR}"
    const val KTOR_LOGGING = "io.ktor:ktor-client-logging:${Version.KTOR}"
    const val KTOR_CONTENT_NEGOTIATION = "io.ktor:ktor-client-content-negotiation:${Version.KTOR}"
    const val KTOR_JSON = "io.ktor:ktor-serialization-kotlinx-json:${Version.KTOR}"
    const val KTOR_ANDROID = "io.ktor:ktor-client-android:${Version.KTOR}"
    const val KTOR_IOS = "io.ktor:ktor-client-ios:${Version.KTOR}"
    const val KTOR_MOCK = "io.ktor:ktor-client-mock:${Version.KTOR}"
    const val KTOR_AUTH = "io.ktor:ktor-client-auth:${Version.KTOR}"

    // BuildKonfig
    const val BUILD_KONFIG = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Version.BUILD_KONFIG}"

    // Native Coroutines
    const val NATIVE_COROUTINES = "com.rickclephas.kmp.nativecoroutines:${Version.NATIVE_COROUTINES_KOTLIN}"

    // Koin
    const val KOIN = "io.insert-koin:koin-core:${Version.KOIN}"
    const val KOIN_TEST = "io.insert-koin:koin-test:${Version.KOIN}"
    const val KOIN_ANDROID = "io.insert-koin:koin-android:${Version.KOIN}"

    // KOTEST
    const val KOTEST_FRAMEWORK = "io.kotest:kotest-framework-engine:${Version.KOTEST}"
    const val KOTEST_ASSERTIONS = "io.kotest:kotest-assertions-core:${Version.KOTEST}"
    const val KOTEST_PROPERTY = "io.kotest:kotest-property:${Version.KOTEST}"

    // Key-Value
    const val MULTIPLATFORM_SETTINGS = "com.russhwolf:multiplatform-settings:${Version.MULTIPLATFORM_SETTINGS}"
    const val MULTIPLATFORM_SETTINGS_SERIALIZATION = "com.russhwolf:multiplatform-settings-serialization:${Version.MULTIPLATFORM_SETTINGS}"
    const val MULTIPLATFORM_SETTINGS_TEST = "com.russhwolf:multiplatform-settings-test:${Version.MULTIPLATFORM_SETTINGS}"
    const val SECURITY = "androidx.security:security-crypto-ktx:${Version.SECURITY}"

    // Resources
    const val MOKO_RESOURCES_GENERATOR = "dev.icerock.moko:resources-generator:${Version.MOKO_RESOURCES}"
    const val MOKO_RESOURCES = "dev.icerock.moko:resources:${Version.MOKO_RESOURCES}"

    // Turbine
    const val TURBINE = "app.cash.turbine:turbine:${Version.TURBINE}"

    // Napier
    const val NAPIER = "io.github.aakira:napier:${Version.NAPIER}"
}
