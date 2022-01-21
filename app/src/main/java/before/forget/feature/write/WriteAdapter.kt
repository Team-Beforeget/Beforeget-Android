package before.forget.feature.write

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import before.forget.data.remote.request.RequestPost
import before.forget.databinding.ItemCategoryListBinding

class WriteAdapter : RecyclerView.Adapter<WriteAdapter.WriteViewHolder>() {
    private val categotyList = mutableListOf<WriteData>()

    fun addAllDataOf(list: List<WriteData>) {
        categotyList.clear()
        categotyList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriteViewHolder {
        val binding = ItemCategoryListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return WriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WriteViewHolder, position: Int) {
        holder.onBind(categotyList[position])
    }

    fun getCategoryToAdditional() = categotyList.map {
        RequestPost.Additional(it.itemtitle, it.itemcontent.get() ?: "")
    }

    override fun getItemCount(): Int = categotyList.size

    class WriteViewHolder(private val binding: ItemCategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: WriteData) {
            binding.data = data
        }
    }
}
