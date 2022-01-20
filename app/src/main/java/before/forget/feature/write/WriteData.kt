package before.forget.feature.write

data class WriteData( // 기록항목추가 in 글쓰기뷰
    val itemtitle: String,
    val itemcontent: String,
    val hint: String
)
