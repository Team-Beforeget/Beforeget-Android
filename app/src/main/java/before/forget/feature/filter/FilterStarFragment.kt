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
    private var starListWithSelection = mutableListOf<Boolean>(false, false, false, false, false)
    private lateinit var binding: FragmentFilterStarBinding
    private var callbackButtonClickListener: ((List<Boolean>, Int) -> Unit)? = null
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

    fun setCallbackButtonClickListener(listener: (List<Boolean>, Int) -> Unit) {
        this.callbackButtonClickListener = listener
    }

    private fun clickBtnStarEvent() {

        binding.apply {
            binding.btnApplyStarFilter.setOnClickListener {
                starListWithSelection[0] = btnOneStar.isSelected
                starListWithSelection[1] = btnTwoStar.isSelected
                starListWithSelection[2] = btnThreeStar.isSelected
                starListWithSelection[3] = btnFourStar.isSelected
                starListWithSelection[4] = btnFiveStar.isSelected

                var starTrueCounting = 0

                for (j in 0 until starListWithSelection.size) {
                    if (starListWithSelection[j]) {
                        starTrueCounting += 1
                    }
                }

                callbackButtonClickListener?.invoke(starListWithSelection, starTrueCounting)
            }
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
