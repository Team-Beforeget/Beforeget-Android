package before.forget.feature.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.databinding.FragmentReportSentenceBinding

class ReportSentenceFragment : Fragment() {
    private var _binding: FragmentReportSentenceBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")
    var isFront1 = false
    var isFront2 = false
    var isFront3 = false
    var isFront4 = false
    var isFront5 = false
    var isFront6 = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportSentenceBinding.inflate(layoutInflater, container, false)
        initCardFront(R.id.sentence_type1)
        initCardFront(R.id.sentence_type2)
        initCardFront(R.id.sentence_type3)
        initCardFront(R.id.sentence_type4)
        initCardFront(R.id.sentence_type5)
        initCardFront(R.id.sentence_type6)
        cardClickListener()
        return binding.root
    }

    private fun cardClickListener() {
        binding.sentenceType1.setOnClickListener {
            isFront1 = !isFront1
            initAnimation(R.id.sentence_type1, isFront1)
        }
        binding.sentenceType2.setOnClickListener {
            isFront2 = !isFront2
            initAnimation(R.id.sentence_type2, isFront2)
        }
        binding.sentenceType3.setOnClickListener {
            isFront3 = !isFront3
            initAnimation(R.id.sentence_type3, isFront3)
        }
        binding.sentenceType4.setOnClickListener {
            isFront4 = !isFront4
            initAnimation(R.id.sentence_type4, isFront4)
        }
        binding.sentenceType5.setOnClickListener {
            isFront5 = !isFront5
            initAnimation(R.id.sentence_type5, isFront5)
        }
        binding.sentenceType6.setOnClickListener {
            isFront6 = !isFront6
            initAnimation(R.id.sentence_type6, isFront6)
        }
    }

    private fun initAnimation(containerViewId: Int, isFront: Boolean) {
        if (isFront) {
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.cardflip_right_in, R.animator.cardflip_right_out,
                    R.animator.cardflip_left_in, R.animator.cardflip_left_out
                )
                .replace(containerViewId, CardFlipBack())
                .commit()
        } else {
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.cardflip_right_in, R.animator.cardflip_right_out,
                    R.animator.cardflip_left_in, R.animator.cardflip_left_out
                )
                .replace(containerViewId, CardFlipFront())
                .commit()
        }
    }

    private fun initCardFront(containerViewId: Int) {
        childFragmentManager.beginTransaction()
            .replace(containerViewId, CardFlipFront())
            .commit()
    }
}
