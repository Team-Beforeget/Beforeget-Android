package beforeget.data

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import beforeget.feature.myrecord.MyRecordActivity
import com.example.beforeget.databinding.ItemMediaListBinding


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

    class MainViewHolder(private val binding: ItemMediaListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: MainData) {
            binding.main = data
            itemView.setOnClickListener {
                val media = binding.tvMainRvmedia.text.toString()
                val mainToMyRecordintent =
                    Intent(itemView?.context, MyRecordActivity::class.java).apply {
                        putExtra("media", media)
                    }
                ContextCompat.startActivity(itemView.context, mainToMyRecordintent, null)
            }
        }
    }
}


