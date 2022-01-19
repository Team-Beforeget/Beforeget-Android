package before.forget.feature.report

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.data.remote.BeforegetClient
import before.forget.databinding.FragmentReportLabelingBinding
import before.forget.util.callback
import com.bumptech.glide.Glide

class ReportLabelingFragment : Fragment() {
    private var _binding: FragmentReportLabelingBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportLabelingBinding.inflate(layoutInflater, container, false)
        initNetwork()
        return binding.root
    }

    private fun initNetwork() {
        BeforegetClient.statisticService.requestReportLabelingData(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJzdWJpbjA3MjNAYmVmb3JlZ2V0LmNvbSIsIm5pY2siOiLtj6zrppAiLCJpZEZpcmViYXNlIjoiaXNRM1kzVU4xSVlqdmQzMXpsZk5Bd2FHejFtMSIsImlhdCI6MTY0MjQzNTEzMSwiZXhwIjoxNjQzNjQ0NzMxLCJpc3MiOiJjaGFud29vIn0.zIK0c8Gq1f_GcJ_UjkwABWfXQ5UbVSU5M69uEqZhKkc",
            "2021-12"
        )
            .callback
            .onSuccess {
                Log.d("#######ReportLabelingFragment", "서버 통신 성공")
                var startDate = it.data?.start
                binding.tvTitle.text = it.data?.title
                binding.tvSubtitle.text = (it.data?.comment)?.replace("\n", "\n")
                Glide.with(this).load(it.data?.poster).into(binding.ivLabeling)
            }
            .onError {
                Log.d("####ReportLabelingFragment", "ReportLabelingFragment 서버 오류")
            }
            .enqueue()
    }
}
