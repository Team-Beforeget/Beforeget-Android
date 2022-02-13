package before.forget.feature.main

import android.content.Intent
import android.graphics.Movie
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.data.MainAdapter
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.response.MainResponseData
import before.forget.databinding.ActivityMainBinding
import before.forget.feature.myrecord.MyRecordActivity
import before.forget.feature.report.ReportActivity
import before.forget.feature.setting.SettingActivity
import before.forget.feature.write.MediaSelectActivity
import before.forget.util.callback
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainAdapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // 실제 레이아웃 인플레이트 하는부분
        setContentView(binding.root) // 반환부분
        initMainAdapter() // 레이아웃 동작들 정의
        initImage()
        onNetwork()
        setOnClickListener()
    }

    private fun setOnClickListener() {
        with(binding) {
            ivMainStats.setOnClickListener {
                startActivity(Intent(this@MainActivity, ReportActivity::class.java))
            }
            btnMainRecord.setOnClickListener {
                startActivity(Intent(this@MainActivity, MediaSelectActivity::class.java))
            }
            tvMainViewallbtn.setOnClickListener {
                startActivity(Intent(this@MainActivity, MyRecordActivity::class.java))
            }
            ivMainSetting.setOnClickListener {
                startActivity(Intent(this@MainActivity, SettingActivity::class.java))
            }
        }
    }

    private fun initImage() {
        Glide.with(this).load(R.drawable.icn_main_img).override(900, 900)
            .into(binding.ivMainImage)
    }

    private fun initMainAdapter() {
        binding.rvMainBottom.adapter = mainAdapter
    }

    private fun onNetwork() {
        BeforegetClient.mainService
            .getMain()
            .callback
            .onSuccess { response ->
                response.data?.let {
                    binding.tvMainCountlabel.text = it.totalString()
                    addAll()
                }

                mainAdapter.notifyDataSetChanged()

            }.onError {
            }.enqueue()
    }

    private fun addAll() {
        mainAdapter.mediaList.addAll(
            listOf<MainData>(
                MainData(R.drawable.ic_icn_media_movie, "Movie", Movie),
                MainData(R.drawable.ic_icn_media_book, "Book", it.Book),
                MainData(R.drawable.ic_icn_media_tv, "TV", it.TV),
                MainData(R.drawable.ic_icn_media_music, "Music", it.Music),
                MainData(R.drawable.ic_icn_media_webtoon, "Webtoon", it.Webtoon),
                MainData(R.drawable.ic_icn_media_youtube, "Youtube", it.Youtube)
            )
        )
    }

    private fun MainResponseData.totalString() = "${Movie + Book + TV + Music + Webtoon + Youtube}개"
}
