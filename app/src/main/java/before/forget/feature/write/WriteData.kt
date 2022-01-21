package before.forget.feature.write

import androidx.databinding.ObservableField

data class WriteData( // 기록항목추가 in 글쓰기뷰
    val itemtitle: String,
    val itemcontent: ObservableField<String>,
    val hint: String
)

enum class MediaInputInfo(
    val id: Int,
    val title: String,
    val hint: String
){
    
}
