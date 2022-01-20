package before.forget.feature.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.databinding.ItemCardBackBinding

class CardFlipBack(var s1: String, var s2: String, var s3: String) : Fragment() {
    private var _binding: ItemCardBackBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemCardBackBinding.inflate(layoutInflater, container, false)
        initCardView()
        return binding.root
    }

    private fun initCardView() {
        if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) {
            binding.ivSentenceIcon.visibility = View.VISIBLE
            binding.tvSentenceEmpty.visibility = View.VISIBLE
            binding.tvSentence1.visibility = View.INVISIBLE
            binding.tvSentence2.visibility = View.INVISIBLE
            binding.tvSentence3.visibility = View.INVISIBLE
        } else {
            binding.ivSentenceIcon.visibility = View.INVISIBLE
            binding.tvSentenceEmpty.visibility = View.INVISIBLE
            binding.tvSentence1.visibility = View.VISIBLE
            binding.tvSentence2.visibility = View.VISIBLE
            binding.tvSentence3.visibility = View.VISIBLE

            binding.tvSentence1.text = s1
            binding.tvSentence2.text = s2
            binding.tvSentence3.text = s3
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
