package before.forget.feature.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.databinding.ItemCardFrontBinding

class CardFlipFront(var value: String) : Fragment() {
    private var _binding: ItemCardFrontBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemCardFrontBinding.inflate(layoutInflater, container, false)
        initTypeText(value)
        return binding.root
    }

    private fun initTypeText(type: String) {
        binding.tvSentenceType.text = type
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
