package before.forget.feature.write

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import before.forget.R
import before.forget.databinding.FragmentWriteBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class WriteBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentWriteBottomSheetBinding
    private lateinit var writeViewPagerAdapter: WriteViewPagerAdapter
    private var writeGoodFragment = WriteGoodFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_write_bottom_sheet,
            container,
            false
        )

        writeGoodFragment.setCallbackButtonClickListener {
            dismiss()
        }

        initAdapter()
        initTabLayout()

        return binding.root
    }

    private fun initAdapter() {
        val fragmentList = listOf(writeGoodFragment)
        writeViewPagerAdapter = WriteViewPagerAdapter(this)
        writeViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpWriteMenu.adapter = writeViewPagerAdapter
    }

    private fun initTabLayout() {
        val menuList = listOf("좋았어요", "아쉬워요")

        TabLayoutMediator(binding.tlWriteMenu, binding.vpWriteMenu) { tab, position ->
            tab.text = menuList[position]
        }.attach()
    }

    private fun test() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
