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

        binding.clMediaBook.setOnClickListener {
            binding.clMediaBook.isSelected = true
            media = binding.tvMediaBook.text.toString()
        }
        binding.clMediaMovie.setOnClickListener {
            binding.clMediaMovie.isSelected = true
            media = binding.tvMediaMovie.text.toString()
        }
        binding.clMediaMusic.setOnClickListener {
            binding.clMediaMusic.isSelected = true
            media = binding.tvMediaMusic.text.toString()
        }
        binding.clMediaTv.setOnClickListener {
            binding.clMediaTv.isSelected = true
            media = binding.tvMediaTv.text.toString()
        }
        binding.clMediaWebtoon.setOnClickListener {
            binding.clMediaWebtoon.isSelected = true
            media = binding.tvMediaWebtoon.text.toString()
        }
        binding.clMediaYoutube.setOnClickListener {
            binding.clMediaYoutube.isSelected = true
            media = binding.tvMediaYoutube.text.toString()
        }
        binding.btnMediaNext.setOnClickListener {
            binding.btnMediaNext.isSelected = true
            val intent = Intent(this, WriteActivity::class.java).apply {
                putExtra("media", media)
            }
            startActivity(intent)
        }
        binding.ivMediaClose.setOnClickListener { finish() }
        setContentView(binding.root)
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
