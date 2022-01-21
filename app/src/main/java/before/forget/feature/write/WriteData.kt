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
) {
    ACTOR(0, "배우", "누가 영화에 출연했나요?"),
    DIRECTOR(1, "감독", "영화의 줄거리는 무엇인가요?"),
    GENRE(2, "장르", "영화의 장르는 무엇인가요?"),
    STORY(3, "줄거리", "영화의 줄거리는 무엇인가요?"),
    FAMOUS_LINE(4, "명대사", "어떤 대사가 기억에 남나요?"),
    OST(5, "OST", "마음에 드는 영화의 OST는 무엇이었나요?"),
    POSTER(6, "포스터", "이곳을 눌러 영화의 포스터를 추가해주세요"),
    USER_ADD(-1, "항목 이름을 직접 설정할 수 있어요.", "적고 싶은 내용이나 이미지를  적을 수 있어요.");

    fun isEqualOf(id: Int) = (this.id == id)

    companion object {
        fun findHintById(id: Int): MediaInputInfo {
            return values().find { it.isEqualOf(id) }
                ?: throw IllegalArgumentException("영화에는 $id 에 해당하는 항목이 없습니다.")
        }
    }
}
