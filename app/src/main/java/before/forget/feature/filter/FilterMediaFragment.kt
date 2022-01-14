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
    private var callbackButtonClickListener: (() -> Unit)? = null
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
                callbackButtonClickListener?.invoke()
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

    fun test() {
        binding.btnApplyMediaFilter.setOnClickListener {
            val filterBottomSheetFragment = FilterBottomSheetFragment()
            filterBottomSheetFragment.dismiss()
        }
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

    fun setCallbackButtonClickListener(listener: () -> Unit) {
        this.callbackButtonClickListener = listener
    }
}
