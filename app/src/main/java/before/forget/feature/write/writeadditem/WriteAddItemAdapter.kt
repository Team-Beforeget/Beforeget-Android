package before.forget.feature.write.writeadditem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import before.forget.databinding.ItemItemListBinding

class WriteAddItemAdapter : RecyclerView.Adapter<WriteAddItemAdapter.WriteAddItemViewHolder>() {

    val itemList = mutableListOf<WriteAddItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriteAddItemViewHolder {
        val binding = ItemItemListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WriteAddItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WriteAddItemViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class WriteAddItemViewHolder(private val binding: ItemItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: WriteAddItemData) {
            binding.writeadditem = data
        }
    }
}
