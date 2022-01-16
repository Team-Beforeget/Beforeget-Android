package before.forget.feature.myrecord

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import before.forget.data.local.MyRecordData
import before.forget.databinding.ActivityMyrecodBinding
import before.forget.feature.filter.FilterBottomSheetFragment
import before.forget.feature.filter.FilterStarFragment
import before.forget.feature.filter.FilterTermFragment

class MyRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyrecodBinding
    private val filterTermFragment = FilterTermFragment()
    private val filterStarFragment = FilterStarFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyrecodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtonFilter()
        initClickFilterButtonEvent()
        initMyRecordAdapter()
        binding.btnScore.text = "별점"

        filterTermFragment.setTermButtonClickListener {
            binding.btnTerm.text = it
        }
        filterStarFragment.setCallbackButtonClickListener {
            binding.btnScore.text = "선택되었다"
            Log.d("보내진걸까...?", "받았니?흑")
        }
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
            binding.btnMedia.text = it
        }
        filterBottomSheetFragment.startCallback {
            binding.btnScore.isActivated = true
        }
        filterBottomSheetFragment.show(supportFragmentManager, filterBottomSheetFragment.tag)
    }

    private fun getMediaFromMainActivity() {
        // 메인뷰 완성된거 풀받으면 실행하기
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
