package before.forget.feature.write

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableBoolean
import before.forget.databinding.ActivityWriteAddItemBinding

class WriteAddItemActivity : AppCompatActivity() {
    private val categories by lazy {
        intent.getParcelableArrayListExtra<WriteCategory>(WriteActivity.EXTRA_CATEGORIES) ?: arrayListOf()
    }
    private lateinit var binding: ActivityWriteAddItemBinding
    private val writeadditemAdapter = WriteAddItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteAddItemBinding.inflate(layoutInflater)
        binding.btnAddwriteNextbtn.setOnClickListener {
            onClickNextButton()
        }
        setContentView(binding.root)
        initMainAdapter()
        //activeNextButton()
    }

    private fun initMainAdapter() {
        binding.rvWriteadditemItemlist.adapter = writeadditemAdapter
        writeadditemAdapter.itemList.clear()
        categories?.let { categories ->
            categories.map {
                WriteAddItemData(
                    it.category,
                    ObservableBoolean(it.isSelected)
                )
            }.also {
                writeadditemAdapter.itemList.addAll(it)
            }
        }
    }

    /*private fun activeNextButton() {
        val enableCondition = writeadditemAdapter.hasSelectedItem()
        binding.btnAddwriteNextbtn.isEnabled = enableCondition
    }*/

    private fun onClickNextButton() {
        writeadditemAdapter.itemList.mapIndexed { index, writeAddItemData ->
            categories[index]?.isSelected = writeAddItemData.isSelected.get()
        }
        Log.d("TEST", categories.toString())
        intent.putParcelableArrayListExtra(WriteActivity.EXTRA_CATEGORIES, categories)
        setResult(RESULT_OK, intent)
        finish()
    }
}
