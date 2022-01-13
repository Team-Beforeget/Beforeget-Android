package beforeget.feature.filter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.beforeget.R
import com.example.beforeget.databinding.FragmentFilterTermBinding

class FilterTermFragment : Fragment() {
    private lateinit var binding: FragmentFilterTermBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_term, container, false)
        binding.btnApplyTermFilter.isEnabled = false
        initClickEvent()
        return binding.root
    }

    private fun initClickEvent() {
        binding.apply {
            btnMonth.setOnClickListener {
                btnMonth.isSelected = !btnMonth.isSelected
                activateBtnApplyFilterTerm()
            }
            btnThreeMonth.setOnClickListener {
                btnThreeMonth.isSelected = !btnThreeMonth.isSelected
                activateBtnApplyFilterTerm()
            }
            btnTwoWeek.setOnClickListener {
                btnTwoWeek.isSelected = !btnTwoWeek.isSelected
                activateBtnApplyFilterTerm()
            }
            btnDirectInput.setOnClickListener { btnDirectInput.isSelected = !btnDirectInput.isSelected }
        }
    }
    private fun checkEnableApplyFilterTermBtn() {
        binding.btnApplyTermFilter.isEnabled = checkBtnTermSelected()
    }
    private fun checkBtnTermSelected() = binding.btnMonth.isSelected || binding.btnThreeMonth.isSelected || binding.btnTwoWeek.isSelected

    private fun activateBtnApplyFilterTerm() {
        checkBtnTermSelected()
        checkEnableApplyFilterTermBtn()
    }
}
