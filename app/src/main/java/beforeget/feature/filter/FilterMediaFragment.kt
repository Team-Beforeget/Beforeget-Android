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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_filter_media, container, false)
        Log.d("미디어뷰","실행됨")
        return binding.root
    }
}
