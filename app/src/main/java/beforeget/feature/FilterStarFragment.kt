package beforeget.feature

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.beforeget.R
import com.example.beforeget.databinding.FragmentFilterStarBinding

class FilterStarFragment : Fragment() {
    private lateinit var binding: FragmentFilterStarBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_star, container,false)
        Log.d("별점뷰","실행됨")
        return binding.root
    }
}
