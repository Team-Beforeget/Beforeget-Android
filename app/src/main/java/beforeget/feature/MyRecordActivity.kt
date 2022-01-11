package beforeget.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import beforeget.data.MyRecordAdapter
import beforeget.data.MyRecordData
import com.example.beforeget.databinding.ActivityMyrecodBinding

class MyRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyrecodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyrecodBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showBottomSheet()
        initMyRecordAdapter()
    }

    private fun initMyRecordAdapter() {

        val myRecordDataAdapter = MyRecordAdapter()
        binding.rcvMyrecord.adapter = myRecordDataAdapter

        myRecordDataAdapter.recordList.addAll(
            listOf<MyRecordData>(
                MyRecordData("내가 널 사랑할 수 없는 1가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 1),
                MyRecordData("내가 널 사랑할 수 없는 2가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 2),
                MyRecordData("내가 널 사랑할 수 없는 3가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
                MyRecordData("내가 널 사랑할 수 없는 4가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
                MyRecordData("내가 널 사랑할 수 없는 5가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 4),
                MyRecordData("내가 널 사랑할 수 없는 6가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 5),
                MyRecordData("내가 널 사랑할 수 없는 7가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 5),
                MyRecordData("내가 널 사랑할 수 없는 8가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
                MyRecordData("내가 널 사랑할 수 없는 9가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
                MyRecordData("내가 널 사랑할 수 없는 10가지 이유", "흥미진진한 줄거리", "2022. 12. 11", 3),
            )
        )

        myRecordDataAdapter.notifyDataSetChanged()
    }

    private fun showBottomSheet() {
        binding.btnMedia.setOnClickListener {
            val filterBottomSheetFragment = FilterBottomSheetFragment()
            filterBottomSheetFragment.show(supportFragmentManager, filterBottomSheetFragment.tag)
        }
    }
}
