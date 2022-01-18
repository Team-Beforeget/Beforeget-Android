package before.forget.feature.myrecord

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
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
        onAllDataNetwork()
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
            onFilterDataNetwork()
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

    private fun onFilterDataNetwork() {
        BeforegetClient.postService
            .getMyRecordFilterData(tempToken, "-1", "1", "-1")
            .callback
            .onSuccess {
                myRecordDataAdapter.recordList.addAll(it.data ?: listOf<ResponseMyRecordAll>())
            }
            .enqueue()
    }
}
