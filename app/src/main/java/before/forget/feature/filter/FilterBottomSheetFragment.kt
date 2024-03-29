package before.forget.feature.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import before.forget.R
import before.forget.databinding.FragmentFilterBottomSheetBinding
import before.forget.feature.myrecord.MyRecordActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class FilterBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBottomSheetBinding
    private lateinit var filterViewPagerAdapter: FilterViewPagerAdapter
    private val filterMediaFragment = FilterMediaFragment()
    private val filterTermFragment = FilterTermFragment()
    private val filterStarFragment = FilterStarFragment()
    private var startCallback: ((List<Boolean>, Int) -> Unit)? = null
    private var mediaCallback: ((List<Boolean>, Int, Int) -> Unit)? = null
    private var termCallback: ((String, String) -> Unit)? = null

    fun setTermCallback(listener: (String, String) -> Unit) {
        this.termCallback = listener
    }

    fun setMediaCallback(listener: (List<Boolean>, Int, Int) -> Unit) {
        this.mediaCallback = listener
    }

    fun setStarScoreCallback(listener: (List<Boolean>, Int) -> Unit) {
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
        filterMediaFragment.setCallbackButtonClickListener { mediaListWithSelection, trueCounting, selectedMediaFistNumber ->
            mediaCallback?.invoke(mediaListWithSelection, trueCounting, selectedMediaFistNumber)
            dismiss()
        }
        filterStarFragment.setCallbackButtonClickListener { starListWithSelection, starTrueCounting ->
            startCallback?.invoke(starListWithSelection, starTrueCounting)
            dismiss()
        }
        filterTermFragment.setCallbackButtonClickListener { selectedTerm, startToEndDate ->
            termCallback?.invoke(selectedTerm, startToEndDate)
            dismiss()
        }

        MyRecordActivity().setCallBackButtonListener { selectedButtonNumber ->
            Log.d("액비티티에서", "오긴온거니 흑")
            binding.vpMenu.setCurrentItem(selectedButtonNumber, true)
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
