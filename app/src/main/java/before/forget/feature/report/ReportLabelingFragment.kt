package before.forget.feature.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.databinding.FragmentReportLabelingBinding

class ReportLabelingFragment : Fragment() {
    private var _binding: FragmentReportLabelingBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportLabelingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
