package before.forget.feature.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.data.MainAdapter
import before.forget.data.local.MainData
import before.forget.databinding.ActivityMainBinding
import before.forget.feature.myrecord.MyRecordActivity
import before.forget.feature.report.ReportActivity
import before.forget.feature.write.MediaSelectActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.ivMainStats.setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }

        binding.btnMainRecord.setOnClickListener {
            startActivity(Intent(this, MediaSelectActivity::class.java))
        }

        binding.tvMainViewallbtn.setOnClickListener {
            startActivity(Intent(this, MyRecordActivity::class.java))
        }
        setContentView(binding.root)
        initMainAdapter()
    }

    private fun initMainAdapter() {

        val mainAdapter = MainAdapter()
        binding.rvMainBottom.adapter = mainAdapter

        mainAdapter.mediaList.addAll(
            listOf<MainData>(
                MainData(R.drawable.ic_white, 5, "Movie"),
                MainData(R.drawable.ic_white, 10, "Book"),
                MainData(R.drawable.ic_white, 5, "TV"),
                MainData(R.drawable.ic_white, 9, "Music"),
                MainData(R.drawable.ic_white, 5, "Webtoon"),
                MainData(R.drawable.ic_white, 7, "Youtube")
            )
        )
        mainAdapter.notifyDataSetChanged()
    }
}
