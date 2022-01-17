package before.forget.feature.myrecord.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.R
import before.forget.databinding.ActivityMyRecordDetailBinding

class MyRecordDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyRecordDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRecordDetailBinding.inflate(layoutInflater)

        binding.btnBackDetail.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
        changeFragment()
    }

    private fun changeFragment() {
        // 1: Movie , 2: Book, 3:  TV , 4: Music, 5: Webtoon, 6: Youtube
        // supportFragmentManager.beginTransaction()
        //   .add(R.id.fragment_container_detail, DetailBookFragment()).commit()
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
                transition.replace(R.id.fragment_container_detail, DetailYoutubeFragment()).commit()
            }
        }
    }

    private fun getMediaFromRecordActivity(): Int {
        var media = 0
        if (intent.hasExtra("media")) {
            media = intent.getIntExtra("media", 0)
        }
        return media
    }
}
