package before.forget.feature.report

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.data.local.tempToken
import before.forget.data.remote.BeforegetClient
import before.forget.databinding.FragmentReportRankingBinding
import before.forget.util.callback

class ReportRankingFragment : Fragment() {
    private var _binding: FragmentReportRankingBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportRankingBinding.inflate(layoutInflater, container, false)
        initNetwork()
        return binding.root
    }

    private fun initNetwork() {
        BeforegetClient.statisticService.responseRankingData(
            tempToken,
            "2021-12"
        )
            .callback
            .onSuccess {
                Log.d("#######ReportLabelingFragment", "서버 통신 성공")
                var startDate = it.data?.start
                binding.tvRank1Name.text = it.data?.arr?.get(0)?.type
                binding.tvRank1Count.text = it.data?.arr?.get(0)?.count.toString()
                binding.tvRank2Name.text = it.data?.arr?.get(1)?.type
                binding.tvRank2Count.text = it.data?.arr?.get(1)?.count.toString()
                binding.tvRank3Name.text = it.data?.arr?.get(2)?.type
                binding.tvRank3Count.text = it.data?.arr?.get(2)?.count.toString()
                binding.tvTitle.text = it.data?.title
                binding.tvSubtitle.text = (it.data?.label)?.replace("\n", "\n")
            }
            .onError {
                Log.d("####ReportLabelingFragment", "서버 오류")
            }
            .enqueue()
    }
}
