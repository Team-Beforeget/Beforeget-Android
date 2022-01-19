package before.forget.feature.myrecord

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.response.ResponseMyRecordAll
import before.forget.data.remote.tempToken
import before.forget.databinding.ActivityMyrecodBinding
import before.forget.feature.filter.FilterBottomSheetFragment
import before.forget.feature.write.MediaSelectActivity
import before.forget.util.callback

class MyRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyrecodBinding
    private val filterBottomSheetFragment = FilterBottomSheetFragment()
    private var selectedTerm = -1
    private var selectedStar = "-1"
    private var selectedMedia = "-1"
    private val myRecordDataAdapter = MyRecordAdapter()
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
        test()
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
    }

    private fun showBottomSheet() {
        filterBottomSheetFragment.show(supportFragmentManager, filterBottomSheetFragment.tag)
    }

    private fun test() {
        filterBottomSheetFragment.setTermCallback {
            binding.btnTerm.text = it
            when (it) {
                "2주" -> selectedTerm = 0
                "1개월" -> selectedTerm = 1
                "3개월" -> selectedTerm = 2
            }
            binding.btnTerm.isActivated = true
            Log.d("term 실행됨", "{$selectedTerm}")
            onFilterDataNetwork(selectedTerm, selectedMedia, selectedStar)
        }

        filterBottomSheetFragment.setStarScoreCallback { starListWithSelection, starTrueCounting ->
            binding.btnScore.isActivated = true
            val starList = mutableListOf<Int>(1, 2, 3, 4, 5)

            var selectedStarText = ""

            when (starTrueCounting) {
                0 -> selectedStarText = "-1"
                1 -> selectedStarText = "${starListWithSelection.indexOf(true) + 1}"
                else -> {
                    for (i in 0 until starListWithSelection.size) {
                        if (starListWithSelection[i]) {
                            selectedStarText += "${i + 1}" + ","
                        }
                    }
                    selectedStarText = selectedStarText.dropLast(1)
                }
            }
            selectedStar = selectedStarText
            Log.d("선택된 별점", selectedStar)
            onFilterDataNetwork(selectedTerm, selectedMedia, selectedStar)
        }

        filterBottomSheetFragment.setMediaCallback { mediaListWithSelection, trueCounting, selectedMediaFistNumber ->
            var mediaList =
                mutableListOf<String>("MOVIE", "BOOK", "MUSIC", "YOUTUBE", "WEBTOON", "TV")

            val selectedFirstMediaText = mediaList[selectedMediaFistNumber]
            var selectedMediaText = ""

            when (trueCounting) {
                0 -> selectedMediaText = "-1"
                1 -> selectedMediaText = "${mediaListWithSelection.indexOf(true) + 1}"
                else -> {
                    for (i in 0 until mediaListWithSelection.size) {
                        if (mediaListWithSelection[i]) {
                            selectedMediaText += "${i + 1}" + ","
                        }
                    }
                    selectedMediaText = selectedMediaText.dropLast(1)
                }
            }
            if (trueCounting >= 2) {
                binding.btnMedia.text =
                    selectedFirstMediaText + " " + "외" + " " + "${trueCounting - 1}"
            } else {
                binding.btnMedia.text = selectedFirstMediaText
            }
            binding.btnMedia.isActivated = true
            selectedMedia = selectedMediaText
            Log.d("어떻게 오는거야", selectedMediaText)
            onFilterDataNetwork(selectedTerm, selectedMedia, selectedStar)
        }
        Log.d("여기까지", "안오나...?")
        onFilterDataNetwork(selectedTerm, selectedMedia, selectedStar)
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

    private fun onAllDataNetwork() {
        BeforegetClient.postService
            .getMyrecordAllData()
            .callback
            .onSuccess {
                myRecordDataAdapter.recordList.addAll(it.data ?: listOf<ResponseMyRecordAll>())
                myRecordDataAdapter.notifyDataSetChanged()
            }
            .enqueue()
    }

    private fun onFilterDataNetwork(term: Int, media: String, star: String) {
        BeforegetClient.postService
            .getMyRecordFilterData(
                tempToken,
                term.toString(),
                media,
                star.toString(),
            )
            .callback
            .onSuccess {
                myRecordDataAdapter.recordList =
                    (it.data ?: listOf<ResponseMyRecordAll>()) as MutableList<ResponseMyRecordAll>
                myRecordDataAdapter.notifyDataSetChanged()
            }
            .enqueue()
    }
}
