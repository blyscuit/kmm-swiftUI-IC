package co.nimblehq.blisskmmic.data.model

import co.nimblehq.blisskmmic.domain.model.PaginationMeta
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PaginationMetaApiModel(
    val page: Int,
    val pages: Int,
    @SerialName("page_size")
    val pageSize: Int,
    val records: Int
)

fun PaginationMetaApiModel.toPaginationMeta(): PaginationMeta {
    return PaginationMeta(page, pages, pageSize, records)
}
