package before.forget.feature.write.writeadditem

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableBoolean
import before.forget.databinding.ActivityWriteAddItemBinding
import before.forget.feature.write.WriteActivity
import before.forget.feature.write.WriteCategory

class WriteAddItemActivity : AppCompatActivity() {
    private val categories by lazy {
        intent.getParcelableArrayListExtra<WriteCategory>(WriteActivity.EXTRA_CATEGORIES)
            ?: arrayListOf()
    }
    private lateinit var binding: ActivityWriteAddItemBinding
    private val writeadditemAdapter = WriteAddItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteAddItemBinding.inflate(layoutInflater)
        with(binding) {
            btnAddwriteNextbtn.setOnClickListener {
                onClickNextButton()
            }
            ivAddwriteClosebtn.setOnClickListener {
                finish()
            }
        }

        setContentView(binding.root)
        initMainAdapter()
        enableNextButton()
    }

    private fun initMainAdapter() {
        binding.rvWriteadditemItemlist.adapter = writeadditemAdapter
        writeadditemAdapter.itemList.clear()
        categories?.let { categories ->
            categories.map {
                WriteAddItemData(
                    it.category,
                    ObservableBoolean(it.isSelected)
                ) {
                    enableNextButton()
                }
            }.also {
                writeadditemAdapter.itemList.addAll(it)
            }
        }
    }

    private fun onClickNextButton() {
        writeadditemAdapter.itemList.mapIndexed { index, writeAddItemData ->
            categories[index]?.isSelected = writeAddItemData.isSelected.get()
        }
        Log.d("TEST", categories.toString())
        intent.putParcelableArrayListExtra(WriteActivity.EXTRA_CATEGORIES, categories)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun enableNextButton() {
        binding.btnAddwriteNextbtn.isEnabled = writeadditemAdapter.hasSelectedItem()
    }
}
