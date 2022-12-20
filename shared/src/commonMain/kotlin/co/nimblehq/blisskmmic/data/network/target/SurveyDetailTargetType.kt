package co.nimblehq.blisskmmic.data.network.target

import co.nimblehq.blisskmmic.data.network.helpers.TargetType
import io.ktor.http.*

class SurveyDetailTargetType(id: String): TargetType<Unit> {

    override var path = "surveys/${id}"
    override var method = HttpMethod.Get
    override var body: Unit? = null
}
