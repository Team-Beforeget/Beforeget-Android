package before.forget.feature.report

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.data.local.tempToken
import before.forget.data.remote.BeforegetClient
import before.forget.databinding.FragmentReportGraphBinding
import before.forget.util.callback
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
    private var recordCount = ArrayList<String>()
    private var monthCount = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportGraphBinding.inflate(layoutInflater, container, false)

        chart = binding.bcGraph

        initNetwork()
        initMonthClickEvent()

        return binding.root
    }

    private fun initNetwork() {
        recordCount.clear()
        monthCount.clear()
        BeforegetClient.statisticService.responseGraphData(
            tempToken,
            "2021-12",
            MAX_X_VALUE
        )
            .callback
            .onSuccess {
                Log.d("#######ReportGraphFragment", "서버 통신 성공")
                var startDate = it.data?.start
                for (i in 0 until MAX_X_VALUE) {
                    recordCount.add(it.data?.recordCount?.get(MAX_X_VALUE - 1 - i)?.count.toString())
                    monthCount.add(it.data?.recordCount?.get(MAX_X_VALUE - 1 - i)?.month.toString())
                }
                if (MAX_X_VALUE == 3) {
                    monthCount.add(3, "1")
                    monthCount.add(4, "1")
                }
                Log.d("##########MONTH", monthCount.toString())
                binding.tvGraphTitle.text = it.data?.title
                binding.tvGraphSubtitle.text = (it.data?.comment)?.replace("\n", "\n")

                initBarChart(recordCount)
            }
            .onError {
                Log.d("####ReportGraphgFragment", "서버 오류")
            }
            .enqueue()
    }

    private fun initBarChart(recordCount: ArrayList<String>) {
        val data: BarData = createBarChartData(recordCount)
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
                initNetwork()
            }
            tvGraph5Month.setOnClickListener {
                MAX_X_VALUE = 5
                COUNT_X_LABEL = 5
                tvGraph5Month.setTextColor(Color.WHITE)
                tvGraph3Month.setTextColor(Color.GRAY)
                initNetwork()
            }
        }
    }

    private fun createBarChartData(recordCount: ArrayList<String>): BarData {
        val values: ArrayList<BarEntry> = ArrayList()
        for (i in 0 until MAX_X_VALUE) {
            val x = i.toFloat()
            val y: Float = recordCount[i].toFloat()
            if (recordCount[i].toInt() > MAX_Y_VALUE) MAX_Y_VALUE = recordCount[i].toInt()
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
                    return monthCount[value.toInt()] + "월"
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
