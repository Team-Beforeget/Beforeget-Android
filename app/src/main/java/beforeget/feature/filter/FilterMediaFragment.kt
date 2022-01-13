package beforeget.feature.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.beforeget.R
import com.example.beforeget.databinding.FragmentFilterMediaBinding

class FilterMediaFragment : Fragment() {
    private lateinit var binding: FragmentFilterMediaBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_filter_media, container, false)
        binding.btnApplyFilter.isEnabled = false
        initClickEvent()
        checkEnableApplyBtn()
        return binding.root
    }

    private fun initClickEvent() {
        binding.apply {
            btnBook.setOnClickListener {
                btnBook.isSelected = !btnBook.isSelected
                activateApplyBtn()
            }
            btnMovie.setOnClickListener {
                btnMovie.isSelected = !btnMovie.isSelected
                activateApplyBtn()
            }
            btnMusic.setOnClickListener {
                btnMusic.isSelected = !btnMusic.isSelected
                activateApplyBtn()
            }
            btnTv.setOnClickListener {
                btnTv.isSelected = !btnTv.isSelected
                activateApplyBtn()
            }
            btnWebtoon.setOnClickListener {
                btnWebtoon.isSelected = !btnWebtoon.isSelected
                activateApplyBtn()
            }
            btnYoutube.setOnClickListener {
                btnYoutube.isSelected = !btnYoutube.isSelected
                activateApplyBtn()
            }
        }
    }

    private fun checkBtnSelected() =
        binding.btnBook.isSelected || binding.btnMovie.isSelected || binding.btnMusic.isSelected || binding.btnTv.isSelected || binding.btnWebtoon.isSelected || binding.btnYoutube.isSelected

    private fun checkEnableApplyBtn() {
        binding.btnApplyFilter.isEnabled = checkBtnSelected()
        Log.d("checkBtnSelected() 값 : ", checkBtnSelected().toString())
    }

    private fun activateApplyBtn() {
        checkBtnSelected()
        checkEnableApplyBtn()
    }
}
