package before.forget.feature.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import before.forget.R
import before.forget.databinding.FragmentFilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class FilterBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBottomSheetBinding
    private lateinit var filterViewPagerAdapter: FilterViewPagerAdapter
    private val filterMediaFragment = FilterMediaFragment()
    private val filterTermFragment = FilterTermFragment()
    private val filterStarFragment = FilterStarFragment()
    private var startCallback: (() -> Unit)? = null
    private var mediaCallback: ((Int, Int) -> Unit)? = null
    private var termCallback: ((String) -> Unit)? = null

    fun setTermCallback(listener: (String) -> Unit) {
        this.termCallback = listener
    }

    fun setMediaCallback(listener: (Int, Int) -> Unit) {
        this.mediaCallback = listener
    }

    fun setStarScoreCallback(listener: () -> Unit) {
        this.startCallback = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_filter_bottom_sheet,
            container,
            false
        )
        binding.vpMenu.isSaveEnabled = false
        filterMediaFragment.setCallbackButtonClickListener { selectNumber, trueCounting ->
            mediaCallback?.invoke(selectNumber, trueCounting)
            dismiss()
        }
        filterStarFragment.setCallbackButtonClickListener {
            startCallback?.invoke()
            dismiss()
        }
        filterTermFragment.setCallbackButtonClickListener {
            termCallback?.invoke(it)
            dismiss()
        }
        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter() {
        val fragmentList = listOf(filterTermFragment, filterMediaFragment, filterStarFragment)
        filterViewPagerAdapter = FilterViewPagerAdapter(this)
        filterViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpMenu.adapter = filterViewPagerAdapter
    }

    private fun initTabLayout() {
        val menuNameList = listOf("기간", "미디어", "별점")

        TabLayoutMediator(binding.tlMenu, binding.vpMenu) { tab, position ->
            tab.text = menuNameList[position]
        }.attach()
    }
}
