package before.forget.feature.write

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.databinding.ActivityMediaSelectBinding

class MediaSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMediaSelectBinding
    private lateinit var media: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaSelectBinding.inflate(layoutInflater)

        btnSetOnClickListener()

        setContentView(binding.root)
    }

    private fun btnSetOnClickListener() { // 1. 하나만 셀렉트 2. on/off 셀렉트 3. 다음버튼 활성화
        with(binding) {
            clMediaBook.setOnClickListener {
                clMediaBook.isSelected = !clMediaBook.isSelected
                clMediaMovie.isSelected = false
                clMediaMusic.isSelected = false
                clMediaTv.isSelected = false
                clMediaWebtoon.isSelected = false
                clMediaYoutube.isSelected = false
                media = tvMediaBook.text.toString()
                btnMediaNextActivate()
            }
            clMediaMovie.setOnClickListener {
                clMediaMovie.isSelected = !clMediaMovie.isSelected
                clMediaBook.isSelected = false
                clMediaMusic.isSelected = false
                clMediaTv.isSelected = false
                clMediaWebtoon.isSelected = false
                clMediaYoutube.isSelected = false
                media = tvMediaMovie.text.toString()
                btnMediaNextActivate()
            }
            clMediaMusic.setOnClickListener {
                clMediaMusic.isSelected = !clMediaMusic.isSelected
                clMediaBook.isSelected = false
                clMediaMovie.isSelected = false
                clMediaTv.isSelected = false
                clMediaWebtoon.isSelected = false
                clMediaYoutube.isSelected = false
                media = tvMediaMusic.text.toString()
                btnMediaNextActivate()
            }
            clMediaTv.setOnClickListener {
                clMediaTv.isSelected = !clMediaTv.isSelected
                clMediaBook.isSelected = false
                clMediaMovie.isSelected = false
                clMediaMusic.isSelected = false
                clMediaWebtoon.isSelected = false
                clMediaYoutube.isSelected = false
                media = tvMediaTv.text.toString()
                btnMediaNextActivate()
            }
            clMediaWebtoon.setOnClickListener {
                clMediaWebtoon.isSelected = !clMediaWebtoon.isSelected
                clMediaBook.isSelected = false
                clMediaMovie.isSelected = false
                clMediaMusic.isSelected = false
                clMediaTv.isSelected = false
                clMediaYoutube.isSelected = false
                media = tvMediaWebtoon.text.toString()
                btnMediaNextActivate()
            }
            clMediaYoutube.setOnClickListener {
                clMediaYoutube.isSelected = !clMediaYoutube.isSelected
                clMediaBook.isSelected = false
                clMediaMovie.isSelected = false
                clMediaMusic.isSelected = false
                clMediaTv.isSelected = false
                clMediaWebtoon.isSelected = false
                media = tvMediaYoutube.text.toString()
                btnMediaNextActivate()
            }
            btnMediaNext.setOnClickListener { // 다음페이지로 선택미디어유형에 대한 인텐트 전달
                val mediaToWriteIntent =
                    Intent(this@MediaSelectActivity, WriteActivity::class.java).apply {
                        putExtra("media", media)
                    }
                startActivity(mediaToWriteIntent)
            }
            ivMediaClose.setOnClickListener { finish() } // 엑티비티 종료
        }
    }

    override fun onStart() { // 다른 뷰 왕복 시 모든 버튼 비활성화 상태 세팅
        binding.clMediaBook.isSelected = false
        binding.clMediaYoutube.isSelected = false
        binding.clMediaWebtoon.isSelected = false
        binding.clMediaTv.isSelected = false
        binding.clMediaMusic.isSelected = false
        binding.clMediaMovie.isSelected = false
        binding.btnMediaNext.isEnabled = false // 비활성화 세팅
        super.onStart()
    }

    private fun check() = // 버튼 최소1개 -> true , 다 꺼져있으면 -> false
        with(binding) {
            clMediaBook.isSelected || clMediaMovie.isSelected || clMediaMusic.isSelected || clMediaTv.isSelected || clMediaWebtoon.isSelected || clMediaYoutube.isSelected
        }

    private fun btnMediaNextActivate() { // check -> true일 시 다음버튼 활성화
        binding.btnMediaNext.isEnabled = check()
    }
}
