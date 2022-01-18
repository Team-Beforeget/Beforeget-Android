package before.forget.data.remote.response

data class ResponseDetail(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(

        val id: Int,
        val userId: Int,
        val title: String,
        val category: Int,
        val date: String,
        val star: Int,
        val oneline: List<String>,
        val comment: String,
        val additional: List<Additional>,

    ) {
        data class Additional(
            val type: String,
            val imgUrl1: String,
            val content: String,
            val imgUrl2: String,
        )
    }
}
