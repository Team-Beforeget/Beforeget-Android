package before.forget.feature.myrecord

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.data.local.MyRecordData
import before.forget.databinding.ActivityMyrecodBinding
import before.forget.feature.filter.FilterBottomSheetFragment

class MyRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyrecodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyrecodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnMedia.text = "미디어"
        binding.btnBack.setOnClickListener {
            finish()
        }
        initButtonFilter()
        initClickFilterButtonEvent()
        initMyRecordAdapter()
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

        val myRecordDataAdapter = MyRecordAdapter()
        binding.rcvMyrecord.adapter = myRecordDataAdapter

        myRecordDataAdapter.recordList.addAll(
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
        )
        myRecordDataAdapter.notifyDataSetChanged()
    }

    private fun showBottomSheet() {
        val filterBottomSheetFragment = FilterBottomSheetFragment()
        filterBottomSheetFragment.setMediaCallback {
            var mediaList = mutableListOf<String>("MOVIE", "BOOK", "MUSIC", "YOUTUBE", "WEBTOON", "TV")
            binding.btnMedia.text = mediaList[it]
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
        }
    }
    private fun initButtonFilter() {
        binding.btnMedia.isActivated = false
        binding.btnScore.isActivated = false
        binding.btnTerm.isActivated = false
    }
}
