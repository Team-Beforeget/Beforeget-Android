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
                binding.clMediaBook.isSelected = !binding.clMediaBook.isSelected
                media = binding.tvMediaBook.text.toString()
            }
            clMediaMovie.setOnClickListener {
                binding.clMediaMovie.isSelected = !binding.clMediaMovie.isSelected
                media = binding.tvMediaMovie.text.toString()
            }
            clMediaMusic.setOnClickListener {
                binding.clMediaMusic.isSelected = !binding.clMediaMusic.isSelected
                media = binding.tvMediaMusic.text.toString()
            }
            clMediaTv.setOnClickListener {
                binding.clMediaTv.isSelected = !binding.clMediaTv.isSelected
                media = binding.tvMediaTv.text.toString()
            }
            clMediaWebtoon.setOnClickListener {
                binding.clMediaWebtoon.isSelected = !binding.clMediaWebtoon.isSelected
                media = binding.tvMediaWebtoon.text.toString()
            }
            clMediaYoutube.setOnClickListener {
                binding.clMediaYoutube.isSelected = !binding.clMediaYoutube.isSelected
                media = binding.tvMediaYoutube.text.toString()
            }
            btnMediaNext.setOnClickListener {
                binding.btnMediaNext.isSelected = true
                val intent = Intent(this@MediaSelectActivity, WriteActivity::class.java).apply {
                    putExtra("media", media)
                }
                startActivity(intent)
            }
            binding.ivMediaClose.setOnClickListener { finish() }
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
