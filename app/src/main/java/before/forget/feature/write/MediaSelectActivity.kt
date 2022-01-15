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

    private fun btnSetOnClickListener() {
        with(binding) {
            clMediaBook.setOnClickListener {
                clMediaBook.isSelected = !clMediaBook.isSelected
                media = tvMediaBook.text.toString()
            }
            clMediaMovie.setOnClickListener {
                clMediaMovie.isSelected = !clMediaMovie.isSelected
                media = tvMediaMovie.text.toString()
            }
            clMediaMusic.setOnClickListener {
                clMediaMusic.isSelected = !clMediaMusic.isSelected
                media = tvMediaMusic.text.toString()
            }
            clMediaTv.setOnClickListener {
                clMediaTv.isSelected = !clMediaTv.isSelected
                media = tvMediaTv.text.toString()
            }
            clMediaWebtoon.setOnClickListener {
                clMediaWebtoon.isSelected = !clMediaWebtoon.isSelected
                media = tvMediaWebtoon.text.toString()
            }
            clMediaYoutube.setOnClickListener {
                clMediaYoutube.isSelected = !clMediaYoutube.isSelected
                media = tvMediaYoutube.text.toString()
            }
            btnMediaNext.setOnClickListener {
                btnMediaNext.isSelected = true
                val mediaToWriteIntent = Intent(this@MediaSelectActivity, WriteActivity::class.java).apply {
                    putExtra("media", media)
                }
                startActivity(mediaToWriteIntent)
            }
            ivMediaClose.setOnClickListener { finish() }
        }
    }

    override fun onStart() {
        binding.clMediaBook.isSelected = false
        binding.clMediaYoutube.isSelected = false
        binding.clMediaWebtoon.isSelected = false
        binding.clMediaTv.isSelected = false
        binding.clMediaMusic.isSelected = false
        binding.clMediaMovie.isSelected = false
        super.onStart()
    }
}
