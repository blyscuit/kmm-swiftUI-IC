package co.nimblehq.blisskmmic.domain.model

data class PaginationMeta(
    val page: Int,
    val pages: Int,
    val pageSize: Int,
    val records: Int
)
