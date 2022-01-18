package before.forget.feature.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.data.MainAdapter
import before.forget.data.local.MainData
import before.forget.data.remote.BeforegetClient
import before.forget.databinding.ActivityMainBinding
import before.forget.feature.myrecord.MyRecordActivity
import before.forget.feature.report.ReportActivity
import before.forget.feature.write.MediaSelectActivity
import before.forget.util.callback

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
        setContentView(binding.root)
        initMainAdapter()
        onNetwork()
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
                    mainAdapter.mediaList.addAll( // TODO 함수분리
                        listOf<MainData>(
                            MainData(R.drawable.ic_white, "Movie", it.Movie),
                            MainData(R.drawable.ic_white, "Book", it.Book),
                            MainData(R.drawable.ic_white, "TV", it.Tv),
                            MainData(R.drawable.ic_white, "Music", it.Music),
                            MainData(R.drawable.ic_white, "Webtoon", it.Webtoon),
                            MainData(R.drawable.ic_white, "Youtube", it.Youtube)
                        )
                    )
                }

                mainAdapter.notifyDataSetChanged()

                Log.d("dd", "$(int)") // isSuccess = it.success
            }.onError {
            }.enqueue()
    }
}
