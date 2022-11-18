package co.nimblehq.blisskmmic.presentation.helpers

import co.nimblehq.blisskmmic.MR
import dev.icerock.moko.resources.AssetResource
import dev.icerock.moko.resources.ResourceContainer
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class MokoLocalize {
    fun localize(id: String?): StringDesc? {
        if (id == MR.strings.common_error.toString()) {
            return StringDesc.Resource(MR.strings.common_error)
        }
        return null
    }
}
