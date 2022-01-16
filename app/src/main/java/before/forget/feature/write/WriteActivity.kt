package before.forget.feature.write

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        binding.ivWriteBackbtn.setOnClickListener { finish() }
        setContentView(binding.root)
        test()
    }

    private fun test() {
        if (intent.hasExtra("media")) {
            val media = intent.getStringExtra("media")
            binding.tvWriteMedialabel.text = media.toString()
        }
    }
}
