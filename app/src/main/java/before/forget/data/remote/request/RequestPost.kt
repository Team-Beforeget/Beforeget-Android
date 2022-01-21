package before.forget.data.remote.request

data class RequestPost(
    val media: Int,
    val date: String,
    val star: Int,
    val title: String,
    val oneline: List<String>,
    val additional: List<Additional>
) {
    data class Additional(
        val type: String,
        val content: String
    )
}
