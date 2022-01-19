package before.forget.feature.write

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import before.forget.data.WriteAddItemAdapter
import before.forget.data.remote.BeforegetClient
import before.forget.databinding.ActivityWriteAddItemBinding
import before.forget.util.callback

class WriteAddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteAddItemBinding
    private val writeadditemAdapter = WriteAddItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMainAdapter()
        onNetwork()
    }

    private fun initMainAdapter() {
        binding.rvWriteadditemItemlist.adapter = writeadditemAdapter
    }

    private fun onNetwork() {
        BeforegetClient.categoryService
            .getAddItem(id = 1)
            .callback
            .onSuccess { response ->
                response.data?.let { data ->
                    writeadditemAdapter.itemList.addAll(
                        data.additional.map {
                            WriteAddItemData(it)
                        }
                    )
                }

                writeadditemAdapter.notifyDataSetChanged()

                Log.d("dd", "$response")
            }.onError {
            }.enqueue()
    }
}
