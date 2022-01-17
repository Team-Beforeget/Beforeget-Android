package before.forget.feature.myrecord.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.R

class MyRecordDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_record_detail)
    }
    private fun getMediaFromRecordActivity(): Int {
        var media = 0
        if (intent.hasExtra("media")) {
            media = intent.getIntExtra("media", 0)
        }
        return media
    }
}
