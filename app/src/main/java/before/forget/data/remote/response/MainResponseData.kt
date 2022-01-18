package before.forget.data.remote.response

data class MainResponseData(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val Movie: Int,
        val Book: Int,
        val Tv: Int,
        val Music: Int,
        val Webtoon: Int,
        val Youtube: Int
    )
}
