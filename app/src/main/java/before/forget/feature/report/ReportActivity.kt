package before.forget.feature.report

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.databinding.ActivityReportBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private lateinit var reportViewPagerAdapter: ReportViewPagerAdapter

    private val cal: Calendar = Calendar.getInstance()
    private var currentYear = cal.get(Calendar.YEAR)
    private var currentMonth = cal.get(Calendar.MONTH) + 1
    private var selectYear = currentYear
    private var selectMonth = currentMonth
    private var IS_FIRST = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)

        initAdapter()
        initBtnClickListener()

        setContentView(binding.root)
    }

    private fun initBtnClickListener() {
        with(binding) {
            ivBackBtn.setOnClickListener {
                finish()
            }
            ivDownloadBtn.setOnClickListener {
            }
            btnDatePicker.setOnClickListener {
                setDatePicker()
            }
        }
    }

    private fun setDatePicker() {
        val dialog = AlertDialog.Builder(this).create()
        val view: View = LayoutInflater.from(this).inflate(R.layout.layout_report_datepicker_dialog, null)
        val year: NumberPicker = view.findViewById(R.id.picker_year)
        val month: NumberPicker = view.findViewById(R.id.picker_month)
        val cancel: Button = view.findViewById(R.id.picker_cancel)
        val confirm: Button = view.findViewById(R.id.picker_confirm)

        // TODO: 서버에서 값 받아오기
        var firstRecordYear = 2018
        var firstRecordMonth = 0

        // release edittext setting
        year.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        month.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        year.wrapSelectorWheel = false

        month.minValue = 1
        month.maxValue = 12

        // setting current year and month
        if (IS_FIRST) {
            if (currentMonth == 1) {
                year.minValue = firstRecordYear
                year.maxValue = currentYear - 1
                currentYear = currentYear - 1
                currentMonth = 12
                year.value = currentYear
                month.value = currentMonth
            }
        } else {
            year.minValue = firstRecordYear
            year.maxValue = currentYear
            year.value = selectYear
            month.value = selectMonth
        }
        IS_FIRST = false
        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }

        confirm.setOnClickListener {
            // TODO: date 전달
            selectMonth = month.value
            selectYear = year.value
            binding.btnDatePicker.text = selectYear.toString() + "년 " + selectMonth.toString() + "월"
            dialog.dismiss()
            dialog.cancel()
        }

        dialog.setView(view)
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun initAdapter() {
        val fragmentList = listOf(
            ReportLabelingFragment(),
            ReportGraphFragment(),
            ReportRankingFragment(),
            ReportSentenceFragment(),
            ReportOnepageFragment()
        )

        reportViewPagerAdapter = ReportViewPagerAdapter(this)
        reportViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpReport.adapter = reportViewPagerAdapter

        initIndicator()
    }

    private fun initIndicator() {
        TabLayoutMediator(binding.tlReport, binding.vpReport) { _tab, _position ->
            _tab.setIcon(R.drawable.tab_selector_report)
        }.attach()
    }
}
