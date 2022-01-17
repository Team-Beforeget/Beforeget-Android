package before.forget.feature.write

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import before.forget.data.WriteAddItemAdapter
import before.forget.data.local.WriteAddItemData
import before.forget.databinding.ActivityWriteAddItemBinding

class WriteAddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteAddItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMainAdapter()
    }

    private fun initMainAdapter() {

        val writeadditemAdapter = WriteAddItemAdapter()
        binding.rvWriteadditemItemlist.adapter = writeadditemAdapter

        writeadditemAdapter.itemList.addAll(
            listOf<WriteAddItemData>(
                WriteAddItemData("명대사"),
                WriteAddItemData("감독"),
                WriteAddItemData("배우"),
                WriteAddItemData("장르"),
                WriteAddItemData("줄거리"),
                WriteAddItemData("OST"),
                WriteAddItemData("포스터")

            )
        )
        writeadditemAdapter.notifyDataSetChanged()
    }
}
