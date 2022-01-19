data class ResponseRankingData(
    val start: String,
    val arr: List<ReportRankData>,
    val title: String,
    val label: String
)
data class ReportRankData(
    val type: String,
    val count: Int
)
