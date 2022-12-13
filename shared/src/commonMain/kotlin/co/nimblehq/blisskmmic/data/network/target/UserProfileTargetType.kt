package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import io.ktor.http.*

class UserProfileTargetType(): TargetType<Unit> {

    override var path = "me"
    override var method = HttpMethod.Get
    override var body: Unit? = null
}
