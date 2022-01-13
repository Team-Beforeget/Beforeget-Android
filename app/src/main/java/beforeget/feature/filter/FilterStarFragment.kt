package beforeget.feature.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.beforeget.R
import com.example.beforeget.databinding.FragmentFilterStarBinding

class FilterStarFragment : Fragment() {
    private lateinit var binding: FragmentFilterStarBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_star, container, false)
        binding.btnApplyStarFilter.isEnabled = false
        initClickEvent()
        return binding.root
    }
    private fun initClickEvent() {
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
    private fun checkBtnStarSelected() = binding.btnOneStar.isSelected || binding.btnTwoStar.isSelected || binding.btnThreeStar.isSelected || binding.btnFourStar.isSelected || binding.btnFiveStar.isSelected
    private fun checkBtnApplyFilterStarEnabled() {
        binding.btnApplyStarFilter.isEnabled = checkBtnStarSelected()
    }
    private fun activateBtnApplyFilterStarEnabled() {
        checkBtnStarSelected()
        checkBtnApplyFilterStarEnabled()
    }
}
