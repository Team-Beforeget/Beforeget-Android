package before.forget.data.remote.response

data class ReponseSentenceData(
    val start: String,
    val oneline: TypeSentenceData
)

data class TypeSentenceData(
    val Movie: List<String>,
    val Book: List<String>,
    val TV: List<String>,
    val Music: List<String>,
    val Webtoon: List<String>,
    val Youtube: List<String>
)