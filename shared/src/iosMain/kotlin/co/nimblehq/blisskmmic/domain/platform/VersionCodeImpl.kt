package co.nimblehq.blisskmmic.domain.platform

import platform.Foundation.NSBundle

actual class VersionCodeImpl: VersionCode {

    actual override val versionCode =
        NSBundle
            .mainBundle
            .infoDictionary
            ?.get("CFBundleShortVersionString")
            .toString()
    actual override val buildNumber =
        NSBundle
            .mainBundle
            .infoDictionary
            ?.get("CFBundleVersion")
            .toString()
}
