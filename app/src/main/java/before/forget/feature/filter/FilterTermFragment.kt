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
import java.time.LocalDate

class FilterTermFragment : Fragment() {
    private lateinit var binding: FragmentFilterTermBinding
    private var callbackButtonClickListener: ((String) -> Unit)? = null
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
                if (binding.btnThreeMonth.isSelected) callbackButtonClickListener?.invoke(binding.btnThreeMonth.text.toString())
                if (binding.btnTwoWeek.isSelected) callbackButtonClickListener?.invoke(binding.btnTwoWeek.text.toString())
                if (binding.btnMonth.isSelected) callbackButtonClickListener?.invoke(binding.btnMonth.text.toString())
                if (binding.btnDirectInput.isSelected) callbackButtonClickListener?.invoke("기간")
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

    fun setCallbackButtonClickListener(listener: (String) -> Unit) {
        this.callbackButtonClickListener = listener
    }

    private fun checkDateData() =
        binding.tvSelectEndDateFilter.text != "날짜를 선택해주세요." && binding.tvSelectStartDateFilter.text != "날짜를 선택해주세요."

    private fun setStartDate(year: String, month: String, day: String) {
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
