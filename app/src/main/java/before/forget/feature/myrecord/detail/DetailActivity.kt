package before.forget.feature.myrecord.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.data.remote.BeforegetClient
import before.forget.data.remote.response.ResponseDetail
import before.forget.data.remote.tempToken
import before.forget.databinding.ActivityDetailBinding
import before.forget.util.callback

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    var postId = 0
    val detailAdapter = DetailAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDetailAdapter()
        initOneReview()
        getPostIdFromRecordActivity()
        test()
    }

    private fun initDetailAdapter() {
        binding.rcvAddtionalDetail.adapter = detailAdapter
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

    private fun test() {
        BeforegetClient.postService
            .getDetailFilterData(tempToken, postId)
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
                        6 -> ivMediaDetail.setImageResource(R.drawable.ic_youtube_detail)
                    }
                    tvTitleDetail.text = it.data!![0].title
                    tvDateDetail.text = it.data!![0].date

                    val additionalData = it.data!![0].additional
                    setMutliData(additionalData)

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

                    when (it.data!![0].star) {
                        1 -> ivStarDetail.setImageResource(R.drawable.ic_star_1_detail)
                        2 -> ivStarDetail.setImageResource(R.drawable.ic_star_2_detail)
                        3 -> ivStarDetail.setImageResource(R.drawable.ic_star_3_detail)
                        4 -> ivStarDetail.setImageResource(R.drawable.ic_star_4_detail)
                        5 -> ivStarDetail.setImageResource(R.drawable.ic_star_5_detail)
                    }
                    tvCommentDetail.text = it.data!![0].comment
                }

                Log.d("성공", "성공")
                Log.d("Test", it.data.toString())
                // Log.d("냥냥냥", it.data!![1].additional!![1].type.toString())
                // it.data!![23].additional!![1].type
                // Log.d("냥냥", it.data!![0].additional!![0].type)
                // Log.d("additional의 사이즈", it.data!![0].additional?.size.toString())
            }
            .enqueue()
    }

    private fun getPostIdFromRecordActivity(): Int {
        if (intent.hasExtra("postId")) {
            postId = intent.getIntExtra("postId", 0)
        }
        return postId
    }

    private fun setMutliData(additionalData: List<ResponseDetail.Additional>?) {

        var additionalSize = 0
        additionalSize = additionalData?.size ?: 0

        if (additionalSize >= 1) {
            for (i in 0 until additionalSize) {
                when (additionalData!![i].type) {
                    "감독", "배우", "작가", "출판사", "배우", "방송사OTT", "가수", "요일", "플랫폼", "채널" -> {
                        Log.d("실행된거 맞냐", "아닌가?")
                        detailAdapter.dataList.addAll(
                            listOf<ResponseDetail.Additional>(
                                ResponseDetail.Additional(
                                    7,
                                    additionalData!![i].type,
                                    "",
                                    additionalData!![i].content,
                                    ""
                                )
                            )
                        )
                        Log.d("viewType", additionalData!![i].viewType.toString())
                        detailAdapter.notifyDataSetChanged()
                    }
                    "명대사", "인상 깊은 구절" -> {
                        detailAdapter.dataList.addAll(
                            listOf<ResponseDetail.Additional>(
                                ResponseDetail.Additional(
                                    6,
                                    additionalData!![i].type,
                                    "",
                                    additionalData!![i].content,
                                    ""
                                )
                            )
                        )
                        Log.d("viewType", additionalData!![i].viewType.toString())
                        detailAdapter.notifyDataSetChanged()
                    }
                    "장르", "카테고리" -> {
                        detailAdapter.dataList.addAll(
                            listOf<ResponseDetail.Additional>(
                                ResponseDetail.Additional(
                                    2,
                                    additionalData!![i].type,
                                    "",
                                    additionalData!![i].content,
                                    ""
                                )
                            )
                        )
                        Log.d("viewType", additionalData!![i].viewType.toString())
                        detailAdapter.notifyDataSetChanged()
                    }
                    "링크" -> {
                        detailAdapter.dataList.addAll(
                            listOf<ResponseDetail.Additional>(
                                ResponseDetail.Additional(
                                    4,
                                    additionalData!![i].type,
                                    "",
                                    additionalData!![i].content,
                                    ""
                                )
                            )
                        )
                        Log.d("viewType", additionalData!![i].viewType.toString())
                        detailAdapter.notifyDataSetChanged()
                    }
                    "OST", "앨범" -> {
                        detailAdapter.dataList.addAll(
                            listOf<ResponseDetail.Additional>(
                                ResponseDetail.Additional(
                                    5,
                                    additionalData!![i].type,
                                    "",
                                    additionalData!![i].content,
                                    ""
                                )
                            )
                        )
                        Log.d("viewType", additionalData!![i].viewType.toString())
                        detailAdapter.notifyDataSetChanged()
                    }
                    "타임스탬프" -> {
                        detailAdapter.dataList.addAll(
                            listOf<ResponseDetail.Additional>(
                                ResponseDetail.Additional(
                                    8,
                                    additionalData!![i].type,
                                    "",
                                    additionalData!![i].content,
                                    ""
                                )
                            )
                        )
                        Log.d("viewType", additionalData!![i].viewType.toString())
                        detailAdapter.notifyDataSetChanged()
                    }
                    "포스터", "표지", "명장면", "앨범 커버" -> {
                        Log.d("아직", "하지말자")
                    }
                    "줄거리", "가사" -> {
                        detailAdapter.dataList.addAll(
                            listOf<ResponseDetail.Additional>(
                                ResponseDetail.Additional(
                                    9,
                                    additionalData!![i].type,
                                    "",
                                    additionalData!![i].content,
                                    ""
                                )
                            )
                        )
                        Log.d("viewType", additionalData!![i].viewType.toString())
                        detailAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

        // for(i in 0 until limit)
        // val additionalData = it.data!![0].additional
        // it.data!![0].additional?.size.toString()
        // for (i in it.data!![0].oneline.indices) {
        // additionalData!![0].type
    }
}
