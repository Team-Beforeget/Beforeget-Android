package before.forget.feature.report

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.data.remote.BeforegetClient
import before.forget.data.local.tempToken
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
            tempToken,
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
                Log.d("####ReportLabelingFragment", "서버 오류")
            }
            .enqueue()
    }
}
