package before.forget.feature.report

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.databinding.ActivityReportBinding
import com.google.android.material.tabs.TabLayoutMediator
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import before.forget.R
import java.util.*


class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private lateinit var reportViewPagerAdapter: ReportViewPagerAdapter

    private val cal: Calendar = Calendar.getInstance()
    private val reportYear = cal.get(Calendar.YEAR)
    private val reportMonth = cal.get(Calendar.MONTH)
    // TODO: 서버에서 값 받아오기
    private var firstRecordYear = 0
    private var firstRecordMonth = 0

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

        year.minValue = firstRecordYear
        year.maxValue = reportYear
        month.minValue = 1
        month.maxValue = 12

        // setting current year and month
        month.value = cal.get(Calendar.MONTH) + 1
        year.value = cal.get(Calendar.YEAR)

        // release edittext setting
        year.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        month.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
        year.wrapSelectorWheel = false

        cancel.setOnClickListener {
            dialog.dismiss()
            dialog.cancel()
        }

        confirm.setOnClickListener {
            // TODO: date 전달
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
