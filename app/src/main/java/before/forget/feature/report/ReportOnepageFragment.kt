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
import before.forget.databinding.FragmentReportOnepageBinding
import before.forget.util.callback
import com.bumptech.glide.Glide
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet

class ReportOnepageFragment : Fragment() {
    private var _binding: FragmentReportOnepageBinding? = null
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
        _binding = FragmentReportOnepageBinding.inflate(layoutInflater, container, false)

        chart = binding.bcGraphOnepage

        initNetwork()

        return binding.root
    }

    private fun initNetwork() {
        BeforegetClient.statisticService.responseOnepageData(
            tempToken,
            "2021-12",
            5
        )
            .callback
            .onSuccess {
                Log.d("#######ReportOnepageFragment", "서버 통신 성공")
                var startDate = it.data?.start
                Glide.with(this).load(it.data?.graphic).into(binding.ivOnepageGraphic)
                binding.tvOnepageSentence1.text = it.data?.oneline?.get(0)
                binding.tvOnepageSentence2.text = it.data?.oneline?.get(1)
                binding.tvOnepageSentence3.text = it.data?.oneline?.get(2)
                for (i in 0 until MAX_X_VALUE) {
                    recordCount.add(it.data?.monthly?.get(MAX_X_VALUE - 1 - i)?.count.toString())
                    monthCount.add(it.data?.monthly?.get(MAX_X_VALUE - 1 - i)?.month.toString())
                }
                binding.tvOnepageRank1Type.text = it.data?.media?.get(0)?.type
                binding.tvOnepageRank2Type.text = it.data?.media?.get(1)?.type
                binding.tvOnepageRank3Type.text = it.data?.media?.get(2)?.type
                binding.tvOnepageRank1Count.text = it.data?.media?.get(0)?.count.toString()
                binding.tvOnepageRank2Count.text = it.data?.media?.get(1)?.count.toString()
                binding.tvOnepageRank3Count.text = it.data?.media?.get(2)?.count.toString()

                initBarChart(recordCount)
            }
            .onError {
                Log.d("####ReportOnepageFragment", "서버 오류")
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
            extraRightOffset = 30f
            extraTopOffset = 45f
            extraBottomOffset = 45f

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
