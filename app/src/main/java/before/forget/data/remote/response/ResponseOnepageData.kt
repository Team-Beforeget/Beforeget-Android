package before.forget.data.remote.response

data class ResponseOnepageData(
    val start: String,
    val graphic: String,
    val oneline: List<String>,
    val monthly: List<GraphData>,
    val media: List<SentenceData>
)

data class GraphData(
    val count: Int,
    val month: Int
)

data class SentenceData(
    val type: String,
    val count: Int
)