package before.forget.feature.myrecord.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import before.forget.R
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.tempToken
import before.forget.databinding.FragmentDetailYoutubeBinding
import before.forget.util.callback

class DetailYoutubeFragment : Fragment() {
    private lateinit var binding: FragmentDetailYoutubeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_youtube, container, false)
        // test()
        return binding.root
    }

    private fun test() {
        // val media = intent.getStringExtra("media")
        /* if (intent.hasExtra("media")) {
            binding.btnMedia.text = media.toString()
            binding.btnMedia.isActivated = true
        } */
        val postId: Int = requireArguments().getInt("postId")
        Log.d("postId", postId.toString())
        BeforegetClient.postService
            .getDetailFilterData(tempToken, postId)
            .callback
            .onSuccess {
                Log.d("성공", "성공")
                Log.d("Test", it.data.toString())
            }
            .enqueue()
    }
}
