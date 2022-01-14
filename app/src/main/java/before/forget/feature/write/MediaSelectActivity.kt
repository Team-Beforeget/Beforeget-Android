package before.forget.feature.write

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.databinding.ActivityMediaSelectBinding

class MediaSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMediaSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaSelectBinding.inflate(layoutInflater)

        binding.clMediaBook.setOnClickListener {
            initClickEvent()
            binding.clMediaBook.isSelected = true
        }
        binding.clMediaMovie.setOnClickListener { initClickEvent() }
        binding.clMediaMusic.setOnClickListener { initClickEvent() }
        binding.clMediaTv.setOnClickListener { initClickEvent() }
        binding.clMediaWebtoon.setOnClickListener { initClickEvent() }
        binding.clMediaYoutube.setOnClickListener { initClickEvent() }
        binding.ivMediaClose.setOnClickListener { finish() }
        binding.btnMediaNext.setOnClickListener { initClickEvent() }
        setContentView(binding.root)
    }

    private fun initClickEvent() {
        startActivity(Intent(this, WriteActivity::class.java))
    }
}
