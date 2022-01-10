package beforeget.feature.report

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.beforeget.R
import com.example.beforeget.databinding.ActivityReportBinding
import com.google.android.material.tabs.TabLayoutMediator

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private lateinit var reportViewPagerAdapter: ReportViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)

        initAdapter()

        setContentView(binding.root)
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
