package before.forget.feature.myrecord.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.tempToken
import before.forget.databinding.ActivityDetailBinding
import before.forget.util.callback

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    val detailAdapter = DetailAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initDetailAdapter()
        test()
    }

    private fun initDetailAdapter() {
        binding.rcvAddtionalDetail.adapter = detailAdapter
        // detailAdapter.dataList.addAll(
        // listOf<ResponseDetail.Additional>(
        // ResponseDetail.Additional("dd","ddd","dd","dd")
        // )
        // )
        detailAdapter.notifyDataSetChanged()
    }

    private fun test() {
        BeforegetClient.postService
            .getDetailFilterData(tempToken, 23)
            .callback
            .onSuccess {
                // 1: Movie , 2: Book, 3:  TV , 4: Music, 5: Webtoon, 6: Youtube
                binding.apply {
                    when (it.data!![0].category) {
                        1 -> ivMediaDetail.setImageResource(R.drawable.ic_movie_detail)
                        2 -> ivMediaDetail.setImageResource(R.drawable.ic_book_myrecord)
                        3 -> ivMediaDetail.setImageResource(R.drawable.ic_tv_detail)
                        4 -> ivMediaDetail.setImageResource(R.drawable.ic_music_detail)
                        5 -> ivMediaDetail.setImageResource(R.drawable.ic_webtoon_detail)
                        6 -> ivMediaDetail.setImageResource(R.drawable.ic_icn_media_youtube)
                    }
                    tvTitleDetail.text = it.data!![0].title
                    tvDateDetail.text = it.data!![0].date
                    tvReview1.text = it.data!![0].oneline[0]

                    tvReview2.text = it.data!![0].oneline[1]
                    // tvReview3.text = it.data!![0].oneline[2]
                    // tvReview4.text = it.data!![0].oneline[3]
                    // tvReview5.text = it.data!![0].oneline[4]

                    tvCommentDetail.text = it.data!![0].comment
                }

                Log.d("성공", "성공")
                Log.d("Test", it.data.toString())
                // Log.d("냥냥냥", it.data!![1].additional!![1].type.toString())
                // it.data!![23].additional!![1].type
                Log.d("냥냥", it.data!![0].additional!![0].type)
                Log.d("additional의 사이즈", it.data!![0].additional?.size.toString())
            }
            .enqueue()
    }
}
