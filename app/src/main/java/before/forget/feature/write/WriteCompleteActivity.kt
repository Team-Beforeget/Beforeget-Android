package before.forget.feature.write

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import before.forget.databinding.ActivityWriteCompleteBinding
import before.forget.feature.main.MainActivity
import before.forget.feature.myrecord.MyRecordActivity

class WriteCompleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteCompleteBinding.inflate(layoutInflater)
        initBtnClickListener()
        setContentView(binding.root)
    }

    private fun initBtnClickListener() {
        binding.btnCompleteMain.setOnClickListener {
            ActivityCompat.finishAffinity(this)
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnCompleteRecord.setOnClickListener {
            ActivityCompat.finishAffinity(this)
            startActivity(Intent(this, MainActivity::class.java))
            startActivity(Intent(this, MyRecordActivity::class.java))
        }
    }
}
