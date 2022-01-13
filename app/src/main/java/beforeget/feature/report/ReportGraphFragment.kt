package beforeget.feature.report

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.beforeget.databinding.FragmentReportGraphBinding
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

    private var MAX_X_VALUE = 5 // 바 갯수 // TODO: 3,5 변경사항
    private var MAX_Y_VALUE = 0
    private var chart: BarChart? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportGraphBinding.inflate(layoutInflater, container, false)

        chart = binding.bcGraph

        val data: BarData = createChartData()
        // set bar width
        data.barWidth = 0.1f
        configureChartAppearance()
        prepareChartData(data)

        return binding.root
    }

    private fun createChartData(): BarData {
        val values: ArrayList<BarEntry> = ArrayList()
        val record_count = arrayOf("8", "21", "25", "15", "18") // TODO: Server에서 받아오기
        for (i in 0 until MAX_X_VALUE) {
            val x = i.toFloat()
            val y: Float = record_count[i].toFloat()
            if(record_count[i].toInt()> MAX_Y_VALUE) MAX_Y_VALUE = record_count[i].toInt()

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
        chart!!.description.isEnabled = false
        chart!!.setDrawValueAboveBar(false)
        // hiding the grey background of the chart, default false if not set
        chart!!.setDrawGridBackground(false)
        // x, y space
        chart!!.extraLeftOffset = 20f
        chart!!.extraBottomOffset = 20f
        val xAxis = chart!!.xAxis
        // xAxis position
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.textColor = Color.WHITE
        xAxis.labelCount = 5 // TODO: 3,5 변경사항
        xAxis.textSize = 14f

        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val DAYS = arrayOf("8", "9", "10", "11", "12") // TODO: datepicker 기반으로 계산하기
                return DAYS[value.toInt()] + "월"
            }
        }

        // 왼쪽 축 (기록 갯수)
        val axisLeft = chart!!.axisLeft
        // axisLeft.axisMinimum = 0f
        // TODO : 기록의 최대값 받아오기
        axisLeft.setLabelCount(3, true)
        axisLeft.textColor = Color.WHITE
        axisLeft.axisLineColor = Color.TRANSPARENT
        axisLeft.textSize = 14f
        // axisleft max, min
        axisLeft.axisMinimum = 0f
        axisLeft.axisMaximum = MAX_Y_VALUE.toFloat()

        // y축 격자
        // TODO : 축 색상 axisLeft.gridColor = Color

        // 오른쪽 축 색상
        val axisRight = chart!!.axisRight
        axisRight.isEnabled = false

        // X, Y 바의 애니메이션 효과
        chart!!.animateY(2000)
        // bar background
        chart!!.setDrawBarShadow(true)
        // bar touch
        chart!!.setTouchEnabled(false)
        // chart label
        chart!!.getLegend().isEnabled = false
    }

    private fun prepareChartData(data: BarData) {
        // data.setValueTextSize(12f)
        // val tf = Typeface.createFromAsset(assetManager, "font/montserrat_regular.ttf")
        // data.setValueTypeface(tf)
        chart!!.data = data
        chart!!.invalidate()
    }
}
