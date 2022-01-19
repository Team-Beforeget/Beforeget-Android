package before.forget.data.remote.response

data class ResponseGraphData(
    val start: String,
    val recordCount: List<ReportRecordCount>,
    val title: String,
    val comment: String
)

data class ReportRecordCount(
    val count: Int,
    val month: Int
)
