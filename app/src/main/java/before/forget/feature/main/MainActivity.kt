package before.forget.feature.main

import android.content.Intent
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

        binding.ivMainSetting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }
        setContentView(binding.root)
        initMainAdapter()
        initImage()
        onNetwork()
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
                    mainAdapter.mediaList.addAll( // TODO 함수분리
                        listOf<MainData>(
                            MainData(R.drawable.ic_icn_media_movie, "Movie", it.Movie),
                            MainData(R.drawable.ic_icn_media_book, "Book", it.Book),
                            MainData(R.drawable.ic_icn_media_tv, "TV", it.TV),
                            MainData(R.drawable.ic_icn_media_music, "Music", it.Music),
                            MainData(R.drawable.ic_icn_media_webtoon, "Webtoon", it.Webtoon),
                            MainData(R.drawable.ic_icn_media_youtube, "Youtube", it.Youtube)
                        )
                    )
                }

                mainAdapter.notifyDataSetChanged()

                Log.d("dd", "$(int)") // isSuccess = it.success
            }.onError {
            }.enqueue()
    }

    fun MainResponseData.totalString() = "${Movie + Book + TV + Music + Webtoon + Youtube}개"
}
