package beforeget.feature.filter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.beforeget.R
import com.example.beforeget.databinding.FragmentFilterBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class FilterBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBottomSheetBinding
    private lateinit var filterViewPagerAdapter: FilterViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_bottom_sheet, container, false)
        test()
        initAdapter()
        initTabLayout()
        return binding.root
    }

    private fun initAdapter() {
        val fragmentList = listOf(FilterTermFragment(), FilterMediaFragment(), FilterStarFragment())
        filterViewPagerAdapter = FilterViewPagerAdapter(this)
        filterViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpMenu.adapter = filterViewPagerAdapter
    }

    private fun initTabLayout() {
        val menuNameList = listOf("기간", "미디어", "별점")

        TabLayoutMediator(binding.tlMenu, binding.vpMenu) {
            tab, position ->
            tab.text = menuNameList[position]
        }.attach()
    }
    private fun test() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
