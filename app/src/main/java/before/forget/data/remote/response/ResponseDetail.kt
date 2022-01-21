package before.forget.data.remote.response

data class ResponseDetail(
    val id: Int,
    val userId: Int,
    val title: String,
    val category: Int,
    val date: String,
    val star: Int,
    val oneline: List<String>,
    val comment: String,
    val additional: List<Additional>?,

) {
    data class Additional(
        var viewType: Int,
        val type: String,
        val imgUrl1: String,
        val content: String,
        val imgUrl2: String,
    )
}
