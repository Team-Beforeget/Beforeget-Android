package before.forget.data.remote.response

data class ResponseFilter(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<Data>
) {
    data class Data(
        val id: Int,
        val title: String,
        val category: Int,
        val date: String,
        val star: Int,
        val oneline: String
    )
}

