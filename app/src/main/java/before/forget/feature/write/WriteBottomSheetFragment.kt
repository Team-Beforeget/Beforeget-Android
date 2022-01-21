package before.forget.feature.write

import android.os.Bundle
import android.util.Log
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
    val writeGoodFragment = WriteGoodFragment()
    val writeBadFragment = WriteBadFragment()
    private var oneLineCallback: ((List<String>) -> Unit)? = null

    fun setOneLineCallback(listener: (List<String>) -> Unit) {
        this.oneLineCallback = listener
    }

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

        binding.btnWriteApply.visibility = View.GONE
        binding.clWriteResetbtn.visibility = View.GONE

        /*  writeBadFragment.setCallbackButtonClickListener {
              dismiss()
          }*/

        initAdapter()
        initTabLayout()
        test()
        initDialog()
        writeGoodFragment.setCallbackButtonClickListener { oneLine ->
            oneLineCallback?.invoke(oneLine)
            Log.d("액비티티에서", "오긴온거니 흑")
            dismiss()
        }

        return binding.root
    }

    private fun initAdapter() { // 뷰페이저 어댑터
        val fragmentList = listOf(writeGoodFragment, writeBadFragment)
        writeViewPagerAdapter = WriteViewPagerAdapter(this)
        writeViewPagerAdapter.fragments.addAll(fragmentList)
        binding.vpWriteMenu.adapter = writeViewPagerAdapter
    }

    private fun initTabLayout() { // 탭레이아웃
        val menuList = listOf("좋았어요", "아쉬워요")
        TabLayoutMediator(binding.tlWriteMenu, binding.vpWriteMenu) { tab, position ->
            tab.text = menuList[position]
        }.attach()
    }

    private fun test() {
        binding.btnWriteApply.setOnClickListener {
            val writeBottomSheetFragment = WriteBottomSheetFragment()
            writeBottomSheetFragment.dismiss()
            // 데이터전달
        }
    }

    private fun initDialog() { // 바텀시트달기
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}
