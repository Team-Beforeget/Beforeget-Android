package before.forget.feature.write

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import before.forget.databinding.ActivityWriteBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WriteActivity : AppCompatActivity() {
    var dateString = ""

    @RequiresApi(Build.VERSION_CODES.N)

    private lateinit var binding: ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)

        setContentView(binding.root)

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
        }
    }

    private fun getMediaLabel() { // 유형선택 뷰에서 미디어 불러오기
        if (intent.hasExtra("media")) {
            val media = intent.getStringExtra("media")
            binding.tvWriteMedialabel.text = media.toString()
        }
    }
}
