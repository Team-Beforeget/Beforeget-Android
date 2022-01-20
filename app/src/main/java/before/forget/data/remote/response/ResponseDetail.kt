package before.forget.data.remote.response
import java.net.URL

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
        val type: String,
        val imgUrl1: URL,
        val content: String,
        val imgUrl2: URL,
    )
}
