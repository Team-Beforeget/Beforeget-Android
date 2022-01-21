package before.forget.feature.write

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.response.CategoryResponseData
import before.forget.databinding.ActivityWriteBinding
import before.forget.databinding.ViewChipBinding
import before.forget.util.callback
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WriteActivity : AppCompatActivity() {
    var dateString = ""

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
                Log.d("TEST111", writeCategories.toString())
                updateWriteAdapter()
            }
        }

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

        binding.tvWriteDatepickerbtn.text =
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy. MM. dd")) + day
        getMediaLabel()
        btnSetOnClickListener()
        (1..6).forEach {
            val a = ViewChipBinding.inflate(layoutInflater).root
            a.text = it.toString() + "looooooooooooong"
            binding.chipGroup.addView(a)
        }

        binding.rvWriteItemlist.adapter = writeAdapter
        updateWriteAdapter()
    }

    private fun createBottomSheet() { // 바텀시트 프래그먼트 생성
        val writeBottomSheetFragment = WriteBottomSheetFragment()
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
            }
            ivWriteStar2.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = true
                ivWriteStar3.isSelected = false
                ivWriteStar4.isSelected = false
                ivWriteStar5.isSelected = false
            }
            ivWriteStar3.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = true
                ivWriteStar3.isSelected = true
                ivWriteStar4.isSelected = false
                ivWriteStar5.isSelected = false
            }
            ivWriteStar4.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = true
                ivWriteStar3.isSelected = true
                ivWriteStar4.isSelected = true
                ivWriteStar5.isSelected = false
            }
            ivWriteStar5.setOnClickListener {
                ivWriteStar1.isSelected = true
                ivWriteStar2.isSelected = true
                ivWriteStar3.isSelected = true
                ivWriteStar4.isSelected = true
                ivWriteStar5.isSelected = true
            }
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
                Log.d("addi", writeAdapter.getCategoryToAdditional().toString())
            }
        }
    }

    private fun getMediaLabel() { // 유형선택 뷰에서 미디어 불러오기
        if (intent.hasExtra("media")) {
            val media = intent.getStringExtra("media")
            binding.tvWriteMedialabel.text = media.toString()
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
