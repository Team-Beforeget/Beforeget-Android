package before.forget.feature.report

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.data.local.tempToken
import before.forget.data.remote.BeforegetClient
import before.forget.databinding.FragmentReportSentenceBinding
import before.forget.util.callback

class ReportSentenceFragment : Fragment() {
    private var _binding: FragmentReportSentenceBinding? = null
    private val binding get() = _binding ?: error("Binding이 초기화되지 않았습니다.")
    private var isFront1 = false
    private var isFront2 = false
    private var isFront3 = false
    private var isFront4 = false
    private var isFront5 = false
    private var isFront6 = false
    private var s1Movie = ""
    private var s2Movie = ""
    private var s3Movie = ""
    private var s1Book = ""
    private var s2Book = ""
    private var s3Book = ""
    private var s1TV = ""
    private var s2TV = ""
    private var s3TV = ""
    private var s1Music = ""
    private var s2Music = ""
    private var s3Music = ""
    private var s1Webtoon = ""
    private var s2Webtoon = ""
    private var s3Webtoon = ""
    private var s1Youtube = ""
    private var s2Youtube = ""
    private var s3Youtube = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReportSentenceBinding.inflate(layoutInflater, container, false)

        initNetwork()
        initCardFront()
        initcardClickListener()
        return binding.root
    }

    private fun initNetwork() {
        BeforegetClient.statisticService.responseSentenceData(
            tempToken,
            "2021-12"
        )
            .callback
            .onSuccess {
                Log.d("#######ReportSentenceFragment", "서버 통신 성공")
                var startDate = it.data?.start
                if (it.data?.oneline?.Movie?.isNotEmpty() == true) {
                    s1Movie = it.data.oneline.Movie[0]
                    s2Movie = it.data.oneline.Movie[1]
                    s3Movie = it.data.oneline.Movie[2]
                }
                if (it.data?.oneline?.Book?.isNotEmpty() == true) {
                    s1Book = it.data.oneline.Book[0]
                    s2Book = it.data.oneline.Book[1]
                    s3Book = it.data.oneline.Book[2]
                }
                if (it.data?.oneline?.TV?.isNotEmpty() == true) {
                    s1TV = it.data.oneline.TV[0]
                    s2TV = it.data.oneline.TV[1]
                    s3TV = it.data.oneline.TV[2]
                }
                if (it.data?.oneline?.Music?.isNotEmpty() == true) {
                    s1Music = it.data.oneline.Music[0]
                    s2Music = it.data.oneline.Music[1]
                    s3Music = it.data.oneline.Music[2]
                }
                if (it.data?.oneline?.Webtoon?.isNotEmpty() == true) {
                    s1Webtoon = it.data.oneline.Webtoon[0]
                    s2Webtoon = it.data.oneline.Webtoon[1]
                    s3Webtoon = it.data.oneline.Webtoon[2]
                }
                if (it.data?.oneline?.Youtube?.isNotEmpty() == true) {
                    s1Youtube = it.data.oneline.Youtube[0]
                    s2Youtube = it.data.oneline.Youtube[1]
                    s3Youtube = it.data.oneline.Youtube[2]
                }
            }
            .onError {
                Log.d("####ReportSentenceFragment", "서버 오류")
            }
            .enqueue()
    }

    private fun initCardFront() {
        setCardFront(R.id.sentence_type1, "Movie")
        setCardFront(R.id.sentence_type2, "Book")
        setCardFront(R.id.sentence_type3, "TV")
        setCardFront(R.id.sentence_type4, "Music")
        setCardFront(R.id.sentence_type5, "Webtoon")
        setCardFront(R.id.sentence_type6, "Youtube")
    }

    private fun initcardClickListener() {
        binding.sentenceType1.setOnClickListener {
            isFront1 = !isFront1
            initAnimation(R.id.sentence_type1, isFront1, "Movie", s1Movie, s2Movie, s3Movie)
        }
        binding.sentenceType2.setOnClickListener {
            isFront2 = !isFront2
            initAnimation(R.id.sentence_type2, isFront2, "Book", s1Book, s2Book, s3Book)
        }
        binding.sentenceType3.setOnClickListener {
            isFront3 = !isFront3
            initAnimation(R.id.sentence_type3, isFront3, "TV", s1TV, s2TV, s3TV)
        }
        binding.sentenceType4.setOnClickListener {
            isFront4 = !isFront4
            initAnimation(R.id.sentence_type4, isFront4, "Music", s1Music, s2Music, s3Music)
        }
        binding.sentenceType5.setOnClickListener {
            isFront5 = !isFront5
            initAnimation(R.id.sentence_type5, isFront5, "Webtoon", s1Webtoon, s2Webtoon, s3Webtoon)
        }
        binding.sentenceType6.setOnClickListener {
            isFront6 = !isFront6
            initAnimation(R.id.sentence_type6, isFront6, "Youtube", s1Youtube, s2Youtube, s3Youtube)
        }
    }

    private fun initAnimation(containerViewId: Int, isFront: Boolean, typeName: String, s1: String, s2: String, s3: String) {
        if (isFront) {
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.cardflip_right_in, R.animator.cardflip_right_out,
                    R.animator.cardflip_left_in, R.animator.cardflip_left_out
                )
                .replace(containerViewId, CardFlipBack(s1, s2, s3))
                .commit()
        } else {
            childFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.animator.cardflip_right_in, R.animator.cardflip_right_out,
                    R.animator.cardflip_left_in, R.animator.cardflip_left_out
                )
                .replace(containerViewId, CardFlipFront(typeName))
                .commit()
        }
    }

    private fun setCardFront(containerViewId: Int, typeName: String) {
        childFragmentManager.beginTransaction()
            .replace(containerViewId, CardFlipFront(typeName))
            .commit()
    }
}
