package before.forget.feature.write

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.request.RequestPost
import before.forget.data.remote.response.CategoryResponseData
import before.forget.databinding.ActivityWriteBinding
import before.forget.feature.write.writeadditem.WriteAddItemActivity
import before.forget.util.callback
import before.forget.util.enqueueUtil
import before.forget.util.shortToast
import com.google.android.material.chip.Chip
import retrofit2.Call
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WriteActivity : AppCompatActivity() {
    var dateString = ""
    var check: Int = 0 // 체크포인트 0
    var starClickCheckUp: Boolean = false

    // 리퀘스트 전역변수
    var postMedia: Int = 0
    var postStar: Int = 0
    var postOneLine: List<String> = emptyList()

    private val writeAdapter = WriteAdapter()
    private val writeCategories = arrayListOf<WriteCategory>()
    private val getAddItemResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                writeCategories.clear()
                it.data?.getParcelableArrayListExtra<WriteCategory>(
                    EXTRA_CATEGORIES
                )?.let { list ->
                    writeCategories.addAll(list)
                }
                updateWriteAdapter()
            }
        }

    private val writeBottomSheetFragment = WriteBottomSheetFragment() // 전역변수로 인스턴스생성
    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)

        setContentView(binding.root)
        onNetwork()
        val cal = Calendar.getInstance() // 현재시각 기입 + 요일
        var day = ""
        var num = cal.get(Calendar.DAY_OF_WEEK)
        when (num) {
            1 -> day = ". SUN"
            2 -> day = ". MON"
            3 -> day = ". TUE"
            4 -> day = ". WED"
            5 -> day = ". THU"
            6 -> day = ". FRI"
            7 -> day = ". SAT"
        }

        with(binding) {
            tvWriteAddonelinebtn.visibility = View.INVISIBLE
            tvWriteDatepickerbtn.text =
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy. MM. dd")) + day
        }

        getMediaLabel()
        getOneLineData()
        btnSetOnClickListener()
    }

    private fun getOneLineData() {
        writeBottomSheetFragment.setOneLineCallback { oneLine ->
            postOneLine = oneLine
            for (i in oneLine) {

                binding.chipGroup.addView(
                    Chip(this).apply {
                        text = i
                        Log.d("qt", i)
                        isCloseIconVisible = true
                        setOnCloseIconClickListener { binding.chipGroup.removeView(this) }
                    }
                )
            }
            Log.d("ㅅㄱ", "123")
        }

        binding.rvWriteItemlist.adapter = writeAdapter
        updateWriteAdapter()
    }

    private fun createBottomSheet() { // 바텀시트 프래그먼트 생성
// 전역변수로 이미 초기화 해줬기 때문에 여기는 지워도 됨, 다른 인스턴스 생성
        writeBottomSheetFragment.show(supportFragmentManager, writeBottomSheetFragment.tag)
    }

    private fun btnSetOnClickListener() { // 별점 구현

        with(binding) {
            ivWriteStar1.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = false
                ivWriteStar3.isSelected = false
                ivWriteStar4.isSelected = false
                ivWriteStar5.isSelected = false
                if (starClickCheckUp == false) {
                    check += 1
                    starClickCheckUp = true
                    Log.d("ㅇㅇ", "$check")
                }
                postStar = 1
            }
            ivWriteStar2.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = true
                ivWriteStar3.isSelected = false
                ivWriteStar4.isSelected = false
                ivWriteStar5.isSelected = false
                if (starClickCheckUp == false) {
                    check += 1
                    starClickCheckUp = true
                    Log.d("ㅇㅇ", "$check")
                }
                postStar = 2
            }
            ivWriteStar3.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = true
                ivWriteStar3.isSelected = true
                ivWriteStar4.isSelected = false
                ivWriteStar5.isSelected = false
                if (starClickCheckUp == false) {
                    check += 1
                    starClickCheckUp = true
                    Log.d("ㅇㅇ", "$check")
                }
                postStar = 3
            }
            ivWriteStar4.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = true
                ivWriteStar3.isSelected = true
                ivWriteStar4.isSelected = true
                ivWriteStar5.isSelected = false
                if (starClickCheckUp == false) {
                    check += 1
                    starClickCheckUp = true
                    Log.d("ㅇㅇ", "$check")
                }
                postStar = 4
            }
            ivWriteStar5.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = true
                ivWriteStar3.isSelected = true
                ivWriteStar4.isSelected = true
                ivWriteStar5.isSelected = true
                if (starClickCheckUp == false) {
                    check += 1
                    starClickCheckUp = true
                    Log.d("ㅇㅇ", "$check")
                }
                postStar = 5
            }

            // 체크포인트 1

            tvWriteAddonelinebtn.setOnClickListener { // 바텀시트 생성
                createBottomSheet()
            }
            tvWriteDatepickerbtn.setOnClickListener { // 데이트피커 + 요일
                val cal = Calendar.getInstance()
                var day = ""

                val dateSetListener =
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        cal.set(year, month, dayOfMonth)

                        if (month < 9) {
                            var months = "0" + (month + 1)
                            if (dayOfMonth < 10) {
                                var dayofmonths = "0" + dayOfMonth
                                dateString = "$year. $months. $dayofmonths"
                            } else {
                                dateString = "$year. $months. $dayOfMonth"
                            }
                        } else {
                            dateString = "$year. ${month + 1}. $dayOfMonth"
                        }

                        var num = cal.get(Calendar.DAY_OF_WEEK)
                        when (num) {
                            1 -> day = ". SUN"
                            2 -> day = ". MON"
                            3 -> day = ". TUE"
                            4 -> day = ". WED"
                            5 -> day = ". THU"
                            6 -> day = ". FRI"
                            7 -> day = ". SAT"
                        }
                        tvWriteDatepickerbtn.text = dateString + day
                    }

                DatePickerDialog(
                    this@WriteActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            ivWriteBackbtn.setOnClickListener { finish() } // 엑티비티 종료
            tvWriteAddoneline.setOnClickListener {
                createBottomSheet()
                tvWriteAddoneline.visibility = View.GONE
                tvWriteAddonelinebtn.visibility = View.VISIBLE
            }
            tvWriteAdditem.setOnClickListener {
                Intent(
                    this@WriteActivity,
                    WriteAddItemActivity::class.java
                ).also {
                    it.putParcelableArrayListExtra(EXTRA_CATEGORIES, writeCategories)
                    getAddItemResult.launch(it)
                }
            }

            tvWriteDone.setOnClickListener {
                if (check == 1 && postOneLine.size > 0 && tvWriteTitleinput.text?.isEmpty() == false) {
                    val requestPost = RequestPost(
                        media = postMedia,
                        date = binding.tvWriteDatepickerbtn.text.split(". ")[0] + "-" +
                            binding.tvWriteDatepickerbtn.text.split(". ")[1] + "-" +
                            binding.tvWriteDatepickerbtn.text.split(". ")[2],
                        star = postStar,
                        title = binding.tvWriteTitleinput.text.toString(),
                        oneline = postOneLine,
                        additional = writeAdapter.getCategoryToAdditional()

                    )

                    val call: Call<RequestPost> =
                        BeforegetClient.postService.postUpload(body = requestPost)
                    call.enqueueUtil(
                        onSuccess = {
                            Log.d("좀", "돼라")
                        }
                    )
                    startActivity(Intent(this@WriteActivity, WriteCompleteActivity::class.java))
                } else {
                    shortToast("필수 항목을 입력해주세요!")
                    Log.d("ㅇㅇ", "$check")
                }
            }
        }
    }

    private fun getMediaLabel() { // 유형선택 뷰에서 미디어 불러오기
        if (intent.hasExtra("media")) {
            val media = intent.getStringExtra("media")
            binding.tvWriteMedialabel.text = media.toString()
            when (media.toString()) {
                "Movie" -> postMedia = 1
                "Book" -> postMedia = 2
                "Tv" -> postMedia = 3 // ㅈ버그
                "Music" -> postMedia = 4
                "Webtoon" -> postMedia = 5
                "Youtube" -> postMedia = 6
            }

            when (postMedia) {
                1 -> binding.tvWriteTitle.text = "영화 제목"
                2 -> binding.tvWriteTitle.text = "책 제목"
                3 -> binding.tvWriteTitle.text = "프로그램 제목"
                4 -> binding.tvWriteTitle.text = "음악 제목"
                5 -> binding.tvWriteTitle.text = "웹툰 제목"
                6 -> binding.tvWriteTitle.text = "유튜브 제목"
            }
        }
    }

    private fun onNetwork() {
        BeforegetClient.categoryService
            .getAddItem(id = 1)
            .callback
            .onSuccess {
                it.data?.let { data -> onNetworkSuccess(data) }
            }.enqueue()
    }

    private fun onNetworkSuccess(data: CategoryResponseData) {
        val categories = data.additional.mapIndexed { index, s ->
            WriteCategory(index, s, false)
        }
        writeCategories.clear()
        writeCategories.addAll(categories)
    }

    private fun updateWriteAdapter() {
        val additionalData = writeCategories.filter { it.isSelected }.map {
            val mediaInfo = MediaInputInfo.findHintById(it.id)
            WriteData(mediaInfo.title, ObservableField(""), mediaInfo.hint)
        }
        writeAdapter.addAllDataOf(additionalData)
    }

    companion object {
        const val EXTRA_CATEGORIES = "categories"
    }
}
