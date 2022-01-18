package before.forget.data.remote.response

data class ResponseMyRecordAll(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<Data>
) {
    data class Data(
        val id: Int,
        val userId: Int,
        val category: Int,
        val date: String,
        val star: Int,
        val title: String,
        val oneline: String
    )
}
