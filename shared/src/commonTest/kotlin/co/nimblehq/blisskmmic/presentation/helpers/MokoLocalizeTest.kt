package co.nimblehq.blisskmmic.presentation.helpers

import co.nimblehq.blisskmmic.MR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class MokoLocalizeTest {

    @Test
    fun `When calling localize with common_error desc, it returns correct StringDesc`() {
        StringDesc.Resource(MR.strings.common_error) shouldBe
                MokoLocalize().localize(MR.strings.common_error.toString())
    }
}
