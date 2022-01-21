package before.forget.feature.write

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.data.remote.BeforegetClient
import before.forget.databinding.FragmentWriteGoodBinding
import before.forget.util.callback

class WriteGoodFragment : Fragment() {
    private var callbackButtonClickListener: ((List<String>) -> Unit)? = null
    var oneLine = mutableListOf<String>()
    private lateinit var binding: FragmentWriteGoodBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_write_good, container, false)
        binding.btnWriteGoodapply.isEnabled = false
        clickWriteButtonEvent()
        checkEnableApplyBtn()
        resetSelectedOneLine()
        onNetwork()
        return binding.root
    }

    private fun clickWriteButtonEvent() {
        binding.apply {
            tvWriteOnelinereview1.setOnClickListener {
                tvWriteOnelinereview1.isSelected = !tvWriteOnelinereview1.isSelected
                btnApplyActivate()
                oneLine.add(tvWriteOnelinereview1.text.toString())
            }
            tvWriteOnelinereview2.setOnClickListener {
                tvWriteOnelinereview2.isSelected = !tvWriteOnelinereview2.isSelected
                btnApplyActivate()
                oneLine.add(tvWriteOnelinereview2.text.toString())
            }
            tvWriteOnelinereview3.setOnClickListener {
                tvWriteOnelinereview3.isSelected = !tvWriteOnelinereview3.isSelected
                btnApplyActivate()
                oneLine.add(tvWriteOnelinereview3.text.toString())
            }
            tvWriteOnelinereview4.setOnClickListener {
                tvWriteOnelinereview4.isSelected = !tvWriteOnelinereview4.isSelected
                btnApplyActivate()
                oneLine.add(tvWriteOnelinereview4.text.toString())
            }
            tvWriteOnelinereview5.setOnClickListener {
                tvWriteOnelinereview5.isSelected = !tvWriteOnelinereview5.isSelected
                btnApplyActivate()
                oneLine.add(tvWriteOnelinereview5.text.toString())
            }
            tvWriteOnelinereview6.setOnClickListener {
                tvWriteOnelinereview6.isSelected = !tvWriteOnelinereview6.isSelected
                btnApplyActivate()
                oneLine.add(tvWriteOnelinereview6.text.toString())
            }
            btnWriteGoodapply.setOnClickListener() {
                // 기록추가 활성화
                // tvWriteAddoneline.visibility = View.GONE
                callbackButtonClickListener?.invoke(oneLine)
                oneLine = mutableListOf<String>()
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

    private fun btnApplyActivate() {
        checkBtnSelected()
        checkEnableApplyBtn()
    }

    private fun checkEnableApplyBtn() {
        binding.btnWriteGoodapply.isEnabled = checkBtnSelected()
    }

    private fun onNetwork() {
        val oneLine = listOf<TextView>(
            binding.tvWriteOnelinereview1,
            binding.tvWriteOnelinereview2,
            binding.tvWriteOnelinereview3,
            binding.tvWriteOnelinereview4,
            binding.tvWriteOnelinereview5,
            binding.tvWriteOnelinereview6
        )

        BeforegetClient.categoryService
            .getOneLine(id = 1)
            .callback
            .onSuccess { response ->
                response.data?.let { data ->
                    data.good.forEachIndexed { index, server ->
                        oneLine[index].text = server
                        Log.d("성공", "${response.data.good}")
                    }
                    Log.d("성공", "${response.data.good}")
                }
            }
            .onError { }
            .enqueue()
    }

    private fun resetSelectedOneLine() {
        binding.clWriteGoodresetbtn.setOnClickListener {
            oneLine = mutableListOf<String>()
            with(binding) {
                btnWriteGoodapply.isEnabled = false
                tvWriteOnelinereview1.isSelected = false
                tvWriteOnelinereview2.isSelected = false
                tvWriteOnelinereview3.isSelected = false
                tvWriteOnelinereview4.isSelected = false
                tvWriteOnelinereview5.isSelected = false
                tvWriteOnelinereview6.isSelected = false
            }
        }
    }

    fun setCallbackButtonClickListener(listener: (oneLine: List<String>) -> Unit) {
        this.callbackButtonClickListener = listener
        Log.d("함수 실행", "wpqkf")
    }
}
