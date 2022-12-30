package co.nimblehq.blisskmmic.presentation.helpers

import co.nimblehq.blisskmmic.MR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class MokoLocalizeTest {

    @Test
    fun `When calling localize with common_error desc - it returns correct StringDesc`() {
        MokoLocalize().localize(MR.strings.common_error.toString()) shouldBe
                StringDesc.Resource(MR.strings.common_error)
    }

    @Test
    fun `When calling localize with an unknown desc - it returns null`() {
        MokoLocalize().localize("unknown") shouldBe null
    }
}
