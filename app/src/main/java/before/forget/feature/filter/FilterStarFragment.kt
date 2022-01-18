package before.forget.feature.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.databinding.FragmentFilterStarBinding

class FilterStarFragment : Fragment() {
    private lateinit var binding: FragmentFilterStarBinding
    private var callbackButtonClickListener: (() -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_star, container, false)
        binding.btnApplyStarFilter.isEnabled = false
        clickBtnStarEvent()
        refreshStarFilter()
        return binding.root
    }

    fun setCallbackButtonClickListener(listener: () -> Unit) {
        this.callbackButtonClickListener = listener
    }

    private fun clickBtnStarEvent() {
        binding.btnApplyStarFilter.setOnClickListener {
            callbackButtonClickListener?.invoke()
        }
        binding.apply {
            btnOneStar.setOnClickListener {
                btnOneStar.isSelected = !btnOneStar.isSelected
                activateBtnApplyFilterStarEnabled()
            }
            btnTwoStar.setOnClickListener {
                btnTwoStar.isSelected = !btnTwoStar.isSelected
                activateBtnApplyFilterStarEnabled()
            }
            btnThreeStar.setOnClickListener {
                btnThreeStar.isSelected = !btnThreeStar.isSelected
                activateBtnApplyFilterStarEnabled()
            }
            btnFourStar.setOnClickListener {
                btnFourStar.isSelected = !btnFourStar.isSelected
                activateBtnApplyFilterStarEnabled()
            }
            btnFiveStar.setOnClickListener {
                btnFiveStar.isSelected = !btnFiveStar.isSelected
                activateBtnApplyFilterStarEnabled()
            }
        }
    }

    private fun checkBtnStarSelected() =
        binding.btnOneStar.isSelected || binding.btnTwoStar.isSelected || binding.btnThreeStar.isSelected || binding.btnFourStar.isSelected || binding.btnFiveStar.isSelected

    private fun checkBtnApplyFilterStarEnabled() {
        binding.btnApplyStarFilter.isEnabled = checkBtnStarSelected()
    }

    private fun activateBtnApplyFilterStarEnabled() {
        checkBtnStarSelected()
        checkBtnApplyFilterStarEnabled()
    }

    private fun refreshStarFilter() {
        binding.apply {
            btnRefreshStarFilter.setOnClickListener {
                btnFiveStar.isSelected = false
                btnFourStar.isSelected = false
                btnThreeStar.isSelected = false
                btnTwoStar.isSelected = false
                btnOneStar.isSelected = false
                btnApplyStarFilter.isEnabled = false
            }
        }
    }
}
