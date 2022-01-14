package before.forget.feature.report

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.databinding.FragmentReportGraphBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class ReportGraphFragment : Fragment() {
    private var _binding: FragmentReportGraphBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    private var chart: BarChart? = null
    private var MAX_X_VALUE = 5 // bar count
    private var MAX_Y_VALUE = 0
    private var COUNT_X_LABEL = 5

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportGraphBinding.inflate(layoutInflater, container, false)

        chart = binding.bcGraph

        initBarChart()
        initMonthClickEvent()

        return binding.root
    }

    private fun initBarChart() {
        val data: BarData = createBarChartData()
        // set bar width
        data.barWidth = 0.1f
        configureChartAppearance()
        prepareChartData(data)
    }

    private fun initMonthClickEvent() {
        with(binding) {
            tvGraph3Month.setOnClickListener {
                MAX_X_VALUE = 3
                COUNT_X_LABEL = 3
                tvGraph5Month.setTextColor(Color.GRAY)
                tvGraph3Month.setTextColor(Color.WHITE)
                initBarChart()
            }
            tvGraph5Month.setOnClickListener {
                MAX_X_VALUE = 5
                COUNT_X_LABEL = 5
                tvGraph5Month.setTextColor(Color.WHITE)
                tvGraph3Month.setTextColor(Color.GRAY)
                initBarChart()
            }
        }
    }

    private fun createBarChartData(): BarData {
        val values: ArrayList<BarEntry> = ArrayList()
        val record_count = arrayOf("8", "21", "25", "15", "18") // TODO: Server에서 받아오기
        for (i in 0 until MAX_X_VALUE) {
            val x = i.toFloat()
            val y: Float = record_count[i].toFloat()
            if (record_count[i].toInt() > MAX_Y_VALUE) MAX_Y_VALUE = record_count[i].toInt()
            values.add(BarEntry(x, y))
        }
        val set1 = BarDataSet(values, "")
        val dataSets: ArrayList<IBarDataSet> = ArrayList()
        dataSets.add(set1)
        // showing the value of the bar
        set1.setDrawValues(false)
        set1.barShadowColor = Color.BLACK
        set1.color = Color.WHITE
        return BarData(dataSets)
    }

    private fun configureChartAppearance() {
        setChart()
        setChartxAis()
        setChartAxisLeftAndRight()
    }

    private fun setChart() {
        chart!!.run {
            description.isEnabled = false
            setDrawValueAboveBar(false)
            // hiding the grey background of the chart, default false if not set
            setDrawGridBackground(false)
            // x, y space
            extraLeftOffset = 20f
            extraBottomOffset = 20f

            // X, Y 바의 애니메이션 효과
            animateY(2000)
            // bar background
            setDrawBarShadow(true)
            // bar touch
            setTouchEnabled(false)
            // chart label
            getLegend().isEnabled = false
        }
    }

    private fun setChartxAis() {
        val xAxis = chart!!.xAxis
        // xAxis position
        xAxis.run {
            position = XAxis.XAxisPosition.BOTTOM
            textColor = Color.WHITE
            labelCount = COUNT_X_LABEL
            textSize = 14f

            setDrawGridLines(false)
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val DAYS = arrayOf("8", "9", "10", "11", "12") // TODO: datepicker 기반으로 계산하기
                    return DAYS[value.toInt()] + "월"
                }
            }
        }
    }

    private fun setChartAxisLeftAndRight() {
        val axisLeft = chart!!.axisLeft
        // axisLeft
        axisLeft.run {
            setLabelCount(3, true)
            textColor = Color.WHITE
            axisLineColor = Color.TRANSPARENT
            textSize = 14f
            // axisleft max, min
            axisMinimum = 0f
            axisMaximum = MAX_Y_VALUE.toFloat()
        }
        // y축 격자
        // TODO : 축 색상 axisLeft.gridColor = Color
        // 오른쪽 축 색상
        val axisRight = chart!!.axisRight
        axisRight.isEnabled = false
    }

    private fun prepareChartData(data: BarData) {
        chart!!.data = data
        chart!!.invalidate()
    }
}
