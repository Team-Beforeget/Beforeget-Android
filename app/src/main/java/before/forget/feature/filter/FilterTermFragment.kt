package before.forget.feature.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.databinding.FragmentFilterTermBinding
import okhttp3.Interceptor.Companion.invoke
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalField

class FilterTermFragment : Fragment() {
    private lateinit var binding: FragmentFilterTermBinding
    private var termButtonClickListener: ((String) -> Unit)? = null
    private var callbackButtonClickListener: (() -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_term, container, false)
        initDate()
        binding.tvSelectStartDateFilter.setOnClickListener {
            showStartDatePicker()
        }

        binding.tvSelectEndDateFilter.setOnClickListener {
            showEndDatePicker()
        }
        activateBtnApplyFilterTerm()
        focusedEndDatePicker()
        focusedStartDatePicker()
        refreshTermFilter()
        clickBtnTermEvent()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun clickBtnTermEvent() {
        binding.apply {
            btnMonth.setOnClickListener {
                btnMonth.isSelected = !btnMonth.isSelected
                btnTwoWeek.isSelected = false
                btnThreeMonth.isSelected = false
                btnDirectInput.isSelected = false
                if (btnMonth.isSelected) {
                    termButtonClickListener?.invoke(btnMonth.text.toString())
                }
            }
            btnThreeMonth.setOnClickListener {
                btnThreeMonth.isSelected = !btnThreeMonth.isSelected
                btnTwoWeek.isSelected = false
                btnMonth.isSelected = false
                btnDirectInput.isSelected = false
            }
            btnTwoWeek.setOnClickListener {
                btnTwoWeek.isSelected = !btnTwoWeek.isSelected
                btnThreeMonth.isSelected = false
                btnMonth.isSelected = false
                btnDirectInput.isSelected = false
            }
            btnDirectInput.setOnClickListener {
                btnDirectInput.isSelected = !btnDirectInput.isSelected
                btnTwoWeek.isSelected = false
                btnThreeMonth.isSelected = false
                btnMonth.isSelected = false
            }
            btnApplyTermFilter.setOnClickListener {
                callbackButtonClickListener?.invoke()
            }
        }
    }

    private fun activateBtnApplyFilterTerm() {
        binding.btnApplyTermFilter.isEnabled = checkDateData()
    }

    private fun refreshTermFilter() {
        binding.apply {
            btnRefreshTermFilter.setOnClickListener {
                btnTwoWeek.isSelected = false
                btnThreeMonth.isSelected = false
                btnMonth.isSelected = false
                btnDirectInput.isSelected = true
                // 끝날짜는 오늘로 되기
                dpDatepikerStartFilter.visibility = View.GONE
                dpDatepikerEndFilter.visibility = View.GONE
                tvSelectStartDateFilter.text = "날짜를 선택해주세요."
                tvSelectEndDateFilter.text = "날짜를 선택해주세요."
                btnApplyTermFilter.isEnabled = false
            }
        }
    }

    private fun showStartDatePicker() {
        binding.dpDatepikerStartFilter.visibility = View.VISIBLE
        binding.dpDatepikerEndFilter.visibility = View.GONE
    }

    private fun showEndDatePicker() {
        binding.dpDatepikerEndFilter.visibility = View.VISIBLE
        binding.dpDatepikerStartFilter.visibility = View.GONE
    }

    private fun setDate() {
        // 2주 ,1개월,  3개월일 경우에 빼기
        // 직접 입력인 경우 그냥 선택하는대로? 분기문 처리해야
        // 끝날짜는 오늘날짜, 시작날날짜는 끝날짜에서 한달빼기
        // 끝날짜를 자유자재로 선택시 시작날짜도 그에 따라 바뀜

        // 끝날짜는 최대 오늘로로

        // int 형식
        val dateNow = LocalDate.now()
    }

    private fun focusedStartDatePicker() {
        binding.dpDatepikerStartFilter.setOnDateChangedListener { _, year, month, day ->

            setStartDate(year.toString(), "${month + 1}", day.toString())

            if (binding.btnMonth.isSelected) {
                if (month == 12) {
                    setEndDate("${year + 1}", "1", day.toString())
                } else {
                    setEndDate(year.toString(), "${month + 2}", day.toString())
                }
            }

            if (binding.btnTwoWeek.isSelected) {
                if (day >= 18) {
                    setEndDate(year.toString(), "${month + 2}", "${day + 14 - 30}")
                    if (month == 11) {
                        setEndDate("${year + 1}", "1", "${day + 14 - 30}")
                    }
                } else if (month == 11) {
                    setEndDate("${year + 1}", "1", "${day + 14}")
                } else {
                    setEndDate(year.toString(), "${month + 1}", "${day + 14}")
                }
            }
            if (binding.btnThreeMonth.isSelected) {
                if (month >= 9) {
                    setEndDate("${year + 1}", "${month + 3 - 10}", day.toString())
                } else {
                    setEndDate(year.toString(), "${month + 4}", day.toString())
                }
            }
            checkDateData()
            activateBtnApplyFilterTerm()
        }
    }

    private fun focusedEndDatePicker() {
        binding.dpDatepikerEndFilter.setOnDateChangedListener { _, year, month, day ->
            setEndDate(year.toString(), "${month + 1}", day.toString())

            if (binding.btnMonth.isSelected) {
                Log.d("focusedEndDatePicker", "month")
                if (month == 0) {
                    setStartDate("${year - 1}", "12", day.toString())
                } else {
                    setStartDate(year.toString(), month.toString(), day.toString())
                }
            }

            if (binding.btnThreeMonth.isSelected) {
                Log.d("focusedEndDatePicker", "threemonth")
                if (month <= 3) {
                    setStartDate("${year - 1}", "${month - 2 + 12}", day.toString())
                } else {
                    setStartDate(year.toString(), "${month - 2}", day.toString())
                }
            }
            if (binding.btnTwoWeek.isSelected) {
                Log.d("focusedEndDatePicker", "twoweek")
                if (day <= 14) {
                    if (month == 0) {
                        setStartDate("${year - 1}", "12", "${day - 14 + 31}")
                    } else {
                        setStartDate(year.toString(), month.toString(), "${day - 14 + 31}")
                    }
                } else {
                    setStartDate(year.toString(), "${month + 1}", "${day - 14}")
                }
            }
            checkDateData()
            activateBtnApplyFilterTerm()
        }
    }

    fun setCallbackButtonClickListener(listener: () -> Unit) {
        this.callbackButtonClickListener = listener
    }

    fun setTermButtonClickListener(listener: (String) -> Unit) {
        this.termButtonClickListener = listener
    }

    private fun checkDateData() =
        binding.tvSelectEndDateFilter.text != "날짜를 선택해주세요." && binding.tvSelectStartDateFilter.text != "날짜를 선택해주세요."

    private fun setStartDate(year: String, month: String, day: String) {
        Log.d("setStartDate", "왜 실행이 갑자기 안되니 ㅜ")
        binding.tvSelectStartDateFilter.text =
            year + "년" + " " + month + "월" + " " + day + "일"
    }

    private fun setEndDate(year: String, month: String, day: String) {
        binding.tvSelectEndDateFilter.text =
            year + "년" + " " + month + "월" + " " + day + "일"
    }
    private fun initDate() {
        binding.apply {
            btnApplyTermFilter.isEnabled = false
            dpDatepikerEndFilter.maxDate = System.currentTimeMillis()
            val dateNow = LocalDate.now()
            setEndDate(dateNow.year.toString(), dateNow.monthValue.toString(), dateNow.dayOfMonth.toString())
            tvSelectStartDateFilter.text = "날짜를 선택해주세요."
            dpDatepikerEndFilter.visibility = View.GONE
            dpDatepikerStartFilter.visibility = View.GONE
            btnDirectInput.isSelected = true
        }
    }
}
