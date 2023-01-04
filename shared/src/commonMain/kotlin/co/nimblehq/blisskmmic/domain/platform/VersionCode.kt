package co.nimblehq.blisskmmic.domain.platform

interface VersionCode {

    val versionCode: String
    val buildNumber: String
}

expect class VersionCodeImpl(): VersionCode {

    override val versionCode: String
    override val buildNumber: String
}
