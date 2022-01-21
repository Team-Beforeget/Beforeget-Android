package before.forget.feature.myrecord.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.tempToken
import before.forget.databinding.ActivityMyRecordDetailBinding
import before.forget.databinding.DialogRecordDetailDeleteBinding
import before.forget.feature.dialog.BeforegetDialog
import before.forget.util.callback

class MyRecordDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyRecordDetailBinding
    private lateinit var subViewBinding: DialogRecordDetailDeleteBinding
    var postId = 0
    val bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRecordDetailBinding.inflate(layoutInflater)

        binding.btnBackDetail.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
        initBtnListener()
        setContentView(binding.root)
        changeFragment()
        getPostIdFromRecordActivity()
        initOneReview()
        onDetailNetwork()
        // Log.d("postId", getPostIdFromRecordActivity().toString())
        bundle.putInt("postId", postId)
    }

    private fun initBtnListener() {
        binding.btnBackDetail.setOnClickListener {
            finish()
        }

        binding.btnDetailDetail.setOnClickListener {
            setDialog()
        }
    }

    private fun setDialog() {
        subViewBinding = DialogRecordDetailDeleteBinding.inflate(layoutInflater)
        val dialog = BeforegetDialog(this, subViewBinding)
        dialog.show()
    }

    private fun changeFragment() {
        // 1: Movie , 2: Book, 3:  TV , 4: Music, 5: Webtoon, 6: Youtube
        val transition = supportFragmentManager.beginTransaction()
        when (getMediaFromRecordActivity()) {
            1 -> {
                transition.replace(R.id.fragment_container_detail, DetailMovieFragment()).commit()
            }
            2 -> {
                transition.replace(R.id.fragment_container_detail, DetailBookFragment()).commit()
            }
            3 -> {
                transition.replace(R.id.fragment_container_detail, DetailTvFragment()).commit()
            }
            4 -> {
                transition.replace(R.id.fragment_container_detail, DetailMusicFragment()).commit()
            }
            5 -> {
                transition.replace(R.id.fragment_container_detail, DetailWebtoonFragment()).commit()
            }
            else -> {
                // DetailYoutubeFragment().arguments = bundle
                transition.replace(R.id.fragment_container_detail, DetailYoutubeFragment()).commit()
            }
        }
    }

    private fun initOneReview() {
        binding.apply {
            isReviewExist2 = false
            isReviewExist3 = false
            isReviewExist4 = false
            isReviewExist5 = false
            isReviewExist6 = false
        }
    }

    private fun getMediaFromRecordActivity(): Int {
        var media = 0
        if (intent.hasExtra("media")) {
            media = intent.getIntExtra("media", 0)
        }

        return media
    }

    private fun getPostIdFromRecordActivity(): Int {
        if (intent.hasExtra("postId")) {
            postId = intent.getIntExtra("postId", 0)
        }
        return postId
    }

    private fun onDetailNetwork() {
        BeforegetClient.postService
            .getDetailFilterData(tempToken, postId)
            .callback
            .onSuccess {
                binding.apply {
                    when (it.data!![0].category) {
                        1 -> ivMediaDetail.setImageResource(R.drawable.ic_movie_detail)
                        2 -> ivMediaDetail.setImageResource(R.drawable.ic_book_myrecord)
                        3 -> ivMediaDetail.setImageResource(R.drawable.ic_tv_detail)
                        4 -> ivMediaDetail.setImageResource(R.drawable.ic_music_detail)
                        5 -> ivMediaDetail.setImageResource(R.drawable.ic_webtoon_detail)
                        6 -> ivMediaDetail.setImageResource(R.drawable.ic_youtube_detail)
                    }

                    tvTitleDetail.text = it.data!![0].title
                    tvDateDetail.text = it.data!![0].date

                    for (i in it.data!![0].oneline.indices) {

                        when (i) {
                            0 -> tvReview1.text = it.data!![0].oneline[0]

                            1 -> {
                                tvReview2.text = it.data!![0].oneline[1]
                                isReviewExist2 = true
                            }
                            2 -> {
                                tvReview3.text = it.data!![0].oneline[2]
                                isReviewExist3 = true
                            }
                            3 -> {
                                tvReview4.text = it.data!![0].oneline[3]
                                isReviewExist4 = true
                            }
                            4 -> {
                                tvReview5.text = it.data!![0].oneline[4]
                                isReviewExist5 = true
                            }
                            5 -> {
                                tvReview6.text = it.data!![0].oneline[5]
                                isReviewExist6 = true
                            }
                        }
                    }
                    tvCommentDetail.text = it.data!![0].comment
                }
            }
            .enqueue()
    }
}
