package before.forget.feature.myrecord

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.response.ResponseMyRecordAll
import before.forget.databinding.ActivityMyrecodBinding
import before.forget.feature.filter.FilterBottomSheetFragment
import before.forget.feature.write.MediaSelectActivity
import before.forget.util.callback

class MyRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyrecodBinding
    val myRecordDataAdapter = MyRecordAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyrecodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnMedia.text = "미디어"

        window.statusBarColor = Color.parseColor("#FFFFFFFF")

        binding.btnPlus.setOnClickListener {
            startActivity(Intent(this, MediaSelectActivity::class.java))
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
        initButtonFilter()
        initClickFilterButtonEvent()
        initMyRecordAdapter()
        onNetwork()
        getMediaFromMainActivity()
    }

    private fun initClickFilterButtonEvent() {
        binding.btnMedia.setOnClickListener {
            showBottomSheet()
        }

        binding.btnTerm.setOnClickListener {
            showBottomSheet()
        }

        binding.btnScore.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun initMyRecordAdapter() {

        binding.rcvMyrecord.adapter = myRecordDataAdapter

        /*myRecordDataAdapter.recordList.addAll(
            listOf<MyRecordData>(
                MyRecordData("내가 널 사랑할 수 없는 1가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 1),
                MyRecordData("내가 널 사랑할 수 없는 2가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 2),
                MyRecordData("내가 널 사랑할 수 없는 3가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
                MyRecordData("내가 널 사랑할 수 없는 4가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
                MyRecordData("내가 널 사랑할 수 없는 5가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 4),
                MyRecordData("내가 널 사랑할 수 없는 6가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 5),
                MyRecordData("내가 널 사랑할 수 없는 7가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 5),
                MyRecordData("내가 널 사랑할 수 없는 8가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
                MyRecordData("내가 널 사랑할 수 없는 9가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
                MyRecordData("내가 널 사랑할 수 없는 10가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
            )
        )*/
    }

    private fun showBottomSheet() {
        val filterBottomSheetFragment = FilterBottomSheetFragment()
        filterBottomSheetFragment.setMediaCallback { selectNumber, trueCounting ->
            var mediaList =
                mutableListOf<String>("MOVIE", "BOOK", "MUSIC", "YOUTUBE", "WEBTOON", "TV")
            val selectedMedia = mediaList[selectNumber]

            if (trueCounting >= 2) {
                binding.btnMedia.text = selectedMedia + " " + "외" + " " + "${trueCounting - 1}"
            } else {
                binding.btnMedia.text = selectedMedia
            }

            binding.btnMedia.isActivated = true
        }
        filterBottomSheetFragment.setStarScoreCallback {
            binding.btnScore.isActivated = true
        }
        filterBottomSheetFragment.setTermCallback {
            binding.btnTerm.text = it
            binding.btnTerm.isActivated = true
        }
        filterBottomSheetFragment.show(supportFragmentManager, filterBottomSheetFragment.tag)
    }

    private fun getMediaFromMainActivity() {
        if (intent.hasExtra("media")) {
            val media = intent.getStringExtra("media")
            binding.btnMedia.text = media.toString()
            binding.btnMedia.isActivated = true
        }
    }

    private fun initButtonFilter() {
        binding.btnMedia.isActivated = false
        binding.btnScore.isActivated = false
        binding.btnTerm.isActivated = false
    }

    private fun onNetwork() {
        BeforegetClient.myRecordAllService
            .getMyrordAllData()
            .callback
            .onSuccess {
                val dataSize = myRecordDataAdapter.itemCount
                myRecordDataAdapter.recordList.addAll(it.data ?: listOf<ResponseMyRecordAll>())
                myRecordDataAdapter.notifyDataSetChanged()
                Log.d("서버통신 데이타 사이즈", "$dataSize")
            }
            .enqueue()
    }
}
