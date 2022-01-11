package beforeget.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.beforeget.databinding.ItemMediaListBinding
import com.example.beforeget.databinding.ItemRecordBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    val mediaList = mutableListOf<MainData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMediaListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(mediaList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    override fun getItemCount(): Int = mediaList.size

    class MainViewHolder(private val binding: ItemMediaListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: MainData) {
            binding.main = data
        }
    }
}