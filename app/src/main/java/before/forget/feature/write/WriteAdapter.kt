package before.forget.feature.write

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import before.forget.databinding.ItemCategoryListBinding

class WriteAdapter : RecyclerView.Adapter<WriteAdapter.WriteViewHolder>() {
    val categotyList = mutableListOf<WriteData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriteViewHolder {
        val binding = ItemCategoryListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WriteViewHolder, position: Int) {
        holder.onBind(categotyList[position])
    }

    override fun getItemCount(): Int = categotyList.size

    class WriteViewHolder(private val binding: ItemCategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: WriteData) {
            // binding.write 어댑터붙이는곳까지함
        }
    }
}
