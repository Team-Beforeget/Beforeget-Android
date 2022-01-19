package before.forget.feature.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.databinding.FragmentFilterMediaBinding

class FilterMediaFragment : Fragment() {
    private var mediaButtonClickListener: ((String) -> Unit)? = null
    private var callbackButtonClickListener: ((List<Boolean>, Int, Int) -> Unit)? = null
    private var mediaListWithSelection = mutableListOf<Boolean>(false, false, false, false, false, false)
    private lateinit var binding: FragmentFilterMediaBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_filter_media, container, false)
        binding.btnApplyMediaFilter.isEnabled = false
        clickMediaButtonEvent()
        checkEnableApplyBtn()
        refreshMediaFilter()
        return binding.root
    }

    private fun clickMediaButtonEvent() {
        binding.apply {
            btnBook.setOnClickListener {
                btnBook.isSelected = !btnBook.isSelected
                activateApplyBtn()
            }
            btnMovie.setOnClickListener {
                btnMovie.isSelected = !btnMovie.isSelected
                activateApplyBtn()
            }
            btnMusic.setOnClickListener {
                btnMusic.isSelected = !btnMusic.isSelected
                activateApplyBtn()
            }
            btnTv.setOnClickListener {
                btnTv.isSelected = !btnTv.isSelected
                activateApplyBtn()
            }
            btnWebtoon.setOnClickListener {
                btnWebtoon.isSelected = !btnWebtoon.isSelected
                activateApplyBtn()
            }
            btnYoutube.setOnClickListener {
                btnYoutube.isSelected = !btnYoutube.isSelected
                activateApplyBtn()
            }
            btnApplyMediaFilter.setOnClickListener {
                mediaListWithSelection[0] = btnMovie.isSelected
                mediaListWithSelection[1] = btnBook.isSelected
                mediaListWithSelection[2] = btnTv.isSelected
                mediaListWithSelection[3] = btnMusic.isSelected
                mediaListWithSelection[4] = btnWebtoon.isSelected
                mediaListWithSelection[5] = btnYoutube.isSelected
                var selectedMediaFistNumber = 6
                for (i in 0 until mediaListWithSelection.size) {
                    if (mediaListWithSelection[i]) {
                        selectedMediaFistNumber = i
                        break
                    }
                }

                var trueCounting = 0

                for (j in 0 until mediaListWithSelection.size) {
                    if (mediaListWithSelection[j]) {
                        trueCounting += 1
                    }
                }

                callbackButtonClickListener?.invoke(mediaListWithSelection, trueCounting, selectedMediaFistNumber)
            }
        }
    }

    private fun checkBtnSelected() =
        binding.btnBook.isSelected || binding.btnMovie.isSelected || binding.btnMusic.isSelected || binding.btnTv.isSelected || binding.btnWebtoon.isSelected || binding.btnYoutube.isSelected

    private fun checkEnableApplyBtn() {
        binding.btnApplyMediaFilter.isEnabled = checkBtnSelected()
    }

    private fun activateApplyBtn() {
        checkBtnSelected()
        checkEnableApplyBtn()
    }

    private fun refreshMediaFilter() {
        binding.btnRefreshMediaFilter.setOnClickListener {
            binding.apply {
                btnYoutube.isSelected = false
                btnWebtoon.isSelected = false
                btnTv.isSelected = false
                btnBook.isSelected = false
                btnMovie.isSelected = false
                btnMusic.isSelected = false
                btnApplyMediaFilter.isEnabled = false
            }
        }
    }

    fun setCallbackButtonClickListener(listener: (mediaListWithSelection: List<Boolean>, trueCounting: Int, selectFistNumber: Int) -> Unit) {
        this.callbackButtonClickListener = listener
    }

    fun setMediaButtonClickListener(listener: (String) -> Unit) {
        this.mediaButtonClickListener = listener
    }
}
