package before.forget.feature.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBtnClickListener()
    }

    private fun initBtnClickListener() {
        binding.btnSettingBack.setOnClickListener {
            finish()
        }
    }
}
