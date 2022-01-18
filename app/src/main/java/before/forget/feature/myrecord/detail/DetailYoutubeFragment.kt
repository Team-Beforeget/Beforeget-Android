package before.forget.feature.myrecord.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.databinding.FragmentDetailYoutubeBinding

class DetailYoutubeFragment : Fragment() {
    private lateinit var binding: FragmentDetailYoutubeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_youtube, container, false)
        return binding.root
    }
}
