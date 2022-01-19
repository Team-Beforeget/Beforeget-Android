package before.forget.feature.write

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.databinding.FragmentWriteGoodBinding

class WriteBadFragment : Fragment() {
    private var callbackButtonClickListener: (() -> Unit)? = null
    private lateinit var binding: FragmentWriteGoodBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_write_good, container, false)
        // binding.btnWriteApply.isEnabled = false
        clickWriteButtonEvent()
        checkEnableApplyBtn()
        resetSelectedOneLine()
        test()
        return binding.root
    }

    private fun clickWriteButtonEvent() {
        binding.apply {
            tvWriteOnelinereview1.setOnClickListener {
                tvWriteOnelinereview1.isSelected = !tvWriteOnelinereview1.isSelected
                btnApplyActivate()
            }
            tvWriteOnelinereview2.setOnClickListener {
                tvWriteOnelinereview2.isSelected = !tvWriteOnelinereview2.isSelected
                btnApplyActivate()
            }
            tvWriteOnelinereview3.setOnClickListener {
                tvWriteOnelinereview3.isSelected = !tvWriteOnelinereview3.isSelected
                btnApplyActivate()
            }
            tvWriteOnelinereview4.setOnClickListener {
                tvWriteOnelinereview4.isSelected = !tvWriteOnelinereview4.isSelected
                btnApplyActivate()
            }
            tvWriteOnelinereview5.setOnClickListener {
                tvWriteOnelinereview5.isSelected = !tvWriteOnelinereview5.isSelected
                btnApplyActivate()
            }
            tvWriteOnelinereview6.setOnClickListener {
                tvWriteOnelinereview6.isSelected = !tvWriteOnelinereview6.isSelected
                btnApplyActivate()
            }
        }
    }

    private fun checkBtnSelected() =
        with(binding) {
            tvWriteOnelinereview1.isSelected ||
                tvWriteOnelinereview2.isSelected ||
                tvWriteOnelinereview3.isSelected ||
                tvWriteOnelinereview4.isSelected ||
                tvWriteOnelinereview5.isSelected ||
                tvWriteOnelinereview6.isSelected
        }

    private fun checkEnableApplyBtn() {
        // binding.btnWriteApply.isEnabled = checkBtnSelected()
    }

    private fun btnApplyActivate() {
        checkBtnSelected()
        checkEnableApplyBtn()
    }

    fun test() {
        /* binding.btnWriteApply.setOnClickListener {
             val writeBottomSheetFragment = WriteBottomSheetFragment()
             writeBottomSheetFragment.dismiss()
         }*/
    }

    private fun resetSelectedOneLine() {
        /* binding.clWriteResetbtn.setOnClickListener {
             with(binding) {
                 tvWriteOnelinereview1.isSelected = false
                 tvWriteOnelinereview2.isSelected = false
                 tvWriteOnelinereview3.isSelected = false
                 tvWriteOnelinereview4.isSelected = false
                 tvWriteOnelinereview5.isSelected = false
                 tvWriteOnelinereview6.isSelected = false
             }
         }*/
    }

    /*fun setCallbackButtonClickListener(listener: () -> Unit) {
        this.callbackButtonClickListener = listener
    }*/
}
