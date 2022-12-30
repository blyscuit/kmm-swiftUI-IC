package co.nimblehq.blisskmmic.helpers

import co.nimblehq.blisskmmic.BuildKonfig

class SharedBuildConfig {

    class UITestConfig {

        fun email(): String {
            return BuildKonfig.UI_TEST_EMAIL
        }

        fun password(): String {
            return BuildKonfig.UI_TEST_PASSWORD
        }
    }
}
