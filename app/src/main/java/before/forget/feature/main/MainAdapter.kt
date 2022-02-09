package before.forget.data

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import before.forget.databinding.ItemMediaListBinding
import before.forget.feature.main.MainData
import before.forget.feature.myrecord.MyRecordActivity

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
    }

    override fun getItemCount(): Int = mediaList.size

    class MainViewHolder(
        private val binding: ItemMediaListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: MainData) {
            binding.main = data
            itemView.setOnClickListener
            {
                val media = binding.tvMainRvmedia.text.toString()
                val mainToMyRecordIntent =
                    Intent(itemView.context, MyRecordActivity::class.java)
                mainToMyRecordIntent.putExtra("media", media)
                ContextCompat.startActivity(itemView.context, mainToMyRecordIntent, null)
            }
        }
    }


}
